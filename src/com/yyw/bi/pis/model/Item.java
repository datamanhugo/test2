package com.yyw.bi.pis.model;

import org.apache.log4j.Logger;

/*
 * YYW Item
 */
public class Item {

  private static Logger logger = Logger.getLogger(Item.class);

  private String id;
  private String productID;
  private String productName;
  private String approveNo;
  private String norm;
  private String levelOneCat;
  private String levelTwoCat;
  private String levelThreeCat;
  private String manufacturer;
  private String price;
  private String salesNum;
  private String date;
  private String site;
  private String url;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getApproveNo() {
    return approveNo;
  }

  public void setApproveNo(String approveNo) {
    this.approveNo = approveNo;
  }

  public String getNorm() {
    return norm;
  }

  public void setNorm(String norm) {
    this.norm = norm;
  }

  public String getLevelOneCat() {
    return levelOneCat;
  }

  public void setLevelOneCat(String levelOneCat) {
    this.levelOneCat = levelOneCat;
  }

  public String getLevelTwoCat() {
    return levelTwoCat;
  }

  public void setLevelTwoCat(String levelTwoCat) {
    this.levelTwoCat = levelTwoCat;
  }

  public String getLevelThreeCat() {
    return levelThreeCat;
  }

  public void setLevelThreeCat(String levelThreeCat) {
    this.levelThreeCat = levelThreeCat;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getSalesNum() {
    return salesNum;
  }

  public void setSalesNum(String salesNum) {
    this.salesNum = salesNum;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isMatched(CompetitorItem product) {
    if (product != null) {
      return product.isApproveNoMatched(this);
    }
    return false;
  }

  protected boolean isApproveNoMatched(CompetitorItem product) {
    if (product != null) {
      return product.isApproveNoMatched(this);
    }
    return false;
  }

  protected boolean isProductNameMatched(CompetitorItem product) {
    if (product != null) {
      return product.isApproveNoMatched(this);
    }
    return false;
  }

  protected boolean isNormMatched(CompetitorItem product) {
    if (product != null) {
      return product.isApproveNoMatched(this);
    }
    return false;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("productName:" + productName);
    buffer.append(",approveNo:" + approveNo);
    buffer.append(",norm:" + norm);
    buffer.append(",category:" + levelOneCat);
    buffer.append(",price:" + price);
    buffer.append(",site:" + site);
    return buffer.toString();
  }
}
