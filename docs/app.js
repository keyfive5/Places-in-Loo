/* =====================================================================
 * Places in Loo — modern web edition
 * A faithful, browser-based re-build of the 2022 university team project
 * (originally a Java Swing + SQLite desktop app). All data lives in the
 * visitor's own browser via localStorage, so the site is free to host,
 * always online, and needs no server or install.
 *
 * Original team (2022): Nabeel Shahyan, Faisal Jackson, Jack Wrenen,
 *   Kabir Zafar, and the rest of the group.
 * Modern web edition (2026): Hasan Zafar.
 * ===================================================================== */

/* ----------------------------- storage ------------------------------ */
const STORE_KEY = "pil_db";

const DB = {
  data: null,

  load() {
    let raw = null;
    try { raw = JSON.parse(localStorage.getItem(STORE_KEY)); } catch (e) { raw = null; }
    if (!raw || raw.version !== PIL_SEED.version) {
      this.seed();
    } else {
      this.data = raw;
    }
    return this.data;
  },

  seed() {
    // deep clone the seed so we never mutate the source
    const seed = JSON.parse(JSON.stringify(PIL_SEED));
    this.data = {
      version: seed.version,
      users: seed.users,
      posts: seed.posts.map((p, i) => ({ id: "p" + (i + 1), ...p })),
      messages: seedMessages(),
      session: null,
    };
    this.save();
  },

  save() { localStorage.setItem(STORE_KEY, JSON.stringify(this.data)); },

  reset() { localStorage.removeItem(STORE_KEY); this.load(); },

  // helpers
  user(username) { return this.data.users.find(u => u.username === username); },
  currentUser() { return this.data.session ? this.user(this.data.session) : null; },
};

// a small pre-seeded conversation so the messaging feature is visibly working
function seedMessages() {
  const t = Date.now();
  return [
    { id: "m1", from: "caix3600", to: "Kabi9911", text: "Hey! Is the Spruce Street room still available for the fall term?", ts: t - 1000 * 60 * 60 * 26, read: true },
    { id: "m2", from: "Kabi9911", to: "caix3600", text: "Yes it is! Utilities and internet are included. Want to book a viewing this week?", ts: t - 1000 * 60 * 60 * 25, read: true },
    { id: "m3", from: "caix3600", to: "Kabi9911", text: "That would be great. I'm free Thursday afternoon 🙌", ts: t - 1000 * 60 * 60 * 24, read: false },
  ];
}

/* ----------------------------- helpers ------------------------------ */
const $ = (sel, root = document) => root.querySelector(sel);
const $$ = (sel, root = document) => [...root.querySelectorAll(sel)];
const uid = () => Math.random().toString(36).slice(2, 10);

function esc(s) {
  return String(s == null ? "" : s)
    .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;").replace(/'/g, "&#39;");
}
function money(n) { return "$" + Number(n).toLocaleString("en-CA"); }
function initials(u) { return ((u.firstName[0] || "") + (u.lastName[0] || "")).toUpperCase(); }
function fmtDate(d) {
  const dt = new Date(d + (d.length === 10 ? "T00:00:00" : ""));
  if (isNaN(dt)) return d;
  return dt.toLocaleDateString("en-CA", { month: "short", day: "numeric", year: "numeric" });
}
function timeAgo(ts) {
  const s = Math.floor((Date.now() - ts) / 1000);
  if (s < 60) return "just now";
  if (s < 3600) return Math.floor(s / 60) + "m ago";
  if (s < 86400) return Math.floor(s / 3600) + "h ago";
  return Math.floor(s / 86400) + "d ago";
}
function starsHtml(rating) {
  if (rating == null || rating < 0) return '<span class="pill pill-gray">New host</span>';
  const full = Math.round(rating);
  let out = '<span class="stars">';
  for (let i = 1; i <= 5; i++) out += i <= full ? "★" : '<span class="empty">★</span>';
  out += "</span> ";
  out += '<span class="muted" style="font-size:12.5px">' + rating.toFixed(1) + "</span>";
  return out;
}

let toastTimer;
function toast(msg, type = "") {
  const t = $("#toast");
  t.textContent = msg;
  t.className = "toast show " + type;
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => (t.className = "toast " + type), 2600);
}

function go(route) { location.hash = route; }

/* ----------------------------- auth --------------------------------- */
function login(username, password) {
  const u = DB.user(username);
  if (!u || u.password !== password) return "Incorrect username or password.";
  DB.data.session = username;
  DB.save();
  return null;
}

