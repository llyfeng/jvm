package day3;

import day2.ExtendTest;
import day2.TestClass;

/**
 * @Author lyf
 * @Date 2021-01-15 10:29
 * @Description:
 */
public class ExtendTestClass extends TestClass implements ExtendTest {

  private int sum;

  private static final String str = "学习使我快乐～";

  @Override
  public int inc() {
    return sum + 1;
  }

  @Override
  public int reduce() {
    return sum - 1;
  }
}
