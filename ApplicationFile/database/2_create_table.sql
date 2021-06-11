use truyenchu;

CREATE TABLE role
(
    roleid   bigint primary key not null auto_increment,
    rolename varchar(255)
);

CREATE TABLE user
(
    userid      bigint       not null primary key auto_increment,
    name        varchar(255) not null,
    fullname    varchar(300) not null,
    password    varchar(255) not null,
    email       varchar(255) null,
    address     varchar(255) null,
    number      varchar(255) null,
    createddate timestamp    null,
    roleid      bigint       not null
);

CREATE TABLE book
(
    bookid       bigint       not null primary key auto_increment,
    bookname     varchar(500) not null,
    bookfullname varchar(500) not null,
    brief        text         not null,
    createddate  timestamp    null,
    modifieddate timestamp    null,
    userid       bigint       not null,
    authorid     bigint       not null
);

CREATE TABLE comment
(
    commentid    bigint    not null primary key auto_increment,
    content      text      null,
    createddate  timestamp null,
    modifieddate timestamp null,
    bookid       bigint    not null,
    userid       bigint    not null
);

CREATE TABLE author
(
    authorid   bigint       not null primary key auto_increment,
    authorname varchar(255) not null,
    sex        varchar(20)  null,
    content    text         null
);

CREATE TABLE chapter
(
    chapterid    bigint       not null primary key auto_increment,
    title        varchar(255) null,
    content      text         null,
    view         int          null,
    createddate  timestamp    null,
    modifieddate timestamp    null,
    bookid       bigint       not null
);

CREATE TABLE evaluate
(
    evaluateid bigint not null primary key auto_increment,
    count      int    null,
    bookid     bigint not null,
    userid     bigint not null
);

CREATE TABLE category
(
    categoryid bigint not null primary key auto_increment,
    categoryname varchar(255) not null ,
    content text null
);

CREATE TABLE book_category (
    id bigint not null primary key auto_increment,
    categoryid bigint not null,
    bookid bigint not null
);
