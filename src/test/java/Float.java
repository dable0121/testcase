import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

@Slf4j
public class Float {
    public static void main(String[] args) {
        log.info("{}", java.lang.Float.valueOf(999999.00f * 100).intValue());
        log.info("{}", new BigDecimal(9999999f).multiply(new BigDecimal(100)).intValue());
        log.info("{}", (byte) 2 == 2);
        log.info("{}", StandardCharsets.UTF_8.name());
        int i = 10;
        final int j = i;
        int k = i;
        i = 0;
        System.out.println(MessageFormat.format("i={0}j={1}k = {2}", i, j, k));
        String ii = "111";
        final String jj = ii;
        String kk = ii;
        ii = "222";
        kk = ii;
        System.out.println(MessageFormat.format("ii={0}jj={1}kk={2}", ii, jj, kk));
    }
}
