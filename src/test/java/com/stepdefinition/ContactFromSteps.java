package com.stepdefinition;

import com.configuration.AppConfig;
import com.hooks.Hooks;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.utils.Constants;

public class ContactFromSteps {
	public static WebDriver driver;
	public ContactForm contactForm;

	private static final Logger log = LogManager.getLogger(ContactFromSteps.class);

	public ContactFromSteps() {
		driver=Hooks.getDriver();
		
	}
	// This step opens the Contact Us page
	@Given("I open the Contact Us page")
	public void i_open_the_Contact_Us_page() throws IOException {
		try {
			AppConfig appConfig = new AppConfig();
			contactForm = new ContactForm(driver);
			String url = appConfig.getAppUrl();
			driver.get(url);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(appConfig.getWaitTime()));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Constants.CONTACT_US_LOCATOR)));
		} catch (Exception e) {
			log.error("browser failed to load");
			throw new RuntimeException("browser failed to load");
		}
	}

	//clicks submit button
	@When("clicks the \"Submit\" button")
	public void clicks_the_submit_button() {
		try {
			contactForm.clickSubmitButton();
		} catch (Exception e) {
			log.error("error clicking submit button");
		}
	}
	
	//clicks rest button
	@When("clicks the \"Reset\" button")
	public void clicks_the_reset_button() {
		try {
			contactForm.clickResetButton();
		} catch (Exception e) {
			log.error("error clicking reset button");
		}
	}

	//validates Thank you message on confirmation screen
	@Then("the user sees a thank you page")
	public void the_user_sees_thank_you_page() {

		String expectedMessage = Constants.THANK_YOU_MESSAGE;
		String actualMessage = "";
		try {
			actualMessage = contactForm.getSuccessMessageText();
		} catch (Exception e) {
			log.error("Success message element not found");
		}
		Assert.assertEquals(expectedMessage, actualMessage);

	}

	//insert values to input fields from DataTable parameter
	/*** empty spaces in feature file will be treated as null by DataTable
		* in order to test the fields with empty space we have implemented a work around
		* if the value from DataTable is '!-empty space-!' it will insert 3 empty space
		* */
	private void insertDataToFields(DataTable dataTable) {
		try {
			List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
			Map<String, String> input = data.get(0);
			String firstName = StringUtils.isNotBlank(input.get(Constants.FIRST_NAME))
					&& input.get(Constants.FIRST_NAME).equalsIgnoreCase(Constants.EMPTY_SPACE) ? Constants.BLANK_SPACE : input.get(Constants.FIRST_NAME);
			String lastName = StringUtils.isNotBlank(input.get(Constants.LAST_NAME))
					&& input.get(Constants.LAST_NAME).equalsIgnoreCase(Constants.EMPTY_SPACE) ? Constants.BLANK_SPACE : input.get(Constants.LAST_NAME);
			String email = StringUtils.isNotBlank(input.get(Constants.EMAIL))
					&& input.get(Constants.EMAIL).equalsIgnoreCase(Constants.EMPTY_SPACE) ? Constants.BLANK_SPACE : input.get(Constants.EMAIL); 
			String message = StringUtils.isNotBlank(input.get(Constants.MESSAGE))
					&& input.get(Constants.MESSAGE).equalsIgnoreCase(Constants.EMPTY_SPACE) ? Constants.BLANK_SPACE : input.get(Constants.MESSAGE);
			if (StringUtils.isNotEmpty(firstName))
				contactForm.enterFirstName(firstName);
			if (StringUtils.isNotEmpty(lastName))
				contactForm.enterLastName(lastName);
			if (StringUtils.isNotEmpty(email))
				contactForm.enterEmail(email);
			if (StringUtils.isNotEmpty(message))
				contactForm.enterMessage(message);
		} catch (Exception e) {
			log.error("error while inserting data");
		}
	}

	//inserting data from DataTable
	@When("the user enters the following information:")
	public void the_user_enters_the_following_information(DataTable dataTable) {
		insertDataToFields(dataTable);
	}

	//inserting data from DataTable
	@When("the user enters the following information with blank first name")
	public void the_user_enters_the_following_information_with_blank_first_name(DataTable dataTable) {
		insertDataToFields(dataTable);
	}

	//inserting data from DataTable
	@When("the user enters the following information with blank last name")
	public void the_user_enters_the_following_information_with_blank_last_name(DataTable dataTable) {
		insertDataToFields(dataTable);
	}

	//validating error message
	@Then("the user should see an error message")
	public void userShouldSeeErrorMessage() {
		String expectedErrorMessage = Constants.ERROR_REQUIRED;
		String errorMessage = contactForm.getErrorMessageText();
		Assert.assertEquals(expectedErrorMessage, errorMessage);
	}

	//inserting data from DataTable
	@When("the user enters the following information with blank email")
	public void the_user_enters_the_following_information_with_blank_email(DataTable dataTable) {
		insertDataToFields(dataTable);
	}

	//Validating invalid email
	@Then("the user should see all fields required and invalid email error message")
	public void userShouldSeeAllFieldsAndInvalidEmailErrorMessage() {
		
		String expectedErrorMessage = Constants.ERROR_REQUIRED.concat("\n").
				concat(Constants.ERROR_INVALID_EMAIL);
		String errorMessage = contactForm.getErrorMessageText();
		Assert.assertEquals(expectedErrorMessage, errorMessage);
	}

	//inserting data from DataTable
	@When("the user enters the following information with invalid email:")
	public void the_user_enters_the_following_information_with_invalid_email(DataTable dataTable) {
		insertDataToFields(dataTable);
	}

	//Validating invalid email
	@Then("the user should see invalid email error message")
	public void userShouldSeeInvalidEmailErrorMessage() {

		String expectedErrorMessage = Constants.ERROR_INVALID_EMAIL;
		String errorMessage = contactForm.getErrorMessageText();
		Assert.assertEquals(expectedErrorMessage, errorMessage);
	}

	//inserting data from DataTable
	@When("the user enters the following information with blank message:")
	public void the_user_enters_the_following_information_with_blank_message(DataTable dataTable) {
		insertDataToFields(dataTable);
	}
	
	//validating reset button
	@Then("the fields in the form are reset")
	public void the_fields_in_the_form_are_reset() {
		String firstName = contactForm.getFirstName().getText();
		String lastName = contactForm.getLastName().getText();
		String email = contactForm.getEmail().getText();
		String comments = contactForm.getComments().getText();
		Assert.assertEquals("", firstName);
		Assert.assertEquals("", lastName);
		Assert.assertEquals("", email);
		Assert.assertEquals("", comments);
	}
	
	//validating order of elements
	@Then("user validates the order of webelements")
	public void user_validates_the_order_of_webelements() {
		String[] expectedOrder = {Constants.FIRST_NAME, Constants.LAST_NAME, Constants.EMAIL_ADDRESS};
		 List<WebElement> inputElements = contactForm.getInputElements()
				 .stream()
				 .filter(x->x.getAttribute(Constants.TYPE).equalsIgnoreCase(Constants.TEXT))
				 .collect(Collectors.toList());
		 for(int i=0; i<inputElements.size();i++) {
			 if(!inputElements.get(i).getAttribute(Constants.PLACE_HOLDER).equalsIgnoreCase(expectedOrder[i])){
				 Assert.assertFalse(Constants.ERROR_ORDER,true);
			 }
		 }
		 Assert.assertTrue(true);
	}
	
	//validating lable of elements
	@Then("user validates the lable of webelements")
	public void user_validates_the_lable_of_webelements() {
		String firstName = contactForm.getFirstName().getAttribute(Constants.PLACE_HOLDER);
		String lastName = contactForm.getLastName().getAttribute(Constants.PLACE_HOLDER);
		String email = contactForm.getEmail().getAttribute(Constants.PLACE_HOLDER);
		String comments = contactForm.getComments().getAttribute(Constants.PLACE_HOLDER);
		String submitButton = contactForm.getSubmitButton().getAttribute(Constants.VALUE_TEXT);
		String resetButton = contactForm.getResetButton().getAttribute(Constants.VALUE_TEXT);
		String pageName = contactForm.getPagename().getText();
		Assert.assertEquals(Constants.FIRST_NAME, firstName);
		Assert.assertEquals(Constants.LAST_NAME, lastName);
		Assert.assertEquals(Constants.EMAIL_ADDRESS, email);
		Assert.assertEquals(Constants.COMMENTS, comments);
		Assert.assertEquals(Constants.SUBMIT, submitButton);
		Assert.assertEquals(Constants.RESET, resetButton);
		Assert.assertEquals(Constants.CONTACT_US, pageName);
	}
}
