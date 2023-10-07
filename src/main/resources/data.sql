DROP TABLE IF EXISTS user_answers;
DROP TABLE IF EXISTS poll_options;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS poll_question;

CREATE TABLE poll_question (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   title VARCHAR(100) NOT NULL DEFAULT '',
   PRIMARY KEY (id)
);

CREATE TABLE poll_options (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   option_1 VARCHAR(100) NOT NULL DEFAULT '',
   option_2 VARCHAR(100) NOT NULL DEFAULT '',
   option_3 VARCHAR(100) NOT NULL DEFAULT '',
   option_4 VARCHAR(100) NOT NULL DEFAULT '',
   question_id INT UNSIGNED NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (question_id) REFERENCES poll_question (id)
);

CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE user_answers (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   user_id INT UNSIGNED NOT NULL,
   question_id INT UNSIGNED NOT NULL,
   selected_option_id INT UNSIGNED NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY unique_user_question (user_id, question_id),
   FOREIGN KEY (user_id) REFERENCES users (id),
   FOREIGN KEY (question_id) REFERENCES poll_question (id),
   FOREIGN KEY (selected_option_id) REFERENCES poll_options (id)
);

INSERT INTO poll_question (title) VALUES
('Between the following, what do you most love to do?'),
('Where is your preferred place to travel');
INSERT INTO poll_options (option_1, option_2, option_3, option_4, question_id) VALUES
('a. Watch TV', 'b. Play the computer', 'c. Hanging out with friends', 'd. Travel the world', 1),
('a. USA', 'b. France', 'c. South America', 'd. Thailand', 2);