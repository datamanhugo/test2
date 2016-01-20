package com.yyw.bi.pis.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Test {

  public static void write() {
    String title = "一级类目,二级类目,竞争对手,商品编号,竞争对手名称,商品名称,格式化规格,药网价格,竞争对手价格,价格差,比例,高/平/低,销量,网址";
    String fileName = "./output/pis" + Utility.getCurrentDateTime("_HH_mm_ss") + ".csv";
    try {
      File file = new File(fileName);
      if (!file.exists()) {
        file.createNewFile();
      }
      //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
      Writer bw = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
      bw.write(title);
      bw.flush();
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }

  public static void main(String[] args) {
    write();
  }

}
