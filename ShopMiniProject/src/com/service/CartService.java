package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

public class CartService {

	public void cartAdd(CartDTO xxx) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.cartAdd(session,xxx);
		  session.commit();
		}finally {
			session.close();
		}
		
	}//end cartAdd

	public List<CartDTO> cartList(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		List<CartDTO> list = null;
		try {
			list = dao.cartList(session,userid);
		}finally {
			session.close();
		}
		return list;
	}//end cartList

	public void cartUpdate(HashMap<String, Integer> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.cartUpdate(session,map);
		  session.commit();
		}finally {
			session.close();
		}
		
	}

	public void cartDel(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.cartDel(session,num);
		  session.commit();
		}finally {
			session.close();
		}
		
	}

	public void cartAllDel(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.cartAllDel(session,list);
		  session.commit();
		}finally {
			session.close();
		
	}

}

	public CartDTO cartByNum(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		CartDTO dto = null;
		try {
			dto = dao.cartByNum(session,num);
		}finally {
			session.close();
		}
		return dto;
	}
	
	
	public void orderDone(OrderDTO xxx, int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.orderDone(session,xxx);
		  dao.cartDel(session, num);
		  session.commit();
		}catch(Exception e) {
			session.rollback();
		}finally {
			session.close();
			
			//servlet throw 해서 로그인페이지, 정상로그인 페이지, 에러 페이지 3개 필요하다.
}
	}

	public List<CartDTO> orderAllConfirm(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		List<CartDTO> xxx = null;
		try {
			xxx = dao.orderAllConfirm(session,list);
		}finally {
			session.close();
		}
		return xxx;
	}
	
	public void orderAllDone(List<OrderDTO> oList, List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDAO dao =new CartDAO();
		try {
		  dao.orderAllDone(session,oList);
		  dao.cartAllDel(session, list);
		  session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
}
}
}

