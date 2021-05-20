package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 상세내역을 조회하는 클래스.

		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());

		BbsDAO dao = BbsDAO.getInstance();

		/// 조회수 증가 메서드
		dao.bbsHit(board_no);
		BbsDTO dto = dao.getBbsCont(board_no);

		request.setAttribute("cont", dto);
		request.setAttribute("page", nowPage);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_cont.jsp");

		return forward;
	}

}
