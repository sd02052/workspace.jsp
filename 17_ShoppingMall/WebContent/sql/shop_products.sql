-- shop_products 테이블 생성

create table shop_products(
	pnum number(8) primary key,			-- 상품 번호
	pname varchar2(50) not null,		-- 상품명
	pcategory_fk varchar2(20) not null,	-- 카테고리 코드
	pcompany varchar2(50),				-- 상품 제조사
	pimage varchar2(50),				-- 상품 이미지(상품 파일명)
	pqty number(8) default 0,			-- 상품 수량
	price number(8) default 0,			-- 상품 가격
	pspec varchar2(20),					-- 상품 스펙
	pcontents varchar2(1000),			-- 상품 설명
	point number(8) default 0,			-- 상품 포인트
	pinputdate date						-- 상품 입고일
);

1	LG DIOS 김치냉장고	E0010002	LG	ca3069a131.jpg	30	3000000	hit	LG 인기 김치냉장고	25000	21/05/27
2	쿠쿠 전기밥솥	E0010004	쿠쿠	9862f63721.jpg	10	200000	recommand	쿠쿠 추천 전기밥솥	2000	21/05/27
3	삼성 청소기	E0020002	삼성	b09ba8873.jpg	20	450000	new	최신 청소기	4500	21/05/27
4	삼성 양문형 냉장고	E0010001	삼성	d315836f2.jpg	5	1500000	hit	인기 냉장고	15000	21/05/27
5	LG 침구겸용 청소기	E0020002	LG	c4f7c0b12.jpg	50	500000	recommand	LG 추천 청소기	5000	21/05/27
6	딤채 김치냉장고	E0010002	딤채	dd2526702.jpg	10	800000	new	최신 김치냉장고	8000	21/05/27
7	LG 트롬 세탁기	E0020001	LG	e0dc50402.jpg	15	1200000	recommand	트롬 세탁기	12000	21/05/27