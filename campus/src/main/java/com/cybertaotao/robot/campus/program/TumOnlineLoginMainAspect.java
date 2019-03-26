package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.util.MegaLogger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect public class TumOnlineLoginMainAspect {

  private Logger logger;

  public TumOnlineLoginMainAspect (MegaLogger megaLogger) {
    this.logger = megaLogger.getLogger();
  }

  @Around("execution(org.apache.http.client.methods.CloseableHttpResponse com.cybertaotao.robot.campus.program.TumOnlineLoginMain.runAll(..))")
  public org.apache.http.client.methods.CloseableHttpResponse log (ProceedingJoinPoint proceedingJoinPoint) {
    logger.info("Logs for Login TumOnline");
    CloseableHttpResponse ret = null;
    try {
      ret = (CloseableHttpResponse) proceedingJoinPoint.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    logger.info("Logs for Login TumOnline finished");
    return ret;
  }

}
