package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.MembershipService;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.MemberService;


@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "로그인이 필요한 작업");
		response.sendRedirect(nextPage);
		//화면 url 변경하기 위해 
		}else {
			String num = request.getParameter("num");
			String gAmount = request.getParameter("gAmount");
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("num", Integer.parseInt(num));
			map.put("gAmount",Integer.parseInt(gAmount) );
			
			CartService service = new CartService();
			service.cartUpdate(map);
		
			
		 //응답이 없다
	
		}//end if
	
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
