package function.lamda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sorted {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> stillOrdered = numbers.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
// 顺序得到了保留
        assertEquals(Arrays.asList(2, 3, 4, 5), stillOrdered);


        Set<Integer> unordered = new HashSet<>(numbers);
        List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
// 顺序得不到保证
        assertTrue(stillUnordered.contains(2));
        assertTrue(stillUnordered.contains(3));
        assertTrue(stillUnordered.contains(4));
        assertTrue(stillUnordered.contains(5));
        TreeSet<Integer> set = Stream.of(6, 1, 4, 9, 4, 7, 4).collect(Collectors.toCollection(TreeSet::new));
        set.stream().forEachOrdered(System.out::println);
        assertEquals(new TreeSet(Arrays.asList(6, 1, 4, 9, 4, 7, 4)),set);
    }
}
