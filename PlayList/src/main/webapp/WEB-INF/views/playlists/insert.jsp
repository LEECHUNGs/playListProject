<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>재생목록 노래 추가</title>
</head>
<body>
	<main>
		<section>
			
			<c:if test="${!empty plList}">
				<span>${song.songTitle}</span>
				<span>추가할 재생목록을 선택하시오</span>
				<ul>
					<form action="/playLists/insert" method="post">
						<c:forEach var="pl" items="${plList}">
							<li>
								<label>
									<input type="radio" name="plNo" value="${pl.plNo}">
									${pl.plTitle}
								</label>
							</li>
						</c:forEach>
						
						<input type="text" hidden="hidden" name="songNo" value="${song.songNo}">
						
						<button>선택</button>
					</form>
				</ul>
			</c:if>
		</section>

	</main>
</body>
</html>