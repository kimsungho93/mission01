package com.zerobase.mission01.model.dao;

import com.zerobase.mission01.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class History {
    private final String TABLE = "TB_HISTORY";

    private static History dao;
    private History() {}
    public static History getInstance() {
        if (dao == null) {
            dao = new History();
        }
        return dao;
    }

    public void insert(com.zerobase.mission01.model.dto.History h) {
        String sql = "INSERT INTO " + TABLE + " (LAT, LNT, REG_DATETIME) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setDouble(1, h.getLat());
            pstmt.setDouble(2, h.getLnt());
            pstmt.setString(3, h.getRegDateTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<com.zerobase.mission01.model.dto.History> deleteAll() {
        List<com.zerobase.mission01.model.dto.History> list = new ArrayList<>();

        String sb = " SELECT " +
                        " IDX, " +
                        " LNT, " +
                        " LAT, " +
                        " REG_DATETIME " +
                    " FROM " + TABLE +
                    " ORDER BY IDX DESC";

        try (ResultSet resultSet = DB.getStmt().executeQuery(sb)) {
            while (resultSet.next()) {
                com.zerobase.mission01.model.dto.History history = new com.zerobase.mission01.model.dto.History();
                history.setIdx(resultSet.getInt("IDX"));
                history.setLat(resultSet.getDouble("LAT"));
                history.setLnt(resultSet.getDouble("LNT"));
                history.setRegDateTime(resultSet.getString("REG_DATETIME"));
                list.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void delete(String idx) {
        String sql = "DELETE FROM " + TABLE + " WHERE idx = " + idx;

        try {
            DB.getStmt().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}