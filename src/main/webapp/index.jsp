<%@ include file="common/navigation.jsp" %>
<%@ page import="com.zerobase.mission01.model.dto.WifiInfo" %>
<%@ page import="com.zerobase.mission01.controller.ApiServlet" %>
<%@ page import="com.zerobase.mission01.controller.HistoryServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.beans.Encoder" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ApiServlet api = new ApiServlet();
    HistoryServlet history = new HistoryServlet();

    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");

    List<WifiInfo> list = null;
    if (lat != null && lnt != null) {
        history.insertHistory(lat, lnt);
        list = api.getAroundList(lat, lnt);
    }
%>
        <form action="<%= ROOT %>" method="get" id="geoForm">
            <label for="lat">LAT: </label> <input type="text" id="lat" name="lat" placeholder="0.0" value="<%= lat != null ? lat : "" %>"> ,
            <label for="lnt">LNT: </label> <input type="text" id="lnt" name="lnt" placeholder="0.0" value="<%= lnt != null ? lnt : "" %>">
            <button type="button" onclick="getUserLocation()">내 위치 가져오기</button>
            <button type="button" onclick="return geoSubmit()">근처 WIFI 정보 보기</button>
        </form>

        <br/>
        <br/>

        <table>
            <thead>
                <tr>
                    <th>거리(KM)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>wifi접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (list == null || list.size() == 0) {
            %>
            <tr class="list-no-data">
                <% if (list == null) { %>
                <th colspan="17">위치 정보를 입력한 후에 조회해주세요</th>
                <% } else if (list.size() == 0){ %>
                <th colspan="17">Open API 와이파이 정보 가져오기를 눌러주세요</th>
                <% } %>
            </tr>
            <%
                } else {
                    for (WifiInfo i: list) {
            %>
            <tr>
                <td><%= String.format("%.6f", i.getDistance()) %></td>
                <td><%= i.getX_SWIFI_MGR_NO() %></td>
                <td><%= i.getX_SWIFI_WRDOFC() %></td>
                <td><a href="#" onclick="return go('<%= i.getX_SWIFI_MGR_NO() %>', '<%= i.getDistance() %>');"><%= i.getX_SWIFI_MAIN_NM() %></a></td>
                <td><%= i.getX_SWIFI_ADRES1() %></td>
                <td><%= i.getX_SWIFI_ADRES2() %></td>
                <td><%= i.getX_SWIFI_INSTL_FLOOR() %></td>
                <td><%= i.getX_SWIFI_INSTL_TY() %></td>
                <td><%= i.getX_SWIFI_INSTL_MBY() %></td>
                <td><%= i.getX_SWIFI_SVC_SE() %></td>
                <td><%= i.getX_SWIFI_CMCWR() %></td>
                <td><%= i.getX_SWIFI_CNSTC_YEAR() %></td>
                <td><%= i.getX_SWIFI_INOUT_DOOR() %></td>
                <td><%= i.getX_SWIFI_REMARS3() %></td>
                <td><%= i.getLAT() %></td>
                <td><%= i.getLNT() %></td>
                <td><%= i.getWORK_DTTM() %></td>
            </tr>
            <%      }
                }
            %>
            </tbody>
        </table>

        <br/>
        <br/>

        <script>
            function go(v, d) {
                location.href = "<%= ROOT %>detail/" + encodeURI(v) + "/" + d;
            }
            function success({ coords, timestamp }) {
                const latitude = coords.latitude;   // 위도
                const longitude = coords.longitude; // 경도

                document.getElementById("lat").value = latitude;
                document.getElementById("lnt").value = longitude;
            }

            function getUserLocation() {
                if (!navigator.geolocation) {
                    throw "위치 정보가 지원되지 않습니다.";
                }
                navigator.geolocation.getCurrentPosition(success);
            }

            function geoSubmit() {
                var lat = document.getElementById("lat").value;
                var lnt = document.getElementById("lnt").value;

                if (!lat || !lnt) {
                    alert("내 위치를 입력해 주세요")
                    return false;
                }

                if (!isFloat(lat)) {
                    alert("LAT에 올바른 실수를 입력해 주세요")
                    document.getElementById("lat").focus();
                    return false;
                }
                if (!isFloat(lnt)) {
                    alert("LNT에 올바른 실수를 입력해 주세요")
                    document.getElementById("lnt").focus();
                    return false;
                }

                if (lat && lnt) {
                    document.getElementById("geoForm").submit();
                    return true;
                }
            }
            function isFloat(value) {
                floatRegex = /^[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/;

                return floatRegex.test(value);
            }
        </script>
    </body>
</html>