function register(form) {
  const { username, password, email, firstName, lastName, gender, campus, dob } = form;
  if (!username || !password || !email || !firstName || !lastName || !gender || !campus || !dob)
    return "Please fill in every field.";

  // Email validation — mirrors the original CreateAccount.java rules:
  // must look like an email, and the domain must be mylaurier or uwaterloo.
  if (!/^(.+)@(.+)$/.test(email)) return "Invalid email address.";
  const domain = email.substring(email.indexOf("@") + 1, email.lastIndexOf("."));
  if (domain !== "mylaurier" && domain !== "uwaterloo")
    return "You must use a Laurier (mylaurier) or Waterloo (uwaterloo) email.";

  // Unique username
  if (DB.user(username)) return "Username already taken. Please try a different one.";

  DB.data.users.push({
    username, password, email, firstName, lastName, gender, campus, dob,
    rating: -1, totalRatings: 0, ratingSum: 0,
  });
  DB.data.session = username;
  DB.save();
  return null;
}

function logout() { DB.data.session = null; DB.save(); go("#/login"); }

/* ----------------------------- views -------------------------------- */
const app = () => $("#app");

function render() {
  DB.load();
  const me = DB.currentUser();
  const route = location.hash || (me ? "#/home" : "#/login");

  // guard: auth-only pages
  const publicRoutes = ["#/login", "#/register"];
  if (!me && !publicRoutes.includes(route.split("?")[0])) return go("#/login");
  if (me && publicRoutes.includes(route)) return go("#/home");

  const [path, query] = route.split("?");
  const views = {
    "#/login": viewLogin,
    "#/register": viewRegister,
    "#/home": viewHome,
    "#/browse": viewBrowse,
    "#/post": viewPost,
    "#/manage": viewManage,
    "#/rate": viewRate,
    "#/messages": viewMessages,
    "#/profile": viewProfile,
  };
  const view = views[path] || viewHome;
  app().innerHTML = (me && !publicRoutes.includes(path) ? shell(me, path) : "") + view(me, new URLSearchParams(query));
  bindGlobal();
  window.scrollTo(0, 0);
}

/* shared app chrome (top bar) */
function shell(me, path) {
  const unread = DB.data.messages.filter(m => m.to === me.username && !m.read).length;
  const link = (href, label, extra = "") =>
    `<a href="${href}" class="${path === href ? "active" : ""}">${label}${extra}</a>`;
  return `
  <header class="topbar">
    <div class="topbar-inner">
      <div class="brand" onclick="location.hash='#/home'">
        <span class="logo">🏠</span><span>Places in Loo</span>
      </div>
      <nav class="nav">
        ${link("#/home", "Home")}
        ${link("#/browse", "Browse")}
        ${link("#/post", "Post")}
        ${link("#/manage", "My Listings")}
        ${link("#/rate", "Rate")}
        ${link("#/messages", "Messages", unread ? `<span class="badge">${unread}</span>` : "")}
      </nav>
      <div class="userchip">
        <a href="#/profile" class="avatar" title="${esc(me.firstName)} ${esc(me.lastName)}">${initials(me)}</a>
      </div>
    </div>
  </header>`;
}

function footer() {
  return `
  <footer class="site-foot wrap">
    <p><strong>Places in Loo</strong> — student sublets for Laurier &amp; Waterloo.</p>
    <p>Original university project (2022) by Nabeel Shahyan, Faisal Jackson, Jack Wrenen, Kabir Zafar &amp; team.<br>
    Modern web edition built by <strong>Hasan Zafar</strong>. ·
    <a href="https://github.com/keyfive5/Places-in-Loo" target="_blank" rel="noopener">View on GitHub</a></p>
  </footer>`;
}

/* ---------- login ---------- */
function viewLogin() {
  return `
  <div class="auth">
    ${authHero()}
    <div class="auth-panel">
      <div class="auth-card">
        <h2>Welcome back 👋</h2>
        <p class="sub">Sign in to find or post a sublet.</p>
        <div id="err"></div>
        <form id="loginForm" class="stack" autocomplete="off">
          <div class="field">
            <label>Username</label>
            <input name="username" placeholder="e.g. caix3600" required />
          </div>
          <div class="field">
            <label>Password</label>
            <input name="password" type="password" placeholder="Your password" required />
          </div>
          <button class="btn btn-primary btn-block" type="submit">Log in</button>
        </form>
        <div class="demo-note">
          <strong>Try the demo:</strong> username <code>caix3600</code> · password <code>password</code>
        </div>
        <p class="center muted" style="margin-top:18px;font-size:14px">
          New here? <a href="#/register">Create an account</a>
        </p>
      </div>
    </div>
  </div>`;
}

