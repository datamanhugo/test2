package com.yyw.bi.pis.model;

import org.apache.log4j.Logger;

import com.yyw.bi.pis.util.Utility;

/*
 * Competitor product base class, if competitor have diffent rule, override this class
 */
public class CompetitorItem {

  private static Logger logger = Logger.getLogger(CompetitorItem.class);

  public static int counter_1 = 0;
  public static int counter_2 = 0;
  public static int counter_3 = 0;
  public static int counter_4 = 0;
  public static int counter_5 = 0;
  public static int counter_6 = 0;

  private String id;
  private String productID;
  private String productName;
  private String approveNo;
  private String norm;
  private String type;
  private String levelTwoCat;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLevelTwoCat() {
    return levelTwoCat;
  }

  public void setLevelTwoCat(String levelTwoCat) {
    this.levelTwoCat = levelTwoCat;
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

  public boolean isMatched(Item product) {
    if (product != null) {
      if (isApproveNoMatched(product)) {
        if (isNormMatched(product)) {
          counter_1++;
          return true;
        } else {
          logger.debug("Approve No matched, norm mismatched, competitor prouct = " + productName + ", yw product = "
              + product.getProductName() + ", competitor norm = " + Utility.formatNorm(norm) + ", yw norm = "
              + Utility.formatNorm(product.getNorm() + ", competitor url = " + url));
        }
      } else if (isProductNameMatched(product)) {
        if (isNormMatched(product)) {
          counter_2++;
          return true;
        } else {
          logger.debug("Product name matched, norm mismatched, competitor prouct = " + productName + ", yw product = "
              + product.getProductName() + ", competitor norm = " + Utility.formatNorm(norm) + ", yw norm = "
              + Utility.formatNorm(product.getNorm() + ", competitor url = " + url));
        }
      }
    }
    return false;
  }

  protected boolean isApproveNoMatched(Item product) {
    if (this.approveNo != null) {
      if (this.approveNo.equalsIgnoreCase(product.getApproveNo())) {
        return true;
      }
    }
    return false;
  }

  protected boolean isProductNameMatched(Item product) {
    if (this.productName != null) {
      if (this.productName.equalsIgnoreCase(product.getProductName())) {
        return true;
      } else {
        String[] competitorProductNames = Utility.splitProductName(productName);
        String[] productNames = Utility.splitProductName(product.getProductName());
        if (competitorProductNames != null && productNames != null) {
          if (competitorProductNames.length > 1 && productNames.length > 1) {
            if (competitorProductNames[0].equalsIgnoreCase(productNames[0])
                && competitorProductNames[1].equalsIgnoreCase(productNames[1])) {
              counter_3++;
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  protected boolean isNormMatched(Item product) {
    String competitorFormatNorm = Utility.formatNorm(this.norm);
    String formatNorm = Utility.formatNorm(product.getNorm());
    if (competitorFormatNorm.equalsIgnoreCase(formatNorm)) {
      return true;
    } else {
      String competitorParseNorm = Utility.parseNorm(competitorFormatNorm);
      String parseNorm = Utility.parseNorm(formatNorm);
      if (competitorParseNorm.equalsIgnoreCase(parseNorm)) {
        counter_4++;
        return true;
      } else if (Utility.evalNorm(competitorParseNorm).equalsIgnoreCase(parseNorm)) {
        counter_5++;
        return true;
      }
    }
    return false;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("productName:" + productName);
    buffer.append(",approveNo:" + approveNo);
    buffer.append(",norm:" + norm);
    buffer.append(",type:" + type);
    buffer.append(",price:" + price);
    buffer.append(",site:" + site);
    return buffer.toString();
  }
}
