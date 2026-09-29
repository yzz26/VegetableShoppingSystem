package com.hbdf.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类
 * 采用静态方法封装数据库连接和资源释放操作
 */
public class DBUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    // 静态代码块：加载驱动和读取配置，仅执行一次[reference:12]
    static {
        try {
            // 读取配置文件
            Properties props = new Properties();
            props.load(new FileInputStream(DBUtil.class.getClassLoader()
                    .getResource("db.properties").getPath()));

            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");

            // 加载数据库驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("数据库配置加载失败：" + e.getMessage());
        }
    }

    /**
     * 获取数据库连接
     * @return Connection对象
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败：" + e.getMessage());
        }
    }

    /**
     * 释放数据库资源[reference:13]
     * @param rs ResultSet结果集
     * @param stmt Statement对象
     * @param conn Connection对象
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放数据库资源（重载方法，用于不需要ResultSet的场景）
     */
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }
}