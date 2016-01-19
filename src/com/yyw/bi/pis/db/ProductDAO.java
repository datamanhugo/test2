package com.yyw.bi.pis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.model.CompetitorProduct;
import com.yyw.bi.pis.model.Product;

public class ProductDAO {

  private static Logger logger = Logger.getLogger(ProductDAO.class);

  // public static String YYW_SQL =
  // "select PROD_NAME from crawler.yyw_prod_info_new where date(CREATE_date) = '2016-01-15' limit 0,50 ";
  public static String YYW_SQL = "select PROD_ID, PROD_NAME,APPROVALNUM,NEW_NORMS,CAT1_NAME,CAT2_NAME, originalPrice,PM_NUM from crawler.yyw_prod_info_new where date(CREATE_date) = '2016-01-15'";
  // public static String CRAWLER_SQL =
  // "select title from crawler.medicine_info where site='康爱多官网' and date='2016-01-15' AND type='中西药' limit 0,50 ";
  public static String COMPETITOR_SQL = "select title,pzwh,guige,type,price,site, detailUrl from crawler.medicine_info where date='2016-01-15'";

  public List<Product> getYywProductList(String date) {
    List<Product> productList = new ArrayList<Product>();
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
        String productID = rs.getString("PROD_ID");
        String productName = rs.getString("PROD_NAME");
        String approveNo = rs.getString("APPROVALNUM");
        String norm = rs.getString("NEW_NORMS");
        String type = rs.getString("CAT1_NAME");
        String levelTwoCat = rs.getString("CAT2_NAME");
        String price = "" + rs.getDouble("originalPrice");
        String salesNum = "" + rs.getInt("PM_NUM");
        String site = "YYW";
        if (type == null) {
          // System.out.println("productName:" + productName
          // + " type is null");
        } else if (type.equalsIgnoreCase("中西药品")) {
          // System.out.println("中西药品");
          Product product = new Product();
          product.setProductID(productID);
          product.setProductName(productName);
          product.setApproveNo(approveNo);
          product.setNorm(norm);
          product.setType(type);
          product.setLevelTwoCat(levelTwoCat);
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

  public List<CompetitorProduct> getCompetitorProductList(String date, String crawlerSite) {
    List<CompetitorProduct> productList = new ArrayList<CompetitorProduct>();
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
          // System.out.println("中西药品");
          CompetitorProduct product = new CompetitorProduct();
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
