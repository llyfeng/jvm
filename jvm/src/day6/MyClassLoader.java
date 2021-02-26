package day6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

  private final static Path DEFAULT_PATH = Paths.get("/Users/doudou/workspace", "jvm/jvm/src");
  private final Path classdir;

  public MyClassLoader() {
    super();
    classdir = DEFAULT_PATH;
  }

  public MyClassLoader(String classPath) {
    super();
    this.classdir = Paths.get(classPath);
  }

  public MyClassLoader(ClassLoader parent, String classPath) throws ClassNotFoundException {
    super(parent);
    classdir = Paths.get(classPath);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] bytes = this.readClassBytes(name);
    if (null == bytes || bytes.length == 0) {
      throw new ClassNotFoundException("Can not load the class" + name);
    }
    return this.defineClass(name, bytes, 0, bytes.length);

  }

  private byte[] readClassBytes(String name) throws ClassNotFoundException {
    String classPath = name.replace(".", "/");
    Path classPullPath = classdir.resolve(Paths.get(classPath) + ".class");
    if (!classPullPath.toFile().exists()) {
      throw new ClassNotFoundException("The class" + name + "not found.");
    }
    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      Files.copy(classPullPath, byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();

    } catch (IOException e) {
      throw new ClassNotFoundException("load the class" + name + "occur error." + e);
    }
  }

  @Override
  public String toString() {
    return "My  ClassLoader";
  }
}
