package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

@WebServlet("/updateOk.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateOkServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int prod_num = Integer.parseInt(request.getParameter("prod_num").trim());
		String prod_category = request.getParameter("prod_category").trim();
		String prod_name = request.getParameter("prod_name").trim();
		String prod_code = request.getParameter("prod_code").trim();
		int prod_input = Integer.parseInt(request.getParameter("prod_input").trim());
		int prod_output = Integer.parseInt(request.getParameter("prod_output").trim());
		int prod_trans = Integer.parseInt(request.getParameter("prod_trans").trim());
		int prod_mileage = Integer.parseInt(request.getParameter("prod_mileage").trim());
		String prod_company = request.getParameter("prod_company").trim();

		ProductDTO dto = new ProductDTO();
		dto.setPnum(prod_num);
		dto.setCategory_fk(prod_category);
		dto.setProducts_name(prod_name);
		dto.setEp_code_fk(prod_code);
		dto.setInput_price(prod_input);
		dto.setOutput_price(prod_output);
		dto.setTrans_cost(prod_trans);
		dto.setMileage(prod_mileage);
		dto.setCompany(prod_company);

		ProductDAO dao = ProductDAO.getInstance();
		int res = dao.updateProduct(dto);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (res > 0) {
			out.println("<script>");
			out.println("alert('제품 정보 수정 완료!')");
			out.println("location.href='content.do?num=" + dto.getPnum() + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('제품 정보 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
