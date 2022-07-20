-- Create Post table
CREATE TABLE POSTS (
  post_id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  email VARCHAR(40) NOT NULL,
  sublet_start_date DATE NOT NULL,
  sublet_end_date DATE NOT NULL,
  price_per_month INTEGER NOT NULL,
  location VARCHAR(999) NOT NULL,
  description VARCHAR(999) NOT NULL,
  FOREIGN KEY(user_id) REFERENCES USER(user_id) 
);