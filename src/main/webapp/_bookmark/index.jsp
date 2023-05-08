<%@ page import="com.zerobase.mission01.model.dto.Bookmark" %>
<%@ page import="java.util.List" %>
<%@include file="../common/navigation.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
  <tr>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>와이파이명</th>
    <th>등록일자</th>
    <th>비고</th>
  </tr>
  <%
    List<Bookmark> list = (List<Bookmark>) request.getAttribute("list");
    if (list == null || list.size() == 0) {
  %>
  <tr class="list-no-data">
    <th colspan="5">등록된 북마크가 없습니다.</th>
  </tr>
  <%
  } else {
    for (Bookmark b: list) {
  %>
  <tr>
    <td><%= b.getIdx() %></td>
    <td><%= b.getName() %></td>
    <td><a href="<%= ROOT %>detail/<%= b.getIdxWifi() %>"><%= b.getNameWifi() %></a></td>
    <td><%= b.getRegDatetime() %></td>
    <td>
      <a href="<%= ROOT %>bookmark/delete?idx=<%= b.getIdx() %>">삭제</a>
    </td>
  </tr>
  <%
    }
  }
  %>
</table>
</body>
</html>