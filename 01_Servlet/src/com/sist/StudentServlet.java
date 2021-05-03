package com.sist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// method="post" 경우에는 한글이 깨짐 현상이 발생을 함.
		// 한글이 깨지지 않게 설정을 해 주어야 함.
		request.setCharacterEncoding("UTF-8");
		
		// 1단계 : Ex05.jsp 페이지에서 넘어온 데이터들 처리
		String hakbun = request.getParameter("hakbun");
		String name = request.getParameter("name");
		String[] major = request.getParameterValues("major");
		String phone = request.getParameter("phone");
		
		// 2단계 : 웹 브라우저에 요청한 결과를 화면에 보여주자.
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>");
		out.println("학번 : " + hakbun + "<br>");
		out.println("이름 : " + name + "<br>");
		out.println("전공과목 : ");
		for(int i = 0; i<major.length; i++) {
			out.println(major[i]+"&nbsp;&nbsp;&nbsp;");
		}
		out.println("<br>");
		out.println("연락처 : " + phone + "<br>");
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
	}

}
