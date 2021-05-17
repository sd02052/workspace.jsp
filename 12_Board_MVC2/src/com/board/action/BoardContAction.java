package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수 증가 메서드 호출
		dao.boardHit(board_no);
		
		BoardDTO dto = dao.boardCont(board_no);
		
		request.setAttribute("Cont", dto);
		
		return "view/board_content.jsp";
	}

}
