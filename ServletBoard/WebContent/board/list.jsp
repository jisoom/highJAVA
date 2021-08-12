<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");

	String msg = request.getParameter("msg") == null? "" : request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
	<%
		int boardSize = boardList.size();
	
		if(boardSize > 0){
			for(int i=0; i<boardSize;i++){
	%>
		<tr>
			<td><%=boardList.get(i).getBoardNo() %></td>
			<td><a href="detail?boardNo=<%=boardList.get(i).getBoardNo() %>"><%=boardList.get(i).getBoardTitle() %></a></td>
			<td><%=boardList.get(i).getBoardWriter() %></td>
			<td><%=boardList.get(i).getBoardDate() %></td>
		</tr>
	<%		} 
		}else {
	%>
		<tr>
			<td colspan="4">게시글이 존재하지 않습니다.</td>
		</tr>
	<%		
		}
	%>
		<tr align="center">
			<td colspan="4"><a href="insert">[게시글 등록]</a></td>
		</tr>
	</table>
	<a href="/board/search"><button type="button">게시글 검색</button></a>
		<%
		if(msg.equals("성공")){	
	%>
	<script type="text/javascript">
		alert("정상적으로 처리되었습니다.");
	</script>
	<%
		}
	%>
</body>
</html>