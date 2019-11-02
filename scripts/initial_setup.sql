create database workshop2
    character set utf8mb4
    collate utf8mb4_unicode_ci;

Create TABLE users (
                       id int auto_increment primary key,
                       email varchar(255) unique,
                       username varchar(255),
                       password varchar(60)
);

create table user_group(
                           id int(11) primary key,
                           name VARCHAR(255)
);

Alter TABLE users add user_group_id int;

alter table users add foreign key (user_group_id) references user_group(id);

create table exercise (
                          id int(11) AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255),
                          description TEXT);

Create table solution(
                         id INT(11) auto_increment primary key,
                         created DATETIME,
                         updated DATETIME,
                         description TEXT,
                         exercise_id INT(11),
                         users_id INT(11),
                         FOREIGN KEY (exercise_id) references exercise(id),
                         foreign key (users_id) references users(id)
);