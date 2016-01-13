package com.manheim.shoestore;

/**
 * Created by rpeddi on 1/12/2016.
 *
 * Story 2: Submit email for reminder
 *
 *  In order to be reminded of upcoming shoe releases As a user of the Shoe Store I want to be able to submit my email address
 *
 *  Acceptance Criteria
 *
 *  There should be an area to submit email address
 *  on successful submission of a valid email address user should receive a message Thanks! We will notify you of our new shoes at this email: users email address
 */


import com.manheim.shoestore.pages.HomePage;
import com.manheim.shoestore.pages.Month;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Story2 extends TestExtension {

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Test data using dataprovider to feed emails and flag to check if its valid email
     *
     * @return out value
     */
    @DataProvider(name = "email")
    public Object[][] createData() {
        return new Object[][] {
                new Object[] {"ravi@at.com", new Integer(0)},
                {"test@test.com", new Integer(0)},
                {"test@testcom", new Integer(1)},
                {"testtestcom", new Integer(1)},
                {"testtest.com", new Integer(1)},
                {"test@test.", new Integer(1)},
                {"@test.com", new Integer(1)},
                {"test@.com", new Integer(1)},
                {"", new Integer(1)},
                {"!@#$%^&*()", new Integer(1)},
                {" ", new Integer(1)},
                {"@.", new Integer(1)}
            };
    }

    /**
     * This test method iputs email id and verifies success Message
     * or message did not display if the email is invalid
     *
     * @param emailID in value
     * @param flag in value
     *
     * @throws Exception on error
     */
    @Test(dataProvider = "email")
    public void shouldBeAbleToVerifyEmailForReminder(String emailID, Integer flag) throws Exception {
        HomePage homePage = new HomePage();
        homePage.setTextEmail(driver, emailID);
        homePage.clickSubmit(driver);
        homePage.verifyMessage(driver, emailID, flag);
        homePage.assertAll();
    }
}
