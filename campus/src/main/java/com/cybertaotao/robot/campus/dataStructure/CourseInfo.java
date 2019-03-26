package com.cybertaotao.robot.campus.dataStructure;

public class CourseInfo {
  public String cst_person_nr;//==pStPersonNr
  public String clvnr;//anmeldung
  public String copener; //""
  public String cabgrppersnr;//""
  public String cgrpnr;//anmeldung
  public String csave;//"J"
  public String pLVGrpProfNr;
  public String pPHOrgNr;
  public String pPHOrgName;
  public String pPHOrgKennung;
  public String pPHSchultypNr;
  public String pPHSchultypKB;
  public String pPHKategorie;
  public String pPHSchultypName;
  public String pPHFunktypNr;
  public String pPHFunktypKB;
  public String pPHFunktypName;
  public String pStStudiumNr;
  public String pStpKnotenNr;
  public String cststudnr;//==pStStudiumNr

  public CourseInfo () {
  }

  public CourseInfo (String cst_person_nr, String clvnr, String copener, String cabgrppersnr, String cgrpnr,
    String csave, String pLVGrpProfNr, String pPHOrgNr, String pPHOrgName, String pPHOrgKennung, String pPHSchultypNr,
    String pPHSchultypKB, String pPHKategorie, String pPHSchultypName, String pPHFunktypNr, String pPHFunktypKB,
    String pPHFunktypName, String pStStudiumNr, String pStpKnotenNr, String cststudnr) {
    this.cst_person_nr = cst_person_nr;
    this.clvnr = clvnr;
    this.copener = copener;
    this.cabgrppersnr = cabgrppersnr;
    this.cgrpnr = cgrpnr;
    this.csave = csave;
    this.pLVGrpProfNr = pLVGrpProfNr;
    this.pPHOrgNr = pPHOrgNr;
    this.pPHOrgName = pPHOrgName;
    this.pPHOrgKennung = pPHOrgKennung;
    this.pPHSchultypNr = pPHSchultypNr;
    this.pPHSchultypKB = pPHSchultypKB;
    this.pPHKategorie = pPHKategorie;
    this.pPHSchultypName = pPHSchultypName;
    this.pPHFunktypNr = pPHFunktypNr;
    this.pPHFunktypKB = pPHFunktypKB;
    this.pPHFunktypName = pPHFunktypName;
    this.pStStudiumNr = pStStudiumNr;
    this.pStpKnotenNr = pStpKnotenNr;
    this.cststudnr = cststudnr;
  }

}
