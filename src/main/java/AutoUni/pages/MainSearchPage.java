package AutoUni.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainSearchPage {

    WebDriver driver;
    WebDriverWait webDriverWait;
    By searchByPictureButtonLocator = By.cssSelector("button[aria-label = 'Поиск по изображению']");

    public MainSearchPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public SearchByPicturePage selectSearchingByDownloadedPicture() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchByPictureButtonLocator)).click();
        return new SearchByPicturePage(driver);
    }


}

