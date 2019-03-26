package com.cybertaotao.robot.campus.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
import java.util.stream.IntStream;

public class MegaLogger {

  private String filename;
  private Logger logger;

  public MegaLogger (String filename) {
    this.filename = filename;
    this.logger = Logger.getLogger(filename);
    FileHandler fileHandler = null;
    try {
      fileHandler = new FileHandler(filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
//    SimpleFormatter simpleFormatter = new SimpleFormatter(){};
    fileHandler.setFormatter(new Formatter() {
      @Override
      public String format (LogRecord record) {
        return String.format("[%1$-7s] %2$s %n",record.getLevel().getLocalizedName(),record.getMessage());
      }
    });
    fileHandler.setLevel(Level.INFO);
    this.logger.addHandler(fileHandler);
  }

  public Logger getLogger () {
    return logger;
  }
}
