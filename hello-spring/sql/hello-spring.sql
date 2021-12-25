--========================================
--관리자계정  -spring계정 생성
--========================================
alter session set "_oracle_script" = true; -- 일반사용자 c## 접두어 없이 계정생성

create user spring
identified by spring
default tablespace users;

alter user spring quota unlimited on users;

grant connect, resource to spring;

--========================================
--spring계정
--========================================
--dev 테이블
create table dev(
    no number,
    name varchar2(50) not null,
    career number not null,
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(100) not null, -- vo String[] <---> varchar2 'java,c,js'
    constraint pk_dev_no primary key(no),
    constraint ck_dev_gender check(gender in ('M','F'))
);
create sequence seq_dev_no;

select * from dev;







