package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.CourseInfo;
import com.cybertaotao.robot.campus.dataStructure.PUserInfo;
import com.cybertaotao.robot.campus.dataStructure.PtreeInfo;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TumOnlineVisitCard {

  private PUserInfo pUserInfo;
  private String visitcard;
  private TumOnline tumOnline;
  private String pStPersonNr;
  private PtreeInfo ptreeInfo;

  public TumOnlineVisitCard (TumOnline tumOnline) {
    this.tumOnline = tumOnline;
  }

  public CourseInfo transParameters () {
    CourseInfo paras = new CourseInfo();
    paras.cststudnr = this.ptreeInfo.pStStudiumNr;
    paras.cst_person_nr = this.pStPersonNr;
    return paras;
  }

  /**
   * 4. get visitcard
   *
   * @param loginResponse
   * @return
   */
  public CloseableHttpResponse runGetVisitCard (CloseableHttpResponse loginResponse) {
    this.visitcard = parserlocation(loginResponse);
    this.pUserInfo = parserVisitcard(this.visitcard);
    tumOnline.prepareHeader("https://campus.tum.de/tumonline/" + this.visitcard, null);
    return tumOnline.getInfo();
  }

  private static PUserInfo parserVisitcard (String visitcard) {
    String pp = "pPersonenId=";
    int lo1 = visitcard.indexOf(pp);
    int lo2 = visitcard.indexOf("&", lo1 + pp.length());
    int start = lo1 + pp.length();
    int end = lo2;
    String pPersonenId = visitcard.substring(start, end);
    //    System.out.println(pPersonenId);
    String gg = "pPersonenGruppe=";
    int lo3 = visitcard.indexOf(gg);
    int lo4 = visitcard.indexOf("&", lo3 + gg.length());
    int start1 = lo3 + gg.length();
    int end1 = lo4;
    String pPersonenGruppe = visitcard.substring(start1, end1);
    //    System.out.println(pPersonenGruppe);
    return new PUserInfo(pPersonenId, pPersonenGruppe);
  }

  private static String parserlocation (CloseableHttpResponse response) {
    // TODO Auto-generated method stub
    Header[] a = response.getAllHeaders();
    if (response.getStatusLine().getStatusCode() == 302) {
      for (Header h : a) {
        if (h.getName().equals("Location")) {
          return h.getValue();
        }
      }
    } else {
      System.out.println("**********not 302");
    }
    System.out.println("**********cannot find Location");
    return null;
  }

  /**
   * 5.weiter
   *
   * @return
   */
  public CloseableHttpResponse runPostWeiter (String body) {
    String refervisitcard = "https://campus.tum.de/tumonline/" + this.visitcard;
    tumOnline
      .prepareHeader("https://campus.tum.de/tumonline/wbHooks.actionBenutzerWaehlbar", gene4Header(refervisitcard),
        parserPhtml(body));
    return tumOnline.postInfo();
  }

  private HashMap<String, String> gene4Header (String refer) {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Referer", refer);
    return headers;
  }

  private List<NameValuePair> parserPhtml (String allhtml) {
    List<NameValuePair> ret = new ArrayList<>();
    int lo1 = allhtml.indexOf("pBkontoNr");
    int lo2 = allhtml.indexOf("VALUE=", lo1);
    int pBkontoNrs = lo2 + 7;
    // System.out.println(pBkontoNrs);
    int pBkontoNre = allhtml.indexOf("\">", lo2);
    // System.out.println(pBkontoNre);
    String pBkontoNr = allhtml.substring(pBkontoNrs, pBkontoNre);
    // System.out.println(pBkontoNr);
    ret.add(new BasicNameValuePair("pBkontoNr", pBkontoNr));
    lo1 = allhtml.indexOf("pEventTypKb", pBkontoNre);
    lo2 = allhtml.indexOf("VALUE=", lo1);
    int pEventTypKbs = lo2 + 7;
    int pEventTypKbe = allhtml.indexOf("\">", lo2);
    // System.out.println(pEventTypKbs);
    // System.out.println(pEventTypKbe);
    String pEventTypKbNr = allhtml.substring(pEventTypKbs, pEventTypKbe);
    // System.out.println(pEventTypKbNr);
    ret.add(new BasicNameValuePair("pEventTypKb", pEventTypKbNr));
    lo1 = allhtml.indexOf("NAME=\"pUrl\"", pEventTypKbe);
    lo2 = allhtml.indexOf("VALUE=", lo1);
    int pUrls = lo2 + 7;
    int pUrle = allhtml.indexOf("\">", lo2);
    // System.out.println(pUrls);
    // System.out.println(pUrle);
    String pUrlNr = allhtml.substring(pUrls, pUrle);
    // System.out.println(pUrlNr);
    ret.add(new BasicNameValuePair("pUrl", pUrlNr));
    return ret;
  }

  /**
   * 7.studium
   *
   * @return
   */
  public CloseableHttpResponse runGetStudium (String body) {
    this.pStPersonNr = parserpStPersonNr(body);
    tumOnline
      .prepareHeader("https://campus.tum.de/tumonline/studienstatus.ht2ststatus?pStPersonNr=" + this.pStPersonNr, null);
    return tumOnline.getInfo();
  }

  /**
   * clickMajor
   *
   * @param body7
   * @return
   */
  public CloseableHttpResponse runGetMajor (String body7) {
    this.ptreeInfo = parser8paras(body7);
    String refer8 = "https://campus.tum.de/tumonline/studienstatus.ht2ststatus?pStPersonNr=" + this.pStPersonNr;
    tumOnline.prepareHeader(
      "https://campus.tum.de/tumonline/wbstpcs.showSpoTree?pStStudiumNr=" + ptreeInfo.pStStudiumNr + "&pSJNr="
        + ptreeInfo.pSJNr + "&pStpStpNr=" + ptreeInfo.pStpStpNr + "&pStartSemester=", gene4Header(refer8));
    return tumOnline.getInfo();
  }

  private static String parserpStPersonNr (String body6) {
    // TODO Auto-generated method stub
    String cc = "pStPersonNr=";
    int lo1 = body6.indexOf(cc);
    int start = lo1 + cc.length();
    int end = start;
    while (end < body6.length() && body6.charAt(end) >= '0' && body6.charAt(end) <= '9') {
      end++;
    }
    String ret = body6.substring(start, end);
    return ret;
  }

  private PtreeInfo parser8paras (String body7) {
    String pp = "wbstpcs.showSpoTree";
    String cc = "pStStudiumNr=";
    int lo1 = body7.indexOf(pp);
    int lo2 = body7.indexOf(cc, lo1 + pp.length());
    int lo3 = body7.indexOf("&amp", lo2 + cc.length());
    int start = lo2 + cc.length();
    int end = lo3;
    String pStStudiumNr = body7.substring(start, end);
    String dd = "pSJNr=";
    lo2 = body7.indexOf(dd, lo1 + pp.length());
    lo3 = body7.indexOf("&amp", lo2 + dd.length());
    start = lo2 + dd.length();
    end = lo3;
    String pSJNr = body7.substring(start, end);
    String ee = "pStpStpNr=";
    lo2 = body7.indexOf(ee, lo1 + pp.length());
    lo3 = body7.indexOf("&amp", lo2 + ee.length());
    start = lo2 + ee.length();
    end = lo3;
    String pStpStpNr = body7.substring(start, end);
    return new PtreeInfo(pStStudiumNr, pSJNr, pStpStpNr);
  }

  public CloseableHttpResponse runTest () {
    System.out.println("runtest");
    return null;
  }

}
