package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("dev")
public class RedisTest {

  /**
   * logger
   */
  private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void getCustomerAccountInfo() {
    try {
      redisTemplate.opsForValue().increment("mykey", 1L);
    } catch (RedisSystemException e) {
      logger.error("增长失败错误");
    }

    logger.info("mykey===" + redisTemplate.opsForValue().get("mykey"));
  }

}