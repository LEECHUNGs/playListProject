package edu.kh.playlist.member.model.service;

import edu.kh.playlist.member.model.dto.Member;
import edu.kh.playlist.member.model.dao.MemberDAO;
import static edu.kh.todoList.common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 로그인 서비스
	 * @param inputId
	 * @param inputPw
	 * @return member
	 */
	public Member login(String inputId, String inputPw) throws Exception{
		Connection conn = getConnection();
		
		Member member = dao.login(conn, inputId, inputPw);
		
		close(conn);
		
		return member;
	}

	/** 회원 가입 서비스
	 * @param inputPw 
	 * @param inputId 
	 * @param member
	 * @return result
	 */
	public int signup(String inputNm, String inputId, String inputPw) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signup(conn, inputNm, inputId, inputPw);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회원 탈퇴 서비스
	 * @param memberNo
	 * @return result
	 */
	public int deleteMember(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteMember(conn, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	} 
}
