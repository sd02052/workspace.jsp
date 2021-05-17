package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		String board_pwd = request.getParameter("pwd").trim();
		String db_pwd = request.getParameter("db_pwd").trim();

		BoardDAO dao = BoardDAO.getInstance();

		PrintWriter out = response.getWriter();

		if (board_pwd.equals(db_pwd)) { // 입력 비밀번호와 DB 비밀번호 같을때
			int res = dao.deleteBoard(board_no);
			
			if (res > 0) {
				out.println("<script>");
				out.println("alert('게시글 삭제 성공!')");
				out.println("location.href='board_list.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 삭제 실패.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else { // 비밀번호 다를때
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
