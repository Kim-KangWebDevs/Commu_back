--게시판
create table board_tb(
    board_no number(16) generated always as identity primary key,
    board_id varchar2(32) not null, 
    user_no number(16) not null, 
    board_title varchar2(64) not null,  
    board_content varchar2(2048) not null, 
    board_views number(16) default 0, 
    board_good number(16) default 0, 
    board_bad number(16) default 0, 
    board_regdate date default sysdate, 
    board_updatedate date default sysdate 
);

--게시판 종류
create table board_category_tb(
    board_id varchar2(32) not null primary key,
    board_desc varchar2(32) not null constraint board_desc_uq unique, 
    board_category_regdate date default sysdate
);

--댓글
create table reply_tb(
    board_no number(16) not null, 
    reply_no number(16) generated always as identity, 
    user_no number(16) not null,
    reply_group number(16) not null,
    reply_dept number(2) default 1 not null,
    reply_content varchar2(128) not null,
    reply_regdate date default sysdate,
    reply_updatedate date default sysdate,
    constraint reply_pk primary key(board_no, reply_no) 
);

--파일
create table file_tb(
    file_no number generated always as identity primary key,
    board_no number not null,
    board_id varchar2(32) not null,
    file_name varchar2(256) not null, 
    file_regdate date default sysdate 
);

--유저
create table user_tb(
    user_no number(16) generated always as identity primary key,
    user_id varchar(32) not null, 
    user_pw varchar(128) not null, 
    user_chr varchar(32) not null, 
    user_email varchar(64) not null, 
    user_regdate date default sysdate,
    file_name varchar(256) default null 
);

--유저 seq
create sequence user_seq 
        increment by 1 
        start with 1;

-- 권한
create table role_tb(
    role_no number default 3 primary key,
    role_name varchar(32) not null constraint role_name_uq unique, 
    role_desc varchar(32) not null constraint role_desc_uq unique
);

-- 유저 권한 
create table user_role_tb(
     user_no number(16) not null primary key,
     role_no number(16) default 3 
);