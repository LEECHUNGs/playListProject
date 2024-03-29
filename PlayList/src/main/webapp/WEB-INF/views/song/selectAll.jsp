<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>노래 목록</title>
	<link rel="stylesheet" href="/resources/css/default.css">
	<script src="https://kit.fontawesome.com/2bc76866e9.js" crossorigin="anonymous"></script>
</head>
<body>
	<main>
		<table>
			<tr>
				<th>순위</th>
				<th>노래 제목</th>
				<th>가수</th>
				<th>앨범</th>
				<th>좋아요</th>
				
				<c:choose>
					<c:when test="${sessionScope.loginMember.memberNo == 1}">
						<th>노래 삭제</th>
						<th>노래 수정</th>
					</c:when>
					
					<c:otherwise>
						<th>목록에 담기</th>
					</c:otherwise>
				</c:choose>
				
			</tr>

			<c:forEach var="song" items="${songList}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${song.songTitle}</td>
					<td>${song.songArtist}</td>
					<td>${song.songAlbum}</td>
					<td>${song.songLike}</td>
					
					<c:choose>
						<c:when test="${sessionScope.loginMember.memberNo == 1}">
							<th>
								<a href="/song/delete?songNo=${song.songNo}" 
									class="fa-solid fa-trash"
									onclick="return confirm('정말 삭제하시겠습니까?')">
								</a>
							</th>
							<th>
								<a href="/song/update?songNo=${song.songNo}" class="fa-solid fa-wrench"></a>
							</th>
						</c:when>
						
						<c:otherwise>
							<th>
								<a href="/playLists/insert?songNo=${song.songNo}" class="fa-solid fa-plus"></a>							
							</th>
						</c:otherwise>
					</c:choose>
					
				</tr>
			</c:forEach>

		</table>

	</main>
	
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert('${message}');
	
		</script>
		
		<c:remove var="message"/>
	</c:if>
	
</body>
</html>