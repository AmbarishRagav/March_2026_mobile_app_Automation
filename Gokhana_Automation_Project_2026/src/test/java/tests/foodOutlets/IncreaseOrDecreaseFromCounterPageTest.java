package tests.foodOutlets;

import bases.Base;
import pages.FoodOutletPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IncreaseOrDecreaseFromCounterPageTest extends Base {

    FoodOutletPage foodOutletPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        foodOutletPage = new FoodOutletPage(driver);
    }

    @Test
    public void modifyQuantityFromCounterPage() {
        try {
            foodOutletPage.increaseAndDecreaseFromCounterPage();
            Assert.assertTrue(true, "Quantity modified from counter page successfully");
        } catch (Exception e) {
            Assert.fail("Counter page quantity modification failed: " + e.getMessage());
        }
    }
}
