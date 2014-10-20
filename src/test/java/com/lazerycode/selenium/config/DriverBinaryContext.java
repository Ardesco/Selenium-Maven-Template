package com.lazerycode.selenium.config;

class DriverBinaryContext {

    private static final String ROOT_FOLDER = System.getProperty("binaryRootFolder");
    private final DriverType driverType;
    private final SystemArchitecture systemArchitecture;
    private final OperatingSystem operatingSystem;

    private DriverBinaryContext(DriverType driverType, OperatingSystem operatingSystem, SystemArchitecture systemArchitecture) {
        this.operatingSystem = operatingSystem;
        this.driverType = driverType;
        this.systemArchitecture = systemArchitecture;
    }

    static DriverBinaryContext binaryFor(DriverType browserType, OperatingSystem osName, SystemArchitecture architecture) {
        return new DriverBinaryContext(browserType, osName, architecture);
    }

    static String binaryPath(String relativePath) {
        return ROOT_FOLDER + relativePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverBinaryContext that = (DriverBinaryContext) o;

        if (driverType != that.driverType) return false;
        if (operatingSystem != that.operatingSystem) return false;
        if (systemArchitecture != that.systemArchitecture) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = driverType.hashCode();
        result = 31 * result + systemArchitecture.hashCode();
        result = 31 * result + operatingSystem.hashCode();
        return result;
    }
}