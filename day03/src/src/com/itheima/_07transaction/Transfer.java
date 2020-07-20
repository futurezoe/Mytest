package src.com.itheima._07transaction;


import src.com.itheima._04util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 模拟银行转账功能：account表里 tom给jerry转账1000
 *
 * sql语句： update account set money = money + ? where name = ?
 *      tom：  转账金额 -1000， name是tom
 *      jerry：转账金额 1000,   name是jerry
 * @author liuyp
 * @date 2020/01/09
 */
public class Transfer {
    public static void main(String[] args) throws Exception {
        //转账人
        String from = "tom";
        //收款人
        String to = "jerry";
        //转账金额
        Double money = 1000d;

        transfer(from, to, money);
    }

    /**
     * 转账 方法
     * @param from 转账人
     * @param to 收款人
     * @param money 转账金额
     */
    private static void transfer(String from, String to, Double money) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动，获取连接
            connection = JdbcUtils.getConnection();

            //===开启事务===
            connection.setAutoCommit(false);

            //2.创建预编译对象
            String sql = "update account set money = money + ? where name = ?";
            preparedStatement = connection.prepareStatement(sql);

            //3.执行SQL语句
            //3.1 转账人扣钱
            preparedStatement.setDouble(1, -money);
            preparedStatement.setString(2, from);
            preparedStatement.executeUpdate();

            int i = 1/0;

            //3.2 收款人加钱
            preparedStatement.setDouble(1, money);
            preparedStatement.setString(2, to);
            preparedStatement.executeUpdate();

            //4.处理结果
            System.out.println("转账完成");

            //===提交事务===
            connection.commit();
            System.out.println("提交事务");
        } catch (Exception e) {
            //===回滚事务===
            try {
                connection.rollback();
                System.out.println("回滚事务");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //5.释放资源
            JdbcUtils.close(preparedStatement, connection);
        }
    }
}
