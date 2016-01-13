package com.manheim.shoestore.pages;
/**
 * Created by rpeddi on 1/12/2016.
 */


import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

public class Month {

    //~ Instance fields ------------------------------------------------------------------------------------------------

    By price = By.xpath("//td[@class='shoe_result_value shoe_price']");
    By shoeDescription = By.xpath("//td[@class='shoe_result_value shoe_description']");
    By shoeFrames = By.xpath("//ul['shoe_list']/li/div['shoe_result']");
    By shoeImage = By.xpath("//td[@class='shoe_image']/img");

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Check to see if Description, Pricing and Image Exist for row under test
     *
     * @param driver    in value
     * @param rowNumber in value
     */
    public void doesShoeDescriptionPricingImageExist(WebDriver driver, int rowNumber) {
        doesShoeDescriptionExist(driver, rowNumber);
        doesShoePriceExist(driver, rowNumber);
        doesImagesExist(driver, rowNumber);
    }


    /**
     * get count of shoes available
     *
     * @param driver    in value
     */
    public int getShoeFrameCount(WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver.findElements(shoeFrames).size();
        } catch (Exception e) {
            System.out.println("Cannot Find any Shoes");
            return 0;
        }
    }

    /**
     * Generic method to verify data Exist for a element
     *
     * @param driver    in value
     * @param rowNumber in value
     * @param element   in value
     * @param message   in value
     */
    private void doesElementDataExist(WebDriver driver, int rowNumber, By element, String message) {
        try {
            List<WebElement> webElements = driver.findElements(element);
            int textLength = webElements.get(rowNumber).getText().length();
            Assert.assertTrue(textLength > 0, "Verify " + message + " - " + webElements.get(rowNumber).getText());
        } catch (AssertionError e) {
            System.out.println("Cannot Find - " + message);
        }
    }

    /**
     * Method to verify if Image exist
     *
     * @param driver    in value
     * @param rowNumber in value
     */
    private void doesImagesExist(WebDriver driver, int rowNumber) {
        try {
            List<WebElement> webElements = driver.findElements(shoeImage);
            String imgURL = webElements.get(rowNumber).getAttribute("src");
            Assert.assertTrue(imgURL.toLowerCase().contains(".jpg"), "Verify Image - " + imgURL);
        } catch (AssertionError e) {
            System.out.println("Cannot Find - Image");
        }
    }

    /**
     * Method to verify of shoe description has text
     *
     * @param driver    in value
     * @param rowNumber in value
     */
    private void doesShoeDescriptionExist(WebDriver driver, int rowNumber) {
        doesElementDataExist(driver, rowNumber, shoeDescription, " Shoe Names ");
    }

    /**
     * Method to verify of shoe price has value
     *
     * @param driver    in value
     * @param rowNumber in value
     */
    private void doesShoePriceExist(WebDriver driver, int rowNumber) {
        doesElementDataExist(driver, rowNumber, price, " Shoe Price ");
    }
}
