package function;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCount {

    public static Map<String, Long> count() {
        return Stream.of("John", "Paul", "George", "John",
                "Paul", "John").collect(Collectors.groupingBy(k -> k, Collectors.counting()));
    }

    public static void main(String[] args) {
        count().forEach((k,v)->System.out.println(k+":"+v));
        System.out.println(count());
    }
}
