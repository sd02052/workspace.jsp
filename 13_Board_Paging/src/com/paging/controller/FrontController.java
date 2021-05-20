package com.paging.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.action.*;

public class FrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI();

		String path = request.getContextPath();

		String command = uri.substring(path.length() + 1);

		Action action = null;
		String viewPage = null;

		if (command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
		} else if (command.equals("board_write.do")) { // 데이터를 가져올 필요가 없다
			viewPage = "view/board_write.jsp"; // 바로 페이지 이동해주면 된다.
		} else if (command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
		} else if (command.equals("board_cont.do")) {
			action = new BoardContAction();
			action.execute(request, response);
			viewPage = "view/board_cont.jsp";
		} else if (command.equals("board_update.do")) {
			action = new BoardUpdateAction();
			action.execute(request, response);
			viewPage = "view/board_update.jsp";
		} else if (command.equals("board_update_ok.do")) {
			action = new BoardUpdateOkAction();
			action.execute(request, response);
		} else if (command.equals("board_delete.do")) {
			action = new BoardDeleteAction();
			action.execute(request, response);
			viewPage = "view/board_delete.jsp";
		} else if (command.equals("board_delete_ok.do")) {
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		} else if (command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "view/board_search.jsp";
		} else if (command.equals("board_searchCont.do")) {
			action = new BoardSearchContAction();
			action.execute(request, response);
			viewPage = "view/board_search_cont.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
