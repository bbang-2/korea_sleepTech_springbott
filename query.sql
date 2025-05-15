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

CREATE TABLE post (
	id bigint auto_increment primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    author varchar(255) not null
);

CREATE TABLE comment (
	id bigint auto_increment primary key,
    post_id bigint,
    content varchar(255) not null,
    commentor varchar(255) not null,
    foreign key (post_id) references post(id) on delete cascade
);

CREATE TABLE users(
	id bigint auto_increment primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    created_at datetime not null,
    updated_at datetime
);

select * from users;

-- authority(권한) 관련 테이블 --
# 권한 관리 테이블 설계 (정규화 방식)
# : 권한 종류를 roles 테이블로 분리
#	, 이를 참조하여 user_roles에 저장
# > 역할 이름을 고유값으로 관리, role_id를 통해 연결

create table if not exists roles (
	role_id bigint auto_increment primary key, -- 역할 고유 ID
    role_name varchar(50) not null unique -- 역할 이름 (ex. ADMIN, USER), 중복 불가
);

insert into roles (role_name)
values
	('USER'), ('ADMIN'), ('MANAGER');

# 해당 테이블은 사용자와 역할을 관계를 나타내는 중간 테이블
# : 사용자 한 명이 여러 역할을 가질 수 있고
# , 하나의 역할도 여러 사용자에게 부여 될 수 있음
# > 다대다(ManyToMany)
create table if not exists user_roles (
	user_id bigint not null,
	role_id bigint not null,
    primary key (user_id, role_id), -- 복합 기본키: 중복 매핑 방지
    constraint fk_user foreign key (user_id) references users(id) on delete cascade,
    constraint fk_role foreign key (role_id) references roles(role_id) on delete cascade
);

select * from roles;
select * from user_roles;