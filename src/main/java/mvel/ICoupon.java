package mvel;

import java.util.Map;

public interface ICoupon<T> {
    T offer(String formula, Map param);
}
