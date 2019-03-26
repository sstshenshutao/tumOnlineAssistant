package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.CourseInfo;
import com.cybertaotao.robot.campus.dataStructure.UserInfo;
import com.cybertaotao.robot.campus.util.Util;
import org.apache.http.client.methods.CloseableHttpResponse;

public class TumOnlineVisitCardMain {

  private TumOnlineVisitCard tumOnlineVisitCard;

  public TumOnlineVisitCardMain (TumOnlineVisitCard tumOnlineVisitCard) {
    this.tumOnlineVisitCard = tumOnlineVisitCard;
  }

  public CourseInfo transParameters () {
    return tumOnlineVisitCard.transParameters();
  }

  public CloseableHttpResponse runAll (CloseableHttpResponse loginResponse) {
    CloseableHttpResponse response = tumOnlineVisitCard.runGetVisitCard(loginResponse);
    String responseStr = Util.getbody(response);
    if (responseStr.contains("<TITLE>Informationen")) {
      //need post info weiter
      tumOnlineVisitCard.runPostWeiter(responseStr);
      response = tumOnlineVisitCard.runGetVisitCard(loginResponse);
      responseStr = Util.getbody(response);
    } else if (responseStr.contains("<TITLE>Visitenkarte")) {
      //skip
    } else {
      System.out.println("something wrong");
    }
    CloseableHttpResponse response7 = tumOnlineVisitCard.runGetStudium(responseStr);
    String body7 = Util.getbody(response7);
    return tumOnlineVisitCard.runGetMajor(body7);
  }

}
