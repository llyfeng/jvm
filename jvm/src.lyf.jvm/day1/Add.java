package day1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author lyf
 * @Date 2021-01-04 14:51
 * @Description:
 */
public class Add {

  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    System.out.println(a+b);
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    set.add(1);
    System.out.println(set);
  }
}
