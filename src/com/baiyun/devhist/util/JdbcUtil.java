package com.baiyun.devhist.util;


import java.sql.*;

/**
 * @program: TheVote
 * @description: 数据库工具类
 * @author: HuaiAnGG
 * @create: 2020-11-20 14:47
 **/
public class JdbcUtil {
    /**
     * mysql 驱动地址
     */
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * 连接地址
     */
    private static final String CONN_URL = "jdbc:mysql://localhost:3306/db_baiyun?serverTimezone=UTC";
    /**
     * 用户名
     */
    private static final String USERNAME = "root";
    /**
     * 密码
     */
    private static final String PASSWORD = "123456";

    /**
     * 数据库连接对象
     */
    private volatile static Connection conn = null;

    // 加载驱动
    static {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     *
     * @return {@linkplain Connection} 数据库连接对象
     */
    public static Connection getConnectionInstance() {
        if (null == conn) {
            synchronized (JdbcUtil.class) {
                if (null == conn) {
                    try {
                        conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            }
        }
        return conn;
    }

    /**
     * 释放数据库连接资源
     *
     * @param rs        {@linkplain ResultSet} 数据集
     * @param statement {@linkplain Statement}
     * @param conn      {@linkplain Connection} 数据连接对象
     */
    public static void close(ResultSet rs, Statement statement, Connection conn) {
        // 关闭资源
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

}
