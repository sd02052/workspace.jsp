package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String db_pwd = request.getParameter("db_pwd").trim();
		
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		String board_pwd = request.getParameter("pwd").trim();

		PrintWriter out = response.getWriter();

		if (db_pwd.equals(board_pwd)) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(board_no);
			dto.setBoard_title(board_title);
			dto.setBoard_cont(board_cont);
			dto.setBoard_pwd(board_pwd);

			BoardDAO dao = BoardDAO.getInstance();
			int res = dao.updateBoard(dto);

			if (res > 0) {
				out.println("<script>");
				out.println("alert('게시물 수정 완료!')");
				out.println("location.href='board_cont.do?no=" + board_no + "&page=" + page + "'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시물 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
