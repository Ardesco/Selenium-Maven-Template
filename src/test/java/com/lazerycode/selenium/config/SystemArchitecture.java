package com.lazerycode.selenium.config;

import java.util.Arrays;
import java.util.List;

enum SystemArchitecture {

    ARCHITECTURE_64_BIT("64 bit"),
    ARCHITECTURE_32_BIT("32 bit");

    private String systemArchitectureName;

    SystemArchitecture(String systemArchitectureName) {
        this.systemArchitectureName = systemArchitectureName;
    }

    String getSystemArchitectureType() {
        return systemArchitectureName;
    }

    public static final SystemArchitecture defaultSystemArchitecture = ARCHITECTURE_32_BIT;
    private static List<String> architecture64bitNames = Arrays.asList("amd64", "x86_64");

    static SystemArchitecture getSystemArchitecture() {

        final String currentArchitecture = System.getProperties().getProperty("os.arch");

        SystemArchitecture result = defaultSystemArchitecture;

        if (architecture64bitNames.contains(currentArchitecture)) {
            result = ARCHITECTURE_64_BIT;
        }

        return result;
    }
}