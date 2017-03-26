
create database TEST;

create table Teacher (
  name varchar(45) not null,
  subject varchar(45) not null,
  primary key(name,subject)) ;

create table ParentSubject (
subjectName varchar(45) not null,
primary key(subjectName)
);

create table ChildSubject (
  id bigint(20) not null auto_increment,
  subjectName varchar(45) not null,
  childSubjectName varchar(45) not null,
  primary key (id),
  foreign key fk_parentSubject(subjectName) references ParentSubject(subjectName) on delete cascade 
);


