package edu.kh.playlist.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.playlist.member.model.dto.Member;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class
								.getResource("/edu/kh/playlist/sql/member-sql.xml")
								.getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 로그인용 SQL을 실행하는 DAO
	 * @param conn
	 * @param inputId
	 * @param inputPw
	 * @return member
	 */
	public Member login(Connection conn, String inputId, String inputPw) throws Exception{
		Member member = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setMemberNo(rs.getInt(1));
				member.setMemberId(rs.getString(2));
				member.setMemberNm(rs.getString(3));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	/** 회원가입용 SQL을 실행하는 DAO
	 * @param conn
	 * @param inputNm
	 * @param inputId
	 * @param inputPw
	 * @return result
	 */
	public int signup(Connection conn, String inputNm, String inputId, String inputPw) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("signup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			pstmt.setString(3, inputNm);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원탈퇴용 SQL을 실행하는 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 */
	public int deleteMember(Connection conn, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String spl = prop.getProperty("deleteMember");
			
			pstmt = conn.prepareStatement(spl);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
