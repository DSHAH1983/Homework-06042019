package LearnJunit;

import javafx.scene.layout.Priority;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Element;
import javax.xml.soap.SAAJResult;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/* the below function will always run tests in ascending order
of name as I would like the driver to register first
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NopCommerceAutomation {

    protected static WebDriver driver;

    DateFormat dateFormat = new SimpleDateFormat("mmddyyyyHHmmss");
    Date date = new Date();



    @Before

    public void setUp() {

        //C:\Users\User\IdeaProjects\LearnJunit\src\test\java\LearnJunit\BrowserDrivers\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\LearnJunit\\BrowserDrivers\\chromedriver.exe");


        driver = new ChromeDriver();


        //implicit wait is being used for driver instance which wil be applied to all driver instance.
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        //asking driver to get URL
        driver.get("https://demo.nopcommerce.com/");

    }

    @Test
    public void aUserShouldBeRegisteredSuccesfully()
    {

        //register as new user
        //asking driver to click on register
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        //asking driver to select gender
        driver.findElement(By.xpath("//input[@id='gender-female']")).click();

        //asking driver to enter first name
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Dhara");

        //asking driver to enter last name
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Desai");

        //asking driver to enter day of birth
        Select date2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        date2.selectByValue("1");

        //asking driver to enter month of birth
        Select month = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        month.selectByVisibleText("May");

        //asking driver to enter year of birth
        Select year = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        year.selectByVisibleText("1983");


        //asking driver to enter email
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("text" + date.getTime() + "@test.com");

        //asking driver to untick newsletter
        driver.findElement(By.xpath("//input[@checked='checked']")).click();

        // asking driver to enter password
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Dhara123");

        //asking driver to enter confirm password
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("Dhara123");

        //asking driver to click on register and user should see welcome message
        driver.findElement(By.xpath("//input[@name='register-button']")).click();


        // Verify actual result  equals expected result
        String actualRegistrationSuccessfulMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals("Your registration completed", actualRegistrationSuccessfulMessage);

    }

    @Test
    public void userShouldbeAbletoNavigateToNotebookCategoryPage()
    {
        //asking driver to open category page
        //hover on element with assert class
        Actions action = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//a[@href='/computers'][1]"));
        action.moveToElement(computer).moveToElement(driver.findElement(By.xpath("//a[@href='/notebooks'][1]"))).click().build().perform();

        // verify expected result meets actual result
        String actualNotebooks = driver.findElement(By.xpath("//h1[contains(text(),'Notebooks')]")).getText();
        Assert.assertEquals("Notebooks", actualNotebooks);

    }

    @Test
    public void userShouldBeAbleToNavigateToCellPage()
    {
        //asking driver to open category page
        //hover on element with assert class
        Actions action = new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//a[@href='/electronics'][1]"));
        action.moveToElement(electronics).moveToElement(driver.findElement(By.xpath("//a[@href='/cell-phones'][1]"))).click().build().perform();



        // verify expected result meets actual result
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://demo.nopcommerce.com/cell-phones", url);
    }

    @Test

    public void loginSuccessfully()
    {
        //asking driver should be able login
        driver.findElement(By.xpath("//a[@href='/login']")).click();

        //asking driver to enter email
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("text@test.com");

        //asking driver to enter password
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Dhara123");

        //asking driver to click login
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).submit();

        //verify expected result equals to actual result
        String logOut = driver.findElement(By.xpath("//a[@href='/logout']")).getText();
        Assert.assertEquals("Log out", logOut);

    }

    @Test

    public void userShouldBeAbleToAddProductToShoppingCart()

    {    //asking driver to click on Digital downloads
        driver.findElement(By.xpath("//a[@href='/digital-downloads']")).click();

        //verify driver is on download page
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://demo.nopcommerce.com/digital-downloads", url);

        //asking driver to click on 'If you wait (donation)'
        driver.findElement(By.xpath("//img[@alt='Picture of If You Wait (donation)']")).click();

        //verify driver can 'if you wait donation' album
        url = driver.getCurrentUrl();
        Assert.assertEquals("https://demo.nopcommerce.com/if-you-wait-donation", url);

        //asking driver to click on add to cart
        driver.findElement(By.xpath("//input[@id='add-to-cart-button-35']")).click();

        // verify expected meets actual
        String productAddedToCartMessage = driver.findElement(By.xpath("//p[@class='content']")).getText();
        Assert.assertEquals("The product has been added to your shopping cart", productAddedToCartMessage);


        //asking driver to click on cross sign
      //  driver.findElement(By.xpath("//span[@class='close']")).click();

        //asking driver to click on shoppin cart
        driver.findElement(By.xpath("//a[@href='/cart']")).click();


        //asking driver to click on Shopping cart
        url = driver.getCurrentUrl();
        Assert.assertEquals("https://demo.nopcommerce.com/cart", url);


        // verify expected result meets actual result
        String ifYouWaitDonation = driver.findElement(By.xpath("(//a[@href='/if-you-wait-donation'])[4]")).getText();
        Assert.assertEquals("If You Wait (donation)", ifYouWaitDonation);

    }

    @After
            public void closeBrowser()
    {

      driver.quit();
    }

}





