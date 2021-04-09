package AutoUni;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import java.nio.file.Paths;

public class DriverInit {

    public WebDriver driver = null;
    public String current_env = "chrome";
    public static final String webdriversFolder = "C:\\Dih\\tries\\TestNgTries\\src\\main\\resources\\webdrivers\\";
    public static final String baseUrl = "https://www.ozon.ru";
    public static final String expectedTitle = "OZON — интернет-магазин. Миллионы товаров по выгодным ценам";
    public static final String actualTitle = "";


    @BeforeTest
    public void initBrowser() {
        String pathToDriver;
        switch (this.current_env) {
            case "firefox":
                pathToDriver = Paths.get(webdriversFolder + "geckodriver.exe")
                        .toAbsolutePath()
                        .toString();

                System.setProperty("webdriver.gecko.driver", pathToDriver);
                this.driver = new FirefoxDriver();
                this.driver.manage().window().maximize();
                break;
            case "chrome":
                pathToDriver = Paths.get(webdriversFolder + "chromedriver.exe")
                        .toAbsolutePath()
                        .toString();

                System.setProperty("webdriver.chrome.driver", pathToDriver);
                this.driver = new ChromeDriver();
                this.driver.manage().window().maximize();
                break;
            case "opera":
                pathToDriver = Paths.get(webdriversFolder + "operadriver.exe")
                        .toAbsolutePath()
                        .toString();

                System.setProperty("webdriver.opera.driver", pathToDriver);
                this.driver = new OperaDriver();
                this.driver.manage().window().maximize();
                break;
            default:
                this.driver = null;
                System.out.println("DRIVER IS NULL");
                break;
        }


    }

    @AfterTest
    public void closeBrowser() {
        this.driver.quit();
    }

//    public isElementPresent(By by) {
//
//    }

}
