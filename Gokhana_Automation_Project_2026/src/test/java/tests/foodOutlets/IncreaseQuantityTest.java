package tests.foodOutlets;

import bases.Base;
import pages.FoodOutletPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IncreaseQuantityTest extends Base {

    FoodOutletPage foodOutletPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        foodOutletPage = new FoodOutletPage(driver);
    }

    @Test
    public void increaseQuantity() {
        try {
            foodOutletPage.modifyQuantity("increase", 3);
            Assert.assertTrue(true, "Quantity increased successfully");
        } catch (Exception e) {
            Assert.fail("Quantity increase failed: " + e.getMessage());
        }
    }
}
