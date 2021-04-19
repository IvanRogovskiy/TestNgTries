package AutoUni.pages;

import AutoUni.helper.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class FoundItemsPage {

    WebDriver driver;
    WebDriverWait webDriverWait;
    private final By priceLocator = By.cssSelector(".item .b5v6.c8c6.c4v8"),
    itemTitleLocator = By.cssSelector(".a2g0.tile-hover-target");

    public FoundItemsPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public FoundItemsPage isOpened() {
        Assert.assertTrue(new CommonHelper(driver).isPageLoaded());
        return this;
    }

    public List<WebElement> getFoundItems() {
        By itemLocator = By.cssSelector(".a0c6.a0c9");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
        return driver.findElements(itemLocator);
    }

    public WebElement getItem() {
        return getFoundItems().get(0);
    }

    public FoundItemsPage hasValidPrice(String price) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator));
        String actualPrice = driver.findElement(priceLocator).getText().replace(" ₽", "");
        Assert.assertEquals(
                actualPrice, price
        );
        return this;
    }

    public FoundItemsPage hasValidPriceTagColor() {
        String currentColor = driver.findElement(priceLocator).getCssValue("color");
        Assert.assertEquals(currentColor, "rgba(249, 17, 85, 1)");
        return this;
    }

    public FoundItemsPage hasValidBrand(String brandName) {
        Assert.assertTrue(driver.findElement(itemTitleLocator).getText().contains(brandName));
        return new FoundItemsPage(driver);
    }





}
