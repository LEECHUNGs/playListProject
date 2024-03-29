package edu.kh.playlist.playlists.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.playlist.member.model.dto.Member;
import edu.kh.playlist.playlists.model.dto.PlayList;
import edu.kh.playlist.playlists.model.service.PlayListService;
import edu.kh.playlist.plsong.model.service.PlSongService;
import edu.kh.playlist.song.dto.Song;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/playList")
public class PlayListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String plNo = null;
		try {
			plNo = req.getParameter("plNo");
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			if(plNo == null) {
				// 플레이 리스트 가져오기
				PlayListService plService = new PlayListService();
				List<PlayList> plList = plService.selectAll(member.getMemberNo());
				
				req.setAttribute("plList", plList);
				
				req.getRequestDispatcher("/WEB-INF/views/playlists/playList.jsp").forward(req, resp);
				
			} else {
				PlSongService service = new PlSongService();
				List<Song> songList = service.selectMember(member.getMemberNo(), plNo);
				
				req.setAttribute("songList", songList);
				req.setAttribute("plNo", plNo);
				
				req.getRequestDispatcher("/WEB-INF/views/playlists/playList.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println("[재생목록 출력 중 예외 발생]");
			e.printStackTrace();
		}
		
	}
	
}
