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
		<section class="songInsert-section">
			<form action="/song/insert" method="post">
				<div>
					<p>노래 제목</p>
					<input type="text" name="songTitle" required>
				</div>
	
				<div>
					<p>가수</p>
					<input type="text" name="songArtist" required>
				</div>
	
				<div>
					<p>앨범</p>
					<input type="text" name="songAlbum" required>
				</div>

				<button>추가하기</button>
			</form>
		</section>

	</main>
	
</body>
</html>