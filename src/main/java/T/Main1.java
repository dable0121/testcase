package T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main1 {

  public static void main(String[] args) {
    List<? super Fruit> flist = new ArrayList<>();
    flist.add(new Apple());
    List<? extends Fruit> alist = new ArrayList(Arrays.asList(new Fruit()));
    Fruit f = alist.get(0);
  }

}
