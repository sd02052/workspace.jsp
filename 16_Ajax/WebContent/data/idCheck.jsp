<%@page import="java.io.PrintWriter"%>
<%@page import="com.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String userId = request.getParameter("paramId").trim();
	
	MemberDAO dao = MemberDAO.getInstance();
	
	int res = dao.checkMemberId(userId);
	System.out.println(res);
	// ajax에게 응답을 해 주면 됨.

	out.println(res);
%>