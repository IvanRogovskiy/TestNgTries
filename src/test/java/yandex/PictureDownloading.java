package yandex;

import AutoUni.DriverInit;
import AutoUni.helper.CommonHelper;
import AutoUni.pages.MainSearchPage;
import AutoUni.pages.SearchByPicturePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PictureDownloading extends DriverInit {

    @Test(groups = {"yandex"})
    public void openPage() {
        driver.get(config.getProperty("baseUrl"));
        new CommonHelper(driver).isPageLoaded();
    }

    @Test(groups = {"yandex"})
    public void searchByImage() {
        openPage();
        MainSearchPage mainSearchPage = new MainSearchPage(driver);
        mainSearchPage.selectSearchingByDownloadedPicture()
                .uploadPicture(config.getProperty("testfilesFolder") + "pictures//test_automation.png");
        new CommonHelper(driver).isPageLoaded();
        new SearchByPicturePage(driver).hasContent("Сайты, где встречается изображение");

    }

    @Test(groups = {"yandex"})
    public void downloadImage() {
        searchByImage();
        String downloadedImageName = "downloadedImage2";
        String fileType = "png";
        CommonHelper commonHelper = new CommonHelper(driver);
        commonHelper.saveAsImage(driver.findElement(By.cssSelector(".CbirPreview-Image")), downloadedImageName, fileType);
        String filePath = "src\\main\\resources\\testfiles\\downloaded\\" + downloadedImageName + "." + fileType;
        Assert.assertTrue(Files.exists((Paths.get(filePath))));
        commonHelper.deleteFile(filePath);
    }

}
