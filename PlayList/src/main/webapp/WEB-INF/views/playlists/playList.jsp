<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>재생 목록</title>
	<link rel="stylesheet" href="/resources/css/default.css">
	<script src="https://kit.fontawesome.com/2bc76866e9.js" crossorigin="anonymous"></script>
	
</head>
<body>
	<main>
		<section>
				<c:choose>
					<c:when test="${empty songList}">
						<span>재생목록</span>
						<ul>
							<c:forEach var="pl" items="${plList}">
								<li>
									<a href ="/playList?plNo=${pl.plNo}">${pl.plTitle}</a>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					
					<c:otherwise>
						<span>재생목록</span>
						<table>
							<tr>
								<th>순위</th>
								<th>노래 제목</th>
								<th>가수</th>
								<th>앨범</th>
								<th>좋아요</th>
								<th>재생목록에서 삭제</th>
								<c:forEach var="song" items="${songList}" varStatus="vs">
										<tr>
											<td>${vs.count}</td>
											<td>${song.songTitle}</td>
											<td>${song.songArtist}</td>
											<td>${song.songAlbum}</td>
											<td>${song.songLike}</td>
											<th>
												<a href="/playList/delete?songNo=${song.songNo}
														&&plNo=${plNo}"
													class="fa-solid fa-trash"
													onclick="return confirm('정말 삭제하시겠습니까?')">
												</a>
											</th>
			
										</tr>
								</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
		</section>

	</main>
	
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert('${message}');
	
		</script>
		
		<c:remove var="message"/>
	</c:if>
	
</body>
</html>