package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.CategoryDAO;
import com.product.model.CategoryDTO;
import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int product_num = Integer.parseInt(request.getParameter("num"));

		ProductDAO pdao = ProductDAO.getInstance();
		ProductDTO pdto = pdao.contentProduct(product_num);

		CategoryDAO cdao = CategoryDAO.getInstance();
		List<CategoryDTO> list = cdao.getCategoryList();
		
		request.setAttribute("edit", pdto);
		request.setAttribute("List", list);

		RequestDispatcher rd = request.getRequestDispatcher("view/product_edit.jsp");
		rd.forward(request, response);
	}

}