/* ---------- register ---------- */
function viewRegister() {
  return `
  <div class="auth">
    ${authHero()}
    <div class="auth-panel">
      <div class="auth-card">
        <h2>Create your account</h2>
        <p class="sub">Laurier &amp; Waterloo students only.</p>
        <div id="err"></div>
        <form id="registerForm" autocomplete="off">
          <div class="grid-2">
            <div class="field"><label>First name</label><input name="firstName" required /></div>
            <div class="field"><label>Last name</label><input name="lastName" required /></div>
          </div>
          <div class="field">
            <label>Email</label>
            <input name="email" type="email" placeholder="you@mylaurier.ca" required />
            <div class="hint">Must end in <b>mylaurier.ca</b> or <b>uwaterloo.ca</b></div>
          </div>
          <div class="grid-2">
            <div class="field"><label>Username</label><input name="username" required /></div>
            <div class="field"><label>Password</label><input name="password" type="password" required /></div>
          </div>
          <div class="grid-2">
            <div class="field">
              <label>Campus</label>
              <select name="campus" required>
                <option value="Wilfrid Laurier University">Wilfrid Laurier University</option>
                <option value="University of Waterloo">University of Waterloo</option>
              </select>
            </div>
            <div class="field">
              <label>Gender</label>
              <select name="gender" required>
                <option>Female</option><option>Male</option>
                <option>Non-binary</option><option>Prefer not to say</option>
              </select>
            </div>
          </div>
          <div class="field"><label>Date of birth</label><input name="dob" type="date" required /></div>
          <button class="btn btn-primary btn-block" type="submit">Create account</button>
        </form>
        <p class="center muted" style="margin-top:18px;font-size:14px">
          Already have an account? <a href="#/login">Log in</a>
        </p>
      </div>
    </div>
  </div>`;
}

function authHero() {
  return `
  <div class="auth-hero">
    <div class="brand"><span class="logo">🏠</span><span>Places in Loo</span></div>
    <div>
      <h1 class="hero-headline">Sublets made<br><span class="gold">simple</span> for<br>Laurier &amp; Waterloo.</h1>
      <p class="hero-sub">Post your place, browse verified student listings, message hosts, and rate your stay.</p>
      <ul class="hero-features">
        <li><span class="tick">✓</span> Post &amp; browse sublets in seconds</li>
        <li><span class="tick">✓</span> Message hosts directly — no more group chats</li>
        <li><span class="tick">✓</span> Ratings you can actually trust</li>
      </ul>
    </div>
    <p class="hero-credit">
      A 2022 university team project, rebuilt for the web in 2026 by <strong>Hasan Zafar</strong>.<br>
      Original team: Nabeel, Faisal, Jack, Kabir &amp; friends.
    </p>
  </div>`;
}

/* ---------- home / dashboard ---------- */
function viewHome(me) {
  const posts = DB.data.posts;
  const available = posts.filter(p => p.available && p.owner !== me.username).length;
  const mine = posts.filter(p => p.owner === me.username).length;
  const unread = DB.data.messages.filter(m => m.to === me.username && !m.read).length;
  const myRating = me.rating >= 0 ? me.rating.toFixed(1) : "—";

  const tile = (href, ico, title, desc) =>
    `<button class="tile" onclick="location.hash='${href}'">
       <div class="ico">${ico}</div><h3>${title}</h3><p>${desc}</p>
     </button>`;

  return `
  <main class="page">
    <section class="welcome">
      <h1>Hey ${esc(me.firstName)} 👋</h1>
      <p>Welcome to your sublet dashboard. Find a place, list your own, or check your messages.</p>
    </section>

    <div class="stat-row">
      <div class="stat"><div class="ico">🏘️</div><div class="n">${available}</div><div class="l">Sublets available now</div></div>
      <div class="stat"><div class="ico">📌</div><div class="n">${mine}</div><div class="l">Your listings</div></div>
      <div class="stat"><div class="ico">✉️</div><div class="n">${unread}</div><div class="l">Unread messages</div></div>
      <div class="stat"><div class="ico">⭐</div><div class="n">${myRating}</div><div class="l">Your host rating</div></div>
    </div>

    <div class="tiles">
      ${tile("#/browse", "🔍", "Browse sublets", "See every available place near campus.")}
      ${tile("#/post", "➕", "Post a sublet", "List your place in under a minute.")}
      ${tile("#/manage", "🗂️", "My listings", "Edit or cancel what you've posted.")}
      ${tile("#/rate", "⭐", "Rate a host", "Leave a rating for a past sublessor.")}
      ${tile("#/messages", "💬", "Messages", "Chat with hosts and renters.")}
      ${tile("#/profile", "👤", "My profile", "Your account &amp; demo controls.")}
    </div>
  </main>
  ${footer()}`;
}

