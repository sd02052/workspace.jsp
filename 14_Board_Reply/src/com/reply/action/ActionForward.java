package com.reply.action;

/*
 * ActionForward 클래스
 * 1. Action 인터페이스에서 리턴타입(반환형)으로 사용될 클래스.
 * 2. 클래스의 구성 요소
 * 		1) isRedirect() 멤버 - boolean 타입
 * 			- *.do 페이지(true)
 * 			- *.jsp 페이지(false)
 * 		2) path 멤버 - String 타입
 * 			- 파일 경로 지정
 * 
 */
public class ActionForward {

	private boolean ieRedirect;
	private String path;

	public boolean isIeRedirect() {
		return ieRedirect;
	}

	public void setIeRedirect(boolean ieRedirect) {
		this.ieRedirect = ieRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
