<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 검색</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/board/search">
		<table>
			<tr>
				<td>제목 : </td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>작성자: </td>
				<td><input type="text" name="boardWriter"></td>
			</tr>
		</table>
		<input type="submit" value="검색">
	</form>
</body>
</html>