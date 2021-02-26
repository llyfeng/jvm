package day5;

/**
 * @Author lyf
 * @Date 2021-02-24 10:56
 * @Description:
 */
public class ConstClass {

  static {
    System.out.println("常量类静态块执行");
  }
  public static final String HELLOWORLD = "hello world";
}
