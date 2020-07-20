package src.com.itheima.demo1_单例设计模式饿汉式;

/**
 * @author 彭智林
 * @date 2020/1/5 8:55
 */
public class Demo {
    public static void main(String[] args) {
        /*
            单例设计模式:在程序运行期间,保证某个类有且仅能创建一个对象,从而节省资源
            单例设计模式 --- 饿汉式:
                1.将构造方法私有化,以确保在类的外部不能通过new来创建对象
                2.定义一个本类的成员变量,并创建本类对象,使用private static final修饰
                3.提供一个公共静态方法,供外界获取本类的唯一对象
         */
        System.out.println(Person.getInstance());
        System.out.println(Person.getInstance());
        System.out.println(Person.getInstance());
        System.out.println(Person.getInstance());
    }
}
