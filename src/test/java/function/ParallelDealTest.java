package function;

import org.junit.Test;

public class ParallelDealTest {

    @Test
    public void fastSumOfSquares() {
        ParallelDeal parallelDeal = new ParallelDeal();
        parallelDeal.init();
        System.out.println("快速计算平方并行结果：" + parallelDeal.fastSumOfSquares());
    }
}