use truyenchu;

alter table user
    add constraint fk_user_role foreign key (roleid) references role (roleid);
alter table comment
    add constraint fk_comment_user foreign key (userid) references user (userid);
alter table comment
    add constraint fk_comment_book foreign key (bookid) references book (bookid);
alter table book
    add constraint fk_book_user foreign key (userid) references user (userid);
alter table book
    add constraint fk_book_author foreign key (authorid) references author (authorid);
alter table evaluate
    add constraint fk_evaluate_user foreign key (userid) references user (userid);
alter table evaluate
    add constraint fk_evaluate_book foreign key (bookid) references book (bookid);
alter table book_category
    add constraint fk_bookcategory_book foreign key (bookid) references book (bookid);
alter table book_category
    add constraint fk_bookcategory_category foreign key (categoryid) references category (categoryid);
alter table chapter
    add constraint fk_chapter_book foreign key (bookid) references book (bookid);
