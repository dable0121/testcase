package bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

public class BloomTest {

  @Test
  public void guavaTest() {
    BloomFilter<Integer> filter = BloomFilter.create(
        Funnels.integerFunnel(),
        100000000,
        0.001);

    //放数字
    for (int i = 0; i < 100000000; i++) {
      filter.put(i);
    }
    System.out.println("我放完了数值，开始判断---");
    long star = System.currentTimeMillis();
    Assert.assertTrue(filter.mightContain(1));
    Assert.assertTrue(filter.mightContain(2));
    Assert.assertTrue(filter.mightContain(3));
    Assert.assertFalse(filter.mightContain(100000000));
    Assert.assertTrue(filter.negate().test(1000000001));
    long end = System.currentTimeMillis();
    System.out.println("执行时间：" + (end - star) + "毫秒");
  }
}
