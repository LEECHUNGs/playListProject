package edu.kh.playlist.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
	
	private int memberNo; // 회원 번호
	private String memberNm; // 회원 닉네임
	private String memberId; // 회원 아이디
}
