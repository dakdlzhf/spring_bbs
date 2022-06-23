<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<%
String root = request.getContextPath();
%>

<body>
	<!--상단메뉴-->
	<div class="container">

		<div class="page-header row">
			<div class="col-sm-4">
				<img src="<%=root%>/images/img_chania.jpg" class="img-responsive img-thumbnail" alt="Cinque Terre">
			</div>
			<div class="col-sm-8">
				<h1>Homepage</h1>
			</div>
		</div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="<%=root%>/index.jsp">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">MVC 실습 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<%=root%>/mvc/hello.do">인사말</a></li>
					<li><a href="<%=root%>/mvc/date.do">오늘의 날짜</a></li>
					<li><a href="<%=root%>/mvc/myinfo.do">나의정보</a></li>
					<li><a href="<%=root%>/mvc/team.do">팀목록</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<%=root%>/bbs/create">생성</a></li>
					<li><a href="<%=root%>/bbs/list">목록</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">주소록 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<%=root%>/addr/create">생성</a></li>
					<li><a href="<%=root%>/addr/list">목록</a></li>
				</ul></li>
			<li><a href="#">메뉴 2</a></li>
			<li><a href="#">메뉴 3</a></li>
		</ul>



	</div>
</body>
</html>