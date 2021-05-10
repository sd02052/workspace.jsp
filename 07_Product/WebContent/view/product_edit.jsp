<%@page import="com.product.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ProductDTO pdto = (ProductDTO) request.getAttribute("edit");
	List<CategoryDTO> list = (List<CategoryDTO>) request.getAttribute("List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="tomato">
		<h3>PRODUCTS 테이블 제품 상세 정보</h3>
		<hr width="50%" color="tomato">
		<br> <br>


		<form method="post" action="<%=request.getContextPath()%>/updateOk.do">
			<input type="hidden" name="num" value="<%=pdto.getPnum()%>">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>제품 카테고리</th>
					<td>
						<select name="prod_category">
							<%
								if (list.size() == 0) {
							%>
							<option value="">:::카테고리 없음:::</option>
							<%
								} else { // 데이터가 있는 경우
									for (int i = 0; i < list.size(); i++) {
										CategoryDTO cdto = list.get(i);
										if (pdto.getCategory_fk().equals(cdto.getCategory_code())) {
							%>
							<option value="<%=cdto.getCategory_code()%>" selected>
								<%=cdto.getCategory_name()%>[<%=cdto.getCategory_code()%>]
							</option>
							<%
								} else {
							%>
							<option value="<%=cdto.getCategory_code()%>" disabled>
								<%=cdto.getCategory_name()%>[<%=cdto.getCategory_code()%>]
							</option>
							<%
								}
									}
								}
							%>
					</select>
					</td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input type="text" name="prod_name"
						value="<%=pdto.getProducts_name()%>" readonly></td>
				</tr>
				<tr>
					<th>제품코드</th>
					<td><input type="text" name="prod_code"
						value="<%=pdto.getEp_code_fk()%>" readonly></td>
				</tr>
				<tr>
					<th>제품 입고가</th>
					<td><input type="text" name="prod_input"
						value="<%=pdto.getInput_price()%>"></td>
				</tr>
				<tr>
					<th>제품 출고가</th>
					<td><input type="text" name="prod_output"
						value="<%=pdto.getOutput_price()%>"></td>
				</tr>
				<tr>
					<th>배송 비용</th>
					<td><input type="text" name="prod_trans"
						value="<%=pdto.getTrans_cost()%>"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td><input type="text" name="prod_mileage"
						value="<%=pdto.getMileage()%>"></td>
				</tr>
				<tr>
					<th>제조사</th>
					<td><input type="text" name="prod_company"
						value="<%=pdto.getCompany()%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제품 수정">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시 작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>