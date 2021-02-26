package day5;

/**
 * @Author lyf
 * @Date 2021-02-24 10:51
 * @Description:
 */
public class NotInitialization {

  public static void main(String[] args) {
    //引用父类的静态字段不会导致子类初始化
    System.out.println(SubClass.value);

    //通过数组定义来引用类，不会触发此类的初始化
    SuperClass[] sca = new SuperClass[10];

    //常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
    System.out.println(ConstClass.HELLOWORLD);
  }
}
