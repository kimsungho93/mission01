<%@ include file="common/header.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" %>
    <body class="wifi-load">
        <h1><%= request.getAttribute("listCnt") %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
        <a href="<%= ROOT %>">홈으로 가기</a>
    </body>
</html>