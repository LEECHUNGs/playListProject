package edu.kh.playlist.song.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.playlist.member.model.dao.MemberDAO;
import edu.kh.playlist.song.dto.Song;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class SongListDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public SongListDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class
								.getResource("/edu/kh/playlist/sql/song-sql.xml")
								.getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 노래목록 조회 SQL 수행 DAO
	 * @param conn
	 * @return
	 */
	public List<Song> selectAll(Connection conn) throws Exception{
		List<Song> songList = new ArrayList<Song>();
		
		try {
			String sql = prop.getProperty("songList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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

	/** 노래 추가 SQL 수행 DAO
	 * @param conn
	 * @param songTitle
	 * @param songArtist
	 * @param songAlbum
	 * @return result
	 */
	public int insert(Connection conn, String songTitle, String songArtist, String songAlbum) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("songAdd");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, songTitle);
			pstmt.setString(2, songArtist);
			pstmt.setString(3, songAlbum);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 수정할 노래 탐색 SQL 수행 DAO
	 * @param conn
	 * @param songNo
	 * @return song
	 */
	public Song selectOne(Connection conn, String songNo) throws Exception{
		Song song = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, songNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				song = new Song();
				song.setSongNo(rs.getInt(1));
				song.setSongTitle(rs.getString(2));
				song.setSongArtist(rs.getString(3));
				song.setSongAlbum(rs.getString(4));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return song;
	}

	/** 노래 수정 SQL 수행 DAO
	 * @param conn
	 * @param song
	 * @return result
	 */
	public int update(Connection conn, Song song) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getSongTitle());
			pstmt.setString(2, song.getSongArtist());
			pstmt.setString(3, song.getSongAlbum());
			pstmt.setInt(4, song.getSongNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 노래 삭제 SQL 수행 DAO
	 * @param conn
	 * @param songNo
	 * @return result
	 */
	public int delete(Connection conn, int songNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, songNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
