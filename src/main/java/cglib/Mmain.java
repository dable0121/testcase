package cglib;

public class Mmain {

  public static void main(String[] args) {
    CGLibProxy<RealType> p = new CGLibProxy<>(RealType.class);
    RealType proxyInstance = (RealType) p.getProxyInstance();
    System.out.println(proxyInstance.print().toJSONString());
  }

}
