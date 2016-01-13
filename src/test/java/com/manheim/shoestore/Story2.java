package com.manheim.shoestore;

import com.manheim.shoestore.pages.HomePage;
import com.manheim.shoestore.pages.Month;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by rpeddi on 1/12/2016.
 */


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
    }
}
