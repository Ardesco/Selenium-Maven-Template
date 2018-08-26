package com.lazerycode.selenium.config.utilities;

import org.apache.commons.lang3.SystemUtils;

/**
 * <p>Date:    26/08/18
 *
 * @author mengxin
 * @version 1.0
 */
public class OsUtils {
    public static String getOperatingSystemName() {
        return SystemUtils.OS_NAME;
    }

    public static OsType getCurrentOS() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return OsType.WINDOWS;
        }

        if (SystemUtils.IS_OS_LINUX) {
            return OsType.LINUX;
        }

        if (SystemUtils.IS_OS_MAC) {
            return OsType.MAC;
        }

        return OsType.UNKNOWN;
    }
}
