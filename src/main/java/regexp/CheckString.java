package regexp;

import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckString {

  public static void main(String[] args) {
    String regex = "^[a-zA-Z]*\\d+$";
    System.out.println("h232323".matches(regex));
    System.out.println("232323".matches(regex));
    System.out.println("h23xxxxxxx".matches(regex));
    System.out.println("23xxxxxxx".matches(regex));

    Map uBefore = new HashMap<>();
    uBefore.put("date",new Date());
    String s = JSON.toJSONString(uBefore);
    System.out.println(s);
    Map uEnd = JSON.parseObject(s,Map.class);
    System.out.println(uEnd);
  }

}
