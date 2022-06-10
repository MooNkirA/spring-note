package com.moon.springsample;

import com.moon.springsample.handler.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 自定义 JdbcTemplate
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 08:36
 * @description
 */
public class JdbcTemplate {

    // 定义数据源属性
    private DataSource dataSource;

    // 通过set方法给数据源赋值
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 无参构造方法
    public JdbcTemplate() {
    }

    // 通过构造函数给数据源赋值
    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 定义用于执行SQL增删改的方法
     *
     * @param sql    需要执行的SQL语句
     * @param params 执行SQL语句所需要的参数
     * @return 影响数据库记录的行数
     */
    public int update(String sql, Object... params) {
        // 1. 验证数据源是否为空，没有数据源直接抛出异常
        if (this.dataSource == null) {
            throw new NullPointerException("DataSource can not be null!");
        }

        // 2. 定义jdbc相关操作的对象
        Connection conn = null;
        PreparedStatement pstm = null;
        // 返回值
        int res = 0;

        try {
            // 3. 获取数据库连接对象
            conn = dataSource.getConnection();
            // 4. 获取预处理对象
            pstm = conn.prepareStatement(sql);
            // 5. 获取参数的元信息
            ParameterMetaData metaData = pstm.getParameterMetaData();
            // 6. 从参数的元信息中获取sql域中参数的个数（即占位符（?）的个数）
            int parameterCount = metaData.getParameterCount();
            // 7. 判断sql语句中是否有参数，如有，则校验参数
            if (parameterCount > 0) {
                // 7-1. 判断调用方法时是否提供参数
                if (params == null) {
                    throw new IllegalArgumentException("Parameter can not be null!");
                }
                // 7-2. 判断方法的参数个数是否匹配
                if (params.length != parameterCount) {
                    throw new IllegalArgumentException("Incorrect parameter size: expected " +
                            parameterCount + ", actual " + params.length);
                }
                // 7-3. 参数校验通过，给占位符赋值
                for (int i = 0; i < parameterCount; i++) {
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 8. 执行sql语句
            res = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            releaseResources(null, pstm, conn);
        }
        return res;
    }

    /**
     * 定义用于执行SQL查询的方法
     *
     * @param sql              需要执行的SQL语句
     * @param resultSetHandler 结果集处理器
     * @param params           执行SQL语句所需要的参数
     * @return 查询的结果集合
     */
    public Object query(String sql, ResultSetHandler resultSetHandler, Object... params) {
        // 1. 验证数据源是否为空，没有数据源直接抛出异常
        if (this.dataSource == null) {
            throw new NullPointerException("DataSource can not be null!");
        }

        // 2. 定义jdbc相关操作的对象
        Connection conn = null;
        PreparedStatement pstm = null;
        // 返回值
        ResultSet rs = null;

        try {
            // 3. 获取数据库连接对象
            conn = dataSource.getConnection();
            // 4. 获取预处理对象
            pstm = conn.prepareStatement(sql);
            // 5. 获取参数的元信息
            ParameterMetaData metaData = pstm.getParameterMetaData();
            // 6. 从参数的元信息中获取sql域中参数的个数（即占位符（?）的个数）
            int parameterCount = metaData.getParameterCount();
            // 7. 判断sql语句中是否有参数，如有，则校验参数
            if (parameterCount > 0) {
                // 7-1. 判断调用方法时是否提供参数
                if (params == null) {
                    throw new IllegalArgumentException("Parameter can not be null!");
                }
                // 7-2. 判断方法的参数个数是否匹配
                if (params.length != parameterCount) {
                    throw new IllegalArgumentException("Incorrect parameter size: expected " +
                            parameterCount + ", actual " + params.length);
                }
                // 7-3. 参数校验通过，给占位符赋值
                for (int i = 0; i < parameterCount; i++) {
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 8. 执行sql语句
            rs = pstm.executeQuery();
            // 9. 自定义结果处理器，将ResultSet进行处理
            return resultSetHandler.handler(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            releaseResources(rs, pstm, conn);
        }
    }

    /**
     * 释放资源
     *
     * @param rs
     * @param pstm
     * @param conn
     */
    private void releaseResources(ResultSet rs, PreparedStatement pstm, Connection conn) {
        // 关闭ResultSet对象
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭预处理PreparedStatement对象
        if (pstm != null) {
            try {
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭数据库连接Connection对象
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
