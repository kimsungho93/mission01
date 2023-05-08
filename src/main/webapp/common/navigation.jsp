<%@ include file="header.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" %>

<body>

    <h1><%= TITLE %></h1>

    <br/>

    <a href="<%= ROOT %>">홈</a><span> | </span>
    <a href="<%= ROOT %>history">위치 히스토리 목록</a><span> | </span>
    <a href="<%= ROOT %>wifiListUpdate">Open API 와이파이 정보 가져오기</a><span> | </span>
    <a href="<%= ROOT %>bookmark">북마크 보기</a><span> | </span>
    <a href="<%= ROOT %>bookmarkgroup">북마크 그룹 관리</a>

    <br/>
    <br/>