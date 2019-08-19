package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;



@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "·Î±×ÀÎ");
		}else {
			String gImage= request.getParameter("gImage");
			String gCode= request.getParameter("gCode");
			String gName= request.getParameter("gName");
			String gPrice= request.getParameter("gPrice");
			String gSize= request.getParameter("gSize");
			String gColor= request.getParameter("gColor");
			String gAmount= request.getParameter("gAmount");
			String userid = dto.getUserid();
			
	CartDTO xxx = new CartDTO();
		xxx.setgImage(gImage);
		xxx.setgCode(gCode);
		xxx.setgName(gName);
		xxx.setgPrice(Integer.parseInt(gPrice));
		xxx.setgSize(gSize);
		xxx.setgColor(gColor);
		xxx.setgAmount(Integer.parseInt(gAmount));
		xxx.setUserid(userid);	
		
		CartService service = new CartService();
		
		service.cartAdd(xxx);

		nextPage="CartListServlet?gCode="+gCode;
		
		}//end if
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
