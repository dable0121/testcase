package cglib;

import com.alibaba.fastjson.JSONObject;

public class RealType {

  public JSONObject print(){
    System.out.println("我被调用了" );
    JSONObject js = new JSONObject();
    js.put("aaa","你好");
    return js;
  }

}
