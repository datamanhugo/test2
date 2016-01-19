package com.yyw.bi.pis.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.Logger;

public class Utility {

  private static Logger logger = Logger.getLogger(Utility.class);

  private static Pattern normPattern = Pattern.compile("[cm|mm|mg|g|ml|l]+");
  private static ScriptEngineManager mgr = new ScriptEngineManager();
  private static ScriptEngine engine = mgr.getEngineByName("JavaScript");

  public static String formatNorm(String norm) {
    String result = "";
    if (norm != null) {
      result = norm.replace("胶", "");
      result = result.replace("锭", "");
      result = result.replace("层", "");
      result = result.replace("套餐", "");
      result = result.replace("套", "");
      result = result.replace("大号", "");
      result = result.replace("中号", "");
      result = result.replace("小号", "");
      result = result.replace("中", "");
      result = result.replace("大", "");
      result = result.replace("铁盒", "");
      result = result.replace("人份", "");
      result = result.replace("条", "");
      result = result.replace("只", "");
      result = result.replace("套", "");
      result = result.replace("单位", "");
      result = result.replace("每", "");
      result = result.replace("重", "");
      result = result.replace("吸", "");
      result = result.replace("喷", "");
      // result = result.replace("微", "μ");
      result = result.replace("微", "");
      result = result.replace("块", "");
      result = result.replace("贴", "");
      result = result.replace("罐", "");
      result = result.replace("包", "");
      result = result.replace("瓶", "");
      result = result.replace("支", "");
      result = result.replace("装", "");
      result = result.replace(",", "");
      result = result.replace("台", "");
      result = result.replace("枚", "");
      result = result.replace("片", "");
      result = result.replace("贴", "");
      result = result.replace("丸", "");
      result = result.replace("规格：", "");
      result = result.replace("粒", "");
      result = result.replace("小盒", "");
      result = result.replace("盒", "");
      result = result.replace("板", "");
      result = result.replace("版", "");
      result = result.replace("揿", "");
      result = result.replace("袋", "");
      result = result.replace("万", "");
      result = result.replace("桶", "");
      result = result.replace("箱", "");
      result = result.replace("管", "");
      result = result.replace("(", " ");
      result = result.replace(")", "");
      result = result.replace("/", "");
      result = result.replace("s", "");
      result = result.replace("u", "");
      result = result.replace("μ", "");
      result = result.replace("U", "");
      result = result.replaceAll("×", "*");
      // result = result.replace("厘米", "cm");
      result = result.replace("厘米", "*10mm");
      result = result.replace("毫米", "mm");
      result = result.replace("毫克", "mg");
      // result = result.replace("克", "g");
      result = result.replace("克", "*1000mg");
      result = result.replace("毫升", "ml");
      result = result.replace("升", "l");
      result = result.replace("升", "*1000l");
      if (result.endsWith("。")) {
        result = result.substring(0, result.length() - 1);
      }
      if (result.endsWith("*")) {
        result = result.substring(0, result.length() - 1);
      }
    }
    return result;
  }

  public static String parseNorm(String origNorm) {
    // Matcher matcher = normPattern.matcher(origNorm);
    // return matcher.replaceAll(origNorm);
    String[] splits = normPattern.split(origNorm);
    StringBuffer buffer = new StringBuffer();
    if (splits != null && splits.length > 0) {
      for (int index = 0; index < splits.length; index++) {
        buffer.append(splits[index]);
      }
    }
    return buffer.toString();
  }

  public static String evalNorm(String parseNorm) {
    try {
      Object result = engine.eval(parseNorm);
      if (result != null) {
        return result.toString();
      }
    } catch (Exception ex) {
      //logger.error("eval error", ex);
      logger.error("eval error, " + ex.getMessage());
    }
    return "";
  }

  public static String[] splitProductName(String productName) {
    try {
      if (productName != null) {
        return productName.split("\\s+");
      }
    } catch (Exception ex) {
      // logger.error("splitProductName error", ex);
    }
    return new String[0];
  }

  public static String getCurrentDateTime(String format) {
    SimpleDateFormat sdfDate = new SimpleDateFormat(format);
    Date now = new Date();
    String strDate = sdfDate.format(now);
    return strDate;
  }

  public static void main(String[] args) {
    System.out.println(getCurrentDateTime("yyyy_MM_dd_HH_mm_ss"));
    System.out.println(getCurrentDateTime("yyyyMMdd_HH_mm_ss"));
    System.out.println(parseNorm("7cm*10cm*2*3"));
    System.out.println(evalNorm(parseNorm("7cm*10cm*2*3")));
    
    String result ="50*10。";
    if (result.endsWith("。")) {
      result = result.substring(0, result.length() - 1);
    }
    System.out.println("result=" + result);

  }

}
