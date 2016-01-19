package com.yyw.bi.pis.model;

/*
 *Compared Product Report
 */
public class ProductReport implements Comparable {

  private String dateTime;
  private String levelOneCat;
  private String levelTwoCat;
  private String site;
  private String productID;
  private String siteProductName;
  private String productName;
  private String norm;
  private String originalPrice;
  private String price;
  private String diffPrice;
  private String diffPecent;
  private String average;
  private String salesNum;
  private String competitorUrl;

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
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

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getSiteProductName() {
    return siteProductName;
  }

  public void setSiteProductName(String siteProductName) {
    this.siteProductName = siteProductName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getNorm() {
    return norm;
  }

  public void setNorm(String norm) {
    this.norm = norm;
  }

  public String getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(String originalPrice) {
    this.originalPrice = originalPrice;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDiffPrice() {
    return diffPrice;
  }

  public void setDiffPrice(String diffPrice) {
    this.diffPrice = diffPrice;
  }

  public String getDiffPecent() {
    return diffPecent;
  }

  public void setDiffPecent(String diffPecent) {
    this.diffPecent = diffPecent;
  }

  public String getAverage() {
    return average;
  }

  public void setAverage(String average) {
    this.average = average;
  }

  public String getSalesNum() {
    return salesNum;
  }

  public void setSalesNum(String salesNum) {
    this.salesNum = salesNum;
  }

  public String getCompetitorUrl() {
    return competitorUrl;
  }

  public void setCompetitorUrl(String competitorUrl) {
    this.competitorUrl = competitorUrl;
  }

  @Override
  public int compareTo(Object o) {
    if (o != null) {
      if (o instanceof ProductReport) {
        ProductReport productReport = (ProductReport) o;
        return this.productID.compareToIgnoreCase(productReport.productID);
      }
    }
    return 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((productID == null) ? 0 : productID.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductReport other = (ProductReport) obj;
    if (productID == null) {
      if (other.productID != null)
        return false;
    } else if (!productID.equals(other.productID))
      return false;
    return true;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(levelOneCat);
    buffer.append("," + levelTwoCat);
    buffer.append("," + site);
    buffer.append("," + productID);
    buffer.append("," + siteProductName);
    buffer.append("," + productName);
    buffer.append("," + norm);
    buffer.append("," + originalPrice);
    buffer.append("," + price);
    buffer.append("," + diffPrice);
    buffer.append("," + diffPecent);
    buffer.append("," + average);
    buffer.append("," + salesNum);
    buffer.append("," + competitorUrl);
    return buffer.toString();
  }

}
