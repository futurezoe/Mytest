package src.com.itheima.demo1_单例设计模式饿汉式;

/**
 * @author 彭智林
 * @date 2020/1/5 8:57
 */
public class Person {// 饿汉式
    // 将构造方法私有化,以确保在类的外部不能通过new来创建对象
    private Person(){

    }

    // 定义一个本类的成员变量,并创建本类对象,使用private static final修饰
    private static final Person P = new Person();

    // 提供一个公共静态方法,供外界获取本类的唯一对象
    public static Person getInstance(){
        return P;
    }

}
