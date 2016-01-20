package com.yyw.bi.pis;

import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.business.ItemService;
import com.yyw.bi.pis.model.ItemReport;

public class Main {

  private static Logger logger = Logger.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("start...");
    String site = "¿µ°®¶à¹ÙÍø";
    ItemService productService = new ItemService();
    productService.clearCounterStatistics();
    List<ItemReport> productList = productService.processProductList("", site);
    productService.exportProductReport(productList);
    productService.dumpCounterStatistics();
    logger.info("end...");
  }

}
