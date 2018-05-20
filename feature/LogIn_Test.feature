@tag
Feature: Testing the online trade system

@Scenario1
  Scenario: As the user I launch “www.amazon.com” page and validate page header
  
    Given I navigate to the URL
    When I check for the page header 
    Then I validate the page header
    

@Scenario2
  Scenario: As the user I click on login link and Provide incorrect username and validate error.
  
    Given I navigate to the URL
    When I type incorrect user name "incorrectusername.abc.com" and password "asc"
    Then I read the error
    And error should be "We cannot find an account with that email address"
    
@Scenario3
  Scenario: As the user I provide incorrect password and validate error.
  
    Given I navigate to the URL
    When I type valid user name and password "asc"
    Then I read the error
    And error should be "Your password is incorrect"
    
@Scenario4
  Scenario: As the user I provide correct username/password and validate home page header.
  
    Given I navigate to the URL
    When I type valid user name and valid password
    Then I validate the home page header


@Scenario5
  Scenario:As the user I want to search for iphone x 64 gb and click on 1st product and move to product page
           As the user I want to capture product name and price and add 1 quantity to basket
           As the user I want to Validate product is added into basket on product page  
           As the user I want to Vaidate product price and name on basket page. 
           As the user I want to Logout and login again validate product in basket is present or not
  
   Given I navigate to the URL
   When I type valid user name and valid password
    When I search for "iphone x 64 gb"
    And I click on the first product visible
    Then product page should be visible
    And product name is captured as "productNameValue" 
    And product price is captured as "productPriceValue"
    When I add "1" qualitity to the basket
    And I read the message in the product page
    Then Message should be visible as "Added to the cart" 
    When I click on the cart 
    And I read the "productNameValue" in the cart
    And I read the "productPriceValue" in the cart
    Then the product value should be "productNameValue" 
    And the product value should be "productPriceValue"
   And I logout from the system
   When I relogin with valid user name and valid password
   And I click on the cart 
    And I read the "productNameValue" in the cart
    Then the product value should be "productNameValue" 
    



  
  
  
  
  
  
  
  
  
  
  