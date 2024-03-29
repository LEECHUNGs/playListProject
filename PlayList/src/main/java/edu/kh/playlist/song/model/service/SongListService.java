package edu.kh.playlist.song.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.playlist.song.dto.Song;
import edu.kh.playlist.song.model.dao.SongListDAO;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class SongListService {
	
	private SongListDAO dao = new SongListDAO();

	/** 노래 목록 요청 서비스
	 * @return songList
	 */
	public List<Song> selectAll() throws Exception{
		Connection conn = getConnection();
		
		List<Song> songList = dao.selectAll(conn);
		
		close(conn);
		
		return songList;
	}

	/** 노래 추가 서비스
	 * @param songTitle
	 * @param songArtist
	 * @param songAlbum
	 * @return
	 */
	public int insert(String songTitle, String songArtist, String songAlbum) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insert(conn, songTitle, songArtist, songAlbum);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 수정할 노래 탐색 서비스
	 * @param songNo
	 * @return song
	 */
	public Song selectOne(String songNo) throws Exception{
		Connection conn = getConnection();
		
		Song song = dao.selectOne(conn, songNo);
		
		return song;
	}

	/** 노래 수정 서비스
	 * @param song
	 * @return result
	 */
	public int update(Song song) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.update(conn, song);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 노래 삭제 서비스
	 * @param songNo
	 * @return result
	 */
	public int delete(int songNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.delete(conn, songNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


}