/* ---------- browse / rent ---------- */
function viewBrowse(me, params) {
  const q = (params.get("q") || "").toLowerCase();
  const campus = params.get("campus") || "";
  const sort = params.get("sort") || "recent";

  let posts = DB.data.posts.filter(p => p.available && p.owner !== me.username);
  if (q) posts = posts.filter(p =>
    p.location.toLowerCase().includes(q) || p.description.toLowerCase().includes(q));
  if (campus) posts = posts.filter(p => p.campus === campus);
  if (sort === "price-low") posts.sort((a, b) => a.price - b.price);
  else if (sort === "price-high") posts.sort((a, b) => b.price - a.price);

  const cards = posts.map(p => listingCard(p)).join("");

  return `
  <main class="page">
    <div class="page-head">
      <h1>Browse sublets</h1>
      <p>${posts.length} place${posts.length === 1 ? "" : "s"} available — you won't see your own listings here.</p>
    </div>

    <form class="filters" id="filterForm">
      <div class="field"><label>Search</label><input name="q" value="${esc(params.get("q") || "")}" placeholder="Street, area, keyword…" /></div>
      <div class="field"><label>Campus</label>
        <select name="campus">
          <option value="">All campuses</option>
          <option ${campus === "Wilfrid Laurier University" ? "selected" : ""}>Wilfrid Laurier University</option>
          <option ${campus === "University of Waterloo" ? "selected" : ""}>University of Waterloo</option>
        </select>
      </div>
      <div class="field"><label>Sort by</label>
        <select name="sort">
          <option value="recent" ${sort === "recent" ? "selected" : ""}>Most recent</option>
          <option value="price-low" ${sort === "price-low" ? "selected" : ""}>Price: low to high</option>
          <option value="price-high" ${sort === "price-high" ? "selected" : ""}>Price: high to low</option>
        </select>
      </div>
      <div class="field" style="display:flex;align-items:flex-end"><button class="btn btn-primary" type="submit">Apply</button></div>
    </form>

    ${posts.length ? `<div class="listings">${cards}</div>`
      : `<div class="empty-state"><div class="ico">🏝️</div><h3>No sublets match</h3><p>Try clearing the filters or check back soon.</p></div>`}
  </main>
  ${footer()}`;
}

function listingCard(p) {
  const host = DB.user(p.owner);
  return `
  <article class="listing" onclick="openListing('${p.id}')">
    <div class="cover">${p.photo || "🏠"}<span class="price">${money(p.price)}/mo</span></div>
    <div class="body">
      <h3>${esc(p.location)}</h3>
      <div class="campus">📍 ${esc(p.campus)}</div>
      <p class="desc">${esc(p.description)}</p>
      <div class="meta">
        <span class="pill pill-gray">🛏️ ${p.beds} bed</span>
        <span class="pill pill-gray">🛁 ${p.baths} bath</span>
        ${p.furnished ? '<span class="pill pill-gold">Furnished</span>' : ""}
      </div>
      <div class="foot">
        <div class="host">${starsHtml(host ? host.rating : -1)}</div>
        <span class="pill">${esc(host ? host.firstName : "Host")}</span>
      </div>
    </div>
  </article>`;
}

