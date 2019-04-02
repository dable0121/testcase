package thread;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
  private static long counter = 0;
  public static long addOne(){
    return ++counter;
  }
  private static AtomicLong atomicLong = new AtomicLong(0);
  public static long add(){
    long l = atomicLong.incrementAndGet();
    System.out.println(l);
    return l;
  }
  public static void main(String[] args) throws InterruptedException {
//    for(int i=0;i<100;i++){
//      new Thread(()->{try {
//        Thread.sleep(100);
//        if(Counter.addOne() == 100){
//          System.out.println("counter = 100");
//        }
//        System.out.println("-----"+Counter.counter);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }}).start();
//    }

    for(int i=0;i<100;i++){
      new Thread(()->{try {
        Thread.sleep(100);
        if(Counter.add()==100){
          System.out.println("count=100"+"thread="+Thread.currentThread().getName());
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }}).start();
    }
    Thread.sleep(500L);
    System.out.println("500后==="+Counter.atomicLong);


  }

}
