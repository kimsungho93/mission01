package com.zerobase.mission01.model.dao;

import com.zerobase.mission01.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroup {
    private final String TABLE = "TB_BOOK_MARK_GROUP";
    private static BookmarkGroup dao;
    private BookmarkGroup() {}
    public static BookmarkGroup getInstance() {
        if (dao == null) {
            dao = new BookmarkGroup();
        }
        return dao;
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

    public void update(com.zerobase.mission01.model.dto.BookmarkGroup b) {
        String sql = "UPDATE " + TABLE + " SET NAME = ?, MY_ORDER = ?, UPD_DATETIME = ? WHERE IDX = ?";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getMyOrder());
            pstmt.setString(3, b.getUpdDatetime());
            pstmt.setInt(4, b.getIdx());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(com.zerobase.mission01.model.dto.BookmarkGroup b) {
        String sql = "INSERT INTO " + TABLE + " (NAME, MY_ORDER, REG_DATETIME) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getMyOrder());
            pstmt.setString(3, b.getRegDatetime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public com.zerobase.mission01.model.dto.BookmarkGroup select(int idx) {
        String sql = "SELECT * FROM " + TABLE + " WHERE IDX = ?";

        com.zerobase.mission01.model.dto.BookmarkGroup bmg = null;
        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            ResultSet rs = pstmt.executeQuery();

            bmg = getResultSetTo(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bmg;
    }
    public List<com.zerobase.mission01.model.dto.BookmarkGroup> selectAll() {
        List<com.zerobase.mission01.model.dto.BookmarkGroup> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE + " ORDER BY MY_ORDER, UPD_DATETIME, IDX";
        try (ResultSet rs = DB.getStmt().executeQuery(sql)) {
            while (rs.next()) {
                list.add(getResultSetTo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private com.zerobase.mission01.model.dto.BookmarkGroup getResultSetTo(ResultSet rs) {
        com.zerobase.mission01.model.dto.BookmarkGroup bmg = new com.zerobase.mission01.model.dto.BookmarkGroup();
        try {
            bmg.setIdx(rs.getInt("IDX"));
            bmg.setName(rs.getString("NAME"));
            bmg.setMyOrder(rs.getInt("MY_ORDER"));
            bmg.setRegDatetime(rs.getString("REG_DATETIME"));
            bmg.setUpdDatetime(rs.getString("UPD_DATETIME"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bmg;
    }
}