/* ---------- listing detail modal ---------- */
function openListing(id) {
  const p = DB.data.posts.find(x => x.id === id);
  if (!p) return;
  const host = DB.user(p.owner);
  const me = DB.currentUser();
  const isMine = p.owner === me.username;

  const modal = document.createElement("div");
  modal.className = "modal-backdrop";
  modal.innerHTML = `
    <div class="modal" onclick="event.stopPropagation()" style="position:relative">
      <button class="close" onclick="closeModal()">×</button>
      <div class="cover">${p.photo || "🏠"}</div>
      <div class="modal-body">
        <div class="spread">
          <div>
            <h2>${esc(p.location)}</h2>
            <div class="campus muted" style="font-size:13.5px">📍 ${esc(p.campus)}</div>
          </div>
          <div class="pill pill-gold" style="font-size:16px">${money(p.price)}/mo</div>
        </div>

        <div class="detail-grid">
          <div class="cell"><div class="k">Move in</div><div class="v">${fmtDate(p.start)}</div></div>
          <div class="cell"><div class="k">Move out</div><div class="v">${fmtDate(p.end)}</div></div>
          <div class="cell"><div class="k">Bedrooms</div><div class="v">${p.beds}</div></div>
          <div class="cell"><div class="k">Bathrooms</div><div class="v">${p.baths}</div></div>
        </div>

        <p style="color:#444">${esc(p.description)}</p>

        <div class="spread" style="margin:18px 0;padding:14px;background:var(--bg);border-radius:12px">
          <div class="host row">
            <span class="avatar">${initials(host)}</span>
            <div>
              <div style="font-weight:700">${esc(host.firstName)} ${esc(host.lastName)}</div>
              <div>${starsHtml(host.rating)}</div>
            </div>
          </div>
          ${p.furnished ? '<span class="pill pill-gold">Furnished</span>' : '<span class="pill pill-gray">Unfurnished</span>'}
        </div>

        ${isMine ? `<p class="muted center">This is your own listing.</p>`
          : `<div class="row" style="gap:10px">
               <button class="btn btn-ghost btn-block" onclick="messageHost('${host.username}','${esc(p.location)}')">💬 Message host</button>
               <button class="btn btn-primary btn-block" onclick="rentPost('${p.id}')">Rent this place</button>
             </div>`}
      </div>
    </div>`;
  modal.addEventListener("click", closeModal);
  document.body.appendChild(modal);
}
function closeModal() { const m = $(".modal-backdrop"); if (m) m.remove(); }

function rentPost(id) {
  const p = DB.data.posts.find(x => x.id === id);
  if (!p || !p.available) return;
  p.available = false;
  p.rentedBy = DB.data.session;
  DB.save();
  closeModal();
  toast("🎉 You've rented " + p.location + "! The host has been notified.", "good");
  render();
}

/* ---------- post a sublet ---------- */
function viewPost(me) {
  return `
  <main class="page">
    <div class="page-head"><h1>Post a sublet</h1><p>Fill in the details and your listing goes live instantly.</p></div>
    <div class="auth-card" style="max-width:640px">
      <div id="err"></div>
      <form id="postForm">
        <div class="field"><label>Location / address</label><input name="location" placeholder="e.g. 318 Spruce Street" required /></div>
        <div class="field"><label>Campus</label>
          <select name="campus" required>
            <option value="Wilfrid Laurier University">Wilfrid Laurier University</option>
            <option value="University of Waterloo">University of Waterloo</option>
          </select>
        </div>
        <div class="grid-2">
          <div class="field"><label>Sublet start</label><input name="start" type="date" required /></div>
          <div class="field"><label>Sublet end</label><input name="end" type="date" required /></div>
        </div>
        <div class="grid-2">
          <div class="field"><label>Price / month (CAD)</label><input name="price" type="number" min="1" placeholder="895" required /></div>
          <div class="field"><label>Cover emoji</label>
            <select name="photo">
              <option value="🏠">🏠 House</option><option value="🏢">🏢 Apartment</option>
              <option value="🏬">🏬 Residence</option><option value="🌆">🌆 Uptown</option>
              <option value="🏡">🏡 Student house</option><option value="🏙️">🏙️ Condo</option>
            </select>
          </div>
        </div>
        <div class="grid-2">
          <div class="field"><label>Bedrooms</label><input name="beds" type="number" min="0" value="1" required /></div>
          <div class="field"><label>Bathrooms</label><input name="baths" type="number" min="0" value="1" required /></div>
        </div>
        <div class="field">
          <label style="display:flex;align-items:center;gap:8px;font-weight:600">
            <input type="checkbox" name="furnished" style="width:auto" checked /> Furnished
          </label>
        </div>
        <div class="field"><label>Description</label>
          <textarea name="description" placeholder="Tell renters about the place, roommates, utilities, distance to campus…" required></textarea>
        </div>
        <button class="btn btn-primary btn-block" type="submit">Publish listing</button>
      </form>
    </div>
  </main>
  ${footer()}`;
}

