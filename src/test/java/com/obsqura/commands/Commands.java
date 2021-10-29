package com.obsqura.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Commands {
    WebDriver driver;
    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\selenium\\driverfiles\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver","C:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    @BeforeMethod
    public void setup()
    {
        testInitialize("chrome");
    }
    @AfterMethod
    public void tearDown()
    {
        //driver.close();
    }
    @Test(priority = 1,enabled = false)
    public void verifyUserLogin()
    {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("navyanaveenam@gmail.com");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("Rithul@12");
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();
        WebElement loginText = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = loginText.getText();
        String expectedUsername = "navyanaveenam@gmail.com";
        Assert.assertEquals(actualUserName,expectedUsername,"LOGIN FAILED");

    }
    @Test(priority = 2,enabled = false)
    public void verifyHomePageTitle()
    {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle,expectedTitle,"INVALID HOMEPAGE TITLE");
    }
    @Test(priority = 3,enabled = true)
    public void verifyFileUpload()
    {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile = driver.findElement(By.id("uploadfile_0"));
        chooseFile.sendKeys("C:\\selenium\\data\\sample.txt");
        WebElement accept = driver.findElement(By.id("terms"));
        accept.click();
        WebElement submit = driver.findElement(By.id("submitbutton"));
        submit.click();
    }
}

