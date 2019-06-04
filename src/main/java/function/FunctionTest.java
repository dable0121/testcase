package function;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        System.out.println(addUp(Stream.of(1,3,5,-1)));
    }

    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, x) -> acc + x);
//        return numbers.reduce(0, Integer::sum);
//        return numbers.mapToInt(Integer::intValue).sum();
    }

    // Question 6
    public static int countLowercaseLetters(String string) {
        return (int) string.chars()
                .filter(Character::isLowerCase)
                .count();
    }

    // Question 7
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream()
                .max(Comparator.comparing(FunctionTest::countLowercaseLetters));
    }

}
