-- 게시판
CREATE TABLE board_tb(
    board_no NUMBER(16) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    board_category varchar2(32) NOT NULL, 
    user_no NUMBER(16) NOT NULL, 
    board_title varchar2(64) NOT NULL, 
    board_content varchar2(2048) NOT NULL, 
    board_views NUMBER(16) DEFAULT 0, 
    board_good NUMBER(16) DEFAULT 0, 
    board_bad NUMBER(16) DEFAULT 0, 
    board_regdate DATE DEFAULT sysdate, 
    board_updatedate DATE DEFAULT sysdate 
);

-- 게시판 종류
CREATE TABLE board_category_tb(
	board_category_no NUMBER(16) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    board_category varchar2(32) NOT NULL CONSTRAINT board_category_uq UNIQUE, 
    board_category_desc varchar2(32) NOT NULL CONSTRAINT board_category_desc_uq UNIQUE, 
    board_category_regdate DATE DEFAULT sysdate
);

-- 유저
CREATE TABLE user_tb(
    user_no NUMBER(16) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id varchar(32) NOT NULL CONSTRAINT user_id_uq UNIQUE, 
    user_pw varchar(128) NOT NULL, 
    user_chr varchar(32) NOT NULL CONSTRAINT user_chr_uq UNIQUE, 
    user_email varchar(64) NOT NULL CONSTRAINT user_email_uq UNIQUE, 
    user_regdate DATE DEFAULT sysdate,
    file_name varchar(256) DEFAULT NULL 
);

-- 유저 권한
CREATE TABLE user_role_tb(
     user_no NUMBER(16) NOT NULL PRIMARY KEY,
     role_no NUMBER(16) DEFAULT 3 NOT NULL 
);

-- 댓글
CREATE TABLE reply_tb(
	reply_no NUMBER(16) GENERATED ALWAYS AS IDENTITY, 
    board_no NUMBER(16) NOT NULL, 
    user_no NUMBER(16) NOT NULL,
    reply_group NUMBER(16) DEFAULT 0 NOT NULL,
    reply_dept NUMBER(2) DEFAULT 0 NOT NULL,
    reply_content varchar2(128) NOT NULL,
    reply_regdate DATE DEFAULT sysdate, 
    reply_updatedate DATE DEFAULT sysdate, 
    CONSTRAINT reply_pk PRIMARY KEY(board_no, reply_no) 
);

-- 파일
CREATE TABLE file_tb(
    file_no NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    board_no NUMBER NOT NULL,
    file_src varchar2(256) NOT NULL, 
    file_desc varchar2(256) NOT NULL, 
    file_regdate DATE DEFAULT sysdate 
);

-- 권한
CREATE TABLE role_tb(
    role_no NUMBER DEFAULT 3 PRIMARY KEY,
    role_name varchar(32) NOT NULL CONSTRAINT role_name_uq UNIQUE, 
    role_desc varchar(32) NOT NULL CONSTRAINT role_desc_uq UNIQUE
);