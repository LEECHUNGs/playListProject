package edu.kh.playlist.song.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.playlist.song.dto.Song;
import edu.kh.playlist.song.model.service.SongListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/song/selectAll")
public class SelectAllController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			SongListService service = new SongListService();
			
			List<Song> songList = service.selectAll();
			
			if(!songList.isEmpty()) {
				req.setAttribute("songList", songList);
				
				req.getRequestDispatcher("/WEB-INF/views/song/selectAll.jsp").forward(req, resp);
				
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("message", "노래가 없습니다!! 노래를 추가해 주세요");
				
				resp.sendRedirect("/");
			}
			
			
		} catch (Exception e) {
			System.out.println("[노래 목록 조회중 예외 발생]");
			e.printStackTrace();
		}
	}
}
