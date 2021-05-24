package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UploadDTO dto = new UploadDTO();

		// 첨부파일에 저장될 경로(위치)
		String saveFolder = "C:\\Users\\SOS\\git\\workspace.jsp\\15_Board_FileUpload\\WebContent\\upload";

		// 첨부파일 최대 크기
		int filesize = 10 * 1024 * 1024; // 10MB

		// 파일 업로드를 진행 시 이진파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, saveFolder, filesize, "UTF-8",
				new DefaultFileRenamePolicy());

		String upload_writer = multi.getParameter("writer").trim();
		String upload_title = multi.getParameter("title").trim();
		String upload_cont = multi.getParameter("cont").trim();
		String upload_pwd = multi.getParameter("pwd").trim();
		int upload_no = Integer.parseInt(multi.getParameter("no").trim());

		File upload_file = multi.getFile("file");

		if (upload_file != null) {
			// getName() : 첨부파일의 이름을 문자열로 반환해 주는 메서드
			String fileName = upload_file.getName();

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			// ..../upload/2021-05-24
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;

			// 날짜 폴더 만들자
			File path1 = new File(homedir);
			if (!path1.exists()) { // 해당 폴더가 존재하지 않는 경우
				path1.mkdirs(); // 실제로 폴더가 만들어짐.
			}

			// 파일을 만들어 보자
			// 작성자_파일명
			// ..../upload/2021-05-24/작성자_파일명
			String refileName = upload_writer + "_" + fileName;
			upload_file.renameTo(new File(homedir + "/" + refileName));

			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + refileName;
			dto.setUpload_file(fileDBName);
		}

		dto.setUpload_no(upload_no);
		dto.setUpload_writer(upload_writer);
		dto.setUpload_title(upload_title);
		dto.setUpload_cont(upload_cont);
		dto.setUpload_pwd(upload_pwd);

		UploadDAO dao = UploadDAO.getInstance();
		int res = dao.updateUpload(dto);

		PrintWriter out = response.getWriter();

		ActionForward forward = new ActionForward();

		if (res > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_cont.do?no=" + upload_no);
		} else if (res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
