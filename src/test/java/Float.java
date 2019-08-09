import java.math.BigDecimal;

public class Float {
    public static void main(String[] args) {
        System.out.println(java.lang.Float.valueOf(999999.00f * 100).intValue());
        System.out.println(new BigDecimal(9999999f).multiply(new BigDecimal(100)).intValue());
    }
}