/* ---------- my listings / cancel ---------- */
function viewManage(me) {
  const mine = DB.data.posts.filter(p => p.owner === me.username);
  const rows = mine.map(p => {
    const status = p.available
      ? '<span class="pill pill-green">Available</span>'
      : (p.rentedBy ? `<span class="pill pill-gray">Rented</span>` : '<span class="pill pill-gray">Cancelled</span>');
    return `
    <article class="listing" style="cursor:default">
      <div class="cover">${p.photo || "🏠"}<span class="price">${money(p.price)}/mo</span></div>
      <div class="body">
        <div class="spread"><h3>${esc(p.location)}</h3>${status}</div>
        <div class="campus">📍 ${esc(p.campus)}</div>
        <p class="desc">${esc(p.description)}</p>
        <div class="meta">
          <span class="pill pill-gray">${fmtDate(p.start)} → ${fmtDate(p.end)}</span>
        </div>
        <div class="foot">
          ${p.available
            ? `<button class="btn btn-danger btn-sm" onclick="cancelPost('${p.id}')">Cancel listing</button>`
            : `<span class="muted" style="font-size:13px">No longer listed</span>`}
        </div>
      </div>
    </article>`;
  }).join("");

  return `
  <main class="page">
    <div class="page-head"><h1>My listings</h1><p>Everything you've posted. Cancel a listing to take it off the market.</p></div>
    ${mine.length ? `<div class="listings">${rows}</div>`
      : `<div class="empty-state"><div class="ico">🗂️</div><h3>You haven't posted anything yet</h3>
         <p>Post your place so other students can find it.</p>
         <button class="btn btn-primary" style="margin-top:14px" onclick="location.hash='#/post'">Post a sublet</button></div>`}
  </main>
  ${footer()}`;
}

function cancelPost(id) {
  const p = DB.data.posts.find(x => x.id === id);
  if (!p) return;
  p.available = false;
  p.rentedBy = null;
  DB.save();
  toast("Listing cancelled.", "");
  render();
}

/* ---------- rate a host ---------- */
function viewRate(me) {
  // any user who has ever made a posting, except myself
  const hosts = DB.data.users.filter(u =>
    u.username !== me.username && DB.data.posts.some(p => p.owner === u.username));

  const cards = hosts.map(u => `
    <article class="listing" style="cursor:pointer" onclick="openRate('${u.username}')">
      <div class="body">
        <div class="row"><span class="avatar">${initials(u)}</span>
          <div><div style="font-weight:700">${esc(u.firstName)} ${esc(u.lastName)}</div>
          <div class="muted" style="font-size:12.5px">${esc(u.campus)}</div></div>
        </div>
        <div class="foot"><div>${starsHtml(u.rating)}</div>
          <span class="pill">${u.totalRatings} rating${u.totalRatings === 1 ? "" : "s"}</span></div>
      </div>
    </article>`).join("");

  return `
  <main class="page">
    <div class="page-head"><h1>Rate a host</h1><p>Leave an honest rating for a sublessor. You can't rate yourself.</p></div>
    ${hosts.length ? `<div class="listings">${cards}</div>`
      : `<div class="empty-state"><div class="ico">⭐</div><h3>No hosts to rate yet</h3></div>`}
  </main>
  ${footer()}`;
}

function openRate(username) {
  const u = DB.user(username);
  const modal = document.createElement("div");
  modal.className = "modal-backdrop";
  modal.innerHTML = `
    <div class="modal" onclick="event.stopPropagation()" style="position:relative;max-width:420px">
      <button class="close" onclick="closeModal()">×</button>
      <div class="modal-body center">
        <span class="avatar" style="width:56px;height:56px;font-size:20px;margin:0 auto">${initials(u)}</span>
        <h2 style="margin-top:12px">Rate ${esc(u.firstName)} ${esc(u.lastName)}</h2>
        <p class="muted">Currently ${u.rating >= 0 ? u.rating.toFixed(1) + " ★" : "unrated"} · ${u.totalRatings} rating${u.totalRatings === 1 ? "" : "s"}</p>
        <div class="star-picker" id="starPicker" data-val="0">
          ${[1,2,3,4,5].map(i => `<span data-i="${i}">★</span>`).join("")}
        </div>
        <button class="btn btn-primary btn-block" id="submitRate" disabled>Submit rating</button>
      </div>
    </div>`;
  modal.addEventListener("click", closeModal);
  document.body.appendChild(modal);

  const picker = $("#starPicker");
  const stars = $$("span", picker);
  const paint = n => stars.forEach((s, i) => s.classList.toggle("on", i < n));
  stars.forEach(s => {
    s.addEventListener("mouseenter", () => paint(+s.dataset.i));
    s.addEventListener("click", () => {
      picker.dataset.val = s.dataset.i;
      paint(+s.dataset.i);
      $("#submitRate").disabled = false;
    });
  });
  picker.addEventListener("mouseleave", () => paint(+picker.dataset.val));
  $("#submitRate").addEventListener("click", () => submitRate(username, +picker.dataset.val));
}

function submitRate(username, val) {
  if (!val) return;
  const u = DB.user(username);
  u.ratingSum = (u.ratingSum || 0) + val;
  u.totalRatings = (u.totalRatings || 0) + 1;
  u.rating = u.ratingSum / u.totalRatings;
  DB.save();
  closeModal();
  toast(`Thanks! You rated ${u.firstName} ${val}★`, "good");
  render();
}

