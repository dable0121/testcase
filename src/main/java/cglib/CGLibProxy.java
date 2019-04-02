package cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


public class CGLibProxy<T> implements MethodInterceptor {

  //维护目标对象
  private Class<T> target;

  public CGLibProxy(Class target) {
    this.target = target;
  }

  //给目标对象创建一个代理对象
  public Object getProxyInstance() {
    //1.工具类
    Enhancer en = new Enhancer();
    //2.设置父类
    en.setSuperclass(target);
    //3.设置回调函数
    en.setCallback(this);
    //4.创建子类(代理对象)
    return en.create();

  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args,
      MethodProxy proxy) throws Throwable {
    return proxy.invokeSuper(obj, args);
  }
}
