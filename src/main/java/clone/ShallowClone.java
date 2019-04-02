package clone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
public class ShallowClone implements Cloneable {

  private String name;

  private int age;

  private List<String> books;


  @Override
  public ShallowClone clone() {
    ShallowClone clone = null;
    try {
      clone = (ShallowClone) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return clone;
  }

  public ShallowClone deepClone() {
    ShallowClone clone = new ShallowClone();
    clone.name = this.name;
    clone.age = this.age;
    if (this.books != null) {
      clone.books = new ArrayList<>(this.books);
    }
    return clone;
  }


  public static void main(String[] args) {
    ShallowClone source = new ShallowClone();
    source.setName("source");
    source.setAge(28);
    List<String> list = new ArrayList<>();
    list.add("java");
    list.add("c++");
    source.setBooks(list);

    ShallowClone clone = source.clone();

    // 判断两个对象是否为同一个对象（即是否是新创建了一个实例）
    System.out.println(source == clone);

    // 修改一个对象的内容是否会影响另一个对象
    System.out.println("未修改\nsource: " + source.toString() + "\nclone:" + clone.toString());
    clone.setName("newName");
    clone.setAge(20);
    clone.getBooks().add("javascript");
    System.out.println("修改克隆对象后影响原对象list不影响非集合类型\nsource: " + source.toString() + "\nclone:" + clone.toString());

    source.setBooks(Arrays.asList("hello"));
    System.out.println("修改原对象集合类型后不影响克隆对象\nsource: " + source.toString() + "\nclone:" + clone.toString());
    /****************************************/
    ShallowClone deepclone = new ShallowClone();
    deepclone.setName("SourceName");
    deepclone.setAge(new Integer(1280));
    List<String> list1 = new ArrayList<>();
    list1.add("java");
    list1.add("c++");
    deepclone.setBooks(list1);


    ShallowClone cloneObj = deepclone.deepClone();
//    BeanCopier bc = BeanCopier.create(ShallowClone.class,ShallowClone.class,false);
//    ShallowClone cloneObj = new ShallowClone();
//    bc.copy(deepclone,cloneObj,null);


    // 判断两个对象是否为同一个对象（即是否是新创建了一个实例）
    System.out.println(deepclone == cloneObj);
    System.out.println("原对象：\nsource: " + deepclone.toString() + "\nclone:" + cloneObj.toString());
    // 修改一个对象的内容是否会影响另一个对象
    cloneObj.setName("newName");
    cloneObj.setAge(2000);
    cloneObj.getBooks().add("javascript");
    System.out.println("修改克隆对象\nsource: " + deepclone.toString() + "\nclone:" + cloneObj.toString());


    deepclone.getBooks().add("hello");
    System.out.println("修改原对象\nsource: " + deepclone.toString() + "\nclone:" + cloneObj.toString());
    cloneObj.getBooks().add("hello222222222222");
    System.out.println("修改克隆对象\nsource: " + deepclone.toString() + "\nclone:" + cloneObj.toString());
  }
}

