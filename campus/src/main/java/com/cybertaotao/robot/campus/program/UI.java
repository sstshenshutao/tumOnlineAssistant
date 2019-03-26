package com.cybertaotao.robot.campus.program;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UI extends Frame {

  private final static String CONFIG = "setting.ini";

  public UI () {
    init();
  }

  private static void initFile (String account, String password, String clvnr, String cgrpnr) {
    File settingFile = new File(CONFIG);
    if (!settingFile.exists()) {
      try {
        settingFile.createNewFile();
        Properties setting = new Properties();
        setting.load(new FileInputStream(settingFile));
        setting.put("account", account);
        setting.put("password", password);
        setting.put("clvnr", clvnr);
        setting.put("copener", "");
        setting.put("cabgrppersnr", "");
        setting.put("cgrpnr", cgrpnr);
        setting.put("csave", "J");
        setting.put("pLVGrpProfNr", "");
        setting.put("pPHOrgNr", "");
        setting.put("pPHOrgName", "");
        setting.put("pPHOrgKennung", "");
        setting.put("pPHSchultypNr", "");
        setting.put("pPHSchultypKB", "");
        setting.put("pPHKategorie", "");
        setting.put("pPHSchultypName", "");
        setting.put("pPHFunktypNr", "");
        setting.put("pPHFunktypKB", "");
        setting.put("pPHFunktypName", "");
        setting.put("pStStudiumNr", "");
        setting.put("pStpKnotenNr", "");
        FileOutputStream ops = new FileOutputStream(settingFile);
        setting.store(ops, "init, please fill in the Info, some of them could be empty");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private TextField account;
  private TextField password;
  private TextField clvnr;
  private TextField cgrpnr;

  private void init () {
    this.setTitle("TUMOnline Assistant");
    Button settingBut = new Button("generate setting");
    Button run = new Button("run");
    Panel topRightPanel = new Panel();
    Panel downRightPanel = new Panel();
    Label accountLabel = new Label("account");
    this.account = new TextField(20);
    Panel acc = new Panel();
    acc.setLayout(new GridLayout(2, 1));
    acc.add(accountLabel);
    acc.add(this.account);
    Label passwordLabel = new Label("password");
    this.password = new TextField(20);
    Panel pass = new Panel();
    pass.setLayout(new GridLayout(2, 1));
    pass.add(passwordLabel);
    pass.add(this.password);
    Label clvnrLabel = new Label("clvnr");
    this.clvnr = new TextField(20);
    Panel clv = new Panel();
    clv.setLayout(new GridLayout(2, 1));
    clv.add(clvnrLabel);
    clv.add(this.clvnr);
    Label cgrpnrLabel = new Label("cgrpnr");
    this.cgrpnr = new TextField(20);
    Panel cgr = new Panel();
    cgr.setLayout(new GridLayout(2, 1));
    cgr.add(cgrpnrLabel);
    cgr.add(this.cgrpnr);
    topRightPanel.setLayout(new GridLayout(4, 1));
    downRightPanel.setLayout(new GridLayout(1, 2));
    topRightPanel.add(acc);
    topRightPanel.add(pass);
    topRightPanel.add(clv);
    topRightPanel.add(cgr);
    downRightPanel.add(settingBut);
    downRightPanel.add(run);
    this.setLayout(new BorderLayout());
    this.add("North", topRightPanel);
    this.add("South", downRightPanel);
    this.pack();
    this.setVisible(true);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing (WindowEvent e) {
        System.exit(0);
      }
    });
    settingBut.addActionListener(x -> {
      initFile(this.account.getText(), this.password.getText(), this.clvnr.getText(), this.cgrpnr.getText());
    });
    run.addActionListener(x -> {
      transRun();
    });
  }

  public void transRun () {
    Main.runable = true;
  }

}
