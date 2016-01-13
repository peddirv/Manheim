package com.manheim.shoestore.util;

import java.io.File;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

/**
 * Created by rpeddi on 5/17/2015.
 */
public class SetupUtil {

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private String baseUrl;

    private WebDriver driver;

    private File file;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new SetupUtil object.
     *
     * @param propertiesFile in value
     */
    public SetupUtil(PropertyLoader propertiesFile) {
        propertiesFile.loadProperties("platform.properties");
        Properties propEnvironment = propertiesFile.getProps();
        String envt = (String) propEnvironment.get("envt");

        String platformUrl = envt + ".url";
        String browserUsed = envt + ".browser";
        String browserRunning = (String) propEnvironment.get(browserUsed);

        Browser browserRun = new Browser();
        this.driver = browserRun.createDriver(browserRunning);
        this.baseUrl = (String) propEnvironment.get(platformUrl);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Getter for property base url
     *
     * @return out value
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Getter for property driver
     *
     * @return out value
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Getter for property file
     *
     * @return out value
     */
    public File getFile() {
        return file;
    }

    /**
     * Setter for property base url
     *
     * @param baseUrl in value
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Setter for property driver
     *
     * @param driver in value
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Setter for property file
     *
     * @param file in value
     */
    public void setFile(File file) {
        this.file = file;
    }
}
