package src.com.itheima._06preparedstatement;


import src.com.itheima._04util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用预编译对象进行CURD操作
 * @author liuyp
 * @date 2020/01/09
 */
public class DemoPreparedCurd {

    /**
     * 查询所有用户: select * from user
     */

    public void test1() throws Exception {
        //1. 注册驱动，获取连接：使用工具类jdbcUtils
        Connection connection = JdbcUtils.getConnection();

        //2. 创建预编译对象
        String sql = "select * from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3. 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        //4. 处理结果
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            System.out.println(username);
        }
        //5. 释放资源：使用工具类：jdbcUtils
        JdbcUtils.close(resultSet, preparedStatement, connection);
    }

    /**
     * 根据主键查询一个：查询id为1的用户
     */

    public void test2() throws Exception {
        //1.注册驱动，获取连接
        Connection connection = JdbcUtils.getConnection();

        //2.创建预编译对象
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);

        //3.执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //4.处理结果
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            Object id = resultSet.getObject("id");
            System.out.println("id: " + id + ", username: " + username);
        }
        //5.释放资源
        JdbcUtils.close(resultSet, preparedStatement, connection);
    }

    /**
     * 添加一个用户
     */

    public void test3() throws Exception {
        //1.注册驱动，获取连接
        Connection connection = JdbcUtils.getConnection();

        //2.创建预编译对象
        String sql = "insert into user (id,username,password,name,birthday,age,sex) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, null);
        preparedStatement.setString(2, "2哥");
        preparedStatement.setString(3, "naicha");
        preparedStatement.setString(4, "东哥");
        preparedStatement.setString(5, "1965-04-21");
        preparedStatement.setInt(6, 55);
        preparedStatement.setString(7, "男");

        //3.执行SQL语句
        int update = preparedStatement.executeUpdate();
        //4.处理结果
        System.out.println("影响行数：" + update);
        //5.释放资源
        JdbcUtils.close(preparedStatement, connection);
    }

    /**
     * 修改用户
     */

    public void test4() throws Exception {
        //1.注册驱动，获取连接
        Connection connection = JdbcUtils.getConnection();

        //2.创建预编译对象
        String sql = "update user set password = ? where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "2ge");
        preparedStatement.setString(2, "2哥");

        //3.执行SQL语句
        int update = preparedStatement.executeUpdate();
        //4.处理结果
        System.out.println("影响行数：" + update);
        //5.释放资源
        JdbcUtils.close(preparedStatement, connection);
    }

    /**
     * 删除用户
     */

    public void test5() throws Exception {
        //1.注册驱动，获取连接
        Connection connection = JdbcUtils.getConnection();

        //2.创建预编译对象
        String sql = "delete from user where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "2哥");

        //3.执行SQL语句
        int update = preparedStatement.executeUpdate();
        //4.处理结果
        System.out.println("影响行数：" + update);
        //5.释放资源
        JdbcUtils.close(preparedStatement, connection);
    }

    /**
     * 查询数量
   */
    public void test6() throws Exception {
        //1. 注册驱动，获取连接：使用工具类jdbcUtils
        Connection connection = JdbcUtils.getConnection();

        //2. 创建预编译对象
        String sql = "select count(*) from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3. 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        //4. 处理结果
        while (resultSet.next()) {
            String count = resultSet.getString(1);
            System.out.println("总数量：" + count);
        }
        //5. 释放资源：使用工具类：jdbcUtils
        JdbcUtils.close(resultSet, preparedStatement, connection);
    }
}
