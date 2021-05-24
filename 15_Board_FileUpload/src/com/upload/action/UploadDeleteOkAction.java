package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		String upload_pwd = request.getParameter("pwd").trim();

		UploadDAO dao = UploadDAO.getInstance();

		UploadDTO dto = dao.uploadContent(upload_no);

		// 업로드된 파일까지 삭제
		String up = "C:\\Users\\SOS\\git\\workspace.jsp\\15_Board_FileUpload\\WebContent\\upload";

		// 업로드된 파일명 : /년-월-일/작성자_파일명
		String fileName = dto.getUpload_file();

		ActionForward forward = new ActionForward();

		PrintWriter out = response.getWriter();

		int res = 0;

		// 비밀번호가 틀린 경우
		if (!upload_pwd.equals(dto.getUpload_pwd())) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 비밀번호가 같은 경우
			res = dao.uploadDelete(upload_no);

			if (res > 0) {
				if (fileName != null) { // 첨부파일이 있는 경우
					File file = new File(up + fileName);
					file.delete(); // 기존 이진 파일 삭제하는 메서드
				}

				forward.setRedirect(true);
				forward.setPath("upload_list.do");
			} else {
				out.println("<script>");
				out.println("alert('게시물 삭제 실패.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}

		return forward;
	}

}
