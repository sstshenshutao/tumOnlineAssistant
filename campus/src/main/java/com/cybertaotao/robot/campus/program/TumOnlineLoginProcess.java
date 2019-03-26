package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.UserInfo;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TumOnlineLoginProcess {

  private TumOnline tumOnline;
  private UserInfo userInfo;

  public TumOnlineLoginProcess (TumOnline tumOnline) {
    this.tumOnline = tumOnline;
  }

  public void setUserInfo (UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  /**
   * 1. get first PSESSIONID
   */
  public CloseableHttpResponse runGetFirstPSESSIONID () {
    tumOnline.prepareHeader("https://campus.tum.de/tumonline/webnav.ini", null);
    return tumOnline.getInfo();
  }

  /**
   * 2. get session PLOGINID
   */
  public CloseableHttpResponse runGetSessionPLOGINID () {
    tumOnline.prepareHeader("https://campus.tum.de/tumonline/wbanmeldung.durchfuehren", gene2Header());
    return tumOnline.getInfo();
  }

  private HashMap<String, String> gene2Header () {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Host", "campus.tum.de");
    headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
    headers.put("Referer", "https://campus.tum.de/tumonline/webnav.menue");
    headers.put("Accept-Encoding", "gzip, deflate, br");
    headers.put("Accept-Language", "de,zh;q=0.9,zh-CN;q=0.8,en;q=0.7");
    return headers;
  }

  /**
   * 3a. check
   */
  public CloseableHttpResponse runGetCheck () {
    tumOnline.prepareHeader("https://campus.tum.de/tumonline/wbanmeldung"
      + ".durchfuehren?ctxid=check&cusergroup=&cinframe=&pLogonMask=&curl=", null);
    return tumOnline.getInfo();
  }

  /**
   * 3b. post login
   */
  public CloseableHttpResponse runPostLogin () {
    tumOnline.prepareHeader("https://campus.tum.de/tumonline/wbAnmeldung.durchfuehren", gene3Header(), setPostInfo());
    return tumOnline.postInfo();
  }

  private List<NameValuePair> setPostInfo () {
    List<NameValuePair> formparams = new ArrayList<>();
    formparams.add(new BasicNameValuePair("ctxid", "check"));
    formparams.add(new BasicNameValuePair("curl", ""));
    formparams.add(new BasicNameValuePair("cinframe", ""));
    formparams.add(new BasicNameValuePair("pLogonMask", ""));
    formparams.add(new BasicNameValuePair("cp1", this.userInfo.acc));
    formparams.add(new BasicNameValuePair("cp2", this.userInfo.pass));
    return formparams;
  }

  private HashMap<String, String> gene3Header () {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Host", "campus.tum.de");
    headers.put("Origin", "https://campus.tum.de");
    headers.put("Referer",
      "https://campus.tum.de/tumonline/wbanmeldung.durchfuehren?ctxid=check&cusergroup=&cinframe=&pLogonMask=&curl=");
    headers.put("Accept-Encoding", "gzip, deflate, br");
    headers.put("Accept-Language", "de,zh;q=0.9,zh-CN;q=0.8,en;q=0.7");
    return headers;
  }

}
