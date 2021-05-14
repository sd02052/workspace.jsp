package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI();

		String path = request.getContextPath();

		String command = uri.substring(path.length() + 1);

		Action action = null;

		if (command.equals("board_list.do")) {
			action = new BoardListAction();
		} else if (command.equals("board_write.do")) {
			action = new BoardWriteAction();
		} else if ( command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
		}

		String path1 = action.execute(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);
	}
}
