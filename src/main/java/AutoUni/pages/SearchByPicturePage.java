package AutoUni.pages;

import AutoUni.helper.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchByPicturePage {

    WebDriver driver;
    WebDriverWait webDriverWait;

    private final By downloadFileFieldLocator = By.cssSelector(".cbir-panel__file-input");
    public final By otherSitesSpanLocator = By.cssSelector("div[data-bem = '{\"other-sites\":{}}']>h2");

    public SearchByPicturePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public SearchByPicturePage uploadPicture(String filePath) {
        new CommonHelper(driver).uploadFile(downloadFileFieldLocator, filePath);
        return this;
    }

    //TODO Maybe move this method to the common interface of pages
    public SearchByPicturePage hasContent(String expectedContent) {
        String elementTextContent = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(otherSitesSpanLocator)).getText();
        Assert.assertEquals(elementTextContent, expectedContent);
        return this;
    }
}
