package tests.foodOutlets;

import bases.Base;
import pages.FoodOutletPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidateViewCartTest extends Base {

    FoodOutletPage foodOutletPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        foodOutletPage = new FoodOutletPage(driver);
    }

    @Test
    public void validateViewCart() {
        try {
            foodOutletPage.validateViewCart();
            Assert.assertTrue(true, "View cart validated successfully");
        } catch (Exception e) {
            Assert.fail("View cart validation failed: " + e.getMessage());
        }
    }
}
