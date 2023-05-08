<%@ page import="com.zerobase.mission01.model.dto.Bookmark" %>
<%@ include file="../common/navigation.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Bookmark b = (Bookmark) request.getAttribute("data"); %>
        북마크를 삭제하시겠습니까?
        <table>
            <colgroup>
                <col style="width: 10%">
                <col style="width: 90%">
            </colgroup>
            <tr>
                <th>북마크 이름</th>
                <td><%= b.getName() %></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%= b.getNameWifi() %></td>
            </tr>
            <tr>
                <th>등록일자</th>
                <td><%= b.getRegDatetime() %></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <a href="<%= ROOT %>bookmark">돌아가기</a> | <button onclick="bmSubmit();">삭제</button>
                </td>
            </tr>
        </table>
        <form action="<%= ROOT %>bookmark/delete" method="post" id="deleteForm">
            <input type="hidden" name="idx" value="<%= b.getIdx() %>">
        </form>
        <script>
            function bmSubmit() {
                alert("북마크 정보를 삭제하였습니다.");
                document.getElementById('deleteForm').submit();
            }
        </script>
    </body>
</html>