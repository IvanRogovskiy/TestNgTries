package AutoUni.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class CommonHelper {

    WebDriver driver;
    private final int TIMEOUT_FOR_PAGE_LOADED = 5;

    public CommonHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTimeout(long currentTime, long timeoutInSeconds) {
        long wait_time = timeoutInSeconds * 1000;
        long end_time =currentTime + wait_time;
        return (System.currentTimeMillis() > end_time);
    }

    private String returnDocumentStatus() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        return (String) javascriptExecutor.executeScript("return document.readyState");
    }

    public boolean isPageLoaded() {
        String pageLoadStatus = "";
        boolean pageWasLoaded = false;
        long startTime = System.currentTimeMillis();

        do {
            try {
                pageLoadStatus = this.returnDocumentStatus();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (pageLoadStatus.equals("complete") || pageLoadStatus.equals("interactive")) {
                pageWasLoaded = true;
            }
        } while (!pageWasLoaded && this.isTimeout(startTime, TIMEOUT_FOR_PAGE_LOADED));
        return  pageWasLoaded;

    }

    public void uploadFile(By by, String filePath) {
        driver.findElement(by).sendKeys(filePath);

    }

    public void downloadFile(String linkToFile) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "C:\\Dih\\tries\\TestNgTries\\src\\main\\resources\\testfiles\\downloaded");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver.get(linkToFile);
    }

    public void saveAsImage(WebElement imageElement, String imageName, String type) {
        String imageSrc = imageElement.getAttribute("src");

        try {
            URL imageURL = new URL(imageSrc);
            BufferedImage saveImage = ImageIO.read(imageURL);
            ImageIO.write(saveImage, "png", new File("src\\main\\resources\\testfiles\\downloaded\\" + imageName + "." + type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFile(String filePath) {
        return new File(filePath).delete();

    }
}
