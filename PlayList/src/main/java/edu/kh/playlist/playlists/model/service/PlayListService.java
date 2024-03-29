package edu.kh.playlist.playlists.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.playlist.playlists.model.dao.PlayListDAO;
import edu.kh.playlist.playlists.model.dto.PlayList;

import static edu.kh.todoList.common.JDBCTemplate.*;

public class PlayListService {
	private PlayListDAO dao = new PlayListDAO();

	/** 플레이리스트를 가져오는 서비스
	 * @param memberNo 
	 * @return plList
	 */
	public List<PlayList> selectAll(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<PlayList> plList = dao.selectAll(conn, memberNo);
		
		close(conn);
		
		return plList;
	}

	public int delete(String songNo, String plNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.delete(conn, songNo, plNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
}
