create trigger truyenchu.delete_author
after delete ON truyenchu.author
for each row
update book set
authorid = 0
where authorid = OLD.authorid