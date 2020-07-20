package src.com.itheima._04util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类封装：
 * 原始JDBC操作数据库存在的问题有：
 *  问题1.
 *      描述：重复代码太多了
 *          注册驱动
 *          获取连接
 *          释放资源
 *      解决：
 *          把重复代码抽取成公用的方法
 *          getConnection()：获取连接的方法
 *          close()：释放资源的方法
 *  问题2：
 *      描述：注册驱动的代码，只要执行一次。现在的状态是：每次获取连接都会执行一次
 *      解决：把代码放到静态代码块里
 *
 *  问题3：
 *      描述：硬编码问题。工具类代码里写死了数据库的驱动类名、url地址、用户名和密码，如果值有变化就必须要修改源码
 *      解决：提取到jdbc.properties配置文件里
 *           在工具类里，使用Java代码读取jdbc.properties配置文件，得到所有配置信息-放静态代码块里
 *
 * @author liuyp
 * @date 2020/01/09
 */
public class JdbcUtils {
    private static String url;
    private static String username;
    private static String password;

    static {
        InputStream is = null;
        try {
            // 自己写的类.class.getClassLoader().getResourceAsStream()：从类路径里(src)加载文件，得到inputStream
            is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //0. 读取配置文件
            Properties properties = new Properties();
            properties.load(is);

            String driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //1. 注册驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 释放资源
     */
    public static void close(Statement statement, Connection connection){
        close(null, statement, connection);
    }

    /**
     * 释放资源
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        // inn 生成 if not null ,  ifn 生成 if null
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
