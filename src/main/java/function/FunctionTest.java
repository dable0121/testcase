package function;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FunctionTest {

    //    abstract boolean check(Predicate<Integer> predicate);
    abstract boolean check(IntPred predicate);

    private final static ThreadLocal<DateFormat> formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        System.out.println(formatter.get().format(new Date()));
        Runnable helloWorld = () -> System.out.println(" hello world ");
        Thread t = new Thread(helloWorld);
        t.start();
        JButton button = new JButton();
        button.addActionListener(event ->
                System.out.println(event.getActionCommand()));
    }

}
