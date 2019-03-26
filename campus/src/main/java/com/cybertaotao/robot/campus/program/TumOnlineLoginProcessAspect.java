package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.util.MegaLogger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

@Aspect public class TumOnlineLoginProcessAspect {

  private static int SLEEP_TIME = 300;
  private Logger logger;

  public TumOnlineLoginProcessAspect (MegaLogger megaLogger) {
    this.logger = megaLogger.getLogger();
  }

  @Pointcut("execution(* com.cybertaotao.robot.campus.program.TumOnlineLoginProcess.run*())")
  void runAspect () {
  }

  @Around("runAspect()")
  CloseableHttpResponse label (ProceedingJoinPoint pjp) {
    String methodName = pjp.getSignature().getName();
    logger.severe("[----------" + methodName + " start----------]");
    CloseableHttpResponse ret = null;
    try {
      ret = (CloseableHttpResponse) pjp.proceed();
      sleep(SLEEP_TIME);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    logger.severe("[//////////" + methodName + " end  //////////]");
    return ret;
  }

  private void sleep (int time) {
    if (time == 0) {
      return;
    }
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
