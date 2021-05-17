package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String field = request.getParameter("search_field").trim();
		String name = request.getParameter("search_name").trim();

		BoardDAO dao = BoardDAO.getInstance();

		List<BoardDTO> list = dao.searchBoard(field, name);
		
		request.setAttribute("target", name);
		request.setAttribute("search", list);

		return "view/board_search.jsp";
	}

}
