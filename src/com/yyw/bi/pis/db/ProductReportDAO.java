package com.yyw.bi.pis.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.model.ProductReport;
import com.yyw.bi.pis.util.Utility;

;

public class ProductReportDAO {

  private static Logger logger = Logger.getLogger(ProductReportDAO.class);

  public void saveProductReport(List<ProductReport> productReportList) {
    // tbd
  }

  public void exportProductReport(List<ProductReport> productReportList) {
    logger.info("exportProductReport, match size: " + productReportList.size());

    String title = "一级类目,二级类目,竞争对手,商品编号,竞争对手名称,商品名称,格式化规格,药网价格,竞争对手价格,价格差,比例,高/平/低,销量,网址";
    String site ="";
    if(productReportList.size() > 0)
    {
      site = productReportList.get(0).getSite();
    }
    String fileName = "./output/pis_" + site + "_" + Utility.getCurrentDateTime("yyyyMMdd_HH_mm_ss") + ".csv";

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
      for (ProductReport product : productReportList) {
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
