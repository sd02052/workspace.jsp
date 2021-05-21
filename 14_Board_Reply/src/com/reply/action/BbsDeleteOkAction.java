package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;

public class BbsDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int page = Integer.parseInt(request.getParameter("page").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		String board_pwd = request.getParameter("pwd").trim();
		int board_group = Integer.parseInt(request.getParameter("group").trim());
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();

		if (db_pwd.equals(board_pwd)) {
			BbsDAO dao = BbsDAO.getInstance();
			int res = dao.deleteBbs(board_no, board_group);

			if (res > 0) {
				dao.updateNo(board_no);

				forward.setRedirect(true);
				forward.setPath("bbs_list.do?page=" + page);
			} else if (res == -1) {
				out.println("<script>");
				out.println("alert('답글 삭제 후 원글을 삭제해 주세요.')");
				out.println("location.href='bbs_cont.do?no=" + board_no + "&page=" + page+"'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시물 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
