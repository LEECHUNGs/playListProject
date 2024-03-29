package edu.kh.playlist.member.controller;

import java.io.IOException;

import edu.kh.playlist.member.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/signup")
public class SignupController extends HttpServlet{
	
	// 회원가입 버튼을 누르면 실행
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/signup.jsp").forward(req, resp);
	}
	
	// 회원가입 실행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String inputNm = req.getParameter("inputNm");
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			MemberService service = new MemberService();
			
			int result = service.signup(inputNm, inputId, inputPw);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "회원가입이 완료되었습니다!!");
				resp.sendRedirect("/");
				
			} else {
				session.setAttribute("message", "회원가입 오류;;");
				resp.sendRedirect( req.getHeader("referer") );
				
			}
			
		} catch (Exception e) {
			System.out.println("[회원 가입 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
