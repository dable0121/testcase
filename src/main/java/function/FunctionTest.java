package function;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class FunctionTest {

    //    abstract boolean check(Predicate<Integer> predicate);
    abstract boolean check(IntPred predicate);

    private final static ThreadLocal<DateFormat> formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static void testMultiReduce() {
        ArrayList<List<String>> strings = new ArrayList<>();
        strings.add(Arrays.asList("1", "2", "3", "4"));//10
        strings.add(Arrays.asList("2", "3", "4", "5"));//14
        strings.add(Arrays.asList("3", "4", "5", "6"));//18

        // 非并行流
        Integer reduce1 = strings.stream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // 非并行流，不会执行第三个参数
                    System.out.println("u----:" + u);
                    // 这里的返回值并没有影响返回结果
                    return null;
                });
        System.out.println("add with no parallel:" + reduce1);

        // 并行流
        Integer reduce2 = strings.parallelStream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // u，t分别为并行流每个子任务的结果
                    System.out.println("u----:" + u);
                    System.out.println("t----:" + t);
                    return u + t;
                });
        System.out.println("add with parallel:" + reduce2);
    }

    public static void main(String[] args) {
        System.out.println(formatter.get().format(new Date()));
        Runnable helloWorld = () -> System.out.println(" hello world ");
        Thread t = new Thread(helloWorld);
        t.start();
        JButton button = new JButton();
        button.addActionListener(event ->
                System.out.println(event.getActionCommand()));
        System.out.println(addUp(Stream.of(1,3,5,-1)));


        //filter test
        filter(Stream.of("abc","bbb","ddddd"),x->x.contains("b")).forEach(System.out::println);

        //reduce
        testMultiReduce();
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

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(
                new ArrayList<>(),
                (acc, x) -> {
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        },
                (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial,
                (List<I> acc, I x) -> {
                    if (predicate.test(x)) {
                        // We are copying data from acc to new list instance. It is very inefficient,
                        // but contract of Stream.reduce method requires that accumulator function does
                        // not mutate its arguments.
                        // Stream.collect method could be used to implement more efficient mutable reduction,
                        // but this exercise asks to use reduce method explicitly.
                        List<I> newAcc = new ArrayList<>(acc);
                        newAcc.add(x);
                        return newAcc;
                    } else {
                        return acc;
                    }
                },
                FunctionTest::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        // We are copying left to new list to avoid mutating it.
        List<I> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }

}
