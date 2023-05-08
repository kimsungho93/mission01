package com.zerobase.mission01.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private static final String DB_NAME = new PrivateInfo().getDbInfo();

    private static Connection conn = null;
    private static Statement stmt = null;

    private DB() {}
    public static void open() {
        getConn();
        getStmt();

        createTables();
//        clearTables();
    }

    private static void clearTables() {
        createTables();
        dropTables();
        createTables();
    }
    // table 생성
    private static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS TB_WIFI_INFO (                            " +
                "	DISTANCE	REAL,                                               " +
                "	X_SWIFI_MGR_NO	TEXT NOT NULL,                                      " +
                "	X_SWIFI_WRDOFC	TEXT,                                               " +
                "	X_SWIFI_MAIN_NM	TEXT,                                               " +
                "	X_SWIFI_ADRES1	TEXT,                                               " +
                "	X_SWIFI_ADRES2	TEXT,                                               " +
                "	X_SWIFI_INSTL_FLOOR	TEXT,                                           " +
                "	X_SWIFI_INSTL_TY	TEXT,                                             " +
                "	X_SWIFI_INSTL_MBY	TEXT,                                             " +
                "	X_SWIFI_SVC_SE	TEXT,                                               " +
                "	X_SWIFI_CMCWR	TEXT,                                                 " +
                "	X_SWIFI_CNSTC_YEAR	TEXT,                                           " +
                "	X_SWIFI_INOUT_DOOR	TEXT,                                           " +
                "	X_SWIFI_REMARS3	TEXT,                                               " +
                "	LAT	REAL,                                                           " +
                "	LNT	REAL,                                                           " +
                "	WORK_DTTM	TEXT,                                                   " +
                "	PRIMARY KEY(X_SWIFI_MGR_NO)                                         " +
                ");                                                                     " +
                "                                                                     " +
                "CREATE TABLE IF NOT EXISTS TB_BOOK_MARK_GROUP                        " +
                "(                                                                    " +
                "    `IDX`            INTEGER    NOT NULL    ,                        " +
                "    `NAME`           TEXT       NOT NULL    ,                        " +
                "    `MY_ORDER`       INTEGER    NOT NULL    ,                        " +
                "    `REG_DATETIME`   TEXT       NOT NULL    ,                        " +
                "    `UPD_DATETIME`   TEXT       NULL        ,                        " +
                "     PRIMARY KEY (IDX AUTOINCREMENT)                                 " +
                ");                                                                   " +
                "                                                                       " +
                "CREATE TABLE IF NOT EXISTS TB_BOOK_MARK (                              " +
                "	IDX	INTEGER NOT NULL,                                               " +
                "	IDX_BOOK_MARK_GROUP	INTEGER NOT NULL,                               " +
                "	IDX_WIFI	TEXT NOT NULL,                                          " +
                "	REG_DATETIME	TEXT NOT NULL,                                      " +
                "	FOREIGN KEY(IDX_BOOK_MARK_GROUP) REFERENCES TB_BOOK_MARK_GROUP(IDX)," +
                "	FOREIGN KEY(IDX_WIFI) REFERENCES TB_WIFI_INFO(X_SWIFI_MGR_NO),      " +
                "	PRIMARY KEY(IDX AUTOINCREMENT)                                      " +
                ");                                                                   " +
                "                                                                     " +
                "CREATE TABLE IF NOT EXISTS TB_HISTORY                                " +
                "(                                                                    " +
                "    `IDX`           INTEGER    NOT NULL   ,                          " +
                "    `LAT`           REAL       NOT NULL   ,                          " +
                "    `LNT`           REAL       NOT NULL   ,                          " +
                "    `REG_DATETIME`  TEXT       NOT NULL   ,                          " +
                "     PRIMARY KEY (IDX AUTOINCREMENT)                                 " +
                ");                                                                   ";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTables() {
        try {
            stmt.executeUpdate("DROP TABLE TB_HISTORY;");
            stmt.executeUpdate("DROP TABLE TB_BOOK_MARK;");
            stmt.executeUpdate("DROP TABLE TB_BOOK_MARK_GROUP;");
            stmt.executeUpdate("DROP TABLE TB_WIFI_INFO;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // db 연결
    public static Statement getStmt() {
        try {
            if (stmt == null) {
                stmt = getConn().createStatement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public static Connection getConn() {
        try {
            if (conn == null) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
