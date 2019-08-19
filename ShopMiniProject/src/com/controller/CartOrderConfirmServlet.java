package com.controller;

import java.io.IOException;
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


@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "메시지");
	      response.sendRedirect(nextPage);
		}else {
		
			//상품정보
			String num = request.getParameter("num");
			CartService cService = new CartService();
			CartDTO cDTO = cService.cartByNum(Integer.parseInt(num));
			
			//회원정보
			String userid =dto.getUserid();
			MemberService mService = new MemberService();
			MemberDTO mDTO = mService.mypage(userid);
			
			request.setAttribute("cDTO", cDTO);
			request.setAttribute("mDTO", mDTO);
			System.out.println("고고");
	
			
		nextPage="orderConfirm.jsp";
		RequestDispatcher dis =
				request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	 
		}//end if
	
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
