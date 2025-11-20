CREATE DATABASE gym_db;
USE gym_db;

CREATE TABLE members (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  phone VARCHAR(20),
  plan VARCHAR(50)
);
