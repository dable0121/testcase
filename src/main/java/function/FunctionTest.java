package function;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionTest {
    public final static ThreadLocal<DateFormat> formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        System.out.println(formatter.get().format(new Date()));
    }

}
