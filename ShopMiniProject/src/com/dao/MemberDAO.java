package com.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;
		
public class MemberDAO {

	public void memberAdd(SqlSession session, MemberDTO dto) {
		int n = session.insert("MemberMapper.memberAdd", dto);
	}

	public MemberDTO login(SqlSession session, HashMap<String, String> map) {
		MemberDTO dto =
				session.selectOne("MemberMapper.login", map);
		return dto;
	}

	public MemberDTO mypage(SqlSession session, String userid) {
		MemberDTO dto =
				session.selectOne("MemberMapper.mypage", userid);
		return dto;
	}

	public void memberUpdate(SqlSession session, MemberDTO dto) {
		int n = 
		session.update("MemberMapper.memberUpdate", dto);
		
	}

}
