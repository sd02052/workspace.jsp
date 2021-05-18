package com.paging.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));

		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.getBoardCont(board_no);

		request.setAttribute("dto", dto);
		request.setAttribute("page", page);
	}

}
