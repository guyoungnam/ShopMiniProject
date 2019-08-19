package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.OrderDTO;

public class CartDAO {

	public void cartAdd(SqlSession session, CartDTO xxx) {
		
		int n= session.insert("CartMapper.cartAdd",xxx);
		
	}

	public List<CartDTO> cartList(SqlSession session, String userid) {
		List<CartDTO> list =
				session.selectList("CartMapper.cartList", userid);
		return list;
	}

	public void cartUpdate(SqlSession session, 
			HashMap<String, Integer> map) {
		int n= session.update("CartMapper.cartUpdate",map);
		
	}

	public void cartDel(SqlSession session, int num) {
		int n= session.delete("CartMapper.cartDel",num);
		
	}

	public void cartAllDel(SqlSession session, List<String> list) {
		int n= session.delete("CartMapper.cartAllDel",list);
		
	}

	public CartDTO cartByNum(SqlSession session, int num) {
		CartDTO dto = session.selectOne("CartMapper.cartByNum",num);
		return dto;
	}

	public void orderDone(SqlSession session, OrderDTO xxx) {
		
		int n= session.insert("CartMapper.orderDone",xxx);
		
	}

	public List<CartDTO> orderAllConfirm(SqlSession session, List<String> list) {
		List<CartDTO> xxx =
				session.selectList("CartMapper.orderAllConfirm", list);
		return xxx;
	}

	public void orderAllDone(SqlSession session, List<OrderDTO> oList) {
		int n= session.insert("CartMapper.orderAllDone",oList);
		
	}

}
