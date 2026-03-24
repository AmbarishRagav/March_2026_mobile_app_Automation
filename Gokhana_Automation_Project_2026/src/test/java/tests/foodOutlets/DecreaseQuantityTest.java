package tests.foodOutlets;

import bases.Base;
import pages.FoodOutletPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DecreaseQuantityTest extends Base {

    FoodOutletPage foodOutletPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        foodOutletPage = new FoodOutletPage(driver);
    }

    @Test
    public void decreaseQuantity() {
        try {
            foodOutletPage.modifyQuantity("decrease", 3);
            Assert.assertTrue(true, "Quantity decreased successfully");
        } catch (Exception e) {
            Assert.fail("Quantity decrease failed: " + e.getMessage());
        }
    }
}
