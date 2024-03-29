<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/resources/css/default.css">
</head>
<body>
	<main>
		<section class="songUpdate-section">
			<form action="/song/update" method="post">
				<div>
					<p>노래 제목</p>
					<input type="text" name="songTitle" value="${song.songTitle}" required>
				</div>
	
				<div>
					<p>가수</p>
					<input type="text" name="songArtist" value="${song.songArtist}" required>
				</div>
	
				<div>
					<p>앨범</p>
					<input type="text" name="songAlbum" value="${song.songAlbum}" required>
				</div>
				
				<input type="text" name="songNo" value="${song.songNo}" hidden="hidden">

				<button>수정하기</button>
			</form>
		</section>

	</main>
	
</body>
</html>