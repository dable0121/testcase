package mvel;

import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CouponOfferImpl<T> implements ICoupon<T> {
    @Override
    public T offer(String formula, Map param) {
        return (T) MVEL.eval(formula, param);
    }
}
