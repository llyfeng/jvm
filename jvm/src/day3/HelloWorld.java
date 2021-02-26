package day3;

public class HelloWorld {
        static {
            System.out.println("helloworld initialization");
        }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;
    public String say() {
        return "hello world被修改了";
    }
}
