package src.com.itheima._05login;


import src.com.itheima._04util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 模拟登录功能
 * @author liuyp
 * @date 2020/01/09
 */
public class DemoLogin {

    public static void main(String[] args) {
        //1. 获取用户输入的用户名和密码
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        //2. 校验用户名输入的用户名和密码：调用一个方法校验，得到校验的结果
        boolean success = login(username, password);

        //3. 处理结果：如果成功，输出：登录成功；否则输出：登录失败
        if (success) {
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

    /**
     * 登录校验：
     *      select * from user where username = '用户输入的用户名' and password = '用户输入的密码'
     *      只要查询的有结果，就是成功； 只要resultSet.next()为true，就是说明有结果
     * @param username 用户输入的用户名
     * @param password 用户输入的密码
     * @return 登录校验的结果。true：登录成功； false：登录失败
     */
    private static boolean login(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1.注册驱动，获取连接
            connection = JdbcUtils.getConnection();
            //2.创建SQL执行平台
            statement = connection.createStatement();
            //3.执行SQL语句
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            //4.处理结果
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.释放资源
            JdbcUtils.close(resultSet, statement, connection);
        }

        return false;
    }
}
