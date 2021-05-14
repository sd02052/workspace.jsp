package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberJoinAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 등록 폼 페이지로 이동
				
		return "view/member_join.jsp";
	}

}
