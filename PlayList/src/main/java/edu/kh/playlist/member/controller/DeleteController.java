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

@WebServlet("/member/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			MemberService service = new MemberService();
			int result = service.deleteMember(member.getMemberNo());
			
			if(result > 0) {
				session.removeAttribute("loginMember");
				session.setAttribute("message", "계정을 삭제하였습니다.");
				
				resp.sendRedirect("/");
				
			} else {
				if(member.getMemberNo() == 1) // 삭제할 계정이 관리자 계정일 때
					session.setAttribute("message", "관리자 계정은 삭제할 수 없습니다!!!");
				else
					session.setAttribute("message", "계정 삭제 실패!!!");
				
				resp.sendRedirect("/");
			}
			
		} catch (Exception e) {
			System.out.println("[회원 탈퇴 중 예외 발생]");
			e.printStackTrace();
		}
	}
}
