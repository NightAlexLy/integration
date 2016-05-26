drop table if exists user; 

/*==============================================================*/ 
/* Table: user                                                  */ 
/*==============================================================*/ 
create table user_test 
( 
   id                   bigint AUTO_INCREMENT not null, 
   name                 varchar(24), 
   age                  int, 
   primary key (id) 
);