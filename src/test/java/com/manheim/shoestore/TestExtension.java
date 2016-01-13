package com.manheim.shoestore;

import com.manheim.shoestore.util.PropertyLoader;
import com.manheim.shoestore.util.SetupUtil;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This is the base class from which "Tests" can extend from. The "Instance fields" are declared here. However, the
 * instances are passed from the "testsuites" package in the .xml file(s). By passing the instances via the .xml file
 * this solves two prerequisites, (1) running the test via IntelliJ and (2) running the test via a Jenkins command line.
 * This will make it easier for the platform and services team to execute tests without going into the code and then
 * point it to a different environment.
 */
public class TestExtension {

    //~ Instance fields ------------------------------------------------------------------------------------------------

    protected String baseUrl;
    protected WebDriver driver;

    //~ Methods --------------------------------------------------------------------------------------------------------

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        SetupUtil setupUtil = new SetupUtil(new PropertyLoader());
        driver = setupUtil.getDriver();
        String baseUrl = setupUtil.getBaseUrl();
        driver.get(baseUrl);
    }

    /**
     * Tears down and captures the screenshot.
     *
     * @throws Exception on error
     */
    @AfterClass(alwaysRun = true)
    public void teardown() throws Exception {
        driver.quit();
    }
}
