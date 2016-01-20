package com.yyw.bi.pis.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.model.ItemReport;
import com.yyw.bi.pis.util.Utility;

;

public class ItemReportDAO {

  private static Logger logger = Logger.getLogger(ItemReportDAO.class);

  public void saveItemReport(List<ItemReport> productReportList) {
    // tbd
  }

  public void exportItemReport(List<ItemReport> productReportList) {
    logger.info("exportProductReport, match size: " + productReportList.size());

    String title = "日期,一级类目,二级类目,三级类目,竞争对手,商品编号,商品名称,竞争对手商品名称,规格,竞争对手规格,价格,竞争对手价格,价格差,比例,高/平/低,销量,网址";
    String site ="";
    String dateTime = "";
    if(productReportList.size() > 0)
    {
      site = productReportList.get(0).getSite();
      dateTime =  productReportList.get(0).getDateTime();
    }
    String fileName = "./output/pis_" + site + "_" + dateTime + Utility.getCurrentDateTime("_HH_mm_ss") + ".csv";

    try {
      File file = new File(fileName);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(title);
      bw.newLine();
      //Collections.sort(productReportList);
      for (ItemReport product : productReportList) {
        bw.write(product.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }

}
