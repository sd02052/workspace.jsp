package com.paging.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 제목 클릭했을 때 get 방식으로 넘어온 글 번호에 해당하는 
		// 게시글을 조회하는 클래스
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardHit(board_no);
		
		BoardDTO dto = dao.getBoardCont(board_no);
		
		request.setAttribute("Cont", dto);
		request.setAttribute("page", nowPage);
	}

}
