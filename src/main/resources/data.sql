DROP TABLE IF EXISTS poll_question;
DROP TABLE IF EXISTS poll_options;
DROP TABLE IF EXISTS user_answers;

CREATE TABLE poll_question (
   id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   title VARCHAR(100) NOT NULL DEFAULT '',
   PRIMARY KEY (id)
);

CREATE TABLE poll_options (
   id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   text_option VARCHAR(100) NOT NULL DEFAULT '',
   question_id int(11) UNSIGNED NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (question_id) REFERENCES poll_question(id)
);

CREATE TABLE user_answers (
   id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   user_id int(11) UNSIGNED NOT NULL,
   question_id int(11) UNSIGNED NOT NULL,
   selected_option_id int(11) UNSIGNED NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (question_id) REFERENCES poll_question(id),
   FOREIGN KEY (selected_option_id) REFERENCES poll_options(id)
);

INSERT INTO poll_question (title) VALUES
('Between the following, what do you most love to do?'),
('Where is your preferred place to travel');

INSERT INTO poll_options (text_option, question_id)
VALUES ('Watch TV', 1);

INSERT INTO poll_options (text_option, question_id)
VALUES ('Play the computer', 1);

INSERT INTO poll_options (text_option, question_id)
VALUES ('Hanging out with friends', 1);

INSERT INTO poll_options (text_option, question_id)
VALUES ('Travel the world', 1);

--INSERT INTO poll_options (option_1, option_2, option_3, option_4, question_id) VALUES
--('', 'b. Play the computer', '', ', 1),
--('a. USA', 'b. France', 'c. South America', 'd. Thailand', 2);