package cglib;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.CallbackHelper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;

public class Cglib {

  @Test
  /**
   * Enhancer可能是CGLIB中最常用的一个类，和Java1.3动态代理中引入的Proxy类差不多(如果对Proxy不懂，可以参考这里)。
   * 和Proxy不同的是，Enhancer既能够代理普通的class，也能够代理接口。
   * Enhancer创建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方法）。
   * Enhancer不能够拦截final方法，例如Object.getClass()方法，这是由于Java final方法语义决定的。
   * 基于同样的道理，Enhancer也不能对fianl类进行代理操作。
   * 这也是Hibernate为什么不能持久化final class的原因
   */
  public void enhancerTest() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
          throws Throwable {
        System.out.println("before method run...");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after method run...");
        return result;
      }
    });
    SampleClass sample = (SampleClass) enhancer.create();
    sample.test();
  }


  @Test
  /**
   * FixedValue用来对所有拦截的方法返回相同的值，从输出我们可以看出来，Enhancer对非final方法test()、toString()、hashCode()进行了拦截，没有对getClass进行拦截。
   * 由于hashCode()方法需要返回一个Number，但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。
   Enhancer.setSuperclass用来设置父类型，从toString方法可以看出，使用CGLIB生成的类为被代理类的一个子类，形如：SampleClass$$EnhancerByCGLIB$$e3ea9b7
   Enhancer.create(Object…)方法是用来创建增强对象的，其提供了很多不同参数的方法用来匹配被增强类的不同构造方法。
   （虽然类的构造放法只是Java字节码层面的函数，但是Enhancer却不能对其进行操作。
   Enhancer同样不能操作static或者final类）。
   我们也可以先使用Enhancer.createClass()来创建字节码(.class)，然后用字节码动态的生成增强后的对象。
   */
  public void fixedValueTest() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallback(new FixedValue() {
      @Override
      public Object loadObject() throws Exception {
        return "Hello cglib";
      }
    });
    SampleClass proxy = (SampleClass) enhancer.create();
    System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
    System.out.println(proxy.toString());
    System.out.println(proxy.getClass());
    System.out.println(proxy.hashCode());
  }

  @Test
  public void callbackFilterTest() throws Exception {
    Enhancer enhancer = new Enhancer();
    CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
      @Override
      protected Object getCallback(Method method) {
        if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
          return new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
              return "Hello cglib";
            }
          };
        } else {
          return NoOp.INSTANCE;
        }
      }
    };
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallbackFilter(callbackHelper);
    enhancer.setCallbacks(callbackHelper.getCallbacks());
    SampleClass proxy = (SampleClass) enhancer.create();
    System.out.println(proxy.test(null));
    System.out.println(proxy.toString());
    System.out.println(proxy.hashCode());
    Assertions.assertEquals("Hello cglib", proxy.test(null));
    Assertions.assertEquals("Hello cglib", proxy.toString());
    System.out.println(proxy.hashCode());
  }


}
