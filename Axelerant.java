/*  Assignment A

		Application Under Test: http://automationpractice.com/index.php
		Assumptions: Data on the site is static
		
		1. Navigate to multiple pages of the website and automate verification of header and footer.
		2. Automate Newsletter subscription scenarios. Newsletter subscription is placed in the footer.
		3. Automate Women > Dresses > Summer Dresses listing page. Note that filters and sorting options are broken on the site. Hence to verify filters and sorting, consider the default listing itself. (Tip - These two tests should obviously fail)
		*Consider the default listing and verify the results for any one of the filters.
		*Without applying any sorting for Price and Product name, verify the sorting results.
		4. Verify the checkout journey by adding any product to cart.
 * 
 */
package assessmentPackage;

import org.testng.annotations.Test;
import utility.Configuration;

import junit.framework.Assert;
import objectRepository.LoadPropertySingleton;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Axelerant 
{
	
	public String baseUrl = Configuration.configResourceBundle.getProperty("BaseURL");
	public WebDriver driver ;
	public static WebElement element;
	static Configuration objectLoad = Configuration.getInstance();
	public String driverPath = Configuration.configResourceBundle.getProperty("ChromeDriverPath");
	public static WebDriverWait wait;
	@BeforeTest
	  public void launchApplication() throws InterruptedException 
	  {
		  System.out.println("Launching Chrome browser"); 
	      System.setProperty("webdriver.chrome.driver", driverPath);
	      driver = new ChromeDriver();
	      driver.get(baseUrl);
	      driver.manage().window().maximize();
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  wait = new WebDriverWait(driver, 30);
	  }
	
/* Header and Footer Verification in "Home Page"
 * 
*/
  @Test (priority = 1)
  public void headerAndFooterVerification_HomePage() throws InterruptedException 
  {
	 
	  boolean lv_exist = false;
	  String expectedTitle = "Most Reliable App & Cross Browser Testing Platform | BrowserStack";
	  String actualTitle = driver.getTitle();
      System.out.println("Expected Title: " + expectedTitle);
      System.out.println("Actual Title: " + actualTitle);
      
      String locVal_header = Configuration.configResourceBundle.getProperty("HeaderObject");
      String locVal_footer = Configuration.configResourceBundle.getProperty("FooterObject");
      int lv_hCount = driver.findElements(By.id(locVal_header)).size() ;
      
      JavascriptExecutor js = (JavascriptExecutor) driver; 
      js.executeScript("window.scrollBy(0,10000)");
      Thread.sleep(1000);
      int lv_fCount = driver.findElements(By.id(locVal_footer)).size() ;
      if (lv_hCount !=0 && lv_fCount != 0)
      {
    	 lv_exist = true;
    	 System.out.println("Inside Check");
      }
      System.out.println("Home Page - Header and Footer Verification");
      Assert.assertEquals(true, lv_exist);
  }
  
  /* Header and Footer Verification in "Contact Us" Page
   * 
  */  
  @Test (priority = 2)
  public void headerAndFooterVerification_ContactUsPage() throws InterruptedException 
  {
	 
	  boolean lv_exist = false;
	  String locVal_header = Configuration.configResourceBundle.getProperty("HeaderObject");
      String locVal_footer = Configuration.configResourceBundle.getProperty("FooterObject");
      JavascriptExecutor js = (JavascriptExecutor) driver; 
      js.executeScript("window.scrollBy(0,0)");
      Thread.sleep(500);
       
      String locVal_contactLink = Configuration.configResourceBundle.getProperty("ContactLink");
      wait.until(ExpectedConditions.elementToBeClickable(By.id(locVal_contactLink)));
      
      WebElement we_contactUSLink = driver.findElement(By.id(locVal_contactLink));
      we_contactUSLink.click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locVal_header)));
      System.out.println("Header Count in Contact Us Page");
      int lv_hCount = driver.findElements(By.id(locVal_header)).size() ;
      
      
      js.executeScript("window.scrollBy(0,10000)");
      Thread.sleep(1000);
      int lv_fCount = driver.findElements(By.id(locVal_footer)).size() ;
      if (lv_hCount !=0 && lv_fCount != 0)
      {
    	 lv_exist = true;
    	 System.out.println("Inside Check");
      }
      System.out.println("Contact Us Page - Header and Footer Verification");
      Assert.assertEquals(true, lv_exist);
  }
  
  /* Header and Footer Verification in "Sign In" Page
   * 
  */    
  @Test (priority = 3)
  public void headerAndFooterVerification_SignInPage() throws InterruptedException 
  {
	 
	  boolean lv_exist = false;
	  String locVal_header = Configuration.configResourceBundle.getProperty("HeaderObject");
      String locVal_footer = Configuration.configResourceBundle.getProperty("FooterObject");
      JavascriptExecutor js = (JavascriptExecutor) driver; 
      js.executeScript("window.scrollBy(0,0)");
      Thread.sleep(500);
       
      String locVal_signIn = Configuration.configResourceBundle.getProperty("SignInLink");
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locVal_signIn)));
      
      WebElement we_signInLink = driver.findElement(By.xpath(locVal_signIn));
      we_signInLink.click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locVal_header)));
      System.out.println("Header Count in Contact Us Page");
      int lv_hCount = driver.findElements(By.id(locVal_header)).size() ;
      
      
      js.executeScript("window.scrollBy(0,10000)");
      Thread.sleep(1000);
      int lv_fCount = driver.findElements(By.id(locVal_footer)).size() ;
      if (lv_hCount !=0 && lv_fCount != 0)
      {
    	 lv_exist = true;
    	 System.out.println("Inside Check");
      }
      System.out.println("SignIn Page - Header and Footer Verification");
      Assert.assertEquals(true, lv_exist);
  }
  /* User Journey - News Letter Subscription
   * 
  */    
  @Test (priority = 4)
  public void newsLetterSubscription() throws InterruptedException 
  {
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
      js.executeScript("window.scrollBy(0,10000)");
	  boolean lv_exist = false;
	  String locVal_emailTxtBox = Configuration.configResourceBundle.getProperty("EmailTextBox");
	  String lv_emailId = Configuration.configResourceBundle.getProperty("EmailID");
	  String locVal_emailIDBtn = Configuration.configResourceBundle.getProperty("EmailIDButton");
	  String locVal_successMsg = Configuration.configResourceBundle.getProperty("EmailSuccessMsg");
	  WebElement we_emailIDTxtBox = driver.findElement(By.id(locVal_emailTxtBox));
	  WebElement we_emailIDButton = driver.findElement(By.name(locVal_emailIDBtn));
	  we_emailIDTxtBox.sendKeys(lv_emailId);
	  we_emailIDButton.click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_successMsg)));
	  if (driver.findElements(By.xpath(locVal_successMsg)).size() != 0)
	  {
		  lv_exist = true;
	  }
	  Assert.assertEquals(true, lv_exist);
  }
  
  /* User Journey - CheckOut
   * 
  */  
  @Test (priority = 5)
  public void checkOutJourney() throws InterruptedException 
  {
	  driver.get(baseUrl);
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
      
	  boolean lv_exist = false;
	  
	  String locVal_TShirtLink = Configuration.configResourceBundle.getProperty("T-ShirtsLink");
	  String locVal_productLink = Configuration.configResourceBundle.getProperty("ProductLink");
	  String locVal_addToCartBtn = Configuration.configResourceBundle.getProperty("AddToCartButton");
	  String locVal_proceedChkOutBtn = Configuration.configResourceBundle.getProperty("ProceedCheckOutBtn");
	  String locVal_cartSumryTitle = Configuration.configResourceBundle.getProperty("CartSummaryTitle");
	  String locVal_addCartFrame = Configuration.configResourceBundle.getProperty("Frame_AddToCart");
	  String locVal_cartSumryChkOut = Configuration.configResourceBundle.getProperty("CartSummaryCheckOutBtn");
	  String locVal_emailAdd = Configuration.configResourceBundle.getProperty("EmailAddTxtBox");
	  String locVal_pwTxtBox = Configuration.configResourceBundle.getProperty("PasswordTxtBox");
	  String locVal_SignINBtn = Configuration.configResourceBundle.getProperty("SignINBtn");
	  String locVal_addProcChkOut = Configuration.configResourceBundle.getProperty("AddressProcChckOut");
	  String locVal_trmOfSerChkBox = Configuration.configResourceBundle.getProperty("TermsOfSerChkBox");
	  String locVal_shippingProcChkOut = Configuration.configResourceBundle.getProperty("ShippingProChckOut");
	  String locVal_payByBankLink = Configuration.configResourceBundle.getProperty("PayByBankLink");
	  String locVal_confirmOrdBtn = Configuration.configResourceBundle.getProperty("ConfirmOrderBtn");
	  String locVal_ordSuccMsg = Configuration.configResourceBundle.getProperty("OrderSuccMsg");
	  String locVal_ordConfPage = Configuration.configResourceBundle.getProperty("OrdConfPage");
	  String lv_userId = Configuration.configResourceBundle.getProperty("UserID");
	  String lv_PW = Configuration.configResourceBundle.getProperty("PassWord");
	  WebElement we_TShirtLink = driver.findElement(By.xpath(locVal_TShirtLink));
	  we_TShirtLink.click();
	  Thread.sleep(1000);
	  js.executeScript("window.scrollBy(0,5000)");
	  WebElement we_productLink = driver.findElement(By.className(locVal_productLink));
	  we_productLink.click();
	  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(locVal_addCartFrame)));
	  WebElement we_addToCartBtn = driver.findElement(By.xpath(locVal_addToCartBtn));
	  we_addToCartBtn.click();
	  Thread.sleep(2000);
	  WebElement we_proceedChkOutBtn = driver.findElement(By.xpath(locVal_proceedChkOutBtn));
	  we_proceedChkOutBtn.click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locVal_cartSumryTitle)));
	  js.executeScript("window.scrollBy(0,3000)");
	  WebElement we_cartSumryChkOut = driver.findElement(By.xpath(locVal_cartSumryChkOut));
	  we_cartSumryChkOut.click();
	  js.executeScript("window.scrollBy(0,3000)");
	  WebElement we_emailAddTxtBox = driver.findElement(By.id(locVal_emailAdd));
	  we_emailAddTxtBox.sendKeys(lv_userId);
	  WebElement we_PWTxtBox = driver.findElement(By.id(locVal_pwTxtBox));
	  we_PWTxtBox.sendKeys(lv_PW);
	  WebElement we_SignInBtn = driver.findElement(By.id(locVal_SignINBtn));
	  we_SignInBtn.click();
	  Thread.sleep(1000);
	  js.executeScript("window.scrollBy(0,5000)");
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_addProcChkOut)));
	  WebElement we_addProcCkOutBtn = driver.findElement(By.xpath(locVal_addProcChkOut));
	  we_addProcCkOutBtn.click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locVal_trmOfSerChkBox)));
	  WebElement we_trmOfSerChkBox = driver.findElement(By.id(locVal_trmOfSerChkBox));
	  we_trmOfSerChkBox.click();
	  js.executeScript("window.scrollBy(0,5000)");
	  WebElement we_shipProcChkOutBtn = driver.findElement(By.xpath(locVal_shippingProcChkOut));
	  we_shipProcChkOutBtn.click();
	  js.executeScript("window.scrollBy(0,5000)");
	  WebElement we_payByBankLink = driver.findElement(By.className(locVal_payByBankLink));
	  we_payByBankLink.click();
	  js.executeScript("window.scrollBy(0,5000)");
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_confirmOrdBtn)));
	  WebElement we_confirmOrdBtn = driver.findElement(By.xpath(locVal_confirmOrdBtn));
	  we_confirmOrdBtn.click();
	  Thread.sleep(5000);
	  if(driver.findElements(By.xpath(locVal_ordSuccMsg)).size() !=0)
	  {
		  lv_exist = true;
	  }
	  Assert.assertEquals(true, lv_exist);
  }
  
