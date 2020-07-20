package src.com.itheima._01quickstart;

import java.sql.*;

/**
 * JDBC快速入门：查询heima库里所有的user
 *
 * JDBC步骤太繁琐，后期会学习：DBUtils工具， Mybatis框架
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo01QuickStart {
    public static void main(String[] args) throws Exception {
        //1. 注册驱动：注册驱动包里的驱动类，com.mysql.jdbc.Driver
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接：使用JDBC工具类DriverManager
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/heima", "root", "root");
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
