<%@ include file="../common/navigation.jsp" %>
<%@ page import="com.zerobase.mission01.model.dto.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<table>
    <colgroup>
        <col style="width: 10%">
        <col style="width: 30%">
        <col style="width: 30%">
        <col style="width: 20%">
        <col style="width: 10%">
    </colgroup>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<History> list = (List<History>) request.getAttribute("list");
        if (list == null || list.size() == 0) {
    %>
    <tr class="list-no-data"><th colspan="5"> 히스토리가 없습니다. </th></tr>
    <%
    } else {
        int idx = list.size();
        for (History i: list) {
    %>
        <tr>
            <form method="post" action="history">
                <input type="hidden" name="idx" value="<%= i.getIdx() %>" />
                <td><%= idx-- %></td>
                <td><%= i.getLat() %></td>
                <td><%= i.getLnt() %></td>
                <td><%= i.getRegDateTime() %></td>
                <td><button type="submit" >삭제</button></td>
            </form>
        </tr>
    <%      }
    }
    %>
    </tbody>
</table>
</body>
</html>
