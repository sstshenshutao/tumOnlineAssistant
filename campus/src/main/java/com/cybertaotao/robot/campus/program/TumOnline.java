package com.cybertaotao.robot.campus.program;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import static com.cybertaotao.robot.campus.util.Util.*;

public class TumOnline {

  private CloseableHttpClient httpclient;
  private CookieStore httpCookieStore;
  private HttpGet httpGet;
  private HttpPost httpPost;

  public TumOnline () {
    initHttpClient();
    initGetPost();
  }

  private void initGetPost () {
    this.httpGet = new HttpGet();
    this.httpPost = new HttpPost();
  }

  private void initHttpClient () {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setDefaultMaxPerRoute(DEFAULT_SOCKET_MAX_TOTAL);
    connectionManager.setMaxTotal(DEFAULT_SOCKET_MAX_TOTAL);
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                                               .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT)
                                               .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setRedirectsEnabled(true)
                                               .setCircularRedirectsAllowed(true).setCookieSpec(CookieSpecs.STANDARD)
                                               .build();
    this.httpCookieStore = new BasicCookieStore();
    HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore)
                                                 .setUserAgent(USER_AGENT).setDefaultRequestConfig(requestConfig)
                                                 .setConnectionManager(connectionManager);
    this.httpclient = builder.build();
  }

  public HttpGet prepareHeader (String url, HashMap<String, String> headers) {
    try {
      this.httpGet.setURI(new URI(url));
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    if (headers != null) {
      for (String k : headers.keySet()) {
        this.httpGet.setHeader(k, headers.get(k));
      }
    }
    return this.httpGet;
  }

  public CloseableHttpResponse getInfo () {
    CloseableHttpResponse response1 = null;
    boolean exception = false;
    try {
      response1 = httpclient.execute(httpGet);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      exception = true;
    }
    return exception
           ? getInfo()
           : response1;
  }

  public HttpPost prepareHeader (String url, HashMap<String, String> headers, List<NameValuePair> para) {
    try {
      this.httpPost.setURI(new URI(url));
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    if (headers != null) {
      for (String k : headers.keySet()) {
        this.httpPost.setHeader(k, headers.get(k));
      }
    }
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(para, Consts.UTF_8);
    this.httpPost.setEntity(entity);
    return this.httpPost;
  }

  public CloseableHttpResponse postInfo () {
    CloseableHttpResponse response1 = null;
    boolean exception = false;
    try {
      response1 = httpclient.execute(httpPost);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      exception = true;
    }
    return exception
           ? postInfo()
           : response1;
  }

  private void test () {
  }

  public static void main (String[] args) {
    TumOnline t = new TumOnline();
  }

}
