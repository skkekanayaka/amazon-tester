package testFramework;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumTestBase {
	private static WebDriver driver = null;
	Map <String,String> properties=new HashMap<String,String>();
	Map <String,String> productDetails=new HashMap<String,String>();
	Map <String,String> productDetailsInTheCart=new HashMap<String,String>();
	PropertyFileReader propertyFileReader=new PropertyFileReader();
	Boolean isAvailable=false;
	
	public Map <String,String> readPropertyFile() {
		properties=propertyFileReader.readPropertyFile();
		return properties;
	}
	
	public void initiateTheWebDriver() {
		System.setProperty("webdriver.chrome.driver", properties.get("chromepath"));
		driver = new ChromeDriver();
	}
	
	public void urlLaunched(String url){
		driver.get(url);
		
	}

	public void signin(String username,String password) {
		 driver.findElement(By.id("nav-link-accountList")).click();
	     driver.findElement(By.id("ap_email")).sendKeys(username); 
	     driver.findElement(By.id("continue")).click();
	     driver.findElement(By.id("ap_password")).sendKeys(password);
	     driver.findElement(By.id("signInSubmit")).click();
	}
	
	public void incorrectSignin(String username) {
		 driver.findElement(By.id("nav-link-accountList")).click();
	     driver.findElement(By.id("ap_email")).sendKeys(username); 
	     driver.findElement(By.id("continue")).click();  
	}
	
	public void validateSuccessLogin() {
		 driver.findElement(By.id("nav-logo"));
	}
	
	public void closeBrowser() {
		 driver.close();		
	}
	
	public String readError() {
		String errorMessage=driver.findElement(By.id("auth-error-message-box")).getText();
		return errorMessage;
		
	}
	
	public boolean validatePageHeader() {
		try {
		 driver.findElement(By.id("nav-logo"));
		 isAvailable=true;
		}catch(Exception e) {
			isAvailable=false;
		}
		
		return isAvailable;
	}
	
	public void searchItem(String itemName) {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(itemName);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	public void clickFirstItem() {
		driver.findElement(By.xpath("//img[@class='s-access-image cfMarker']")).click();
	}
	
	public String viewProductPage() {
		String value=driver.findElement(By.id("breadcrumb-back-link")).getText();
		return value;
	}
	
	public Map <String,String> captureProductName(String keyValue) {
		String productName=driver.findElement(By.id("productTitle")).getText();
		productDetails.put(keyValue,productName);
		return productDetails;
	}
	
   public Map <String,String> captureProductPrice(String keyValue) {	
		String productValue=driver.findElement(By.id("priceblock_ourprice")).getText();
		productDetails.put(keyValue,productValue);
		return productDetails;
	}
   
   public void addItemToTheCart(String quantity) { 
	   Select oSelect = new Select( driver.findElement(By.id("quantity")));
       oSelect.selectByVisibleText(quantity);
       driver.findElement(By.id("add-to-cart-button")).click();
	   
   }
   
   public String  readTheMessage() {
	   WebDriverWait wait = new WebDriverWait(driver,20);
	   WebElement element;
	   element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("huc-v2-order-row-confirm-text"))); 
   String message=driver.findElement(By.id("huc-v2-order-row-confirm-text")).getText();
   
   return message;
   }
   
	public void clickOnTheCart() {
		
		driver.findElement(By.id("nav-cart")).click();
	}
	 public String  readTheCartProductName() {
		
	String message=driver.findElement(By.xpath("//a[@class='a-link-normal sc-product-link']")).getText();
		   
	return message;
	}
	 
	 public String  readTheCartProductValue() {
			
	String message=driver.findElement(By.xpath("//div[@class='a-column a-span2 a-text-left']")).getText();
				   
	return message;
	 }
	 
	 
	 public void  logout() throws Exception{
		 Actions a= new Actions(driver);
		 WebElement ele=driver.findElement(By.id("nav-link-accountList"));
		 a.moveToElement(ele).build().perform();
		 driver.manage().window().maximize();
		 WebElement ele1=driver.findElement(By.xpath("//a[@tabindex='62']"));
		 a.moveToElement(ele1).build().perform(); 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",ele1);
		 Thread.sleep(5000);
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 WebElement element;
		 element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sign Out"))); 
		 Thread.sleep(5000);
		 driver.findElement(By.partialLinkText("Sign Out")).click();
		 System.out.println("Log out");
	}
	 
	 
	 public void  LoginAfterlogout(String userName,String password) throws Exception{
         driver.findElement(By.id("ap_email")).sendKeys(userName); 
	     driver.findElement(By.id("continue")).click();
	     driver.findElement(By.id("ap_password")).sendKeys(password);
	     driver.findElement(By.id("signInSubmit")).click();
		        
		}
}
