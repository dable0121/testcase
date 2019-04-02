package uuid;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Code16CharsGenerate {

  /**
   * 每位允许的字符
   */
  private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  /**
   * 生产一个指定长度的随机字符串
   *
   * @param length 字符串长度
   */
  private String generateRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    SecureRandom random = new SecureRandom();
    for (int i = 0; i < length; i++) {
      sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));
    }
    return sb.toString();
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    Set<String> check = new HashSet<>();
//    Collections.synchronizedSet(check);
    Code16CharsGenerate obj = new Code16CharsGenerate();
    Code16CharsGenerate obj1 = new Code16CharsGenerate();
    AtomicInteger ai = new AtomicInteger(0);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    new Thread(() -> {
      //生成2000000个随机字符串，检查是否出现重复
      for (int i = 0; i < 1000000; i++) {
        String s = obj.generateRandomString(16);
        System.out.println(i+"obj-"+s);
        if (check.contains(s)) {
          System.out.println("1111Repeated string found : " + s + "第" + i + "次");
          throw new RuntimeException("1111");
        } else {
          check.add(s);
        }
        ai.incrementAndGet();

      }
      System.out.println("111完了"+ai.toString());
      try {
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        String s = obj1.generateRandomString(16);
        System.out.println(i+"obj1-"+s);
        if (check.contains(s)) {
          System.out.println("2222Repeated string found : " + s + "第" + i + "次");
          throw new RuntimeException("2222");
        } else {
          check.add(s);
        }
        ai.incrementAndGet();

      }
      System.out.println("222完了"+ai.toString());
      try {
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }).start();
    try {
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
       System.out.println("真的没有重复，神奇了！");
       System.out.println("ai="+ai);
   }


}
