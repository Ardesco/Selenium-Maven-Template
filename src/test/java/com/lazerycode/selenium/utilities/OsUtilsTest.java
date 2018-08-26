package com.lazerycode.selenium.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * <p>Date:    26/08/18
 *
 * @author mengxin
 * @version 1.0
 */
public class OsUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(OsUtilsTest.class);

    @Test
    public void testGetOperatingSystemName() {
        String test = OsUtils.getOperatingSystemName();
        LOG.info(test);
    }

    @Test
    public void testGetCurrentOS() {
        OsType osType = OsUtils.getCurrentOS();
        LOG.info(osType.toString());
    }
}