package AutoUni.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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

    public boolean waitForPageToLoad() {
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
}
