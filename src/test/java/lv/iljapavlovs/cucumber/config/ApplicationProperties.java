package lv.iljapavlovs.cucumber.config;

import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.ENV;

import java.util.HashMap;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationProperties {

    private static HashMap<String, Properties> DEFAULT_VALUES = new HashMap<String, Properties>() {
        {
            put("default", new Properties() {
                {
                    //timeout and wait time properties
                    setProperty(ApplicationProperty.WAIT_SHORT_SECONDS.name, "3");
                    setProperty(ApplicationProperty.WAIT_NORMAL_SECONDS.name, "5");
                    setProperty(ApplicationProperty.WAIT_LONG_SECONDS.name, "60");


                    setProperty(ApplicationProperty.BROWSER.name, "chrome");
                    setProperty(ApplicationProperty.HEADLESS.name, "false");
                    setProperty(ApplicationProperty.ENABLE_VNC.name, "false");
                    setProperty(ApplicationProperty.ENABLE_VIDEO.name, "false");

                    setProperty(ApplicationProperty.PROXY_ENABLED.name, "false");
                    setProperty(ApplicationProperty.PROXY_HOST.name, "proxy.com");
                    setProperty(ApplicationProperty.PROXY_PORT.name, "8080");

                    //application URL's
                    setProperty(ApplicationProperty.APP_URL.name, "https://google.com");

                    //Selenium grid settings
                    setProperty(ApplicationProperty.REMOTE_DRIVER.name, "false");
                    setProperty(ApplicationProperty.SELENIUM_GRID_URL.name, "http://localhost:4444/wd/hub");

                    setProperty(ApplicationProperty.DESIRED_BROWSER_VERSION.name, "");
                    setProperty(ApplicationProperty.DESIRED_PLATFORM.name, "");
                    setProperty(ApplicationProperty.SELENIUM_GRID_RETRY_COUNT.name, "3");

                    //TODO - this should be changed for local execution
                    setProperty(ApplicationProperty.CHROME_BINARY_PATH.name, "");
                    setProperty(ApplicationProperty.FIREFOX_BINARY_PATH.name, "C:\\Users\\ipavlov\\AppData\\Local\\Mozilla Firefox\\firefox.exe");


                }
            });
            put("local", new Properties() {
                {
                    setProperty(ApplicationProperty.APP_URL.name, "http://localhost:8080/#");
                    setProperty(ApplicationProperty.REMOTE_DRIVER.name, "false");
                }
            });
            put("test", new Properties() { //DEV environment for running CI tests
                {
                    setProperty(ApplicationProperty.APP_URL.name, "https://google.com");
                    setProperty(ApplicationProperty.REMOTE_DRIVER.name, "true");
                }
            });
        }
    };

    private static String getString(String propertyName) {
        String currentEnv = System.getProperties().getProperty(
                ENV.name, System.getenv(ENV.name.toUpperCase().replace('.', '_')));

        if (System.getProperties().containsKey(propertyName)) {
            return System.getProperties().getProperty(propertyName);
        }
        if (currentEnv != null) {
            if (DEFAULT_VALUES.get(currentEnv).containsKey(propertyName)) {
                return DEFAULT_VALUES.get(currentEnv).getProperty(propertyName);
            }
        }
        if (DEFAULT_VALUES.get("default").containsKey(propertyName)) {
            return DEFAULT_VALUES.get("default").getProperty(propertyName);
        }

        log.warn("Unknown application property: " + propertyName);
        throw new RuntimeException("Unknown application property: " + propertyName);
    }

    public static String getString(ApplicationProperty property) {
        return getString(property.name);
    }

    public static Integer getInteger(ApplicationProperty property) {
        return Integer.valueOf(getString(property));
    }

    public static boolean getBoolean(ApplicationProperty property) {
        return Boolean.valueOf(getString(property));
    }

    public enum ApplicationProperty {

        ENV("env"), APP_URL("appUrl"), BROWSER("browser"),
        HEADLESS("headless"),
        ENABLE_VIDEO("enableVideo"),
        ENABLE_VNC("enableVNC"),
        PROXY_ENABLED("proxyEnabled"),
        PROXY_HOST("proxyHost"), PROXY_PORT("proxyPort"),
        WAIT_SHORT_SECONDS("waitShortSeconds"), WAIT_NORMAL_SECONDS("waitNormalSeconds"), WAIT_LONG_SECONDS("waitLongSeconds"),
        REMOTE_DRIVER("remoteDriver"),
        SELENIUM_GRID_RETRY_COUNT("seleniumGridRetries"),
        SELENIUM_GRID_URL("seleniumGridUrl"),
        DESIRED_BROWSER_VERSION("desiredBrowserVersion"),
        DESIRED_PLATFORM("desiredPlatform"),
        CHROME_BINARY_PATH("chromeBinaryPath"),
        FIREFOX_BINARY_PATH("firefoxBinaryPath"),
        CHROME_DRIVER_PATH("chromeDriverPath"),
        GECKO_DRIVER_PATH("geckoDriverPath");

        private String name;

        ApplicationProperty(String name) {
            this.name = name;
        }
    }
}
