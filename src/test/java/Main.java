import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : dable
 * @version V1.0
 * @Description: 测试用
 * @date Date : 20200421 16:35
 */
@Slf4j
public class Main {

  @Test
  public void test() {
    JSONObject json = new JSONObject() {{
      put("invoiceBornTime", 1587458215000L);
    }};
    LocalDateTime invoiceBornTime = json.getDate("invoiceBornTime")
        .toInstant()
        .atZone(ZoneId.systemDefault()).toLocalDateTime();
    log.info(invoiceBornTime.toString());
  }

  public static void main(String[] args) {
    String[] s = new String[]{"33333", "444444"};
    log.info(JSON.toJSONString(s));

  }
}
