package inherit;

public class Test {

  public Test() {
  }

  protected Test doIt(){
    System.out.println("test");
    return new Test();
  }
}
