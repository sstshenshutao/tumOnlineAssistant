package com.cybertaotao.robot.campus.program;

import com.cybertaotao.robot.campus.dataStructure.CourseInfo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TumOnlineChooseProcess {

  private TumOnline tumOnline;

  public TumOnlineChooseProcess (TumOnline tumOnline) {
    this.tumOnline = tumOnline;
  }

  public void choose (CourseInfo courseInfo) {
    int i = 2240;
    while (i > 0) {
      try {
        tumOnline.prepareHeader("https://campus.tum.de/tumonline/sa2.auswahl", gene6Header("https://campus.tum.de"),
          courseInfo2para(courseInfo));
        tumOnline.postInfo();
        i--;
      } catch (Exception e) {
        continue;
      }
    }
  }

  private List<NameValuePair> courseInfo2para (CourseInfo courseInfo) {
    List<NameValuePair> formparams = new ArrayList<>();
    formparams.add(new BasicNameValuePair("cst_person_nr", courseInfo.cst_person_nr));
    formparams.add(new BasicNameValuePair("clvnr", courseInfo.clvnr));
    formparams.add(new BasicNameValuePair("copener", courseInfo.copener));
    formparams.add(new BasicNameValuePair("cabgrppersnr", courseInfo.cabgrppersnr));
    formparams.add(new BasicNameValuePair("cgrpnr", courseInfo.cgrpnr));
    formparams.add(new BasicNameValuePair("csave", courseInfo.csave));
    formparams.add(new BasicNameValuePair("pLVGrpProfNr", courseInfo.pLVGrpProfNr));
    formparams.add(new BasicNameValuePair("pPHOrgNr", courseInfo.pPHOrgNr));
    formparams.add(new BasicNameValuePair("pPHOrgName", courseInfo.pPHOrgName));
    formparams.add(new BasicNameValuePair("pPHOrgKennung", courseInfo.pPHOrgKennung));
    formparams.add(new BasicNameValuePair("pPHSchultypNr", courseInfo.pPHSchultypNr));
    formparams.add(new BasicNameValuePair("pPHSchultypKB", courseInfo.pPHSchultypKB));
    formparams.add(new BasicNameValuePair("pPHKategorie", courseInfo.pPHKategorie));
    formparams.add(new BasicNameValuePair("pPHSchultypName", courseInfo.pPHSchultypName));
    formparams.add(new BasicNameValuePair("pPHFunktypNr", courseInfo.pPHFunktypNr));
    formparams.add(new BasicNameValuePair("pPHFunktypKB", courseInfo.pPHFunktypKB));
    formparams.add(new BasicNameValuePair("pPHFunktypName", courseInfo.pPHFunktypName));
    formparams.add(new BasicNameValuePair("pStStudiumNr", courseInfo.pStStudiumNr));
    formparams.add(new BasicNameValuePair("pStpKnotenNr", courseInfo.pStpKnotenNr));
    formparams.add(new BasicNameValuePair("cststudnr", courseInfo.cststudnr));
    return formparams;
  }

  private HashMap<String, String> gene6Header (String origin) {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Origin", origin);
    return headers;
  }

}
