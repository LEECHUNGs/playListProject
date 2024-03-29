package edu.kh.playlist.song.controller;

import java.io.IOException;

import edu.kh.playlist.song.model.service.SongListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/song/delete")
public class DeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			SongListService service = new SongListService();
			
			int songNo = Integer.parseInt(req.getParameter("songNo"));
			
			int result = service.delete(songNo);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "노래가 삭제되었습니다.");
				
				resp.sendRedirect("/song/selectAll");
				
			} else {
				session.setAttribute("message", "노래 삭제 실패!!!");
				
				resp.sendRedirect( req.getHeader("referer"));
			}
			
		} catch (Exception e) {
			System.out.println("[노래 삭제 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
