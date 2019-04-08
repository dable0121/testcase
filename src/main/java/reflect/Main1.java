package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main1 {

  private static Class getC() {
    return new Main1().getClass();
  }

  public static void main(String[] args) {
    System.out.println(Main1.class);
    System.out.println(Main1.class.getPackage());
    System.out.println(getC().getPackage());
    for(Method m:Main1.class.getMethods()){
      System.out.println("allmethod|"+m.getName()+"="+m.getModifiers());
      System.out.println("allmethod|"+m.getName()+"="+Modifier.isPrivate(m.getModifiers()));
      System.out.println("allmethod|"+m.getName()+"="+Modifier.toString(m.getModifiers()));
    }
    for(Method m:Main1.class.getDeclaredMethods()){
      System.out.println("Declaredmethod|"+m.getName()+"="+m.getModifiers());
      System.out.println("Declaredmethod|"+m.getName()+"="+Modifier.isPrivate(m.getModifiers()));
      System.out.println("Declaredmethod|"+m.getName()+"="+Modifier.toString(m.getModifiers()));
    }
  }

}
