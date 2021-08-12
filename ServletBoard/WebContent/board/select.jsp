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
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>boardNo: </td>
			<td><%=boardVO.getBoardNo() %></td>
		</tr>
		<tr>
			<td>제목: </td>
			<td><%=boardVO.getBoardTitle()%></td>
		</tr>
		<tr>
			<td>내용: </td>
			<td><%=boardVO.getBoardContent() %></td>
		</tr>
		<tr>
			<td>작성자 :</td>
			<td><%=boardVO.getBoardWriter() %></td>
		</tr>
		<tr>
			<td>작성일: </td>
			<td><%=boardVO.getBoardDate() %></td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="list">[목록]</a>
			<a href="update?boardNo=<%=boardVO.getBoardNo() %>">[수정]</a>
			<a href="delete?boardNo=<%=boardVO.getBoardNo() %>">[삭제]</a>
			</td>
		</tr>
	</table>
</body>
</html>