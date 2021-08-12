<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO boardVO = (BoardVO)request.getAttribute("boardVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath() %>/board/update">
	<table>
		<tr>
			<td>BoardNo</td>
			<td><%=boardVO.getBoardNo() %><input type="hidden" name="boardNo" value="<%=boardVO.getBoardNo() %>"></td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td><input type="text" name="boardTitle" value="<%=boardVO.getBoardTitle() %>"></td>
		</tr>
		<tr>
			<td>내용: </td>
			<td><input type="text" name="boardContent" value="<%=boardVO.getBoardContent() %>"></td>
		</tr>
	</table>
	<input type="submit" value="수정">
	</form>
</body>
</html>