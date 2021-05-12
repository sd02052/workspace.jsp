package com.board1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String type = request.getParameter("find_field").trim();
		String name = request.getParameter("find_name").trim();

		BoardDAO dao = BoardDAO.getInstance();

		List<BoardDTO> list = dao.searchBoard(type, name);

		request.setAttribute("search", list);
		request.setAttribute("name", name);

		RequestDispatcher rd = request.getRequestDispatcher("view/board_search.jsp");
		rd.forward(request, response);

	}

}
