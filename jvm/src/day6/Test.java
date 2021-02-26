package day6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 同一个列加载器的不同实例加载的class是同一个
 * 不同类加载器加载的同一个类的class是不同的
 * 所以说同一个类的class对象在同一个类加载器命名空间下是同一个，不同的类加载器的类的class是不一样的
 */
public class Test {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
////如果把类加载器对象放在循环外部，每次加载都是同一个类加载器的话就起不到类文件热替换的效果，因为他会从缓存中直接把之前已经加载过的类文件加载出来
//    BrockdelegateClassLoader myClassLoader = new BrockdelegateClassLoader();
    while (true) {
      BrockdelegateClassLoader myClassLoader = new BrockdelegateClassLoader();
//      //如果开启就会看到我们的类加载器是sun.misc.Launcher$AppClassLoader，也就是应用类加载器，所以只会加载一次，做不到类文件热替换的效果
//      MyClassLoader myClassLoader = new MyClassLoader();
      Class<?> aClass1 = myClassLoader.loadClass("day3.HelloWorld");
      System.out.println(aClass1.getClassLoader());
      Object o = aClass1.newInstance();
      Method say = aClass1.getMethod("say");
      Object invoke = say.invoke(o);
      System.out.println(invoke);
      TimeUnit.SECONDS.sleep(3);
    }
  }
}
