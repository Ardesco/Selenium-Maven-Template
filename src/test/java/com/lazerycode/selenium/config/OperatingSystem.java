package com.lazerycode.selenium.config;

enum OperatingSystem {

    WINDOWS("windows"),
    MAC("mac"),
    LINUX("linux");

    private String operatingSystemName;

    OperatingSystem(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    String getOperatingSystemType() {
        return operatingSystemName;
    }

    static OperatingSystem getOperatingSystem() {

        String name = System.getProperties().getProperty("os.name");

        for (OperatingSystem operatingSystemName : values()) {
            if (name.toLowerCase().contains(operatingSystemName.getOperatingSystemType())) {
                return operatingSystemName;
            }
        }

        throw new IllegalArgumentException("Unrecognised operating system name '" + name + "'");
    }
}