drop table if exists liked_by;
drop table if exists offer;
drop table if exists employee;

create table user(
	id int primary key auto_increment,
    name varchar(50) not null,
    password varchar(100)
    );
    

    
    
insert into user(id,name,password) values(1,'rohitsharma','rohitsharma');
insert into user(id,name,password) values(2,'kohli','kohli');
insert into user(id,name,password) values(3,'smritimandhana','smritimandhana');





