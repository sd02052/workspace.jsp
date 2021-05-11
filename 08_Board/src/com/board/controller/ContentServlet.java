package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContentServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int board_no = Integer.parseInt(request.getParameter("no").trim());

		BoardDAO dao = BoardDAO.getInstance();

		dao.boardHit(board_no); // 글 조회수 증가시키는 메서드

		BoardDTO dto = dao.boardCont(board_no);

		request.setAttribute("cont", dto);

		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		rd.forward(request, response);

	}

}
