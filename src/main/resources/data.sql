DROP TABLE IF EXISTS poll;
DROP TABLE IF EXISTS user_id;

CREATE TABLE poll (
        question_id int(11) unsigned NOT NULL AUTO_INCREMENT,
        question_title varchar(300) NOT NULL DEFAULT '',
        first_answer_option varchar(300) NOT NULL DEFAULT '',
        second_answer_option varchar(300) NOT NULL DEFAULT '',
        third_answer_option varchar(300)  NULL DEFAULT '',
        fourth_answer_option varchar(300) NOT NULL DEFAULT '',

        PRIMARY KEY (question_id)
);

CREATE TABLE user_id (
    uniqueUser_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_first_name varchar(300) unsigned NOT NULL,
    user_last_name varchar(300) NOT NULL DEFAULT '',
    user_email varchar (300) NOT NULL DEFAULT '',
    user_age varchar (300) NOT NULL DEFAULT '',
    user_address varchar (300) NOT NULL DEFAULT '',


    PRIMARY KEY (uniqueUser_id),
    FOREIGN KEY (user_id) REFERENCES poll(question_id)
);