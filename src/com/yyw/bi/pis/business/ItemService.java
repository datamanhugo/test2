package com.yyw.bi.pis.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.db.ItemDAO;
import com.yyw.bi.pis.db.ItemReportDAO;
import com.yyw.bi.pis.model.CompetitorItem;
import com.yyw.bi.pis.model.Item;
import com.yyw.bi.pis.model.ItemReport;

public class ItemService {

  private static Logger logger = Logger.getLogger(ItemService.class);

  public List<ItemReport> processProductList(String date, String site) {
    List<ItemReport> productReportList = new ArrayList<ItemReport>();
    ItemDAO productDAO = new ItemDAO();
    List<Item> yywProductList = productDAO.getYywItemList("");
    logger.info("getYywItemList,size: " + yywProductList.size());
    List<CompetitorItem> competitorProductList = productDAO.getCompetitorItemList("", site);
    logger.info("getCompetitorItemList,size: " + competitorProductList.size());
    // for (CompetitorProduct competitorProduct : competitorProductList) {
    // for (Product yywProduct : yywProductList) {
    for (int j = 0; j < yywProductList.size(); j++) {
      for (int k = 0; k < competitorProductList.size(); k++) {
        Item yywProduct = yywProductList.get(j);
        CompetitorItem competitorProduct = competitorProductList.get(k);
        if (competitorProduct.isMatched(yywProduct)) {
          ItemReport productReport = new ItemReport();
          productReport.setDateTime(yywProduct.getDate());
          productReport.setLevelOneCat(yywProduct.getLevelOneCat());
          productReport.setLevelTwoCat(yywProduct.getLevelTwoCat());
          productReport.setLevelThreeCat(yywProduct.getLevelThreeCat());
          productReport.setSite(competitorProduct.getSite());
          productReport.setProductID(yywProduct.getProductID());
          productReport.setProductName(yywProduct.getProductName());
          productReport.setCompetitorProductName(competitorProduct.getProductName());
          productReport.setNorm(yywProduct.getNorm());
          productReport.setCompetitorNorm(competitorProduct.getNorm());
          productReport.setPrice(yywProduct.getPrice());
          productReport.setCompetitorPrice(competitorProduct.getPrice());
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
              productReport.setAverage("平");
            } else if (diffPrice > 0) {
              productReport.setAverage("高");
            } else {
              productReport.setAverage("中");
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

  public void saveProductReport(List<ItemReport> productReportList) {
    ItemReportDAO productReportDAO = new ItemReportDAO();
    productReportDAO.saveItemReport(productReportList);
  }

  public void exportProductReport(List<ItemReport> productReportList) {
    ItemReportDAO productReportDAO = new ItemReportDAO();
    productReportDAO.exportItemReport(productReportList);
  }

  public void dumpCounterStatistics() {
    logger.info("dumpStatistics, counter_1: " + CompetitorItem.counter_1 + ",counter_2:"
        + CompetitorItem.counter_2 + ",counter_3:" + CompetitorItem.counter_3 + ",counter_4:"
        + CompetitorItem.counter_4 + ",counter_5:" + CompetitorItem.counter_5);
  }

  public void clearCounterStatistics() {
    CompetitorItem.counter_1 = 0;
    CompetitorItem.counter_2 = 0;
    CompetitorItem.counter_3 = 0;
    CompetitorItem.counter_4 = 0;
    CompetitorItem.counter_5 = 0;
  }

}
