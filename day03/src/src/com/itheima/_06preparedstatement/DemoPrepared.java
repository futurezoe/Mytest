package src.com.itheima._06preparedstatement;


import src.com.itheima._04util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 预编译对象执行多条SQL语句
 * @author liuyp
 * @date 2020/01/09
 */
public class DemoPrepared {

    /**
     * 执行相同的SQL语句，仅仅是参数值不同：可以使用同一个预编译对象，多次设置参数值，多次执行
     */

    public void test1() throws Exception {
        Connection connection = JdbcUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");

        //1.第一次执行
        preparedStatement.setInt(1, 1);
        preparedStatement.executeUpdate();

        //2.第二次执行
        preparedStatement.setInt(1, 2);
        preparedStatement.executeUpdate();


        JdbcUtils.close(preparedStatement, connection);
    }

    /**
     * 执行不同的SQL语句，有不同的预编译对象，分别设置参数执行
     */

    public void test2() throws Exception {
        Connection connection = JdbcUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
        preparedStatement.setInt(1, 4);
        int update = preparedStatement.executeUpdate();
        System.out.println("删除：" + update);

        PreparedStatement preparedStatement1 = connection.prepareStatement("update user set password = ? where username = ?");
        preparedStatement1.setString(1, "xiaoming");
        preparedStatement1.setString(2, "xiaoming");
        int update1 = preparedStatement1.executeUpdate();
        System.out.println("修改：" + update1);

        preparedStatement.close();
        preparedStatement1.close();
        connection.close();
    }
}
