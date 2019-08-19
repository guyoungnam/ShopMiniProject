package com.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

public class MemberService {

	public void memberAdd(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao =new MemberDAO();
		try {
			dao.memberAdd(session,dto);
			session.commit();
		}finally {
			session.close();
		}
	}//end memberAdd

	public MemberDTO login(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDTO dto =null;
		MemberDAO dao =new MemberDAO();
		try {
			dto = dao.login(session,map);
		}finally {
			session.close();
		}
		return dto;
	}

	public MemberDTO mypage(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDTO dto =null;
		MemberDAO dao =new MemberDAO();
		try {
			dto = dao.mypage(session,userid);
		}finally {
			session.close();
		}
		return dto;
	}

	public void memberUpdate(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao =new MemberDAO();
		try {
			dao.memberUpdate(session,dto);
			session.commit();
		}finally {
			session.close();
		}
		
	}

}//end class
