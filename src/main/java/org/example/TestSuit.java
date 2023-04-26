package org.example;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    protected static WebDriver driver;
    static String expectedRegistretionCompleteMSG = "Your registration completed";
    static String expectedproductIntract = "Register is Successful";
    @BeforeMethod
    public static void openBrowser(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().minimize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod

    public static void closeBrowser(){
        driver.close();
    }
    @Test
    public static void veryfyUserShouldableToregisterSucccessfully(){
        openBrowser();
        clickOnElement(By.className("ico-register"));

        typetaxt(By.id("FirstName"),"TestFirst");//enter name
        typetaxt(By.id("LastName"),"TestLastName");//enter surname
        typetaxt(By.name("email"),"test+"+timestamp()+"@gmail.com");//enter email
        typetaxt(By.id("Password"),"test1234");//enter pass word
        typetaxt(By.id("ConfirmPassword"),"test1234");// enter conform password
        clickOnElement(By.id("register-button"));// click on register button
        String actualMessage = getTextFromelement(By.xpath("//div[@class=\"result\"]"));
        System.out.println("My mesage:"+actualMessage);// result willl come out
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"registration"+"is working");
        closeBrowser();
    }

    public static void clickOnElement(By by){driver.findElement(by).click();}
    public static void typetaxt(By by, String text){driver.findElement(by).sendKeys(text);}
    public static String getTextFromelement(By by){return driver.findElement(by).getText();}
    public static long timestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
    @Test
    public static void veryfyingUsersershouldvote(){
        openBrowser();
        clickOnElement(By.id("pollanswers-2"));//click on good button
        clickOnElement(By.id("vote-poll-1"));// click on vote button
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));//for slwo method
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));
        String actualMessage= getTextFromelement(By.xpath("//div[@class=\"poll-vote-error\"]"));
        System.out.println("Actual masage:"+actualMessage);//for result
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"can compair product");//user able to vote
        closeBrowser();
        }
    @Test
    public static void CompaireProducts(){
        openBrowser();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        clickOnElement(By.xpath("//div[@class=\\\"item-grid\\\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        //click on compair product button
        clickOnElement(By.xpath("//div[@class=\\\"item-grid\\\"]/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        clickOnElement(By.xpath("// p[@class=\"content\"]"));//see the two product
        clickOnElement(By.className("//a[@class=\"clear-list\"]"));//click on clear button
        String actualMessage = getTextFromelement(By.xpath("/html/body/div[6]//div[3]/div[2]/div/div[2]/div"));
        System.out.println("Actual masage:"+actualMessage);
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"can compair product");//result will out
        closeBrowser();}
    @Test
    public static void emailtoyourfriend(){
        openBrowser();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        clickOnElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div/div[2]/div[3]" +
                "/div[2]/button[1]"));//clickon product
        clickOnElement(By.className("email-a-friend"));//do email friend
        typetaxt(By.id("//input[@id=\"FriendEmail\"]"),"testEmail+"+timestamp()+"friend@gmail.com");//enter friend email
        typetaxt(By.id("YourEmailAddress"),"testEmail+"+timestamp()+"Bhav@gmail.com");//enter your email
        typetaxt(By.id("PersonalMessage"),"Hello");//type mesage
        clickOnElement(By.name("Send email"));//press on button
        String actualMessage =getTextFromelement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]"));
        System.out.println("actual message"+actualMessage);//message willl print out
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"succsessfuly can send email");
        closeBrowser();
    }
    @Test
    public static void verifyUserShouldAbletoAddproductinShoppingCart(){
        openBrowser();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());//to emplicity iteam
        clickOnElement(By.linkText("Electronic"));//click on electronics button
        clickOnElement(By.linkText("Camera & photo"));//click on camera photo
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));//press on leica
        clickOnElement(By.linkText("add-to-cart-button-16"));//press on add to cart button
        clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));//click on
        String actualMessage = getTextFromelement(By.linkText("Leica T Mirrorless Digital Camera"));
        System.out.println("Message out" +actualMessage);//message willl print out
        Assert.assertEquals(actualMessage, expectedRegistretionCompleteMSG,"product is adding in the shopping cart");

        closeBrowser();}
    @Test

public static void veryfyresisteruseshouldabletoreferefriend(){

        openBrowser();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        clickOnElement(By.className("ico-register"));
        typetaxt(By.id("FirstName"),"ABCD" );
        typetaxt(By.id("LastName"),"XYZ");//enter surname
        typetaxt(By.id("Email"),"Test"+timestamp()+"@gmail.com");
        typetaxt(By.name("Password"),"test1234");//enter pasword
        typetaxt(By.id("ConfirmPassword"),"abcd1234");
        clickOnElement(By.id("register-button"));//click on register
        clickOnElement(By.className("ico-login"));
        typetaxt(By.id("Email"),"Testtest@gmail.com");
        typetaxt(By.id("Password"),"abcd1234");
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));//submit for log in
        clickOnElement(By.linkText("Books "));//click on book icon
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[1]/div[1]/div[1]"));//clickon book picture
        clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]"));//click on emailfriend
        typetaxt(By.id("FriendEmail"),"laxmi"+timestamp()+"@gmail.com");//your friend email
        typetaxt(By.id("YourEmailAddress"),"bhav"+timestamp()+"@gmail.com");//your email
        typetaxt(By.id("PersonalMessage"),"How are you");//message to your friend
        clickOnElement(By.name("send-email"));//click to send
        String actualMessage = getTextFromelement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]"));
        System.out.println("My mesage:"+actualMessage);
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"Successfully message send");
        closeBrowser();

    }
    @Test
    public static void verifyuserShouldAbleTOVote(){
        openBrowser();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        clickOnElement(By.className("ico-register"));
        typetaxt(By.id("FirstName"),"ABCD" );
        typetaxt(By.id("LastName"),"XYZ");
        typetaxt(By.id("Email"),"Test"+timestamp()+"@gmail.com");
        typetaxt(By.name("Password"),"test1234");
        typetaxt(By.id("ConfirmPassword"),"abcd1234");
        clickOnElement(By.id("register-button"));
        clickOnElement(By.className("ico-login"));
        typetaxt(By.id("Email"),"Testtest@gmail.com");
        typetaxt(By.id("Password"),"abcd1234");
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));//submit for log in
        clickOnElement(By.id("pollanswers-2"));//click on good button
        clickOnElement(By.id("vote-poll-1"));//click on vote button
        String actualMessage = getTextFromelement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("actualmessae:"+actualMessage);//message will print fail or not
        Assert.assertEquals(actualMessage,expectedRegistretionCompleteMSG,"Successfully message send");
        closeBrowser();}}












