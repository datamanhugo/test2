package com.yyw.bi.pis.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.db.ProductDAO;
import com.yyw.bi.pis.db.ProductReportDAO;
import com.yyw.bi.pis.model.CompetitorProduct;
import com.yyw.bi.pis.model.Product;
import com.yyw.bi.pis.model.ProductReport;

public class ProductService {

  private static Logger logger = Logger.getLogger(ProductService.class);

  public List<ProductReport> processProductList(String date, String site) {
    List<ProductReport> productReportList = new ArrayList<ProductReport>();
    ProductDAO productDAO = new ProductDAO();
    List<Product> yywProductList = productDAO.getYywProductList("");
    logger.info("getYywProductList,size: " + yywProductList.size());
    List<CompetitorProduct> competitorProductList = productDAO.getCompetitorProductList("", site);
    logger.info("getCompetitorProductList,size: " + competitorProductList.size());
    // for (CompetitorProduct competitorProduct : competitorProductList) {
    // for (Product yywProduct : yywProductList) {
    for (int j = 0; j < yywProductList.size(); j++) {
      for (int k = 0; k < competitorProductList.size(); k++) {
        Product yywProduct = yywProductList.get(j);
        CompetitorProduct competitorProduct = competitorProductList.get(k);
        if (competitorProduct.isMatched(yywProduct)) {
          ProductReport productReport = new ProductReport();
          productReport.setLevelOneCat(yywProduct.getType());
          productReport.setLevelTwoCat(yywProduct.getLevelTwoCat());
          productReport.setSite(competitorProduct.getSite());
          productReport.setProductID(yywProduct.getProductID());
          productReport.setSiteProductName(competitorProduct.getProductName());
          productReport.setProductName(yywProduct.getProductName());
          productReport.setNorm(yywProduct.getNorm());
          productReport.setOriginalPrice(yywProduct.getPrice());
          productReport.setPrice(competitorProduct.getPrice());
          productReport.setSalesNum(yywProduct.getSalesNum());
          productReport.setCompetitorUrl(competitorProduct.getUrl());
          try {
            double yywPrice = Double.parseDouble(yywProduct.getPrice());
            double crawlerPrice = Double.parseDouble(competitorProduct.getPrice());
            double diffPrice = yywPrice - crawlerPrice;
            double diffPercent = diffPrice / yywPrice;
            productReport.setDiffPrice("" + diffPrice);
            productReport.setDiffPecent("" + diffPercent);
            if (diffPrice == 0) {
              productReport.setAverage("Æ½");
            } else if (diffPrice > 0) {
              productReport.setAverage("¸ß");
            } else {
              productReport.setAverage("µÍ");
            }
          } catch (Exception ex) {
            productReport.setDiffPrice("");
            productReport.setDiffPecent("");
            productReport.setAverage("");
          }
          if (productReportList.contains(productReport)) {
            logger.debug("processProductList, dup YW product id = " + yywProduct.getProductID()
                + ", YW product name = " + yywProduct.getProductName() + ", competitor product name = "
                + competitorProduct.getProductName() + ", competitor product url = " + competitorProduct.getUrl());
          } else {
            productReportList.add(productReport);
          }
        }
        continue;
      }
    }
    return productReportList;
  }

  public void saveProductReport(List<ProductReport> productReportList) {
    ProductReportDAO productReportDAO = new ProductReportDAO();
    productReportDAO.saveProductReport(productReportList);
  }

  public void exportProductReport(List<ProductReport> productReportList) {
    ProductReportDAO productReportDAO = new ProductReportDAO();
    productReportDAO.exportProductReport(productReportList);
  }

  public void dumpCounterStatistics() {
    logger.info("dumpStatistics, counter_1: " + CompetitorProduct.counter_1 + ",counter_2:"
        + CompetitorProduct.counter_2 + ",counter_3:" + CompetitorProduct.counter_3 + ",counter_4:"
        + CompetitorProduct.counter_4 + ",counter_5:" + CompetitorProduct.counter_5);
  }

  public void clearCounterStatistics() {
    CompetitorProduct.counter_1 = 0;
    CompetitorProduct.counter_2 = 0;
    CompetitorProduct.counter_3 = 0;
    CompetitorProduct.counter_4 = 0;
    CompetitorProduct.counter_5 = 0;
  }

}
