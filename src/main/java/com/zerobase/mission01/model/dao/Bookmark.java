package com.zerobase.mission01.model.dao;

import com.zerobase.mission01.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookmark {
    private final String TABLE = "TB_BOOK_MARK";

    private static Bookmark dao;
    private Bookmark() {}
    public static Bookmark getInstance() {
        if (dao == null) {
            dao = new Bookmark();
        }
        return dao;
    }

    public void deleteBookmarkGroup(int idxBookmarkGroup) {
        String sql = "DELETE FROM " + TABLE + " WHERE IDX_BOOK_MARK_GROUP = ? ";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idxBookmarkGroup);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int idx) {
        String sql = "DELETE FROM " + TABLE + " WHERE IDX = ? ";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(com.zerobase.mission01.model.dto.Bookmark b) {
        String sql = "INSERT INTO " + TABLE + " (IDX_BOOK_MARK_GROUP, IDX_WIFI, REG_DATETIME) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, b.getIdxBookMarkGroup());
            pstmt.setString(2, b.getIdxWifi());
            pstmt.setString(3, b.getRegDatetime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public com.zerobase.mission01.model.dto.Bookmark select(int idx) {
        String sql = " select" +
                "     tbm.IDX," +
                "     IDX_BOOK_MARK_GROUP," +
                "     IDX_WIFI," +
                "     TBMG.NAME as GROUP_NAME," +
                "     TWI.X_SWIFI_MAIN_NM as WIFI_NAME," +
                "     tbm.REG_DATETIME" +
                " from TB_BOOK_MARK tbm" +
                " left join TB_WIFI_INFO TWI" +
                "        on tbm.IDX_WIFI = TWI.X_SWIFI_MGR_NO" +
                " left join TB_BOOK_MARK_GROUP TBMG" +
                "        on TBMG.IDX = tbm.IDX_BOOK_MARK_GROUP" +
                " where tbm.IDX = ? ;";

        com.zerobase.mission01.model.dto.Bookmark bm = null;
        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            ResultSet rs = pstmt.executeQuery();

            bm = getResultSetTo(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bm;
    }
    public List<com.zerobase.mission01.model.dto.Bookmark> selectAll() {
        List<com.zerobase.mission01.model.dto.Bookmark> list = new ArrayList<>();

        String sql = " select" +
                        "     tbm.IDX," +
                        "     IDX_BOOK_MARK_GROUP," +
                        "     IDX_WIFI," +
                        "     TBMG.NAME as GROUP_NAME," +
                        "     TWI.X_SWIFI_MAIN_NM as WIFI_NAME," +
                        "     tbm.REG_DATETIME" +
                        " from TB_BOOK_MARK tbm" +
                    " left join TB_WIFI_INFO TWI" +
                    "        on tbm.IDX_WIFI = TWI.X_SWIFI_MGR_NO" +
                    " left join TB_BOOK_MARK_GROUP TBMG" +
                    "        on TBMG.IDX = tbm.IDX_BOOK_MARK_GROUP" +
                    "  order by tbm.IDX";

        try (ResultSet rs = DB.getStmt().executeQuery(sql)) {
            while (rs.next()) {
                list.add(getResultSetTo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private com.zerobase.mission01.model.dto.Bookmark getResultSetTo(ResultSet rs) {
        com.zerobase.mission01.model.dto.Bookmark bm = new com.zerobase.mission01.model.dto.Bookmark();
        try {
            bm.setIdx(rs.getInt("IDX"));
            bm.setIdxBookMarkGroup(rs.getInt("IDX_BOOK_MARK_GROUP"));
            bm.setIdxWifi(rs.getString("IDX_WIFI"));
            bm.setName(rs.getString("GROUP_NAME"));
            bm.setNameWifi(rs.getString("WIFI_NAME"));
            bm.setRegDatetime(rs.getString("REG_DATETIME"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bm;
    }
}