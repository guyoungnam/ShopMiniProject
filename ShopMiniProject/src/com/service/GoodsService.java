package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.GoodsDAO;
import com.dto.GoodsDTO;

public class GoodsService {

	public List<GoodsDTO> goodsList(String gCategory) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<GoodsDTO> list = null;
		GoodsDAO dao= new GoodsDAO();
		try {
			list = dao.goodsList(session,gCategory);
		}finally {
			session.close();
		}
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		SqlSession session = MySqlSessionFactory.getSession();
		GoodsDTO dto = null;
		GoodsDAO dao= new GoodsDAO();
		try {
			dto = dao.goodsRetrieve(session,gCode);
		}finally {
			session.close();
		}
		return dto;
	}

}
