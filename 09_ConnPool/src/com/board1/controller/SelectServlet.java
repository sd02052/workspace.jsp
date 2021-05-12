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

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB에서 board 테이블의 게시글 전체 리스트를 조회해서
		// view page로 전송하는 작업
		
		int pageSize = 10; // 한 페이지에 출력할 레코드 수
		
		// 페이지 링크를 클릭한 번호 / 현재 페이지
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null){ // 클릭한게 없으면 1번 페이지
			pageNum = "1";
		}
		// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
		int currentPage = Integer.parseInt(pageNum);
		
		// 해당 페이지에서 시작할 레코드 / 마지막 레코드
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		BoardDAO dao = BoardDAO.getInstance();
		int count = dao.getCount(); // 데이터베이스에 저장된 총 갯수
		

		List<BoardDTO> boardList = dao.getList(startRow, endRow);

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		
		request.setAttribute("List", boardList);

		RequestDispatcher rd = request.getRequestDispatcher("view/board_list.jsp");
		rd.forward(request, response);
	}

}
