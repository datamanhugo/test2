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
      result = norm.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("�ײ�", "");
      result = result.replace("��", "");
      result = result.replace("���", "");
      result = result.replace("�к�", "");
      result = result.replace("С��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("����", "");
      result = result.replace("�˷�", "");
      result = result.replace("��", "");
      result = result.replace("ֻ", "");
      result = result.replace("��", "");
      result = result.replace("��λ", "");
      result = result.replace("ÿ", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      // result = result.replace("΢", "��");
      result = result.replace("΢", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("ƿ", "");
      result = result.replace("֧", "");
      result = result.replace("װ", "");
      result = result.replace(",", "");
      result = result.replace("̨", "");
      result = result.replace("ö", "");
      result = result.replace("Ƭ", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("���", "");
      result = result.replace("��", "");
      result = result.replace("С��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("Ͱ", "");
      result = result.replace("��", "");
      result = result.replace("��", "");
      result = result.replace("(", " ");
      result = result.replace(")", "");
      result = result.replace("/", "");
      result = result.replace("s", "");
      result = result.replace("u", "");
      result = result.replace("��", "");
      result = result.replace("U", "");
      result = result.replaceAll("��", "*");
      // result = result.replace("����", "cm");
      result = result.replace("����", "*10mm");
      result = result.replace("����", "mm");
      result = result.replace("����", "mg");
      // result = result.replace("��", "g");
      result = result.replace("��", "*1000mg");
      result = result.replace("����", "ml");
      result = result.replace("��", "l");
      result = result.replace("��", "*1000l");
      if (result.endsWith("��")) {
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
    
    String result ="50*10��";
    if (result.endsWith("��")) {
      result = result.substring(0, result.length() - 1);
    }
    System.out.println("result=" + result);

  }

}
