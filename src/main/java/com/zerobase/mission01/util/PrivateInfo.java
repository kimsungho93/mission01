package com.zerobase.mission01.util;

public class PrivateInfo {
    // api 키
    public static final String API_KEY = "556a4e636f6b74663131375a764c464f";

    // DB 경로 + DB 이름
    private String DB_INFO = "testSQLite.db";
    public String getDbInfo() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.startsWith("windows"))
            return this.getClass().getClassLoader().getResource(DB_INFO).getPath().replaceFirst("/", "");
        else
            return this.getClass().getClassLoader().getResource(DB_INFO).getPath();
    }
}
