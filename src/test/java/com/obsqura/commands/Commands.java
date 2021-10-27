package com.obsqura.commands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        driver.get("http://demowebshop.tricentis.com/");
    }
    @BeforeMethod
    public void setup()
    {
        testInitialize("chrome");
    }
    @AfterMethod
    public void tearDown()
    {
        driver.close();
    }
    @Test(priority = 1,enabled = false)
    public void verifyUserLogin()
    {
        System.out.println("testNGexample");
    }
    @Test(priority = 2)
    public void verifyHomePageTitle()
    {
        String title = driver.getTitle();
        System.out.println(title);
    }
}

