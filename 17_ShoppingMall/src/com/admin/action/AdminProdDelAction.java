package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;

public class AdminProdDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int product_num = Integer.parseInt(request.getParameter("pnum").trim());

		ProductDAO dao = ProductDAO.getInstance();
		int res = dao.deleteProduct(product_num);

		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();

		if (res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_prod_list.do");
		} else {
			out.println("<script>");
			out.println("alert('상품 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
