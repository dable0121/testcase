package inherit;

public class Test2 extends Test {

  public Test2() {
  }

  @Override
  public Test2 doIt(){
    return new Test2();
  }
}
