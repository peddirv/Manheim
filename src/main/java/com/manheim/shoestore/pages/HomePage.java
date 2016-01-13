package com.manheim.shoestore.pages;
/**
 * Created by rpeddi on 1/12/2016.
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.asserts.SoftAssert;

public class HomePage {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static By email = By.id("remind_email_input");
    private static By submit = By.xpath("//div[@class='left']/input[@type='submit']");
    private static By message = By.id("flash");
    public SoftAssert softAssert = new SoftAssert();

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Method to Click the Month Link
     *
     * @param driver    in value
     * @param monthName in value
     * @throws Exception on error
     */
    public void clickMonth(WebDriver driver, String monthName) throws Exception {
        try {
            driver.findElement(By.linkText(monthName)).click();
        } catch (Exception e) {
            System.out.println("Failed : Cannot find Month : " + monthName);
        }
    }

    /**
     * Method to Click the Submit Button
     *
     * @param driver in value
     * @throws Exception on error
     */
    public void clickSubmit(WebDriver driver) throws Exception {
        try {
            driver.findElement(submit).click();
        } catch (Exception e) {
            softAssert.fail("Failed : Cannot find Submit Button");
        }
    }

    /**
     * Method to set the email id value in email id field
     *
     * @param driver   in value
     * @param strEmail in value
     * @throws Exception on error
     */
    public void setTextEmail(WebDriver driver, String strEmail) throws Exception {
        try {
            driver.findElement(email).sendKeys(strEmail);
        } catch (Exception e) {
            softAssert.fail("Failed : Cannot find email text box");
        }
    }

    /**
     * Method to verify success Message
     *
     * @param driver  in value
     * @param emailID in value
     * @throws Exception on error
     */
    public void verifyMessage(WebDriver driver, String emailID) throws Exception {
        String successMessage = driver.findElement(message).getText();
        softAssert.assertTrue(successMessage.contentEquals(
                        "Thanks! We will notify you of our new shoes at this email: " + emailID),
                "Failed : Success Message for EmailID " + emailID + " does not exist");
    }

    /**
     * Method to verify message exist or not based on the flag
     *
     * @param driver  in value
     * @param emailID in value
     * @param flag    in value, flag = 0 means a valid email
     * @throws Exception on error
     */
    public void verifyMessage(WebDriver driver, String emailID, int flag) throws Exception {
        if (flag == 0) {
            verifyMessage(driver, emailID);
        } else {
            verifyMessageNotExist(driver, emailID);
        }

    }

    /**
     * Method to verify message field does not exist
     *
     * @param driver in value
     * @return out value
     * @throws Exception on error
     */
    public boolean verifyMessageFieldNotExist(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.findElements(message).size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to verify message field does not exist
     *
     * @param driver  in value
     * @param emailID in value
     * @throws Exception on error
     */
    public void verifyMessageNotExist(WebDriver driver, String emailID) throws Exception {

        softAssert.assertTrue(verifyMessageFieldNotExist(driver),
                "Failed : Success Message for EmailID " + emailID + "  exist");

    }

    /**
     * Call the method to assert all the failures
     */
    public void assertAll() {
        softAssert.assertAll();
    }
}
