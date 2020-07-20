package src.com.itheima._02api;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 获取连接的API详解
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo02Connection {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        /**
         * 获取连接：
         *  什么是连接：Jdbc规定了，所有数据库连接，所封装成的类，都必须实现接口java.sql.Connection
         *      数据库驱动里，这个接口的实现类，就是数据库连接类，我们要获取的就是这个类的对象
         *
         *  如何获取连接：
         *      使用JDBC工具类DriverManager.getConnection()方法，方法参数有三个：
         *          第1个：数据库的地址，jdbc规定了写法 由三部分组成，三部分之间用:连接
         *              协议：      jdbc  连接所使用的协议是jdbc协议，固定值
         *              子协议：    mysql 连接数据库的子协议，通常是连接什么数据库，就是什么数据库名
         *              具体地址：  由数据库厂商规定了写法，MySql的写法：
         *                  //数据库的ip:端口/database
         *
         *                  注意：如果连接的是本机、3306端口的MySql，具体地址可以简写： ///database
         */

        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");

        System.out.println(connection);

        connection.close();
    }
}
