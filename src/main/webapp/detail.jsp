
<%@ page import="com.zerobase.mission01.model.dto.WifiInfo" %>
<%@ page import="com.zerobase.mission01.model.dto.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ include file="common/navigation.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    WifiInfo info = (WifiInfo) request.getAttribute("wifiInfo");
    List<BookmarkGroup> list = (List<BookmarkGroup>) request.getAttribute("bmgList");
%>
        <form method="post" action="<%= ROOT %>bookmark/add" id="selectForm">
            <input type="hidden" name="idxBmg" id="idxBmg" value=""/>
            <input type="hidden" name="idxWifi" id="idxWifi" value="<%= info.getX_SWIFI_MGR_NO() %>"/>
            <select id="selectBookmarkGroup" name="selectBookmarkGroup" onchange="selectedBookmarkGroup(this)">
                <option value="">북마크 그룹이름 선택</option>
                <% if (list != null && list.size() > 0) { %>
                <%  for (BookmarkGroup bg: list) { %>
                <option value="<%= bg.getIdx() %>"><%= bg.getName()%></option>
                <%  } %>
                <% } %>
            </select>
            <button onclick="return save();">북마크 추가하기</button>
        </form>

        <table>
            <colgroup>
                <col style="width: 20%">
                <col style="width: 80%">
            </colgroup>
            <tr>
                <th>거리(Km)</th>
                <td><%= String.format("%.6f", info.getDistance()) %></td>
            </tr>
            <tr>
                <th>관리번호</th>
                <td><%= info.getX_SWIFI_MGR_NO() %></td>
            </tr>
            <tr>
                <th>자치구</th>
                <td><%= info.getX_SWIFI_WRDOFC() %></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%= info.getX_SWIFI_MAIN_NM() %></td>
            </tr>
            <tr>
                <th>도로명주소</th>
                <td><%= info.getX_SWIFI_ADRES1() %></td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td><%= info.getX_SWIFI_ADRES2() %></td>
            </tr>
            <tr>
                <th>설치위치(층)</th>
                <td><%= info.getX_SWIFI_INSTL_FLOOR() %></td>
            </tr>
            <tr>
                <th>설치유형</th>
                <td><%= info.getX_SWIFI_INSTL_TY() %></td>
            </tr>
            <tr>
                <th>설치기관</th>
                <td><%= info.getX_SWIFI_INSTL_MBY() %></td>
            </tr>
            <tr>
                <th>서비스구분</th>
                <td><%= info.getX_SWIFI_SVC_SE() %></td>
            </tr>
            <tr>
                <th>망종류</th>
                <td><%= info.getX_SWIFI_CMCWR() %></td>
            </tr>
            <tr>
                <th>설치년도</th>
                <td><%= info.getX_SWIFI_CNSTC_YEAR() %></td>
            </tr>
            <tr>
                <th>실내외구분</th>
                <td><%= info.getX_SWIFI_INOUT_DOOR() %></td>
            </tr>
            <tr>
                <th>WIFI접속환경</th>
                <td><%= info.getX_SWIFI_REMARS3() %></td>
            </tr>
            <tr>
                <th>X좌표</th>
                <td><%= info.getLAT() %></td>
            </tr>
            <tr>
                <th>Y좌표</th>
                <td><%= info.getLNT() %></td>
            </tr>
            <tr>
                <th>작업일자</th>
                <td><%= info.getWORK_DTTM() %></td>
            </tr>
        </table>

        <script>
            var selectValue = "";
            function selectedBookmarkGroup(target) {
                selectValue = target.value;
            }
            function save() {
                if (selectValue === "") {
                    alert("선택된 북마크 그룹이 없습니다.")
                    return false;
                }

                alert("북마크 정보를 추가하였습니다.");
                document.getElementById("idxBmg").value = selectValue;
                document.getElementById("selectForm").submit();
            }
        </script>
    </body>
</html>
