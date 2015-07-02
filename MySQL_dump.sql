CREATE TABLE IF NOT EXISTS categories
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS payment_options
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  description LONGTEXT NOT NULL,
  value INT NOT NULL,
  project_id INT NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS projects
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  short_description LONGTEXT,
  current_money INTEGER,
  need_money INTEGER,
  days_left INTEGER,
  history LONGTEXT,
  video_url VARCHAR(255),
  category_id INTEGER,

  FOREIGN KEY (category_id) REFERENCES categories(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS payment_options (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  value INTEGER,
  description TEXT,
  project_id INTEGER,
  FOREIGN KEY (project_id) REFERENCES projects(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS quotes
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  value LONGTEXT,
  author VARCHAR(50)
)ENGINE = InnoDB;

#################################
# INSERT DATA
#################################

#--------------------------------
#CATEGORIES
#--------------------------------
INSERT INTO categories(name) VALUES ('Category 1'), ('Category 2'), ('Category 3');

#--------------------------------
#PROJECTS
#--------------------------------
INSERT INTO projects
(name, short_description, current_money, need_money, days_left, history, video_url, category_id)
VALUES
  ('name 1', 'short deskr 1', 1000, 5000, 30, 'history 1', 'video url 1', 1),
  ('name 2', 'short deskr 2', 1000, 5000, 30, 'history 2', 'video url 2', 1),
  ('name 3', 'short deskr 3', 1000, 5000, 30, 'history 3', 'video url 3', 2),
  ('name 4', 'short deskr 4', 1000, 5000, 30, 'history 4', 'video url 4', 2),
  ('name 5', 'short deskr 5', 1000, 5000, 30, 'history 5', 'video url 5', 3);

#--------------------------------
#QUOTES
#--------------------------------
INSERT INTO quotes(value, author)
VALUES
  ('Quote 1', 'Author 1'),
  ('Quote 2', 'Author 2'),
  ('Quote 3', 'Author 3');