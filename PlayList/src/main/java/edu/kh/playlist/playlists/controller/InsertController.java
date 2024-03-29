package edu.kh.playlist.playlists.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.playlist.member.model.dto.Member;
import edu.kh.playlist.playlists.model.dto.PlayList;
import edu.kh.playlist.playlists.model.service.PlayListService;
import edu.kh.playlist.plsong.model.dto.PlSong;
import edu.kh.playlist.plsong.model.service.PlSongService;
import edu.kh.playlist.song.dto.Song;
import edu.kh.playlist.song.model.service.SongListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/playLists/insert")
public class InsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String songNo = req.getParameter("songNo");
			
			// 노래 정보 가져오기
			SongListService service = new SongListService();
			Song song = service.selectOne(songNo);
			
			req.setAttribute("song", song);
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			// 플레이 리스트 가져오기
			PlayListService plService = new PlayListService();
			List<PlayList> plList = plService.selectAll(member.getMemberNo());
			
			req.setAttribute("plList", plList);
			
			req.getRequestDispatcher("/WEB-INF/views/playlists/insert.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[노래를 DB에서 탐색중 예외 발생]");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			
			String songNo = req.getParameter("songNo");
			String plNo = req.getParameter("plNo");
			
			PlSongService service = new PlSongService();
			
			// 중복 확인
			PlSong pl = service.check(songNo, plNo);
			
			if(pl == null) {
				session.setAttribute("message", "플레이리스트에 이미 노래가 존재합니다!!");
				
				resp.sendRedirect( "/song/selectAll" );
			} else {
				// 삽입
				int result = service.insert(songNo, plNo);
				
				
				if(result > 0) {
					session.setAttribute("message", "플레이리스트에 노래가 추가되었습니다.");
					
					resp.sendRedirect("/song/selectAll");
					
				} else {
					session.setAttribute("message", "노래를 삽입할 수 없습니다!!");
					
					resp.sendRedirect( req.getHeader("referer") );
				}
			}
			
		} catch (Exception e) {
			System.out.println("[재생목록에 노래 추가 중 예외 발생]");
			e.printStackTrace();
		}
	
	}
	
}
