import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Logging {
    private WebDriver driver;
    private WebDriverWait wait;
    private String Url= "https://www.saucedemo.com/";
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","E:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void test1(){
        driver.get(Url);
        assertTrue(driver.findElement(By.id("user-name")).isDisplayed());
        assertTrue(driver.findElement(By.id("password")).isDisplayed());
        assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }

    @Test
    public void test2(){
        driver.get(Url);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Swag Labs')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]")).isDisplayed());
    }



    @Test
    public void test3(){
        driver.get(Url);
        driver.findElement(By.id("user-name")).sendKeys("Habiba");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();
        WebElement error= driver.findElement(By.cssSelector("div.error-message-container.error"));
        assertTrue(error.isDisplayed());
        assertTrue(error.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }

    @Test
    public void test4(){
        driver.get(Url);
        driver.findElement(By.id("login-button")).click();

        WebElement usernameError = driver.findElement(By.cssSelector("div.error-message-container.error"));
        assertTrue(usernameError.isDisplayed());
        assertTrue(usernameError.getText().contains("Epic sadface: Username is required"));

    }

    @Test
    public void test5(){
        driver.get(Url);
        driver.findElement(By.id("user-name")).sendKeys("Sugar");
        driver.findElement(By.id("login-button")).click();

        WebElement passwordError = driver.findElement(By.cssSelector("div.error-message-container.error"));
        assertTrue(passwordError.isDisplayed());
        assertTrue(passwordError.getText().contains("Epic sadface: Password is required"));
    }

    @Test
    public void test6(){
        driver.get(Url);
        driver.findElement(By.id("password")).sendKeys("birthday");
        driver.findElement(By.id("login-button")).click();

        WebElement usernameError = driver.findElement(By.cssSelector("div.error-message-container.error"));
        assertTrue(usernameError.isDisplayed());
        assertTrue(usernameError.getText().contains("Epic sadface: Username is required"));
    }

    @AfterClass
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
}
