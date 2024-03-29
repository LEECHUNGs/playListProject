package edu.kh.playlist.playlists.model.dao;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.playlist.member.model.dao.MemberDAO;
import edu.kh.playlist.playlists.model.dto.PlayList;

public class PlayListDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public PlayListDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class
								.getResource("/edu/kh/playlist/sql/playList-sql.xml")
								.getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 플레이 리스트를 가져오는 SQL 수행 DAO
	 * @param conn
	 * @param memberNo 
	 * @return plList
	 */
	public List<PlayList> selectAll(Connection conn, int memberNo) throws Exception{
		List<PlayList> plList = new ArrayList<PlayList>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PlayList pl = new PlayList();
				
				pl.setPlNo(rs.getInt(1));
				pl.setPlTitle(rs.getString(2));
				
				plList.add(pl);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return plList;
	}

	public int delete(Connection conn, String songNo, String plNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plNo);
			pstmt.setString(2, songNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
