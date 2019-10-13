create database worskshop2
character set utf8mb4
collate utf8mb4_unicode_ci;

Create TABLE users (
    id int auto_increment primary key,
    email varchar(255) unique,
    username varchar(255),
    password varchar(60)
)