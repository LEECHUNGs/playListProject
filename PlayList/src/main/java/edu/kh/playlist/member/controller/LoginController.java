package edu.kh.playlist.member.controller;

import java.io.IOException;

import edu.kh.playlist.member.model.dto.Member;
import edu.kh.playlist.member.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 입력받은 id/pw 가져옴
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			// DB에 id/pw 대조
			MemberService service = new MemberService();
			Member member = service.login(inputId, inputPw);
			
			HttpSession session = req.getSession();
			if(member != null) { // 로그인 성공
				// 로그인 세션 생성
				session.setAttribute("loginMember", member);
				session.setMaxInactiveInterval(60 * 60);
				
				resp.sendRedirect("/");
				
			} else { // 로그인 실패
				// 로그인 실패 메시지
				session.setAttribute("message", "아이디/비밀번호가 틀렸습니다!");
				
				resp.sendRedirect("/");
			}
			
		} catch (Exception e) {
			System.out.println("[로그인 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