/* ---------- messaging ---------- */
function conversationsFor(me) {
  const map = {};
  DB.data.messages.forEach(m => {
    if (m.from !== me.username && m.to !== me.username) return;
    const other = m.from === me.username ? m.to : m.from;
    (map[other] = map[other] || []).push(m);
  });
  return Object.entries(map)
    .map(([other, msgs]) => {
      msgs.sort((a, b) => a.ts - b.ts);
      const last = msgs[msgs.length - 1];
      const unread = msgs.some(m => m.to === me.username && !m.read);
      return { other, msgs, last, unread };
    })
    .sort((a, b) => b.last.ts - a.last.ts);
}

function viewMessages(me, params) {
  const convos = conversationsFor(me);
  const active = params.get("with") || (convos[0] && convos[0].other) || "";

  // mark active conversation read
  if (active) {
    let changed = false;
    DB.data.messages.forEach(m => {
      if (m.from === active && m.to === me.username && !m.read) { m.read = true; changed = true; }
    });
    if (changed) DB.save();
  }

  const list = convos.map(c => {
    const u = DB.user(c.other);
    return `
    <div class="msg-item ${c.other === active ? "active" : ""}" onclick="location.hash='#/messages?with=${encodeURIComponent(c.other)}'">
      <span class="avatar">${u ? initials(u) : "?"}</span>
      <div style="min-width:0">
        <div class="who">${u ? esc(u.firstName + " " + u.lastName) : esc(c.other)}</div>
        <div class="snippet">${esc(c.last.text)}</div>
      </div>
      ${c.unread ? '<span class="dot"></span>' : ""}
    </div>`;
  }).join("");

  const thread = active ? renderThread(me, active) : "";

  return `
  <main class="page">
    <div class="page-head"><h1>Messages</h1><p>The messaging feature the original team ran out of time to build — now fully working.</p></div>
    ${convos.length ? `
    <div class="msg-layout">
      <div class="msg-list">${list}</div>
      ${active ? thread : '<div class="empty-state"><div class="ico">💬</div><h3>Select a conversation</h3></div>'}
    </div>`
    : `<div class="empty-state"><div class="ico">💬</div><h3>No messages yet</h3>
       <p>Open a listing and tap “Message host” to start a conversation.</p>
       <button class="btn btn-primary" style="margin-top:14px" onclick="location.hash='#/browse'">Browse sublets</button></div>`}
  </main>`;
}

function renderThread(me, other) {
  const u = DB.user(other);
  const msgs = DB.data.messages
    .filter(m => (m.from === me.username && m.to === other) || (m.from === other && m.to === me.username))
    .sort((a, b) => a.ts - b.ts);

  const bubbles = msgs.map(m => `
    <div class="bubble ${m.from === me.username ? "me" : "them"}">
      ${esc(m.text)}<span class="time">${timeAgo(m.ts)}</span>
    </div>`).join("");

  return `
    <div class="msg-thread">
      <div class="msg-thread-head">
        <span class="avatar">${u ? initials(u) : "?"}</span>
        ${u ? esc(u.firstName + " " + u.lastName) : esc(other)}
        ${u ? `<span class="pill pill-gray" style="margin-left:auto">${esc(u.campus)}</span>` : ""}
      </div>
      <div class="msg-scroll" id="msgScroll">${bubbles}</div>
      <form class="msg-compose" id="composeForm" data-to="${esc(other)}">
        <input name="text" placeholder="Type a message…" autocomplete="off" required />
        <button class="btn btn-primary" type="submit">Send</button>
      </form>
    </div>`;
}

function sendMessage(to, text) {
  text = text.trim();
  if (!text) return;
  DB.data.messages.push({ id: uid(), from: DB.data.session, to, text, ts: Date.now(), read: false });
  DB.save();
}

// used from the listing modal
function messageHost(username, location) {
  closeModal();
  const me = DB.currentUser();
  const existing = DB.data.messages.some(m =>
    (m.from === me.username && m.to === username) || (m.from === username && m.to === me.username));
  if (!existing) {
    sendMessage(username, `Hi! I'm interested in your sublet at ${location}. Is it still available?`);
  }
  go("#/messages?with=" + encodeURIComponent(username));
}

