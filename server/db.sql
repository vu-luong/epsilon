DROP DATABASE IF EXISTS `epsilon`;
CREATE DATABASE `epsilon` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use `epsilon`;
CREATE TABLE `learners` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(20) NOT NULL,
    `requirement_category` VARCHAR(100),
    `combined_category` VARCHAR(100),
    `requirement_based_rcms` VARCHAR(100),
    `combined_based_rcms` VARCHAR(100)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `authors` (
    `name` VARCHAR(40) PRIMARY KEY,
    `image_url` VARCHAR(200),
    `description` VARCHAR(1000)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `courses` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(100),
    `author_name` VARCHAR(40) NOT NULL,
    `description` VARCHAR(1000),
    `category` VARCHAR(8),
    `image_url` VARCHAR(200),
    `rating` INT,
    `link` VARCHAR(200) NOT NULL,
    `provider` VARCHAR(40) NOT NULL,
    `price` VARCHAR(100),
    `learners` INT default 0
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `categories` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `enrollments` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `course_id` INT NOT NULL,
    `learner_id` INT NOT NULL,
    `datetime` DATETIME NOT NULL default '0000-00-00 00:00:00'
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `supports` (
    `course1` INT NOT NULL,
    `course2` INT NOT NULL,
    `both` INT NOT NULL default 0,
    `order` INT NOT NULL default 0,
    `prior` INT NOT NULL default 0
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE courses
ADD FOREIGN KEY (author_name)
REFERENCES authors(name);

ALTER TABLE enrollments
ADD FOREIGN KEY (course_id)
REFERENCES courses(id);

ALTER TABLE enrollments
ADD FOREIGN KEY (learner_id)
REFERENCES learners(id);

ALTER TABLE supports
ADD FOREIGN KEY (course1)
REFERENCES courses(id);

ALTER TABLE supports
ADD FOREIGN KEY (course2)
REFERENCES courses(id);