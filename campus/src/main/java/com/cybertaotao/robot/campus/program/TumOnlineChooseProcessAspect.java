package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.util.MegaLogger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect public class TumOnlineChooseProcessAspect {

  private Logger logger;

  public TumOnlineChooseProcessAspect (MegaLogger megaLogger) {
    this.logger = megaLogger.getLogger();
  }

  @Around("execution(org.apache.http.client.methods.CloseableHttpResponse com.cybertaotao.robot.campus.program"
            + ".TumOnlineChooseProcess.choose(..))")
  public CloseableHttpResponse log (ProceedingJoinPoint proceedingJoinPoint) {
    logger.info("[round choose course]");
    CloseableHttpResponse ret = null;
    try {
      ret = (CloseableHttpResponse) proceedingJoinPoint.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return ret;
  }

}
