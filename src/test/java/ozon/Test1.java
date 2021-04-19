package ozon;

import AutoUni.DriverInit;
import AutoUni.pages.FoundItemsPage;
import AutoUni.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends DriverInit {

    @Test(groups = {"smoke"})
    public void openIntraPage() {
        driver.get(baseUrl);
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Test(groups = {"smoke"})
    public void checkItem() {
        driver.get(baseUrl);
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        FoundItemsPage foundItemsPage = new FoundItemsPage(driver);
        marketplacePage.searchItemByPartNumber("32525737")
                .getItem();
        foundItemsPage.hasValidPrice("2 481")
                .hasValidPriceTagColor()
                .hasValidBrand("Apple");
    }

}
