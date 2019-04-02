package clone;

import org.springframework.cglib.beans.BeanCopier;

public class Main {

  public static void main(String[] args) {
    Source s = new Source();
    s.setTimeStamp("1554192976000");
    Target t = new Target();
    BeanCopier beanCopier = BeanCopier.create(Source.class, Target.class, false);//false时类型不同不拷贝
    beanCopier.copy(s,t,null);
    System.out.println(t.toString());
  }

}
