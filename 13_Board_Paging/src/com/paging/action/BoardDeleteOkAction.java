package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String board_pwd = request.getParameter("pwd").trim();

		BoardDAO dao = BoardDAO.getInstance();

		int res = dao.deleteBoard(board_no, board_pwd);
		
		// board 테이블의 글 번호를 수정하는 메서드 호출
		dao.updateNo(board_no);

		PrintWriter out = response.getWriter();

		if (res > 0) {
			out.println("<script>");
			out.println("alert('게시물 삭제 완료!')");
			out.println("location.href='board_list.do?page=" + page + "'");
			out.println("</script>");
		} else if (res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
