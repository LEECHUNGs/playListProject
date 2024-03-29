package edu.kh.playlist.plsong.model.dao;

import static edu.kh.todoList.common.JDBCTemplate.close;

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
import edu.kh.playlist.plsong.model.dto.PlSong;
import edu.kh.playlist.song.dto.Song;

public class PlSongDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public PlSongDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class
								.getResource("/edu/kh/playlist/sql/plSong-sql.xml")
								.getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 재생목록 노래 추가 SQL 수행 DAO
	 * @param conn
	 * @param songNo
	 * @param plNo
	 * @return result
	 */
	public int insert(Connection conn, String songNo, String plNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plNo);
			pstmt.setString(2, songNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 선택 재생목록에 노래가 존재하는지 확인 SQL 수행 DAO
	 * @param conn
	 * @param songNo
	 * @param plNo
	 * @return pl
	 */
	public PlSong check(Connection conn, String songNo, String plNo) throws Exception{
		PlSong pl = null;
		
		try {
			String sql = prop.getProperty("check");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plNo);
			pstmt.setString(2, songNo);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getInt(1) == 0) {
				pl = new PlSong();
				
				pl.setPlNo(Integer.parseInt(plNo));
				pl.setSongNo(Integer.parseInt(songNo));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return pl;
	}
	
	/** 선택된 재생목록 노래를 출력하는 SQL 수행 DAO
	 * @param conn
	 * @param memberNo
	 * @param plNo
	 * @return songList
	 */
	public List<Song> selectMember(Connection conn, int memberNo, String plNo) throws Exception{
		List<Song> songList = new ArrayList<Song>();
		
		try {
			String sql = prop.getProperty("selectMember");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, plNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Song song = new Song();
				song.setSongNo(rs.getInt(1));
				song.setSongTitle(rs.getString(2));
				song.setSongArtist(rs.getString(3));
				song.setSongAlbum(rs.getString(4));
				song.setSongLike(rs.getInt(5));
				
				songList.add(song);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return songList;
	}
}
