package function;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelDeal {

    private List<Integer> arrayListOfNumbers;
    private List<Integer> linkedListOfNumbers;


    public void init() {
        arrayListOfNumbers = new ArrayList<>();
        addNumbers(arrayListOfNumbers);

        linkedListOfNumbers = new LinkedList<>();
        addNumbers(linkedListOfNumbers);
    }

    private void addNumbers(List<Integer> container) {
        IntStream.range(0, 1_000_000)
                .forEach(container::add);
    }


    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                .map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }

    public int intermediateSumOfSquares() {
        return arrayListOfNumbers.parallelStream()
                .map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }

    /**
     * 效率最高
     *
     * @return
     */
    public int fastSumOfSquares() {
        return arrayListOfNumbers.parallelStream()//用效率高的集合类封装数据
                .mapToInt(x -> x * x)//拆包
                .sum();
    }
}
