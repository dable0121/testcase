package sign;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;


@Slf4j
public class Sha256 {

    @SneakyThrows
    public static void main(String[] args) {
        //3ab03839472a23e4efce443a65c398951c3cdab382efe7259cff0b0b4e3069f2
        String s = DigestUtils.sha256Hex("fdfdfdsfdsfsdfsdf&oR8117iSObyojvJKUPM3kKkztYp8fn5e".getBytes(StandardCharsets.UTF_8.name()));
        log.info("sign={}", s);
    }
}
