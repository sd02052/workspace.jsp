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

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int board_no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = BoardDAO.getInstance();

		BoardDTO dto = dao.contentBoard(board_no);

		request.setAttribute("edit", dto);

		RequestDispatcher rd = request.getRequestDispatcher("view/board_edit.jsp");
		rd.forward(request, response);

	}

}
