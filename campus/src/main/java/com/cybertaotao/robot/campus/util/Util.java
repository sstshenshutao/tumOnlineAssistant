package com.cybertaotao.robot.campus.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Util {

  public final static String USER_AGENT =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like "
      + "Gecko) Chrome/69.0.3497.100 Safari/537.36";
  public final static int DEFAULT_SOCKET_MAX_TOTAL = 50;
  public final static int DEFAULT_CONNECT_TIMEOUT = 2500;
  public final static int DEFAULT_REQUEST_TIMEOUT = 2000;
  public final static int DEFAULT_SOCKET_TIMEOUT = 2000;

  public static String getbody (CloseableHttpResponse response2) {
    HttpEntity res2io = response2.getEntity();
    try {
      return EntityUtils.toString(res2io);
    } catch (ParseException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
