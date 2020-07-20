package src.com.itheima._02api;

import java.sql.*;

/**
 * 处理结果集ResultSet的API。释放资源的方法
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo05ResultSet {

    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3.创建SQL执行平台
        Statement statement = connection.createStatement();
        //4.执行SQL语句：查询
        ResultSet resultSet = statement.executeQuery("select * from user");
        //5.处理结果集
        while (resultSet.next()) {
            //获取第一列的值
            String id = resultSet.getString(1);
            //获取username列的值
            String username = resultSet.getString("username");

            //Date birthday = resultSet.getDate("birthday");
            String birthday = resultSet.getString("birthday");

            System.out.println("id: " + id + ", username: " + username + ", birthday: " + birthday);
        }


        /**
         * 为什么要释放资源？
         *      数据库的可用连接是有限的，操作完成数据库之后，一定要释放资源，否则可能：所有可用连接全部占用，不能再连接数据库了
         *  有哪些资源需要释放？都有close()方法，进行关闭/释放
         *      resultSet.close()
         *      statement.close()
         *      connection.close()
         *  资源释放的顺序：和获取的顺序相反
         */
        resultSet.close();
        statement.close();
        connection.close();
    }

}
