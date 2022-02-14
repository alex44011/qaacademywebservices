package selenium.module;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class HomeworkTaskThree {
    private WebDriver driver;
    private static final By proceedToCheckOutLocator = By.xpath("//*[@title='Proceed to checkout']");
    private static final String orderPageUrl = "http://automationpractice.com/index.php?controller=order";
    private static final By searchBarId = By.id("search_query_top");
    @Test
    public void testHomework() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(searchBarId));
        WebElement searchBar = driver.findElement(searchBarId);
        String searchInput = "Shirt";
        searchBar.sendKeys(searchInput);
        WebElement searchButton = driver.findElement(By.name("submit_search"));
        searchButton.click();
        WebElement listView = driver.findElement(By.id("list"));
        listView.click();
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@title='Add to cart']"));
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutLocator));
        WebElement proceedToCheckOutButton = driver.findElement(proceedToCheckOutLocator);
        proceedToCheckOutButton.click();
        wait.until(ExpectedConditions.urlContains(orderPageUrl));
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, orderPageUrl);


    }

    @BeforeClass
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");
    }
    @AfterMethod
    public void tearDownTest() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        // Clean any generated test data
    }
}
