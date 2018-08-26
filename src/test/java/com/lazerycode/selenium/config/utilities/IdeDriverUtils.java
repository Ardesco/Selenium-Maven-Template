package com.lazerycode.selenium.config.utilities;

/**
 * <p>Date:    26/08/18
 *
 * @author mengxin
 * @version 1.0
 */
public class IdeDriverUtils {

    private final static String CHROME_PATH = "googlechrome";
    private final static String GECKO_PATH = "marionette";
    private final static String OPERA_PATH = "operachromium";
    private final static String PHANTOMJS_PATH = "phantomjs";
    private final static String EDGE_PATH = "edge";
    private final static String IE_PATH = "internetexplorer";

    private final static String CHROME = "chromedriver";
    private final static String GECKO = "geckodriver";
    private final static String OPERA = "operadriver";
    private final static String PHANTOMJS = "phantomjs";
    private final static String EDGE = "MicrosoftWebDriver.exe";
    private final static String IE = "IEDriverServer.exe";
    private final static String WINDOWS_SUFFIX = ".exe";

    private final static String ARCHE_64_TYPE = "64bit";
    private final static String ARCHE_32_TYPE = "32bit";

    private final static String LINUX = "linux";
    private final static String MACOS = "osx";
    private final static String WINDOWS = "windows";

    private final static String PATH_SEPARATOR = "/";

    private final static String ROOT_PATH = "/selenium_standalone_binaries";

    private static String generate64Path(String platform, String driverPath, String driverName) {
        return ROOT_PATH
                + PATH_SEPARATOR + platform
                + PATH_SEPARATOR + driverPath
                + PATH_SEPARATOR + ARCHE_64_TYPE
                + PATH_SEPARATOR + driverName;
    }

    public final static String LINUX_GOOGLE_PATH = generate64Path(LINUX, CHROME_PATH, CHROME);
    public final static String LINUX_GECKO_PATH = generate64Path(LINUX, GECKO_PATH, GECKO);
    public final static String LINUX_OPERA_PATH = generate64Path(LINUX, OPERA_PATH, OPERA);
    public final static String LINUX_PHANTOMJS_PATH = generate64Path(LINUX, PHANTOMJS_PATH,
            PHANTOMJS);

    public final static String MACOS_GOOGLE_PATH = generate64Path(MACOS, CHROME_PATH, CHROME);
    public final static String MACOS_GECKO_PATH = generate64Path(MACOS, GECKO_PATH, GECKO);
    public final static String MACOS_OPERA_PATH = generate64Path(MACOS, OPERA_PATH, OPERA);
    public final static String MACOS_PHANTOMJS_PATH = generate64Path(MACOS, PHANTOMJS_PATH,
            PHANTOMJS);

    public final static String WIN_GOOGLE_PATH = generate64Path(WINDOWS, CHROME_PATH,
            CHROME + WINDOWS_SUFFIX);
    public final static String WIN_GECKO_PATH = generate64Path(WINDOWS, GECKO_PATH,
            GECKO + WINDOWS_SUFFIX);
    public final static String WIN_OPERA_PATH = generate64Path(WINDOWS, OPERA_PATH,
            OPERA + WINDOWS_SUFFIX);
    public final static String WIN_PHANTOMJS_PATH = generate64Path(WINDOWS, PHANTOMJS_PATH,
            PHANTOMJS + WINDOWS_SUFFIX);
    public final static String WIN_EDGE_PATH = generate64Path(WINDOWS, EDGE_PATH, EDGE);
    public final static String WIN_IE_PATH = generate64Path(WINDOWS, IE_PATH, IE);

    private static String getAbsPath(String relativePath) {
        switch (OsUtils.getCurrentOS()) {
            case MAC:
            case LINUX:
                return String.valueOf(IdeDriverUtils.class.getResource(relativePath).getPath());
            case WINDOWS:
                return String.valueOf(IdeDriverUtils.class.getResource(relativePath).getPath())
                        .substring(1);
            default:
                return "";
        }
    }


    /**
     * <webdriver.chrome.driver>${webdriver.chrome.driver}</webdriver.chrome.driver>
     * <webdriver.ie.driver>${webdriver.ie.driver}</webdriver.ie.driver>
     * <webdriver.opera.driver>${webdriver.opera.driver}</webdriver.opera.driver>
     * <webdriver.gecko.driver>${webdriver.gecko.driver}</webdriver.gecko.driver>
     * <webdriver.edge.driver>${webdriver.edge.driver}</webdriver.edge.driver>
     */
    public static boolean ideInitialDrive() {
        //if the the system property get the ide=1, use these path configuration
        if (System.getProperty("ide")!=null && System.getProperty("ide").equals(String.valueOf(1))) {
            switch (OsUtils.getCurrentOS()) {
                case LINUX:
                    System.setProperty("webdriver.chrome.driver", getAbsPath(LINUX_GOOGLE_PATH));
                    System.setProperty("webdriver.gecko.driver", getAbsPath(LINUX_GECKO_PATH));
                    System.setProperty("webdriver.opera.driver", getAbsPath(LINUX_OPERA_PATH));
                    break;
                case WINDOWS:
                    System.setProperty("webdriver.chrome.driver", getAbsPath(WIN_GOOGLE_PATH));
                    System.setProperty("webdriver.gecko.driver", getAbsPath(WIN_GECKO_PATH));
                    System.setProperty("webdriver.opera.driver", getAbsPath(WIN_OPERA_PATH));
                    System.setProperty("webdriver.edge.driver", getAbsPath(WIN_EDGE_PATH));
                    System.setProperty("webdriver.ie.driver", getAbsPath(WIN_IE_PATH));
                    break;
                case MAC:
                    System.setProperty("webdriver.chrome.driver", getAbsPath(MACOS_GOOGLE_PATH));
                    System.setProperty("webdriver.gecko.driver", getAbsPath(MACOS_GECKO_PATH));
                    System.setProperty("webdriver.opera.driver", getAbsPath(MACOS_OPERA_PATH));
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }
}
