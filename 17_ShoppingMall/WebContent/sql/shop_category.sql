-- shop_category 테이블 생성
create table shop_category(
	category_num number(4) primary key, -- 카테고리 번호
	category_code varchar2(10) not null, -- 카테고리 코드
	category_name varchar2(100) not null -- 카테고리 이름
);

1	00010000	전자제품	N
2	00010001	TV	N
3	00010002	컴퓨터	N
4	00010003	MP3	N
5	00010004	에어컨	N
6	00020000	의류	N
7	00020001	남방	N
8	00020002	속옷	N
9	00020003	바지	N
10	00030000	도서	N
11	00030001	컴퓨터도서	N
12	00030002	소설	N