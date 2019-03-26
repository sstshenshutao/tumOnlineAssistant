package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.UserInfo;
import org.apache.http.client.methods.CloseableHttpResponse;

public class TumOnlineLoginMain {

  private TumOnlineLoginProcess tumOnlineLoginProcess;

  public TumOnlineLoginMain (TumOnlineLoginProcess tumOnlineLoginProcess) {
    this.tumOnlineLoginProcess = tumOnlineLoginProcess;
  }

  public CloseableHttpResponse runAll (UserInfo userInfo) {
    tumOnlineLoginProcess.setUserInfo(userInfo);
    tumOnlineLoginProcess.runGetFirstPSESSIONID();
    tumOnlineLoginProcess.runGetSessionPLOGINID();
    tumOnlineLoginProcess.runGetCheck();
    return tumOnlineLoginProcess.runPostLogin();
  }

}
