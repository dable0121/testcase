package redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @Auth dable
 */
@SpringBootApplication(scanBasePackages = {"redis"})
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
