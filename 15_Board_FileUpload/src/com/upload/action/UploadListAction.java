package com.upload.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		UploadDAO dao = UploadDAO.getInstance();
		List<UploadDTO> list = dao.getUploadList();

		request.setAttribute("List", list);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/upload_list.jsp");

		return forward;
	}

}
