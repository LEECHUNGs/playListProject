package edu.kh.playlist.playlists.controller;

import java.io.IOException;

import edu.kh.playlist.playlists.model.service.PlayListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/playList/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String songNo = req.getParameter("songNo");
			String plNo = req.getParameter("plNo");
			
			PlayListService service = new PlayListService();
			int result = service.delete(songNo, plNo);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "노래가 재생목록에서 삭제되었습니다.");
				
				resp.sendRedirect("/playList");
				
			} else {
				session.setAttribute("message", "삭제에 실패하였습니다;;");
				
				resp.sendRedirect("/playList");
			}
			
		} catch (Exception e) {
			System.out.println("[재생목록에서 노래 제거 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
