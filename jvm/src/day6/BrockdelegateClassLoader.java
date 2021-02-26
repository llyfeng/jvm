package day6;


/**
 * 破坏类加载机制的父委托机制 重写自定义加载器的loadClass方法，更改类加载的使用类加载器的顺序 首先查看缓存中有没有类信息，有则直接返回 没有的话在看类的名字
 * 是以什么开头，若是以java或者javax开头则直接用系统类加载器 否则先用自定义类加载器，如果没有加载到则查看有没有父加载器，有则使用父加载器，没有则使用系统加载器
 * 这样就破坏了加载顺序，先有自动以的加载器先加载而不是父类加载器
 */
public class BrockdelegateClassLoader extends MyClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        synchronized (getClassLoadingLock(name)) {
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass == null) {
                if (name.startsWith("java.") || name.startsWith("javax.")) {
                    try {
                        loadedClass = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    loadedClass = this.findClass(name);
                    if (loadedClass == null) {
                        if (getParent() != null) {
                            loadedClass = getParent().loadClass(name);
                        } else {
                            loadedClass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            } else {
                System.out.println("缓存中获得了class");
                return loadedClass;
            }
            if (loadedClass == null) {
                throw new ClassNotFoundException("the class " + name + " not found");
            }
            if (resolve) {
                resolveClass(loadedClass);
            }
            return loadedClass;

        }

    }

    public BrockdelegateClassLoader(String name) {
        super(name);
    }

    public BrockdelegateClassLoader() {
        super();
    }
}
