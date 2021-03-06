#create database logindb;
#use logindb;

drop table results;
drop table activity;
drop table users;

CREATE TABLE users (
    id_u INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(20) UNIQUE,
    pass VARCHAR(15),
    perm VARCHAR(3) default '100',
    name  VARCHAR(20),
    last  VARCHAR(20)
);

insert into users(login, pass,perm,name,last) values('admin1','pass1','111','Jan','Kowalski');

select * from users;

CREATE TABLE activity (
    id_a INT AUTO_INCREMENT,
    id_u INT UNIQUE,
    training_materials DOUBLE DEFAULT 0,
    courseX DOUBLE DEFAULT 0,
    courseY DOUBLE DEFAULT 0,
    delegation DOUBLE DEFAULT 0,
    PRIMARY KEY (id_a),
    FOREIGN KEY (id_u)
        REFERENCES users (id_u)
);

select * from activity;
select*from activity where id_u=3;


CREATE TABLE results (
    id_r INT AUTO_INCREMENT,
    month INT,
    id_u INT ,
    training_materials_h INT DEFAULT 0,
    courseX_h INT DEFAULT 0,
    courseY_h INT DEFAULT 0,
    delegation_h INT DEFAULT 0,
    PRIMARY KEY (id_r),
    FOREIGN KEY (id_u)
        REFERENCES users (id_u)
);

select * from results;
select* from results where id_u=3 and month=2;

select* from activity where id_u=3;
select training_materials from activity where id_u=3;
select * from activity natural join results where month=2;