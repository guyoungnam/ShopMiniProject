package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		MemberDTO dto = 
				(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "�α����� �ʿ��� �۾��Դϴ�.");
		}else {
			nextPage="mypage.jsp";
			//DB����
			String userid = dto.getUserid();
			MemberService service = 
					new MemberService();
			MemberDTO xxx = service.mypage(userid);
			session.setAttribute("login", xxx);
		}
		RequestDispatcher dis=
				request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
