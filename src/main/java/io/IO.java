package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO {

  private static final String filePath = "E:\\test\\a.java";

  private void writeFile() throws IOException {
    DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(filePath)));
    dos.write('A');
    dos.writeInt(10);
    dos.writeBoolean(true);
    dos.writeByte(-1);
    dos.writeChar('B');
// 采用UTF-16BE
    dos.writeChars("中国");
    dos.writeUTF("中国");
    dos.writeDouble(20.10);
    dos.writeFloat(10.20f);
    dos.writeLong(100000000L);
    dos.close();
  }

  private void readFile() throws IOException {
    DataInputStream dis = new DataInputStream(new FileInputStream(new File(filePath)));
//怎么写就怎么读，不能不一致
    System.out.println(dis.read());
    System.out.println(dis.readInt());
    System.out.println(dis.readBoolean());
    System.out.println(dis.readByte());
    System.out.println(dis.readChar());
// chars
    System.out.println(dis.readChar());
    System.out.println(dis.readChar());
    System.out.println(dis.readUTF());
    System.out.println(dis.readDouble());
    System.out.println(dis.readFloat());
    System.out.println(dis.readLong());
    dis.close();
  }

  public static void main(String[] args) throws IOException {
    new IO().writeFile();
    new IO().readFile();
  }

}
