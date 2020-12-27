package com.baiyun.devhist.util;

import com.baiyun.devhist.handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 数据库增删改查模板工具类
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:23
 **/
public class CurdTemplate {
    /**
     * DML 语句操作(使用预编译模式，详细可见：{@linkplain PreparedStatement} )
     *
     * @param sqlStr SQL 语句(使用预编译模式，详细可见：{@linkplain PreparedStatement} )
     * @param params 预编译语句中的参数(可变参数)
     * @return 影响条数
     */
    public static int executeUpdate(String sqlStr, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 创建连接
            conn = JdbcUtil.getConnectionInstance();
            // 创建 statement
            assert conn != null;
            ps = conn.prepareStatement(sqlStr);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 执行 sql 语句
            return ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    /**
     * DQL 查询语句(使用预编译模式，详情请参见 {@linkplain PreparedStatement})
     *
     * @param sqlStr SQL 语句
     * @param params 可变参数
     * @return <code>java.util.List<Student></></code>
     */
    public static <T> T executeQuery(String sqlStr, ResultSetHandler<T> res, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 创建连接
            conn = JdbcUtil.getConnectionInstance();
            // 创建 statement
            assert conn != null;
            ps = conn.prepareStatement(sqlStr);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 执行 sql 语句
            rs = ps.executeQuery();
            return res.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 聚合函数
     * @param sqlStr sql语句
     * @return 数值
     */
    public static Integer execute(String sqlStr, Object...params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 创建连接
            conn = JdbcUtil.getConnectionInstance();
            // 创建 statement
            assert conn != null;
            ps = conn.prepareStatement(sqlStr);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 执行 sql 语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
