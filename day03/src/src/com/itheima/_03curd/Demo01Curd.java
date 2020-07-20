package src.com.itheima._03curd;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用JDBC进行curd练习
 * 1. 查询所有
 * 2. 根据主键查询一个
 * 3. 添加数据
 * 4. 修改数据
 * 5. 删除数据
 * 6. 查询数量
 *
 *
 * @author liuyp
 * @date 2020/01/09
 */
public class Demo01Curd {


    /**
     * 查询所有
     */

    public void test1() throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user");
        //5. 处理结果
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String birthday = resultSet.getString("birthday");
            System.out.println("username: " + username + ", birthday: " + birthday);
        }
        //6. 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 根据主键查询一个：查询id为1的
     */

    public void test2() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user where id = 1");
        //5. 处理结果
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String birthday = resultSet.getString("birthday");
            System.out.println("username: " + username + ", birthday: " + birthday);
        }
        //6. 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 添加数据
     */

    public void test3() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        int update = statement.executeUpdate("insert into user(id,username,password,name,birthday,age,sex) values (null, 'xiaowang', 'wang', '小王', '2000-02-02', 20, '男')");
        //5. 处理结果
        System.out.println("影响行数：" + update);
        //6. 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 修改数据：修改xiaowang的密码是 xiaowangba
     */

    public void test4() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        int update = statement.executeUpdate("update user set password = 'xiaowangba' where username = 'xiaowang'");
        //5. 处理结果
        System.out.println("影响行数：" + update);
        //6. 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 删除数据
     */

    public void test5() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        int update = statement.executeUpdate("delete from user where username = 'xiaowang'");
        //5. 处理结果
        System.out.println("影响行数：" + update);
        //6. 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 查询数量
     */

    public void test6() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima", "root", "root");
        //3. 创建SQL执行平台
        Statement statement = connection.createStatement();
        //4. 执行SQL语句
        ResultSet resultSet = statement.executeQuery("select count(*) from user");
        //5. 处理结果
        while (resultSet.next()) {
            int count = resultSet.getInt(1);
            System.out.println("总数量：" + count);
        }
        //6. 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
