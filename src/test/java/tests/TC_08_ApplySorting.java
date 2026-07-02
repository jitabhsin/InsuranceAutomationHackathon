package tests;

import basetest.BaseTest;
import org.insurance.pages.TravelQuotePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_ApplySorting extends BaseTest {

    @Test
    public void testApplyFilter() {

        TravelQuotePage filter = new TravelQuotePage(driver);

        boolean isFilterApply = filter.applyFilter();
        Assert.assertTrue(isFilterApply, "Filter applied from low to high price");
    }
}