/* ---------- profile ---------- */
function viewProfile(me) {
  return `
  <main class="page">
    <div class="page-head"><h1>My profile</h1></div>
    <div class="auth-card" style="max-width:560px">
      <div class="row" style="gap:16px;margin-bottom:20px">
        <span class="avatar" style="width:64px;height:64px;font-size:24px">${initials(me)}</span>
        <div>
          <div style="font-size:22px;font-weight:800">${esc(me.firstName)} ${esc(me.lastName)}</div>
          <div class="muted">@${esc(me.username)}</div>
          <div style="margin-top:4px">${starsHtml(me.rating)}</div>
        </div>
      </div>
      <div class="detail-grid" style="grid-template-columns:1fr 1fr">
        <div class="cell"><div class="k">Email</div><div class="v" style="font-size:14px">${esc(me.email)}</div></div>
        <div class="cell"><div class="k">Campus</div><div class="v" style="font-size:14px">${esc(me.campus)}</div></div>
        <div class="cell"><div class="k">Gender</div><div class="v" style="font-size:14px">${esc(me.gender)}</div></div>
        <div class="cell"><div class="k">Date of birth</div><div class="v" style="font-size:14px">${fmtDate(me.dob)}</div></div>
      </div>

      <div class="stack" style="margin-top:24px">
        <button class="btn btn-ghost btn-block" onclick="logout()">Log out</button>
        <button class="btn btn-danger btn-block" onclick="resetDemo()">Reset all demo data</button>
        <p class="muted center" style="font-size:12.5px">
          All data is stored only in your browser. Resetting restores the original demo listings and accounts.
        </p>
      </div>
    </div>
  </main>
  ${footer()}`;
}

function resetDemo() {
  if (!confirm("Reset Places in Loo to its original demo data? This clears anything you've added in this browser.")) return;
  DB.reset();
  DB.data.session = null;
  DB.save();
  toast("Demo data reset.", "");
  go("#/login");
}

/* ----------------------------- events ------------------------------- */
function bindGlobal() {
  const lf = $("#loginForm");
  if (lf) lf.addEventListener("submit", e => {
    e.preventDefault();
    const f = new FormData(lf);
    const err = login(f.get("username").trim(), f.get("password"));
    if (err) return showErr(err);
    render();
  });

  const rf = $("#registerForm");
  if (rf) rf.addEventListener("submit", e => {
    e.preventDefault();
    const f = Object.fromEntries(new FormData(rf).entries());
    Object.keys(f).forEach(k => (f[k] = typeof f[k] === "string" ? f[k].trim() : f[k]));
    const err = register(f);
    if (err) return showErr(err);
    toast("Welcome to Places in Loo! 🎉", "good");
    render();
  });

  const pf = $("#postForm");
  if (pf) pf.addEventListener("submit", e => {
    e.preventDefault();
    const f = Object.fromEntries(new FormData(pf).entries());
    if (new Date(f.end) <= new Date(f.start)) return showErr("The end date must be after the start date.");
    const me = DB.currentUser();
    DB.data.posts.push({
      id: uid(), owner: me.username, location: f.location.trim(), campus: f.campus,
      start: f.start, end: f.end, price: Number(f.price),
      beds: Number(f.beds), baths: Number(f.baths),
      furnished: f.furnished === "on", description: f.description.trim(),
      photo: f.photo, available: true,
    });
    DB.save();
    toast("Your listing is live! 🏠", "good");
    go("#/manage");
  });

  const ff = $("#filterForm");
  if (ff) ff.addEventListener("submit", e => {
    e.preventDefault();
    const f = new FormData(ff);
    const qs = new URLSearchParams();
    if (f.get("q")) qs.set("q", f.get("q"));
    if (f.get("campus")) qs.set("campus", f.get("campus"));
    if (f.get("sort")) qs.set("sort", f.get("sort"));
    go("#/browse?" + qs.toString());
  });

  const cf = $("#composeForm");
  if (cf) {
    cf.addEventListener("submit", e => {
      e.preventDefault();
      const input = cf.querySelector("input[name=text]");
      sendMessage(cf.dataset.to, input.value);
      render();
    });
    const scroll = $("#msgScroll");
    if (scroll) scroll.scrollTop = scroll.scrollHeight;
  }
}

function showErr(msg) {
  const box = $("#err");
  if (box) { box.innerHTML = `<div class="form-error">${esc(msg)}</div>`; box.scrollIntoView({ block: "nearest" }); }
  else toast(msg, "bad");
}

/* expose handlers used via inline onclick */
Object.assign(window, {
  openListing, closeModal, rentPost, cancelPost, openRate, messageHost,
  logout, resetDemo,
});

/* ----------------------------- boot --------------------------------- */
window.addEventListener("hashchange", render);
DB.load();
render();
