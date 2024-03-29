package edu.kh.playlist.song.controller;

import java.io.IOException;

import edu.kh.playlist.song.dto.Song;
import edu.kh.playlist.song.model.service.SongListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/song/update")
public class UpdateController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String songNo = req.getParameter("songNo");
			
			SongListService service = new SongListService();
			Song song = service.selectOne(songNo);
			
			req.setAttribute("song", song);
			
			req.getRequestDispatcher("/WEB-INF/views/song/update.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[노래를 DB에서 탐색중 예외 발생]");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			SongListService service = new SongListService();
			
			int songNo = Integer.parseInt(req.getParameter("songNo"));
			String songTitle = req.getParameter("songTitle");
			String songArtist = req.getParameter("songArtist");
			String songAlbum = req.getParameter("songAlbum");
			
			Song song = new Song();
			song.setSongNo(songNo);
			song.setSongTitle(songTitle);
			song.setSongArtist(songArtist);
			song.setSongAlbum(songAlbum);
			
			int result = service.update(song);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "노래" + songTitle + "이 수정되었습니다.");
				
				resp.sendRedirect("/song/selectAll");
				
			} else {
				session.setAttribute("message", "노래 수정 실패!!!");
				
				resp.sendRedirect( req.getHeader("referer"));
			}
			
		} catch (Exception e) {
			System.out.println("[노래 수정 중 예외 발생]");
			e.printStackTrace();
		}
	}
}