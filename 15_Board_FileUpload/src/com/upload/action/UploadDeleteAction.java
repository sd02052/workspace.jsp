package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		
		request.setAttribute("no", upload_no);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/upload_delete.jsp");
		
		return forward;
	}

}
