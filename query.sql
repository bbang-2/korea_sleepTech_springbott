create database if not exists `springboot_db`;

use `springboot_db`;

-- 주문 관리 시스템 (트랜잭션, 트리거, 인덱스, 뷰 학습) --
CREATE TABLE IF NOT EXISTS products (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM products;

CREATE TABLE IF NOT EXISTS stocks (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
DROP TABLE order_items;
DROP TABLE order_logs;
DROP TABLE orders;
CREATE TABLE IF NOT EXISTS orders (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- EX) 주문 대기 상태 - 담당자가 주문 승인 시 상태 변경
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS order_items (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL, -- orders 테이블 참조 (주문의 공통 상태를 저장)
    product_id BIGINT NOT NULL, 
    quantity INT NOT NULL, -- 주문 수량
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS order_logs (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    message VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

# 초기 데이터 삽입 스크립트 #
-- 기본 상품 등록
INSERT INTO products (name, price) 
VALUES
	('슬립테크 매트리스', 120), 	-- 1
    ('수면 측정기기', 45), 		-- 2
    ('아로마 수면램프', 30); 	-- 3
-- 재고 등록
INSERT INTO stocks (product_id, quantity)
VALUES
	(1, 100),
    (2, 30),
    (3, 45);
    
# 인덱스 추가 (제품명 순서로 빠르게 검색하는 인덱스 추가)
CREATE INDEX idx_product_name ON products(name);

# 주문 요약 뷰 생성
CREATE OR REPLACE VIEW order_summary AS
SELECT 
	o.id AS order_id, o.user_id, p.name AS product_name, oi.quantity, p.price
    , (oi.quantity * p.price) AS total_price
FROM 
	orders o
    JOIN order_items oi ON o.id = oi.order_id
    JOIN products p ON oi.product_id = p.id;

# 주문 생성 트리거
DELIMITER //

DROP TRIGGER IF EXISTS trg_after_order_insert;

CREATE TRIGGER trg_after_order_insert
AFTER INSERT ON orders
FOR EACH ROW
BEGIN
  INSERT INTO order_logs(order_id, message)
  VALUES (NEW.id, CONCAT('주문이 생성되었습니다. 주문 ID: ', NEW.id));
END;
//

DELIMITER ;

SELECT * FROM products;
SELECT * FROM stocks;
SELECT * FROM orders;
SELECT * FROM order_items;
SELECT * FROM order_logs;


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

-- log(기록) 테이블 --
# 권한 변경 시 기록(로그) 테이블에 자동 저장
create table if not exists role_change_logs (
	id bigint auto_increment primary key,
    user_id bigint not null, -- 권한이 변경된 사용자 ID(PK)
    email varchar(255) not null, -- 사용자 이메일
    prev_roles text,
    new_roles text,
    changed_by varchar(255) not null,-- 변경을 수행한 관리자 이메일
    change_type varchar(255) not null, -- 변경 유형(ADD, ROMOVE, UPDATE 등)
    change_reason varchar(255), -- 변경 사유 (필요 시 사용)
    changed_at timestamp default current_timestamp,
    foreign key (user_id) references users(id) on delete cascade
);

-- -------------------------------------------------
# 파일 시스템
create table if not exists `posts` (
	id bigint auto_increment primary key,
    title varchar(255) not null,
    content text not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp on update current_timestamp
);

select * from posts;

create table if not exists `communities` (
	id bigint auto_increment primary key,
    title varchar(255) not null,
    content text not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp on update current_timestamp
);

create table if not exists `upload_files` (
	id bigint auto_increment primary key,
    original_name varchar(255) not null, -- 사용자가 업로드한 원래 이름
    file_name varchar(255) not null, -- 서버에 저장된 이름
    file_path varchar(500) not null, -- 전체 경로 또는 상대 경로
    file_type varchar(100), -- MIME 타입 (image/png)
    file_size bigint not null, -- 파일 크기(bytes
    
    target_id bigint not null,
    target_type enum('POST', 'COMMUNITY') not null,
    
    index idx_target (target_id, target_type)
);

select * from upload_files;