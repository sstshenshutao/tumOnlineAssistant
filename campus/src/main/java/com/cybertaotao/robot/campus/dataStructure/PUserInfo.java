package com.cybertaotao.robot.campus.dataStructure;

public class PUserInfo {

  public String pPersonenId;
  public String pPersonenGruppe;

  public PUserInfo (String pPersonenId, String pPersonenGruppe) {
    this.pPersonenId = pPersonenId;
    this.pPersonenGruppe = pPersonenGruppe;
  }

  @Override
  public String toString () {
    return pPersonenId + "|" + pPersonenGruppe;
  }

}
