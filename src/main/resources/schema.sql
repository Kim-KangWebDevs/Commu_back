--게시판
create table board_tb(
    board_type varchar2(32) not null,
    board_no number(16) not null,
    board_title varchar2(64) not null,
    user_no number(16) not null,
    board_content varchar2(2048) not null,
    board_views number(16) default 0,
    board_good number(8) default 0,
    board_bad number(8) default 0,
    board_regdate date default sysdate,
    board_updatedate date default sysdate,
	constraint board_pk primary key(board_no, board_type) 
);

--게시판 종류
create table board_category_tb(
    board_type varchar2(32) not null primary key,
    board_name varchar2(32) not null constraint board_name_uq unique, 
    board_type_regdate date default sysdate
);

--게시판 종류 seq
create sequence board_type_seq 
        increment by 1 
        start with 1;

--댓글
create table reply_tb(
    board_no number(16) not null,
    board_type varchar2(32) not null,
    reply_no number(16) not null,
    user_no number(16) not null,
    reply_group number(16) not null,
    reply_dept number(2) default 1 not null,
    reply_content varchar2(128) not null,
    reply_regdate date default sysdate,
    constraint reply_pk primary key(board_no, board_type, reply_no) 
);

--파일
create table file_tb(
    file_no number not null primary key,
    board_no number not null,
    board_type varchar2(32) not null,
    file_name varchar2(256) not null, 
    file_regdate date default sysdate 
);

--파일 seq
create sequence file_seq
        increment by 1
        start with 1;

--유저
create table user_tb(
    user_no number not null primary key,
    user_id varchar(32) not null constraint user_id_uq unique, 
    user_pw varchar(128) not null, 
    user_chr varchar(32) not null constraint user_chr_uq unique, 
    user_email varchar(64) not null, 
    user_regdate date default sysdate,
    file_name varchar(256) default null 
);

--유저 seq
create sequence user_seq 
        increment by 1 
        start with 1;

-- 권한
create table auth_tb(
    auth_no number default 3 primary key,
    auth_name varchar(32) not null constraint auth_name_uq unique 
);

--get seq procedure
create or replace function get_seq(seq_name in varchar2) 
return 
    number 
is
    seq_num number;
    sql_stmt varchar2(64);
begin
    sql_stmt := 'select ' || seq_name || '.nextval from dual';
    execute immediate sql_stmt into seq_num;
    return seq_num;
end;