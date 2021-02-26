package day6;


import java.io.IOException;
import java.io.InputStream;

/**
 * @Author lyf
 * @Date 2021-02-24 17:01
 * @Description:
 */
public class ClassLoaders {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    ClassLoader myLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
          String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
          //获取在类路径下的类文件
          InputStream is = getClass().getResourceAsStream(fileName);

          if (is == null) {
            return super.loadClass(name);
          }
          byte[] b = new byte[is.available()];
          is.read(b);
          //转换成对象
          return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
          throw new ClassNotFoundException(name);
        }
      }
    };
    Object obj = myLoader.loadClass("day6.ExtendTestClass").newInstance();
    //通过new来创建对象，此时对象是使用的
    ExtendTestClass extendTestClass = new ExtendTestClass();

    System.out.println("通过自定义加载器myLoader创建对象，类加载器为：" + obj.getClass().getClassLoader());
    System.out.println("通过new来创建对象，类加载器为：" + extendTestClass.getClass().getClassLoader());

    //通过不同的类加载器加载同一个类，查看instanceof结果是否一致
    System.out.println(obj instanceof ExtendTestClass);
    System.out.println(extendTestClass instanceof ExtendTestClass);
  }
}
