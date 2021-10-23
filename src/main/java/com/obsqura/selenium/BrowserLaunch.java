package com.obsqura.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {
    static WebDriver driver;
    public static void testInitialize(String browser) {
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
    public static void main(String[] args) {
        BrowserLaunch.testInitialize("chrome");
        String title = driver.getTitle();
        System.out.println(title);
        String url = driver.getCurrentUrl();
        System.out.println(url);
        String source = driver.getPageSource();
        //System.out.println(source);
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        String loginText = loginMenu.getText();
        System.out.println(loginText);
        loginMenu.click();
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("navya@gmail.com");
        userName.clear();
        String classValue = userName.getAttribute("class");
        System.out.println(classValue);
        String tagName = userName.getTagName();
        System.out.println(tagName);
        WebElement password = driver.findElement(By.id("Password"));
        //password.sendKeys("rithul123");
        WebElement submit = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
       // submit.click();
        //driver.close();
        //WEB ELEMENTS
       /* WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("navya@gmail.com");
        userName.click();
        WebElement name = driver.findElement(By.name("Email"));
        WebElement className = driver.findElement(By.className("email"));
        WebElement xpath = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        WebElement cssSelector = driver.findElement(By.cssSelector("#Email"));
        WebElement tagName = driver.findElement(By.tagName("input"));
        WebElement linkText = driver.findElement(By.linkText("Log in"));
        WebElement partialLinkText = driver.findElement(By.partialLinkText("Log"));*/
    }
}
