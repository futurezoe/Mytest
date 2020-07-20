package src.com.itheima._02api;


import src.com.itheima._04util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 注册驱动的API详解
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo01Driver {



    public static void main(String[] args) throws Exception {
        /**
         * 什么是驱动：由数据库厂商提供的、实现了Jdbc接口的实现类，打包形成的jar包
         * 注册的驱动：是驱动包里的一个驱动类，这个类实现了java.sql.Driver接口
         *      MySql里的驱动实现类是：com.mysql.jdbc.Driver
         * 如何注册驱动：
         *      1. 使用工具类DriverManager.registerDriver()：有问题
         *          * 注册了2次，而实际只要注册一次：我们自己注册一次，Driver里静态代码块注册一次
         *          * 硬编码问题：代码里写死了注册的MySql驱动类。如果将来要换成Oracle，就必须要修改源码
         *
         *      2. 使用Class.forName()注册：建议使用这种
         *          * 只注册了一次
         *          * 可以解决硬编码问题：
         *              可以把驱动类名，提取到配置文件里。Java代码读取配置文件，得到驱动类名，然后注册驱动
         *              如果要操作的数据库换了，不需要修改源码，只要修改配置文件即可
         */


        Class.forName("com.mysql.jdbc.Driver");

        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());


        //2. 获取连接：使用JDBC工具类DriverManager
        Connection connection = JdbcUtils.getConnection();
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user");
        //5. 处理结果
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println("username: " + username + ", password: " + password);
        }
        //6. 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
