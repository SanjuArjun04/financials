package com.stepdefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactForm  {
	
	public ContactForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
}
	@FindBy(name="first_name")
	private WebElement FirstName;
	
	@FindBy(name="last_name")
	private WebElement LastName;
	
	@FindBy(name="email")
	private WebElement Email;
	
	@FindBy(name="message")
	private WebElement message;
	
	@FindBy(xpath = "//input[@type='reset']")
	private WebElement resetButton;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[contains(text(),'Thank You for your Message!')]")
	private WebElement successMessage;
	
	@FindBy(xpath = "//body")
	private WebElement errorMessage;
	
	@FindBy(name="contactme")
	private WebElement pageName;
	
	@FindBy(tagName="input")
	private List<WebElement> inputElements;
	
	@FindBy(tagName="textarea")
	private List<WebElement> textareaElements;
	
	public WebElement getFirstName() {
		return FirstName;
	}
	
	public WebElement getLastName() {
		return LastName;
	}
	
	public WebElement getEmail() {
		return Email;
	}
	
	public WebElement getComments() {
		return message;
	}
	private void clearAndEnterText(WebElement inputField, String data) {
        inputField.clear();
        inputField.sendKeys(data);
    }
	
	public WebElement getResetButton() {
	    return resetButton;
	}

	public WebElement getSubmitButton() {
	    return submitButton;
	}
	
	public List< WebElement> getInputElements() {
		return inputElements;
	}

	public List< WebElement> getTextareaElements() {
		return textareaElements;
	}

	public String getSuccessMessageText() {
	    return successMessage.getText();
	}
	
	public String getErrorMessageText() {
	    return errorMessage.getText();
	}
	
	public void enterFirstName(String firstName) {
        clearAndEnterText(FirstName, firstName);
    }

    public void enterLastName(String lastName) {
        clearAndEnterText(LastName, lastName);
    }

    public void enterEmail(String email) {
        clearAndEnterText(Email, email);
    }

    public void enterMessage(String input) {
        clearAndEnterText(message, input);
    }
	
    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickResetButton() {
        resetButton.click();
    }
    public WebElement getPagename() {
    	return pageName;
    }
	

	
}
	
		
	
