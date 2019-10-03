package io.iljapavlovs.cucumber.utils.docker;

public class CurrentBrowserHolder {

  private static final ThreadLocal<TestBrowser> HOLDER = new ThreadLocal<>();
  private static final ThreadLocal<Class> BROWSER_CLASS = new ThreadLocal<>();

  private CurrentBrowserHolder() {
  }

  @SuppressWarnings("unchecked")
  public static <T extends TestBrowser> T getBrowser() {
    return (T) HOLDER.get();
  }

  public static <T extends TestBrowser> void setBrowser(T container) {
    HOLDER.set(container);
  }

  public static void stop() {
    TestBrowser browser = getBrowser();
    if (browser != null) {
      browser.stop();
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends TestBrowser> Class<T> getBrowserType() {
    return BROWSER_CLASS.get();
  }

  public static <T extends TestBrowser> void setBrowserType(Class<T> browserType) {
    BROWSER_CLASS.set(browserType);
  }
}
