package com.paging.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardSearchContAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색 리스트에서 글제목을 클릭 시 상세 내용을 보여주는 클래스

		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		String search_field = request.getParameter("field").trim();
		String search_name = request.getParameter("name").trim();

		BoardDAO dao = BoardDAO.getInstance();

		BoardDTO dto = dao.getBoardCont(board_no);

		request.setAttribute("searchCont", dto);
		request.setAttribute("page", nowPage);
		request.setAttribute("field", search_field);
		request.setAttribute("name", search_name);

	}

}
