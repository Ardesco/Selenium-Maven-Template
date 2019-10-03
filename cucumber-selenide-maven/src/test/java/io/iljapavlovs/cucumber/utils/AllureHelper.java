//package io.iljapavlovs.cucumber.utils;
//
//import static java.lang.String.format;
//import static java.lang.System.lineSeparator;
//
//import com.codeborne.selenide.Screenshots;
//import com.codeborne.selenide.WebDriverRunner;
//import io.qameta.allure.Allure;
//import io.qameta.allure.Attachment;
//import java.io.File;
//import java.io.IOException;
//import java.util.stream.Collectors;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogEntry;
//
//
//@Slf4j
//public class AllureHelper {
//
//  @Attachment(type = "image/png")
//  public static byte[] screenshot() {
//    File screenshot = Screenshots.takeScreenShotAsFile();
//    byte[] generatedScreenshot = null;
//    try {
//      generatedScreenshot = FileUtils.readFileToByteArray(screenshot);
//    } catch (IOException e) {
//      log.warn(e.getMessage());
//    }
//    return generatedScreenshot;
//  }
//
//  public static void attachLogTypes(String... logTypes) {
//    for (String logType : logTypes) {
//      attachSeleniumLogs(logType, format("%s_logs", logType));
//    }
//  }
//
//  private static void attachSeleniumLogs(String logType, String attachmentName) {
//    if (WebDriverRunner.hasWebDriverStarted()) {
//      WebDriver webDriver = WebDriverRunner.getWebDriver();
//      LogEntries logEntries = webDriver.manage().logs().get(logType);
//      String fullLog = logEntries.getAll().stream()
//          .map(LogEntry::toString)
//          .collect(Collectors.joining(lineSeparator()));
//      Allure.addAttachment(attachmentName, fullLog);
//    }
//  }
//}
