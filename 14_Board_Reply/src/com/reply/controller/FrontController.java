package com.reply.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.action.Action;
import com.reply.action.ActionForward;

public class FrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 한글 깨짐에 대한 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// getRequestURI() : 현재 프로젝트명과 파일명을 문자열로 변환해 주는 메서드.
		// ==> 반환되는 문자열은 "/14_Board_Reply/*.do" 문자열로 반환을 해 줌.
		String uri = request.getRequestURI();
		System.out.println("uri >>> " + uri);

		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드.
		// ==> 반한되는 문자열은 "/14_Board_Reply" 문자열로 반환을 해 줌.
		String path = request.getContextPath();
		System.out.println("path >>> " + path);

		// command ==> *.do
		String command = uri.substring(path.length() + 1);
		System.out.println("command >>> " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();
		/*
		 * java.util.Properties 클래스
		 * - Properties 클래스는 HashTable의 하위 클래스.
		 * - 보통은 환경 변수 및 속성 값을 Properties 파일에 저장하여 
		 * 	쉽게 접근할 수 있는 장점이 있음.
		 * - Properties 파일은 일련의 키(key)-값(value)의 한 쌍으로 이루어져 있음.
		 * - 보통은 파일에 저장하여 사용을 함. 파일 이름을 *.properties으로 끝나게 함.
		 * - InputStream에 Properties 파일을 인자로 넣어서 그 스트림으로부터
		 * 	파일을 가져올 때 가장 많이 사용을 함. 인자로 들어온 Properties 파일을 읽게 됨.
		 * - 읽어들일 때 사용하는 메서드는 load() 메서드를 이용하여 파일을 읽어들이게 됨.
		 */
	}
}
