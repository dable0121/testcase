package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j//不用每次都写logger变量了
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        log.info("1---" + map.put("key", "oldValue"));
        log.info("2---" + map.put("key", "newValue"));
        log.info("3---" + map.put("key", "newValue2"));
//        log.info("4---" + map.put("key1", null));
//        log.info("5---" + map.put(null, ""));
    }
}
