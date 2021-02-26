package day5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author lyf
 * @Date 2021-02-24 14:47
 * @Description:
 */
public class ThreadsInit {

  static CountDownLatch countDownLatch = new CountDownLatch(6);

  static class Parent {

    static {
      try {
        System.out.println(System.currentTimeMillis() + "---Parent类被初始化");
        TimeUnit.SECONDS.sleep(6);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 6; i++) {
      new Thread(() -> {
        new Parent();
        countDownLatch.countDown();
      }).start();
    }
    countDownLatch.await();
    System.out.println("main函数结束");
  }
}
