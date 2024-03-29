package edu.kh.playlist.song.controller;

import java.io.IOException;

import edu.kh.playlist.song.model.service.SongListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/song/insert")
public class InsertController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/song/insert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			SongListService service = new SongListService();
			
			String songTitle = req.getParameter("songTitle");
			String songArtist = req.getParameter("songArtist");
			String songAlbum = req.getParameter("songAlbum");
			
			int result = service.insert(songTitle, songArtist, songAlbum);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "노래" + songTitle + "이 추가되었습니다.");
				
				resp.sendRedirect("/");
				
			} else {
				session.setAttribute("message", "노래 추가 실패!!!");
				
				resp.sendRedirect("/");
			}
			
		} catch (Exception e) {
			System.out.println("[노래 추가 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
