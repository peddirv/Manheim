package com.manheim.shoestore;
/**
 * Created by rpeddi on 1/12/2016.
 *
 * Story 1: Monthly display of new releases
 * In order to view upcoming shoes being released every month As a user of the Shoe store I want to be able to visit a link for each month and see the shoes being released
 *
 * Acceptance Criteria
 *
 * Month should display a small Blurb of each shoe
 * Month should display an image each shoe being released
 *  Each shoe should have a suggested price pricing
 */

import com.manheim.shoestore.pages.HomePage;
import com.manheim.shoestore.pages.Month;

import java.text.DateFormatSymbols;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class Story1 extends TestExtension {
    private SoftAssert softAssert = new SoftAssert();
    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * dataprovider to use all months
     *
     * @return out value
     */
    @DataProvider(name = "month")
    public Object[][] createData() {
        return new Object[][] {
                new Object[] {"January"},
                {"February"},
                {"March"},
                {"April"},
                {"May"},
                {"June"},
                {"July"},
                {"August"},
                {"September"},
                {"October"},
                {"November"},
                {"December"}
            };
    }

    /**
     * Test that navigates to all the month pages and verifies Description, Pricing and Image for all the shoes
     * If there is no Inventory, it reports no inventory message
     *
     *
     * @param month in value
     *
     * @throws Exception on error
     */
    @Test(dataProvider = "month")
    public void shouldNavigateToSpecificMonthPageAndVerifyData(String month) throws Exception {
        HomePage homePage = new HomePage();
        Month months = new Month();
        homePage.clickMonth(driver, month);

        int noOfShoes = months.getShoeFrameCount(driver);
        if (noOfShoes > 0) {
            for (int i = 0; i < noOfShoes; i++) {
                months.doesShoeDescriptionPricingImageExist(driver, i);
            }
        } else {
            System.out.println("No Inventory");
        }
        months.assertAll();
    }
}
