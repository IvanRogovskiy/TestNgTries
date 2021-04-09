package AutoUni.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MarketplacePage {

    WebDriver driver;
    WebDriverWait webDriverWait;

    public MarketplacePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 15);
    }

    public FoundItemsPage searchItemByPartNumber(String partNumber) {
        By searchFieldLocator = By.cssSelector("input[name='search']");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));
        WebElement searchField = driver.findElement(searchFieldLocator);
        searchField.clear();
        searchField.sendKeys(partNumber);
        searchField.sendKeys(Keys.ENTER);
        return new FoundItemsPage(driver).isOpened();
    }
}
