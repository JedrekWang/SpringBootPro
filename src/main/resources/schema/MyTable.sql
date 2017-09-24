create table blog(
  blog_id int not null AUTO_INCREMENT PRIMARY KEY ,
  title varchar(30) not null,
  writer varchar(20) not null,
  blog_description varchar(100),
  content TEXT not NULL,
  created TIMESTAMP not null default current_timestamp
)CHARSET =utf8;