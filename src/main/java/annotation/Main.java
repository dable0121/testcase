package annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auth dable
 */
@SpringBootApplication(scanBasePackages = {"annotation"})
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
