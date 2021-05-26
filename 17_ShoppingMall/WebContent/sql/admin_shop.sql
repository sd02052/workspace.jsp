-- admin_shop 테이블 생성

create table admin_shop(
	admin_id varchar2(20) primary key,
	admin_pwd varchar2(20) not null,
	admin_name varchar2(30) not null,
	admin_email varchar2(100),
	admin_regdate date
);

-- 관리자 등록하기.
insert into admin_shop values('admin1', 'admin1', '관리자1', 'admin1@naver.com', sysdate);
