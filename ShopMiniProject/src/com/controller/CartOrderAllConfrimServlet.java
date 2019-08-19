package com.controller;

import java.io.IOException;
import java.util.Arrays;
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


@WebServlet("/CartOrderAllConfrimServlet")
public class CartOrderAllConfrimServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "메시지");
	      response.sendRedirect(nextPage);
		}else {
		
			//체크한 넘버값으로 데이터에서 가져오자
			
			String [] checks = request.getParameterValues("check");
			List<String> list = Arrays.asList(checks);
			
			//상품 정보
			//상품이 하나가 아닌 여러개
			CartService service = new CartService();
			List<CartDTO> cList = service.orderAllConfirm(list);
			
			//회원정보
			
			String userid =dto.getUserid();
			MemberService mService = new MemberService();
			MemberDTO mDTO = mService.mypage(userid);
			
			request.setAttribute("cList", cList);
			request.setAttribute("mDTO", mDTO);
			System.out.println("고고");
			
			
	
			
		nextPage="orderAllConfirm.jsp";
		RequestDispatcher dis =
				request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	 
		}//end if
	
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
