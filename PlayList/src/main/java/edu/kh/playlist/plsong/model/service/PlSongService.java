package edu.kh.playlist.plsong.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.playlist.playlists.model.dto.PlayList;
import edu.kh.playlist.plsong.model.dao.PlSongDAO;
import edu.kh.playlist.plsong.model.dto.PlSong;
import edu.kh.playlist.song.dto.Song;

import static edu.kh.todoList.common.JDBCTemplate.*;

public class PlSongService {
	private PlSongDAO dao = new PlSongDAO();

	/** 재생목록에 노래 추가 서비스
	 * @param songNo
	 * @param plNo
	 * @return result
	 */
	public int insert(String songNo, String plNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insert(conn, songNo, plNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 선택 재생목록에 노래가 존재하는지 확인 서비스
	 * @param songNo
	 * @param plNo
	 * @return pl
	 */
	public PlSong check(String songNo, String plNo) throws Exception{
		Connection conn = getConnection();
		
		PlSong pl = dao.check(conn, songNo, plNo);
		
		close(conn);
		
		return pl;
	}

		/** 해당 회원의 재생목록 리턴 서비스
	 * @param memberNo
	 * @param plNo
	 * @return songList
	 */
	public List<Song> selectMember(int memberNo, String plNo) throws Exception{
		Connection conn = getConnection();
		
		List<Song> songList = dao.selectMember(conn, memberNo, plNo);
		
		close(conn);
		
		return songList;
	}
}
