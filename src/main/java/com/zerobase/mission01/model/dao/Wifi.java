package com.zerobase.mission01.model.dao;

import com.zerobase.mission01.model.dto.WifiInfo;
import com.zerobase.mission01.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Wifi {
    private final String TABLE = "TB_WIFI_INFO";

    private static Wifi wifi;
    private Wifi() {}
    public static Wifi getInstance() {
        if (wifi == null) {
            wifi = new Wifi();
        }
        return wifi;
    }

    public int insertAll(List<WifiInfo> list) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT OR REPLACE INTO " + TABLE + " (  X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) VALUES");
        for (WifiInfo wifiInfo : list) {
            sb.append(" (\"").append(wifiInfo.getX_SWIFI_MGR_NO()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_WRDOFC()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_MAIN_NM()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_ADRES1()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_ADRES2()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_INSTL_FLOOR()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_INSTL_TY()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_INSTL_MBY()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_SVC_SE()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_CMCWR()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_CNSTC_YEAR()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_INOUT_DOOR()).append("\", \"")
                    .append(wifiInfo.getX_SWIFI_REMARS3()).append("\", ")
                    .append(wifiInfo.getLAT()).append(", ")
                    .append(wifiInfo.getLNT()).append(", \"")
                    .append(wifiInfo.getWORK_DTTM()).append("\") ")
                    .append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        try {
            return DB.getStmt().executeUpdate(sb.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WifiInfo select(String idx) {
        WifiInfo wifiInfo = new WifiInfo();
        String sb = " SELECT * " +
                " FROM " + TABLE +
                " WHERE X_SWIFI_MGR_NO = ? ;";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sb)) {
            pstmt.setString(1, idx);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                wifiInfo = getResultSetTo(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wifiInfo;
    }

    public List<WifiInfo> selectAll(double lat, double lnt) {
        List<WifiInfo> list = new ArrayList<>();
        String sb = "SELECT " +
                "round(6371 * acos(                                                                                                                                  " +
                "    CASE                                                                                                                                            " +
                "        WHEN cos( radians(?) ) * cos( radians(LAT) ) * cos( radians(LNT) - radians(?) ) + sin( radians(?) ) * sin( radians(LAT) ) > 1               " +
                "        THEN cos( radians(?) ) * cos( radians(LAT) ) * cos( radians(LNT) - radians(?) ) + sin( radians(?) ) * sin( radians(LAT) ) - 0000000000000002" +
                "        ELSE cos( radians(?) ) * cos( radians(LAT) ) * cos( radians(LNT) - radians(?) ) + sin( radians(?) ) * sin( radians(LAT) )                   " +
                "    END                                                                                                                                             " +
                "    ), 6                                                                                                                                            " +
                ") as DISTANCE,                                                                                                                                      " +
                "X_SWIFI_MGR_NO, " +
                "X_SWIFI_WRDOFC, " +
                "X_SWIFI_MAIN_NM, " +
                "X_SWIFI_ADRES1, " +
                "X_SWIFI_ADRES2, " +
                "X_SWIFI_INSTL_FLOOR, " +
                "X_SWIFI_INSTL_TY, " +
                "X_SWIFI_INSTL_MBY, " +
                "X_SWIFI_SVC_SE, " +
                "X_SWIFI_CMCWR, " +
                "X_SWIFI_CNSTC_YEAR, " +
                "X_SWIFI_INOUT_DOOR, " +
                "X_SWIFI_REMARS3, " +
                "LAT, " +
                "LNT, " +
                "WORK_DTTM " +
                "FROM TB_WIFI_INFO " +
                "ORDER BY distance " +
                "LIMIT 20;";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sb)) {
            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);
            pstmt.setDouble(3, lat);
            pstmt.setDouble(4, lat);
            pstmt.setDouble(5, lnt);
            pstmt.setDouble(6, lat);
            pstmt.setDouble(7, lat);
            pstmt.setDouble(8, lnt);
            pstmt.setDouble(9, lat);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(getResultSetTo(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private WifiInfo getResultSetTo(ResultSet rs) {
        WifiInfo wifiInfo = new WifiInfo();
        try {
            wifiInfo.setDistance(rs.getDouble("DISTANCE"));
            wifiInfo.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
            wifiInfo.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
            wifiInfo.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
            wifiInfo.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
            wifiInfo.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
            wifiInfo.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
            wifiInfo.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
            wifiInfo.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
            wifiInfo.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
            wifiInfo.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
            wifiInfo.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
            wifiInfo.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
            wifiInfo.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
            wifiInfo.setLAT(rs.getDouble("LAT"));
            wifiInfo.setLNT(rs.getDouble("LNT"));
            wifiInfo.setWORK_DTTM(rs.getString("WORK_DTTM"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wifiInfo;
    }
}
