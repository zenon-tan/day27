# mysql -uroot -p  -> this is on mysql shell
# show databases;
# source bgg.sql; -> import the database (while being inside the folder)
# show databases;
# use bgg;



use bgg;

show tables;

describe game;

grant all on bgg.* to zenon@localhost;

flush privileges;

select * from game limit 1;
select * from comment limit 1;

select gid, name from game; 