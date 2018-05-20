package stepDefinition;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import testFramework.PropertyFileReader;
import testFramework.SeleniumTestBase;

public class TestSteps extends SeleniumTestBase {
	SeleniumTestBase stb=new SeleniumTestBase();
	PropertyFileReader propertyFileReader=new PropertyFileReader();
	private Map <String,String> properties=new HashMap<String,String>();
	private Map <String,String> productDetails=new HashMap<String,String>();
	private Map <String,String> cartProductDetails=new HashMap<String,String>();
	String errorMessage;
	String message;
	Boolean isAvailable=false;
    @Before
    public void before() {
	properties=stb.readPropertyFile() ;
	stb.initiateTheWebDriver();
	System.out.println("==Before===");
	}

	@Given("^I navigate to the URL$")
	public void navigateURL() throws Throwable {
		stb.urlLaunched(properties.get("url"));
	}

	@When("^I type valid user name and valid password$")
	public void successFulLogin() throws Throwable {
		stb.signin(properties.get("username"),properties.get("password"));
	}


	@Then("^I validate the home page header$")
	public void successLogin() throws Throwable {
		stb.validateSuccessLogin();
	}
	@When("^I type incorrect user name \"(.*)\" and password \"(.*)\"$")
	public void failedLogin(String username,String password) throws Throwable {
		stb.incorrectSignin(username);
	}
	
	@Then("^I read the error$")
	public void readingError() throws Throwable {
		errorMessage=stb.readError();
	}
	
	@Then("error should be \"(.*)\"$")
	public void validateError(String correctMessage) throws Throwable {
		correctMessage.contains(errorMessage);
		
	}
	
	@When("I type valid user name and password \"(.*)\"$")
	public void loginWithinValidPassword(String password)throws Throwable {
			stb.signin(properties.get("username"),password);
	}
	
	@When("I check for the page header")
	public void checkForPageHeader() throws Throwable {
			isAvailable=stb.validatePageHeader();
	
	}
	
	@Then("I validate the page header")
	public void visibilityOfPageHeader() {
		isAvailable.equals(true);
		
	}
	
	@When("I search for \"(.*)\"$")
	public void searchItemName(String itemName)throws Throwable {
		stb.searchItem(itemName);
	}
	
	@When("I click on the first product visible")
	public void clickOnFirstItem()throws Throwable {
		stb.clickFirstItem();
	}
	
	@Then("product page should be visible")
	public void validateTheProductPage() {
		String value=stb.viewProductPage();
		value.contains("Back to search results for");
		
	}
	
	@When("product name is captured as \"(.*)\"$")
	public void captureProdName(String itemName)throws Throwable {
		productDetails=stb.captureProductName(itemName);
	}
	
	@When("product price is captured as \"(.*)\"$")
	public void captureProdPrice(String itemPrice)throws Throwable {
		productDetails=stb.captureProductPrice(itemPrice);
	}
	
	@When("I add \"(.*)\" qualitity to the basket")
	public void addProductToTheCart(String noOfItems)throws Throwable {
		stb.addItemToTheCart(noOfItems);
	}
	@When("I read the message in the product page")
	public void readTheProductMessage()throws Throwable {
		message=stb.readTheMessage();
	}
	@Then("Message should be visible as \"(.*)\"$")
	public void validateMessage(String correctMessage) throws Throwable {
	   correctMessage.equals(message);
		
	}
	
	@When("I click on the cart")
	public void clickTheCart()throws Throwable {
		stb.clickOnTheCart();
		
	}
	@When("I read the \"(.*)\" in the cart$")
	public void readTheProductNameInTheCart(String key)throws Throwable {
		
		if(key.equals("productNameValue")) {
		cartProductDetails.put(key,stb.readTheCartProductName())	;
		}
		else if(key.equals("productPriceValue")) {
			cartProductDetails.put(key,stb.readTheCartProductValue())	;
		}
	}
	
	@When("I logout from the system$")
	public void logOut()throws Throwable {
		stb.logout();
		
	}
	
	
	@When("I relogin with valid user name and valid password$")
	public void reLogin()throws Throwable {
		stb.LoginAfterlogout(properties.get("username"),properties.get("password"));
	}
	
	@Then("the product value should be \"(.*)\"")
	public void validateProductNameInCart(String value) {
		cartProductDetails.get(value).equals(productDetails.get(value));
		
	}

	
   @After
   public void after() {
	stb.closeBrowser();
	
  }




}
