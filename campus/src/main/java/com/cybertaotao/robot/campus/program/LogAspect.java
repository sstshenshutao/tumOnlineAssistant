package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.util.MegaLogger;
import com.cybertaotao.robot.campus.util.Util;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;
import java.util.logging.Logger;

@Aspect public class LogAspect {

  private Logger logger;

  public LogAspect (MegaLogger megaLogger) {
    this.logger = megaLogger.getLogger();
  }

  @Pointcut("execution(* com.cybertaotao.robot.campus.program.TumOnline.*Info())")
  void getInfoAspect () {
  }

  @Pointcut("execution(* com.cybertaotao.robot.campus.program.TumOnline.prepareHeader(..))")
  void prepareHeaderAspect () {
  }

  @Around("getInfoAspect()")
  CloseableHttpResponse printLog (ProceedingJoinPoint pjp) {
    CloseableHttpResponse response = null;
    try {
      response = (CloseableHttpResponse) pjp.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    printResult(response);
    return response;
  }

  @Around("prepareHeaderAspect()")
  void printRequestHeader (ProceedingJoinPoint pjp) {
    HttpRequest get = null;
    try {
      get = (HttpRequest) pjp.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    printRequestHeader(get);
  }

  private void printRequestHeader (HttpRequest get) {
    if (get == null) {
      return;
    }
    logger.info("---Request header---");
    for (Header h : get.getAllHeaders()) {
      logger.info(h.getName() + ":" + h.getValue());
    }
  }

  private void printResult (CloseableHttpResponse response1) {
    if (response1 == null) {
      return;
    }
    logger.info("---responseheader---");
    Header[] a = response1.getAllHeaders();
    logger.info("" + response1.getStatusLine().getStatusCode());
    logger.info(response1.getStatusLine().getReasonPhrase());
    for (Header h : a) {
      logger.info(h.getName() + ":" + h.getValue());
    }
  }



}
