package src.com.itheima._02api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 创建SQL执行平台 API详解
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo03Statement {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");

        /**
         * 创建SQL执行平台：
         *      为什么要创建SQL执行平台：因为这个对象提供了执行SQL语句的方法
         *      什么是SQL执行平台：
         *          jdbc规定了接口java.sql.Statement，数据库驱动包里提供了实现类，我们不需要关注具体的实现类
         *      如何获取SQL执行平台：
         */
        Statement statement = connection.createStatement();

        System.out.println(statement);

        statement.close();
        connection.close();
    }
}
