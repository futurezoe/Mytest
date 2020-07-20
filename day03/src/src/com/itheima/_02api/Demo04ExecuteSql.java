package src.com.itheima._02api;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 执行SQL语句 的API详解
 *
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo04ExecuteSql {

    /**
     * 执行DQL语句：查询所有用户
     *  statement.executeQuery(String sql)：返回值ResultSet
     */

    public void testDql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user");
        System.out.println(resultSet);

        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 执行DML语句：增、删、改
     *  statement.executeUpdate(String sql)：返回值int，表示影响的行数
     */

    public void testDml() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        Statement statement = connection.createStatement();

        int update = statement.executeUpdate("update user set password = '111' where sex = '男'");
        System.out.println("影响行数：" + update);

        statement.close();
        connection.close();
    }

    /**
     * 执行任意SQL语句的方法：
     *  statement.execute(String sql)：返回boolean，表示  执行的语句是否是查询。
     *      如果执行的是查询语句，返回true，说明有结果集ResultSet
     *      否则返回false，说明不是查询语句，没有ResultSet
     */

    public void testAnySql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        Statement statement = connection.createStatement();

        //boolean isQuery = statement.execute("create database aaa");
        boolean isQuery = statement.execute("select * from user");
        System.out.println("执行的是查询吗？" + isQuery);
        if (isQuery) {
            ResultSet resultSet = statement.getResultSet();
            System.out.println(resultSet);
        }

        statement.close();
        connection.close();
    }
}
