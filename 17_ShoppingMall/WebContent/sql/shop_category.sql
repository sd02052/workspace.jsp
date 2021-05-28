-- shop_category 테이블 생성
create table shop_category(
	category_num number(4) primary key, -- 카테고리 번호
	category_code varchar2(10) not null, -- 카테고리 코드
	category_name varchar2(100) not null -- 카테고리 이름
);

1	E0010001	냉장고
2	E0010002	김치냉장고
3	E0010003	건조기
4	E0010004	전기밥솥
5	E0020001	세탁기
6	E0020002	청소기
7	E0020003	정수기
8	E0020004	전자레인지
9	E0030001	공기청정기