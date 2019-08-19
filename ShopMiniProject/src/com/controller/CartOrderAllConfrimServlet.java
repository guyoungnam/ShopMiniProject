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
	session.setAttribute("mesg", "�޽���");
	      response.sendRedirect(nextPage);
		}else {
		
			//üũ�� �ѹ������� �����Ϳ��� ��������
			
			String [] checks = request.getParameterValues("check");
			List<String> list = Arrays.asList(checks);
			
			//��ǰ ����
			//��ǰ�� �ϳ��� �ƴ� ������
			CartService service = new CartService();
			List<CartDTO> cList = service.orderAllConfirm(list);
			
			//ȸ������
			
			String userid =dto.getUserid();
			MemberService mService = new MemberService();
			MemberDTO mDTO = mService.mypage(userid);
			
			request.setAttribute("cList", cList);
			request.setAttribute("mDTO", mDTO);
			System.out.println("���");
			
			
	
			
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
