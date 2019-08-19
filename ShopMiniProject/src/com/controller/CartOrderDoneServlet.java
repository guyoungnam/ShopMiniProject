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
import com.dto.OrderDTO;
import com.service.CartService;
import com.service.MemberService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/CartOrderDoneServlet")
public class CartOrderDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage= null;
		if(dto==null) {
			nextPage="LoginUIServlet";
	session.setAttribute("mesg", "¸Þ½ÃÁö");
		}else {
			String userid = dto.getUserid();
			String gCode=request.getParameter("gCode");
			String gName=request.getParameter("gName");
			String gPrice=request.getParameter("gPrice");
			String gSize=request.getParameter("gSize");
			String gColor=request.getParameter("gColor");
			String gAmount=request.getParameter("gAmount");
			String gImage=request.getParameter("gImage");
			String orderNum=request.getParameter("orderNum");
			String orderName=request.getParameter("orderName");
			String post=request.getParameter("post");
			String addr1=request.getParameter("addr1");
			String addr2=request.getParameter("addr2");
			String phone=request.getParameter("phone");
			String payMethod=request.getParameter("payMethod");
      
		OrderDTO xxx = new OrderDTO(0, userid, gCode, gName,
				Integer.parseInt(gPrice), gSize, gColor, 
				Integer.parseInt(gAmount), gImage,
				orderName, post, addr1, addr2, phone, payMethod, null);
		CartService service = new CartService();
		service.orderDone(xxx, Integer.parseInt(orderNum));
			
	request.setAttribute("oDTO", xxx);	
		nextPage="orderDone.jsp";
		}//end if
		
		RequestDispatcher dis =
				request.getRequestDispatcher(nextPage);
		dis.forward(request, response);;
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
