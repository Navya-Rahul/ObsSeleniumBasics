package com.obsqura.commands;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        driver.close();
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
    @Test(priority = 3,enabled = false)
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
    @Test(priority = 4,enabled = false)
    public void verifyConfirmationAlert()
    {
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        WebElement customerId = driver.findElement(By.name("cusid"));
        customerId.sendKeys("1234");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Alert alert = driver.switchTo().alert();
        //alert.accept();
        //alert.dismiss();
        String alertText = alert.getText();
        alert.accept();
        System.out.println(alertText);
    }
    @Test(priority = 5,enabled = false)
    public void verifySimpleAlert()
    {
        driver.get("https://demoqa.com/alerts");
        WebElement alertOne = driver.findElement(By.id("alertButton"));
        alertOne.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Test(priority = 6,enabled = false)
    public void verifyAlertAfterFiveSeconds() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement alertTwo = driver.findElement(By.id("timerAlertButton"));
        alertTwo.click();
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Test(priority = 7,enabled = false)
    public void verifyToolsQAConfirmationAlert()
    {
        driver.get("https://demoqa.com/alerts");
        WebElement alertThree = driver.findElement(By.id("confirmButton"));
        alertThree.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Test(priority = 7)
    public void verifypromptAlert()
    {
        driver.get("https://demoqa.com/alerts");
        WebElement alertThree = driver.findElement(By.id("promtButton"));
        alertThree.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Rithul");
        alert.accept();
    }
    @Test(priority = 8,enabled = false)
    public void verifyFramesInHtml() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame("frame1");
        //driver.switchTo().frame(1);
        WebElement frame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frame);
        WebElement frameOneText = driver.findElement(By.id("sampleHeading"));
        String frameText = frameOneText.getText();
        System.out.println(frameText);
        //driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }
    @Test(priority = 9,enabled = false)
    public void verifyTotalNumberOfFrames()
    {
        driver.get("https://demoqa.com/frames");
        List<WebElement> frame = driver.findElements(By.tagName("iframe"));
        int totalFrames = frame.size();
        System.out.println(totalFrames);
    }
}

