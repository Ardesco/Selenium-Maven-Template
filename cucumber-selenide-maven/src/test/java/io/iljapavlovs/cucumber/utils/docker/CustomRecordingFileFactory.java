//package io.iljapavlovs.cucumber.utils.docker;
//
//import static org.awaitility.Awaitility.await;
//
//import io.qameta.allure.Attachment;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//import org.testcontainers.containers.RecordingFileFactory;
//
//public class CustomRecordingFileFactory implements RecordingFileFactory {
//
//  private File video;
//  private static final SimpleDateFormat filenameDateFormat = new SimpleDateFormat("YYYYMMdd-HHmmss");
//  private static final String PASSED = "PASSED";
//  private static final String FAILED = "FAILED";
//  private static final String FILENAME_FORMAT = "%s-%s-%s.flv";
//
//  @Override
//  public File recordingFileForTest(File vncRecordingDirectory, String prefix, boolean succeeded) {
//    final String resultMarker = succeeded ? PASSED : FAILED;
//    final String fileName = String.format(FILENAME_FORMAT,
//        resultMarker,
//        prefix,
//        filenameDateFormat.format(new Date())
//    );
//    video = new File(vncRecordingDirectory, fileName);
//    videoAttachment(filenameDateFormat.format(new Date()));
//    return video;
//  }
//
//
//  /**
//   *
//   * Allure only supports
//   * detailed post on video recording - https://automated-testing.info/t/docker-selenium-and-allure-video-attachment-support/9885
//   * example code - https://github.com/sskorol/docker-selenium-samples/blob/master/src/main/java/com/blogspot/notes/automation/qa/utils/AttachmentUtils.java
//   * flv in allure - https://gitter.im/allure-framework/allure-core/archives/2017/04/28
//   * https://github.com/SergeyPirogov/video-recorder-java/issues/36
//   * @param name
//   * @return
//   */
//  @Attachment(value = "{0}", type = "video/x-flv")
//  public byte[] videoAttachment(String name) {
//    try {
//
////      new File("build/tmp")
//
//      await().atMost(60, TimeUnit.SECONDS)
//          .pollDelay(1, TimeUnit.SECONDS)
//          .ignoreExceptions()
////          .until(() -> video.exists() && video.getTotalSpace() > 100)
//          .until(() -> video != null);
//
//
//      return Files.readAllBytes(Paths.get(video.getAbsolutePath()));
//    } catch (IOException e) {
//      return new byte[0];
//    }
//  }
//
//
//}
