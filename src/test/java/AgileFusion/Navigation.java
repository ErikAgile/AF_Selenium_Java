package AgileFusion;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Navigation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    baseUrl = "http://af-web-dept-a.yurasov.me";
    System.out.println(driver.manage().window().getSize());
	Dimension d = new Dimension(1920,1080);
	driver.manage().window().setSize(d);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testNavigation() throws Exception {
    driver.get(baseUrl + "/af-corporate-staging/");
    System.out.println(driver.getTitle());
    driver.findElement(By.cssSelector("i.icon.icon-arrow-down")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Services')]")).click();
    driver.findElement(By.cssSelector("i.icon.icon-arrow-down")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Portfolio')]")).click();
    driver.findElement(By.cssSelector("i.icon.icon-arrow-down")).click();
    driver.findElement(By.xpath("/html/body/section[2]/div[1]/article/div[1]/div/a[1]")).click();
    System.out.println(driver.findElement(By.xpath("/html/body/section[2]/div[1]/article/div[2]/p")).getText().contains("Chefs Feed is the ultimate resource"));
    driver.findElement(By.xpath("/html/body/section[2]/div[1]/article/div[1]/div/a[2]")).click();  
    driver.findElement(By.xpath("//a[contains(text(),'About Us')]")).click();
    driver.findElement(By.cssSelector("i.icon.icon-arrow-down")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Contact Us')]")).click();

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
