-- create user table
CREATE TABLE USER (
  user_id INTEGER PRIMARY KEY,
  username VARCHAR(25) UNIQUE NOT NULL,
  password VARCHAR(40) NOT NULL,
  email VARCHAR(40) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  gender VARCHAR(25) NOT NULL,
  campus VARCHAR(100) NOT NULL,
  date_of_birth DATE NOT NULL
);

-- Altered table after creation
ALTER TABLE USER ADD COLUMN rating DECIMAL(3,2);
ALTER TABLE USER ADD COLUMN total_ratings INT DEFAULT 0 NOT NULL;