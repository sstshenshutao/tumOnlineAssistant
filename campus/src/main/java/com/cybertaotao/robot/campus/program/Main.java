package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.CourseInfo;
import com.cybertaotao.robot.campus.dataStructure.UserInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {

  private static TumOnline tumOnline;
  private final static String CONFIG = "setting.ini";
  public static boolean runable = false;

  public static void main (String[] args) {
    UI ui = new UI();
    while (!runable) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    UserInfo userInfo = getUserInfoFromFile();
    CourseInfo courseInfo = getCourseInfoFromFile();
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("taop.xml");
    //login
    TumOnlineLoginMain tumOnlineLoginMain = applicationContext.getBean(TumOnlineLoginMain.class);
    CloseableHttpResponse loginResponse = tumOnlineLoginMain.runAll(userInfo);
    //visitCard
    TumOnlineVisitCardMain tumOnlineVisitCardMain = applicationContext.getBean(TumOnlineVisitCardMain.class);
    tumOnlineVisitCardMain.runAll(loginResponse);
    //paras transmission for choosing
    /*
    paras.cststudnr = this.ptreeInfo.pStStudiumNr;
    paras.cst_person_nr= this.pStPersonNr;
     */
    CourseInfo paras = tumOnlineVisitCardMain.transParameters();
    courseInfo.cststudnr = paras.cststudnr;
    courseInfo.cst_person_nr = paras.cst_person_nr;
    //choose
    TumOnlineChooseProcess tumOnlineChooseProcess = applicationContext.getBean(TumOnlineChooseProcess.class);
    tumOnlineChooseProcess.choose(courseInfo);
  }

  private static UserInfo getUserInfoFromFile () {
    Properties ret = null;
    try {
      ret = new Properties();
      ret.load(new FileInputStream(CONFIG));
    } catch (Exception e) {
      System.out.println("filetoProperty wrong");
    }
    String account = ret.getProperty("account");
    String password = ret.getProperty("password");
    return (account != null && password != null)
           ? new UserInfo(account, password)
           : null;
  }

  private static CourseInfo getCourseInfoFromFile () {
    Properties ret = null;
    try {
      ret = new Properties();
      ret.load(new FileInputStream(CONFIG));
    } catch (Exception e) {
      System.out.println("filetoProperty wrong");
    }
    String clvnr = ret.getProperty("clvnr");
    String copener = ret.getProperty("copener");
    String cabgrppersnr = ret.getProperty("cabgrppersnr");
    String cgrpnr = ret.getProperty("cgrpnr");
    String csave = ret.getProperty("csave");
    String pLVGrpProfNr = ret.getProperty("pLVGrpProfNr");
    String pPHOrgNr = ret.getProperty("pPHOrgNr");
    String pPHOrgName = ret.getProperty("pPHOrgName");
    String pPHOrgKennung = ret.getProperty("pPHOrgKennung");
    String pPHSchultypNr = ret.getProperty("pPHSchultypNr");
    String pPHSchultypKB = ret.getProperty("pPHSchultypKB");
    String pPHKategorie = ret.getProperty("pPHKategorie");
    String pPHSchultypName = ret.getProperty("pPHSchultypName");
    String pPHFunktypNr = ret.getProperty("pPHFunktypNr");
    String pPHFunktypKB = ret.getProperty("pPHFunktypKB");
    String pPHFunktypName = ret.getProperty("pPHFunktypName");
    String pStStudiumNr = ret.getProperty("pStStudiumNr");
    String pStpKnotenNr = ret.getProperty("pStpKnotenNr");
    return (clvnr != null && copener != null && cabgrppersnr != null && cgrpnr != null && csave != null
      && pLVGrpProfNr != null && pPHOrgNr != null && pPHOrgName != null && pPHOrgKennung != null
      && pPHSchultypNr != null && pPHSchultypKB != null && pPHKategorie != null && pPHSchultypName != null
      && pPHFunktypNr != null && pPHFunktypKB != null && pPHFunktypName != null && pStStudiumNr != null
      && pStpKnotenNr != null)
           ? new CourseInfo("", clvnr, copener, cabgrppersnr, cgrpnr, csave, pLVGrpProfNr, pPHOrgNr, pPHOrgName,
      pPHOrgKennung, pPHSchultypNr, pPHSchultypKB, pPHKategorie, pPHSchultypName, pPHFunktypNr, pPHFunktypKB,
      pPHFunktypName, pStStudiumNr, pStpKnotenNr, "")
           : null;
  }

}
