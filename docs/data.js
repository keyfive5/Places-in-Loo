/*
 * Places in Loo — seed / demo data
 * ---------------------------------
 * The original team seeded their SQLite database (sql/insert_user.sql and
 * sql/insert_post.sql) with their own accounts and a real listing. Those exact
 * records are preserved here so the modern web edition honours the original
 * 2022 project. New realistic listings around the Laurier/Waterloo student
 * neighbourhoods were added on top for a fuller demo.
 */
window.PIL_SEED = {
  // schema version — bump to force a reseed for existing visitors
  version: 4,

  users: [
    // --- Original accounts from sql/insert_user.sql (2022 team) ---
    { username: "shai1640",  password: "password",       email: "shai1640@mylaurier.ca", firstName: "Nabeel",  lastName: "Shahyan",  gender: "Male", campus: "Wilfrid Laurier University", dob: "2001-10-02", rating: 4.8, totalRatings: 12, ratingSum: 57.6 },
    { username: "Lebron123", password: "Bronny123",      email: "jame1910@mylaurier.ca", firstName: "Faisal",  lastName: "Jackson",  gender: "Male", campus: "Wilfrid Laurier University", dob: "2002-09-28", rating: 4.5, totalRatings: 8,  ratingSum: 36.0 },
    { username: "wren112ii", password: "batmanpassword", email: "wren1670@mylaurier.ca", firstName: "Jack",    lastName: "Wrenen",   gender: "Male", campus: "Wilfrid Laurier University", dob: "2001-10-02", rating: 4.2, totalRatings: 5,  ratingSum: 21.0 },
    { username: "Kabi9911",  password: "zafar12321",     email: "kabi1990@mylaurier.ca", firstName: "Kabir",   lastName: "Zafar",    gender: "Male", campus: "Wilfrid Laurier University", dob: "1999-10-08", rating: 4.9, totalRatings: 15, ratingSum: 73.5 },

    // --- Documented demo login from the README ---
    { username: "caix3600",  password: "password",       email: "caix3600@uwaterloo.ca", firstName: "Alex",    lastName: "Cai",      gender: "Prefer not to say", campus: "University of Waterloo", dob: "2002-03-14", rating: -1, totalRatings: 0, ratingSum: 0 },

    // --- A couple more so the marketplace feels alive ---
    { username: "jina2205",  password: "password",       email: "jina2205@uwaterloo.ca", firstName: "Jina",    lastName: "Park",     gender: "Female", campus: "University of Waterloo", dob: "2003-01-19", rating: 5.0, totalRatings: 3, ratingSum: 15.0 },
    { username: "omar9088",  password: "password",       email: "omar9088@mylaurier.ca", firstName: "Omar",    lastName: "Haddad",   gender: "Male",   campus: "Wilfrid Laurier University", dob: "2001-07-30", rating: 4.6, totalRatings: 7, ratingSum: 32.2 }
  ],

  // location coords are approximate, used for the little map dots
  posts: [
    // --- Original listing from sql/insert_post.sql (Nabeel) ---
    { owner: "shai1640", location: "168 King Street West", campus: "Wilfrid Laurier University",
      start: "2022-09-02", end: "2022-12-25", price: 1500, beds: 1, baths: 1, furnished: true,
      description: "1 bed 1 bath fully furnished with balcony, all utilities and free designated spot.",
      photo: "🏙️", available: false },

    // --- New demo listings around the WLU/UW student area ---
    { owner: "Kabi9911", location: "318 Spruce Street", campus: "Wilfrid Laurier University",
      start: "2026-09-01", end: "2026-12-20", price: 895, beds: 1, baths: 1, furnished: true,
      description: "Bright room in a 4-person unit, 6 min walk to WLU. Utilities + internet included, in-suite laundry, gym in building.",
      photo: "🏠", available: true },

    { owner: "Lebron123", location: "256 Phillip Street", campus: "University of Waterloo",
      start: "2026-05-01", end: "2026-08-31", price: 720, beds: 1, baths: 1, furnished: true,
      description: "Summer sublet at ICON. Steps from UW, huge common area, rooftop terrace and study lounge. Female or male roommate welcome.",
      photo: "🏢", available: true },

    { owner: "wren112ii", location: "155 King Street North", campus: "Wilfrid Laurier University",
      start: "2026-09-01", end: "2027-04-30", price: 1050, beds: 1, baths: 1, furnished: false,
      description: "Full 8-month lease takeover, private bathroom, quiet building near Uptown Waterloo. Parking available for +$80.",
      photo: "🌆", available: true },

    { owner: "jina2205", location: "342 Lester Street", campus: "University of Waterloo",
      start: "2026-09-01", end: "2026-12-20", price: 810, beds: 1, baths: 1, furnished: true,
      description: "Fall term room at Sage. 3 min to UW, fully furnished, dishwasher, A/C. Chill roommates, no smoking.",
      photo: "🏬", available: true },

    { owner: "omar9088", location: "45 Ezra Avenue", campus: "Wilfrid Laurier University",
      start: "2026-05-01", end: "2026-08-31", price: 650, beds: 1, baths: 2, furnished: true,
      description: "Heart of the Ezra student village. Cheap summer sublet, big house with 5 students, backyard + BBQ. Great if you want the social term.",
      photo: "🏡", available: true }
  ]
};
