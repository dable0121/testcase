import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Float {
    public static void main(String[] args) {
        log.info("{}", java.lang.Float.valueOf(999999.00f * 100).intValue());
        log.info("{}", new BigDecimal(9999999f).multiply(new BigDecimal(100)).intValue());
        log.info("{}", (byte) 2 == 2);
        log.info("{}", StandardCharsets.UTF_8.name());
    }
}
