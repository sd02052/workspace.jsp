package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.EmpDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 삭제라는 글자를 클릭 시 사원번호를 가지고 DB에 가서
		// 사원번호에 해당하는 사원정보를 삭제하는 작업.

		int empno = Integer.parseInt(request.getParameter("no"));

		EmpDAO dao = new EmpDAO();
		int res = dao.deleteEmp(empno);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		if(res>0) { // 사원 삭제 성공
			response.sendRedirect("select");
		} else { // 사원 삭제 실패
			out.println("<script>");
			out.println("alert('사원 삭제 실패')");
			out.println("history.back()"); // 이전 페이지로 이동하라는 명령어
			out.println("</script>");
		}
	}

}
