package com.board1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContentServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int board_no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = BoardDAO.getInstance();
		
		dao.hitBoard(board_no);

		BoardDTO dto = dao.contentBoard(board_no);

		request.setAttribute("cont", dto);

		response.setContentType("text/html; charset=UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		rd.forward(request, response);
	}

}
