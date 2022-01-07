--비밀번호 확인
select * from member;

-- 권한테이블 생성
--ROLE-USER 회원 공통권한
--ROLE_ADMIN 관리자 권한

create table authority (
    member_id varchar2(20),
    authority varchar2(20),
    constraint pk_authority primary key(member_id, authority),
    constraint fk_authority_member_id foreign key(member_id) references member(id)
);

--모든 사용자에게 공통권한 ROLE_USER 부여
select * from member;
insert into authority values('abcde', 'ROLE_USER');
insert into authority values('qwerty', 'ROLE_USER');
insert into authority values('admin', 'ROLE_USER');
insert into authority values('admin', 'ROLE_ADMIN');
insert into authority values('honggd', 'ROLE_USER');

select * from authority;

--회원가입시, member/authority에 각각 insert해야한다.(transaction처리 필수)

--회원정보 조회(권한포함)
--권한1개면 1행, 권한2개 2행되도록 join query

select * from member m left join authority a on m.id = member_id;
select * from member;
commit;




