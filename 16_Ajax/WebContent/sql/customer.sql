create table customer(
	no number(5) unique, -- 고객 번호/중복불가
	id varchar2(20) primary key, -- 고객 아이디
	name varchar2(20) not null, -- 고객 이름
	age number(3), -- 고객 나이
	phone varchar2(20), -- 고객 전화번호
	addr varchar2(200) -- 고객 주소
);

-- 고객정보 저장
insert into customer values(1, 'hong', '홍길동', 27, '010-1111-1111', '서울시 마포구');
insert into customer values(2, 'lee', '이순신', 31, '010-2222-2222', '전라남도 신안군');
insert into customer values(3, 'yoo', '유관순', 19, '010-3333-3333', '충청남도 천안시');