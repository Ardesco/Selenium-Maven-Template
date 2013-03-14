package com.lazerycode.selenium;

public enum BrowserType {
  FIREFOX("firefox"),
  CHROME("chrome"),
  IE("ie"),
  SAFARI("safari"),
  OPERA("opera"),
  GHOSTDRIVER("ghostdriver"),
  REMOTE("remote"),
  HTMLUNIT("htmlunit");

  private final String browser;

  BrowserType(String browser) {
    this.browser = browser;
  }

  public String getBrowser() {
    return browser;
  }
}
