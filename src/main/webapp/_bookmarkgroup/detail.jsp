<%@ page import="com.zerobase.mission01.model.dto.BookmarkGroup" %>
<%@ include file="../common/navigation.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String cmd = (String) request.getAttribute("cmd"); %>
<% BookmarkGroup bmg = (BookmarkGroup) request.getAttribute("bmg"); %>
        <form action="<%= ROOT %>bookmarkgroup" method="post" id="bmgForm">
            <input type="hidden" name="idx" value="<%= cmd.equals("update") ? bmg.getIdx() : "" %>">
            <input type="hidden" name="cmd" value="<%= cmd %>">
            <table>
                <colgroup>
                    <col style="width: 10%">
                    <col style="width: 90%">
                </colgroup>
                <tr>
                    <th>북마크 이름</th>
                    <td><input type="text" name="name" id="bmgName" value="<%= cmd.equals("update") ? bmg.getName() : "" %>"></td>
                </tr>
                <tr>
                    <th>순서</th>
                    <td><input type="text" name="order" id="order" value="<%= cmd.equals("update") ? bmg.getMyOrder() : "" %>"></td>
                </tr>
            </table>
            <div style="text-align: center">
                <% if (cmd.equals("add")) { %>
                <button onclick="return bmgSubmit()">추가</button>
                <% } else { %>
                <a href="<%= ROOT %>bookmarkgroup">돌아가기</a><span> | </span><button onclick="return bmgSubmit()">수정</button>
                <% } %>
            </div>
        </form>
        <script>
            function bmgSubmit() {
                var name = document.getElementById("bmgName").value.replace(" ", "");
                var id = document.getElementById("order").value;

                if (!name) {
                    alert("이름을 입력해 주세요")
                    return false;
                }
                if (!id) {
                    alert("순서를 입력해 주세요")
                    return false;
                }
                let check = /^[0-9]+$/;
                if (!check.test(id)) {
                    alert("숫자만 입력해 주세요")
                    return false;
                }

                if (name && id) {
                    document.getElementById("bmgForm").submit();
                    return true;
                }
            }
        </script>
    </body>
</html>