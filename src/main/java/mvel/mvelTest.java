package mvel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.mvel2.MVEL;

import java.util.Calendar;
import java.util.Map;


public class mvelTest {


    public static void main(String[] args) {
        //满减规则券
        String exp = "a > b?a-c;";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("a", "10000");//订单金额
        paramMap.put("b", "1000");//优惠阈值
        paramMap.put("c", "500");//优惠金额
        Object object = MVEL.eval(exp, paramMap);
        System.out.println(object);
        //折扣券
        exp = "a > b?a*c;";
        paramMap = Maps.newHashMap();
        paramMap.put("a", "1.0000");//订单金额
        paramMap.put("b", "0");//优惠阈值
        paramMap.put("c", "0.758");//优惠金额
        Object object1 = MVEL.eval(exp, paramMap);
        System.out.println(object1);
        //赠送时间券
        exp = "t + (x * 24*60*60*1000l)";
        paramMap = Maps.newHashMap();
        paramMap.put("t", java.util.Calendar.getInstance().getTimeInMillis());
        paramMap.put("x", "30");//时间毫秒
        Object object2 = MVEL.eval(exp, paramMap);
        System.out.println(object2);
        Calendar days = Calendar.getInstance();
        days.setTimeInMillis((Long) object2);
        System.out.println(
                days.get(Calendar.YEAR)
                        + "年"
                        + (days.get(Calendar.MONTH) + 1)
                        + "月" + days.get(Calendar.DATE)
                        + "日" + days.get(Calendar.HOUR_OF_DAY)
                        + "时" +
                        (days.get(Calendar.AM_PM) == Calendar.AM ? "上午" :
                                "下午") + days.get(Calendar.MINUTE) + "分" + days.get(Calendar.SECOND) +
                        "秒" + days.get(Calendar.MILLISECOND) + "毫秒");
    }

}