/*Verifying Filters  - the count in the message "There are 3 Products" should not be same before and after filter 
 * 					- the product list should not be same before and after filter
 * This will fail as Filter option is broken on the site
 */
  @Test (priority = 6)
  public void verifyFilters() throws InterruptedException 
  {
	  driver.get(baseUrl);
	  Actions act = new Actions(driver);
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
	  boolean lv_exist = false;
	  String locVal_WomenMenu = Configuration.configResourceBundle.getProperty("MenuWomen");
	  String locVal_SumDressMenu = Configuration.configResourceBundle.getProperty("SubMenuSumDress");
	  String locVal_SumDressText = Configuration.configResourceBundle.getProperty("SumDresses");
	  String locVal_FiltChkBox = Configuration.configResourceBundle.getProperty("SmallFilter");
	  String locVal_CounterTxt = Configuration.configResourceBundle.getProperty("HeaderCounter");
	  String locVal_ProdList = Configuration.configResourceBundle.getProperty("ProductList");
	  WebElement we_womenMenu = driver.findElement(By.xpath(locVal_WomenMenu));
	  act.moveToElement(we_womenMenu).build().perform();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_SumDressMenu)));
	  WebElement we_SumDressMenu = driver.findElement(By.xpath(locVal_SumDressMenu));
	  act.moveToElement(we_SumDressMenu).click().build().perform();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_SumDressText)));
	  if(driver.findElement(By.xpath(locVal_SumDressText)).getText().contains("Summer Dresses"))
	  {
		  lv_exist = true;
	  }
	  Assert.assertEquals(true, lv_exist);
	  lv_exist = false;
	  js.executeScript("window.scrollBy(0,400)");
	  String lv_prodCount = driver.findElement(By.className(locVal_CounterTxt)).getText();
	  String lv_words[] = lv_prodCount.split("\\s");
	  int lv_intialCount = Integer.parseInt(lv_words[2]);
	  System.out.println("Initial Count: " + lv_intialCount);
	  WebElement we_FiltChkBox = driver.findElement(By.id(locVal_FiltChkBox));
	  List<WebElement> initProductList = driver.findElements(By.xpath(locVal_ProdList));
	  System.out.println("Initial List: " + initProductList.size());
	  we_FiltChkBox.click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locVal_CounterTxt)));
	  String lv_fiteredCount = driver.findElement(By.className(locVal_CounterTxt)).getText();
	  String lv_filteredWords[] = lv_fiteredCount.split("\\s");
	  int lv_laterCount = Integer.parseInt(lv_filteredWords[2]);
	  System.out.println("Later Count: " + lv_laterCount);
	  List<WebElement> filtProductList = driver.findElements(By.xpath(locVal_ProdList));
	  System.out.println("Later List: " + filtProductList.size());
	  if(lv_intialCount == lv_laterCount && initProductList.size() == filtProductList.size())
	  {
		  lv_exist = true;
	  }
	  Assert.assertEquals(false, lv_exist);
  }
  
  /*Verifying Sorting  - all the product's images are compared before and after Sorting 
   * 				   - the product's images order should not be same after Sorting
   * This will fail as Filter option is broken on the site
   */
  @Test (priority = 7)
  public void verifySorting() throws InterruptedException 
  {
	  driver.get(baseUrl);
	  Actions act = new Actions(driver);
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
	  boolean lv_exist = false;
	  String locVal_SortDDL = Configuration.configResourceBundle.getProperty("SortByDDL");
	  String locVal_ddlValue = Configuration.configResourceBundle.getProperty("SortValue");
	  String locVal_ProdList = Configuration.configResourceBundle.getProperty("ProductList");
	  String locVal_WomenMenu = Configuration.configResourceBundle.getProperty("MenuWomen");
	  String locVal_SumDressMenu = Configuration.configResourceBundle.getProperty("SubMenuSumDress");
	  String locVal_SumDressText = Configuration.configResourceBundle.getProperty("SumDresses");
	  WebElement we_womenMenu = driver.findElement(By.xpath(locVal_WomenMenu));
	  act.moveToElement(we_womenMenu).build().perform();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_SumDressMenu)));
	  WebElement we_SumDressMenu = driver.findElement(By.xpath(locVal_SumDressMenu));
	  act.moveToElement(we_SumDressMenu).click().build().perform();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locVal_SumDressText)));
	  List<WebElement> initProductList = driver.findElements(By.xpath(locVal_ProdList + "//img"));
	  System.out.println("Initial List: " + initProductList.size());
	  int temp = initProductList.size();
	  String initialItems[] = new String[initProductList.size()];
	  for (int i = 1; i <= temp; i++)
	  {
		  initialItems[i-1] = driver.findElement(By.xpath(("(" + locVal_ProdList) + "//img)[" + i + "]")).getAttribute("src");
		  System.out.println(initialItems[i-1]);
	  }
	  WebElement we_SortDDL = driver.findElement(By.id(locVal_SortDDL));
	  Select ddl = new Select (we_SortDDL);
	  ddl.selectByIndex(Integer.parseInt(locVal_ddlValue));
	  
	  List<WebElement> filtProductList = driver.findElements(By.xpath(locVal_ProdList + "//img"));
	  System.out.println("Later List: " + filtProductList.size());
	  temp = filtProductList.size();
	  String filterItems[] = new String[initProductList.size()];
	  for (int i = 1; i <= temp; i++)
	  {
		  filterItems[i-1] = driver.findElement(By.xpath(("(" + locVal_ProdList) + "//img)[" + i + "]")).getAttribute("src");
		  System.out.println(filterItems[i-1]);
	  }
	  
	  for (int i = 1; i <= temp; i++)
	  {
		  if (!initialItems[i-1].equals(filterItems[i-1]))
		  {
			  lv_exist = true;
		  }
		  
		  System.out.println(filterItems[i-1]);
	  }
	  
	  Assert.assertEquals(true, lv_exist);
	  
  }
  @BeforeMethod
  public void beforeMethod() 
  {
	  
  }

  @AfterMethod
  public void afterMethod() 
  {
	  
  }

  @BeforeClass
  public void beforeClass() 
  {
	  
  }

  @AfterClass
  public void afterClass() 
  {
	  
  }

  

  @AfterTest
  public void afterTest() 
  {
	  
  }

  @BeforeSuite
  public void beforeSuite() 
  {
	  
  }

  @AfterSuite
  public void afterSuite() throws IOException 
  {
	  System.out.println("Killing ChromeDriver.exe");
	  Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
  }
  
  

}
