create database if not exists `springboot_db`;

use `springboot_db`;

-- test 테이블

create table test(
	id bigint primary key auto_increment,
    name varchar(50) not null
);

select * from test;

-- STUNDEN 테이블 --
CREATE TABLE student (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique
);

select * from student;

-- BOOK 테이블 --
CREATE TABLE book (
	id bigint auto_increment primary key,
    writer varchar(50) not null,
    title varchar(100) not null,
    content varchar(500) not null,
    category varchar(255) not null,
    CONSTRAINT chk_category CHECK (category IN ('NOVEL', 'ESSAY', 'POEM', 'MAGAZINE'))
);

select * from book;

CREATE TABLE products (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    description text,
    pirce decimal(10,2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp on update current_timestamp
);

