package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		String board_pwd = request.getParameter("pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		String db_pwd = request.getParameter("db_pwd").trim();

		PrintWriter out = response.getWriter();

		if (board_pwd.equals(db_pwd)) {
			BoardDTO dto = new BoardDTO();

			dto.setBoard_no(board_no);
			dto.setBoard_title(board_title);
			dto.setBoard_cont(board_cont);

			BoardDAO dao = BoardDAO.getInstance();
			int res = dao.updateBoard(dto);

			if (res > 0) {
				out.println("<script>");
				out.println("alert('게시글 수정 성공!')");
				out.println("location.href='board_cont.do?no=" + board_no + "'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 수정 실패.')");
				out.println("history.back()");
				out.println("</script>");
			}

		} else { // 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
