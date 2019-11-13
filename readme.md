

create database cos;
use cos;


create database cos;
use cos;


UPDATE user SET userProfile='/blog/media/pa.png' WHERE id=2;

INSERT IGNORE INTO user (username,password,email,createDate,address) values ('VVVV', 'aa', 'aa',now(), 'a');

DESC reply;
select * from user;
select * from board;
select * from reply;
select * from comment;

select r.id, r.commentId, r.userId, r.content, r.createDate, u.username, u.userProfile
from reply r, user u
where r.userId = u.id
and r.id = (select max(id) from reply);


SELECT *
FROM board b, user u 
WHERE b.userId = u.id and 
(b.content LIKE '%글글%' or b.title LIKE '%글글%') 
ORDER BY b.id DESC LIMIT 0,3;



ALTER TABLE reply 
ADD constraint reply_ibkf_2 
foreign key (commentId) 
references comment (id) on delete set null;

ALTER TABLE reply 
ADD constraint reply_ibkf_1 
foreign key (commentId) 
references comment (id) on delete cascade;




ALTER TABLE user ADD emailFlag int default 0;
ALTER TABLE user ADD userProfile varchar(200) default '/blog/img/defaultProfile.jpg' ;

select count(id), email from user where username = 'test' and password = '1234' and emailFlag=1;

SELECT * from user where email='asdf123@asdf123.com';
UPDATE user SET emailFlag=1 WHERE id=25;

select count(id) from user where username = 'alalal' and password = 'alalal' and emailFlag=1;

UPDATE user SET emailFlag=1 WHERE email='asdf123@asdf123.com';
set sql_safe_updates=0;

commit;

delete from user where id=15;
SELECT * FROM board WHERE id = 18;
select * from user where username = 'test' and password = '1234';

select count(id) from user where username = 'test' and password = '1234';

insert into reply values(null,19,26,'테스트',now());
insert into reply values(null,19,26,'오',now());

INSERT IGNORE INTO user values(null,'ssar2','12341','ssar@nate.com',now());
INSERT INTO board(userId,title,content,createDareplyte) values(2,'실화냐','실화임',now());
delete from user where id=1;

commit;

SELECT * FROM Information_schema.table_constraints WHERE table_schema='reply';

INSERT INTO comment(userId, boardId, content, createDate) 
VALUES(28, 25, '첫번째댓글', now());
INSERT INTO comment(userId, boardId, content, createDate) 
VALUES(28, 25, '두번째댓글', now());
INSERT INTO comment(userId, boardId, content, createDate) 
VALUES(28, 25, '세번째댓글', now());
commit;

select c.id,c.userId,c.boardId,c.content,c.createDate,u.username
from comment c, user u
where c.userId=u.id
and c.id =(select max(id) from comment);


select r.id,r.userId,r.commentId,r.content,r.createDate,u.username
from reply r,user u
where r.userId=u.id and
r.commentId=27;

select r.id,r.userId,r.commentId,r.content,r.createDate,u.username
from reply r,user u
where r.userId=u.id
and r.id=(select max(id) from reply);



CREATE TABLE comment(
   id int auto_increment primary key,
    userId int,
    boardId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id),
    foreign key (boardId) references board (id)
) engine=InnoDB default charset=utf8;




CREATE TABLE user(
	id int auto_increment primary key,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    createDate timestamp default now()
) engine=InnoDB default charset=utf8;

ALTER TABLE user ADD adress varchar(200);
ALTER TABLE user CHANGE adress address varchar(200);
CREATE TABLE board(
	id int auto_increment primary key,
    userId int not null,
    title varchar(100) not null,
    content longtext,
    readCount int default 0,
    createDate timestamp,
    foreign key (userId) references user (id)
) engine=InnoDB default charset=utf8;

ALTER TABLE reply
DROP FOREIGN KEY commentId;

DROP TABLE reply;

CREATE TABLE reply(
	id int auto_increment primary key,
    userId int,
    commentId int,
    content varchar(300) not null,
    createDate timestamp
) engine=InnoDB default charset=utf8;

ALTER TABLE reply
ADD constraint 
foreign key (commentId)
references comment (id);

select * from user;
select * from board;

SELECT count(*) maxPage FROM board;



SELECT *
FROM board b LEFT OUTER JOIN user u
ON b.userId = u.id;

-- popularBoard
 SELECT * FROM board ORDER BY readCount DESC limit 3;
 SELECT * FROM board ORDER BY readCount DESC limit 3;


SELECT * FROM board ORDER BY id DESC limit 0,3;

SELECT b.id,b.userId,b.title,b.content,b.readCount,b.createDate,u.username FROM board b LEFT JOIN user u ON (b.userId=u.id) ORDER BY id DESC limit 3,3;

SELECT b.id,b.userId,b.title,b.readCount,u.username FROM board b, user u WHERE b.userId=u.id ORDER BY id DESC limit 0,3;

SELECT * FROM board b, user u WHERE b.userId=u.id ORDER BY b.id DESC limit 0,3;

SELECT id,username,email
FROM user;

SELECT * FROM reply;

SELECT b.id, b.userId, b.title, (select count(*) from reply where  userid=b.id)
FROM board b;
