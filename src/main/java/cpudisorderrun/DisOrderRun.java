package cpudisorderrun;

public class DisOrderRun {
    private static int a, b, x, y;

    /**
     * 第15194638次执行，x=0,y=0
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if (x == 0 && y == 0) {
                System.out.println("第" + i + "次执行，x=" + x + ",y=" + y);
                break;
            } else {
                continue;
            }

        }
    }
}
