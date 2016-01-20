package com.yyw.bi.pis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.model.CompetitorItem;
import com.yyw.bi.pis.model.Item;

public class ItemDAO {

  private static Logger logger = Logger.getLogger(ItemDAO.class);

  public static String YYW_SQL = "select CREATE_date, PROD_ID, PROD_NAME,APPROVALNUM,NEW_NORMS,CAT1_NAME,CAT2_NAME, CAT3_NAME,originalPrice,PM_NUM from crawler.yyw_prod_info_new where date(CREATE_date) = '2016-01-18'";
  public static String COMPETITOR_SQL = "select title,pzwh,guige,type,price,site, detailUrl from crawler.medicine_info where date='2016-01-18'";

  public List<Item> getYywItemList(String date) {
    List<Item> productList = new ArrayList<Item>();
    DBConnectionManager connMgr = DBConnectionManager.getInstance();
    Connection connection = null;
    Statement statement = null;
    try {
      connection = connMgr.getConnection();
      statement = connection.createStatement();
      String sql = YYW_SQL;
      ResultSet rs = statement.executeQuery(sql);
      logger.debug("SQL:" + sql);

      while (rs.next()) {
        String dataTime = rs.getDate("CREATE_date").toString();
        String productID = rs.getString("PROD_ID");
        String productName = rs.getString("PROD_NAME");
        String approveNo = rs.getString("APPROVALNUM");
        String norm = rs.getString("NEW_NORMS");
        String levelOneCat = rs.getString("CAT1_NAME");
        String levelTwoCat = rs.getString("CAT2_NAME");
        String levelThreeCat = rs.getString("CAT3_NAME");
        String price = "" + rs.getDouble("originalPrice");
        String salesNum = "" + rs.getInt("PM_NUM");
        String site = "YYW";
        if (levelOneCat == null) {
          // System.out.println("productName:" + productName
          // + " type is null");
        } else if (levelOneCat.equalsIgnoreCase("中西药品")) {
          Item product = new Item();
          product.setDate(dataTime);
          product.setProductID(productID);
          product.setProductName(productName);
          product.setApproveNo(approveNo);
          product.setNorm(norm);
          product.setLevelOneCat(levelOneCat);
          product.setLevelTwoCat(levelTwoCat);
          product.setLevelThreeCat(levelThreeCat);
          product.setPrice(price);
          product.setSalesNum(salesNum);
          product.setSite(site);
          productList.add(product);
        }
      }
      rs.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (statement != null) {
        connMgr.releaseDBStatement(statement);
      }
      if (connection != null) {
        connMgr.releaseDBConnection(connection);
      }
    }
    return productList;
  }

  public List<CompetitorItem> getCompetitorItemList(String date, String crawlerSite) {
    List<CompetitorItem> productList = new ArrayList<CompetitorItem>();
    DBConnectionManager connMgr = DBConnectionManager.getInstance();
    Connection connection = null;
    Statement statement = null;
    try {
      connection = connMgr.getConnection();
      statement = connection.createStatement();
      String sql = COMPETITOR_SQL;
      ResultSet rs = statement.executeQuery(sql);
      logger.debug("SQL:" + sql);

      while (rs.next()) {
        String productID = null;
        String productName = rs.getString("title");
        String approveNo = rs.getString("pzwh");
        String norm = rs.getString("guige");
        String type = rs.getString("type");
        String price = rs.getString("price");
        String site = rs.getString("site");
        String url = rs.getString("detailUrl");
        if (type == null || site == null) {
          // System.out.println("productName:" + productName
          // + " type is null");
        } else if (type.equalsIgnoreCase("中西药") && site.equalsIgnoreCase(crawlerSite)) {
          CompetitorItem product = new CompetitorItem();
          product.setProductID(productID);
          product.setProductName(productName);
          product.setApproveNo(approveNo);
          product.setNorm(norm);
          product.setType(type);
          product.setPrice(price);
          product.setSite(site);
          product.setUrl(url);
          productList.add(product);
        }
      }
      rs.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (statement != null) {
        connMgr.releaseDBStatement(statement);
      }
      if (connection != null) {
        connMgr.releaseDBConnection(connection);
      }
    }
    return productList;
  }

}
