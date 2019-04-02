package hash;

import db.LocalDBDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Dable on 2017/5/17 13:56.
 */
public class ConsistentHashing {

  /**
   * 待添加入Hash环的服务器列表
   */
//  public static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
  public static String[] servers = {"192.168.0.0:111", "192.168.0.1:111"};

  /**
   * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
   */
  private static List<String> realNodes = new LinkedList<>();

  /**
   * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
   */
  private static SortedMap<Integer, String> virtualNodes =
      new TreeMap<>();

  /**
   * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
   */
  private static final int VIRTUAL_NODES = 100;

  static {
    // 先把原始的服务器添加到真实结点列表中
    for (int i = 0; i < servers.length; i++) {
      realNodes.add(servers[i]);
    }

    // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
    for (String str : realNodes) {
      for (int i = 0; i < VIRTUAL_NODES; i++) {
        String virtualNodeName = str + "&&VN" + String.valueOf(i);
        int hash = getHash(virtualNodeName);
//        System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
        virtualNodes.put(hash, virtualNodeName);
      }
    }
    System.out.println();
  }

  /**
   * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
   */
  public static int getHash(String str) {
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < str.length(); i++) {
      hash = (hash ^ str.charAt(i)) * p;
    }
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;

    // 如果算出来的值为负数则取其绝对值
    if (hash < 0) {
      hash = Math.abs(hash);
    }
    return hash;
  }

  /**
   * 得到应当路由到的结点
   */
  public static String getServer(String node) {
    // 得到带路由的结点的Hash值
    int hash = getHash(node);
    // 得到大于该Hash值的所有Map
    SortedMap<Integer, String> subMap =
        virtualNodes.tailMap(hash);
    if (subMap.isEmpty()) {
      subMap = virtualNodes;
    }
    // 第一个Key就是顺时针过去离node最近的那个结点
    Integer i = subMap.firstKey();
    // 返回对应的虚拟节点名称，这里字符串稍微截取一下
    String virtualNode = subMap.get(i);
    return virtualNode.substring(0, virtualNode.indexOf("&&"));
  }

  public static List<String> getOrders() throws Exception {
    Connection conn = LocalDBDAO.getConnection();
    String sql = "select id from order_record";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
    List<String> petList = new ArrayList<>();
    while (rs.next()) {
      petList.add(rs.getString("id"));
    }
    petList.sort(String::compareTo);
    LocalDBDAO.closeAll(conn, stmt, rs);
    return petList;
  }

  public static void main(String[] args) throws Exception {
    String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
    for (int i = 0; i < nodes.length; i++) {
      System.out.println("[" + nodes[i] + "]的hash值为" +
          getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }
    //orderid分布到不同节点的情况
    Map<String, Integer> count = new HashMap<>();
    for (int i = 0; i < ConsistentHashing.servers.length; i++) {
      count.put(ConsistentHashing.servers[i], 0);
    }
    getOrders().forEach(id -> {
      int hash = ConsistentHashing.getHash(id);
      String server = ConsistentHashing.getServer(id);
      Integer c = count.get(server);
      c = c + 1;
      count.put(server, c);
    });
    count.forEach((k, v) -> System.out.println("服务器" + k + "上落下" + v + "个节点！"));
  }

}
