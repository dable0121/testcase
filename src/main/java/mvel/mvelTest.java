package mvel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

               /* //满减规则券
        String exp = "curprice>thresholdAmount?curprice-reduceCash;";
        JSONObject json = new JSONObject();
        json.put("curprice", "10000");//订单金额
        json.put("thresholdAmount", "1000");//优惠阈值
        json.put("reduceCash", "500");//优惠金额
        Object object = MVEL.eval(exp, json);
        System.out.println(object);
        //折扣券
        exp = "curprice*discount;";
        json = new JSONObject();
        json.put("curprice", "1.0000");//订单金额
        json.put("discount", "0.758");//优惠金额
        Object object1 = MVEL.eval(exp, json);
        System.out.println(object1);
        //赠送时间券
        exp = "current + (days * 24*3600000l)";
        json = new JSONObject();
        json.put("current", java.util.Calendar.getInstance().getTimeInMillis());
        json.put("days", "30");//时间毫秒
        Object object2 = MVEL.eval(exp, json);
        System.out.println(object2);
        Calendar days = Calendar.getInstance();
        days.setTimeInMillis((Long) object2);
        System.out.println(
                days.get(Calendar.YEAR)
                        + "年"
                        + (days.get(Calendar.MONTH) + 1)
                        + "月" + days.get(Calendar.DATE)
                        + "日" +
                        (days.get(Calendar.AM_PM) == Calendar.AM ? "上午" :
                                "下午") + days.get(Calendar.HOUR_OF_DAY)
                        + "时" + days.get(Calendar.MINUTE) + "分" + days.get(Calendar.SECOND) +
                        "秒" + days.get(Calendar.MILLISECOND) + "毫秒");
*/
        CouponOfferImpl impl = new CouponOfferImpl();
        String exp2 = "curprice>thresholdAmount?curprice-reduceCash";
        JSONObject json = new JSONObject();
        json.put("curprice", "10000");//订单金额
        json.put("thresholdAmount", "1000");//优惠阈值
        json.put("reduceCash", "500");//优惠金额
        System.out.println(impl.offer(exp2,json));
        exp2 = "curprice*discount";
        json = new JSONObject();
        json.put("curprice", "1.0000");//订单金额
        json.put("discount", "0.758");//优惠金额
        System.out.println(impl.offer(exp2,json));
        exp2 = "current + (days * 24*3600000l)";
        json = new JSONObject();
        json.put("current", java.util.Calendar.getInstance().getTimeInMillis());
        json.put("days", "30");//时间毫秒
        System.out.println(impl.offer(exp2,json));
        Calendar d = Calendar.getInstance();
        d.setTimeInMillis((Long) impl.offer(exp2,json));
        System.out.println(
                d.get(Calendar.YEAR)
                        + "年"
                        + (d.get(Calendar.MONTH) + 1)
                        + "月"
                        + d.get(Calendar.DATE)
                        + "日"
                        + (d.get(Calendar.AM_PM) == Calendar.AM ? "上午" :  "下午")
                        + d.get(Calendar.HOUR_OF_DAY)
                        + "时"
                        + d.get(Calendar.MINUTE)
                        + "分"
                        + d.get(Calendar.SECOND)
                        +"秒"
                        + d.get(Calendar.MILLISECOND)
                        + "毫秒");
    }

}
