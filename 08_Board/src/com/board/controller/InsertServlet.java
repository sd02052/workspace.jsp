package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 입력폼 창에서 넘어온 데이터들을 DB에 저장하는 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 1단계 : 게시물 입력폼 페이지에서 넘어온 데이터 받아 주어야 함.
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();

		// 2단계 : DB에 전송할 객체인 BoardDTO 객체에 파라미터로 받은 데이터들을
		// setter() 메서드에 인자로 넘겨주자.

		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);

		// 3단계 : BoardDTO 객체를 DB에 전송을 해 주어야 함.
		// BoardDAO 객체 생성 후 해당 메서드의 인자로 dto 객체의
		// 주소값을 넘겨주면 됨.
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.insertBoard(dto);

		PrintWriter out = response.getWriter();

		if (res > 0) {
			out.println("<script>");
			out.println("alert('게시물 등록 완료!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
