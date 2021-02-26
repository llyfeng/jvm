package day3;

import day2.ExtendTestClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author lyf
 * @Date 2021-01-26 10:26
 * @Description:
 */
public class aa {


  static int m = 1;
  static class DeadLoopClass {
    static {
      // 如果不加上这个if语句，编译器将提示“Initializer does not complete normally”并拒绝编译
      if (true) {
        System.out.println(Thread.currentThread() + "init DeadLoopClass");
        while (true) {
          if(m==1){
            System.out.println(Thread.currentThread() + "进来了");
            m++;
          }
        }
      }
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    ClassLoader myLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
          String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
          InputStream is = getClass().getResourceAsStream(fileName);

          if (is == null) {
            return super.loadClass(name);
          }
          byte[] b = new byte[is.available()];
          is.read(b);
          return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
          throw new ClassNotFoundException(name);
        }
      }
    };
    Object obj = myLoader.loadClass("day3.ExtendTestClass").newInstance();

    System.out.println(obj.getClass().getClassLoader());
    System.out.println(obj instanceof day3.ExtendTestClass);
  }
}