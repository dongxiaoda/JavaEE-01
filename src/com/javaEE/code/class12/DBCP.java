package com.javaEE.code.class12;


import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// Database Connection Pool
public class DBCP {

    // 数据库连接池
    private static BasicDataSource dbcp;

    //为不同线程管理连接
    private static ThreadLocal<Connection> tl;

    //通过配置文件来获取数据库参数
    static {
        try {
            Properties properties = new Properties();

            InputStream inputStream = DBCP.class.getClassLoader().getResourceAsStream("com/javaEE/code/class12/Application.properties");

            properties.load(inputStream);
            inputStream.close();

            // 初始化连接池
            dbcp = new BasicDataSource();

            // 数据库驱动 (Class.forName())
            dbcp.setDriverClassName(properties.getProperty("driverName"));
            // 数据库url
            dbcp.setUrl(properties.getProperty("dbUrl"));
            // 数据库用户名
            dbcp.setUsername(properties.getProperty("userName"));
            // 数据库密码
            dbcp.setPassword(properties.getProperty("password"));
            // 初始化连接数量
            dbcp.setInitialSize(Integer.parseInt(properties.getProperty("initSize")));
            // 连接池允许的最大连接数
            dbcp.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
            // 最大等待时间
            dbcp.setMaxWait(Integer.parseInt(properties.getProperty("maxWait")));
            // 最小空闲数
            dbcp.setMinIdle(Integer.parseInt(properties.getProperty("minIdle"))
            );
            // 最大空闲数
            dbcp.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            // 初始化线程本地
            tl = new ThreadLocal<Connection>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过连接池获取一个连接
    public static Connection getConnection() throws SQLException {
        Connection conn = dbcp.getConnection();
        tl.set(conn);
        return conn;
    }

    // 归还连接，未关闭
    public static void closeConnection() {
        try {
            Connection conn = tl.get();
            if (conn != null) {
                conn.close();
                tl.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
