package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;
	
	By email=By.cssSelector("div.form-group");
	By password=By.xpath("/html/body/div[4]/div/div[1]/div/div/div/div/div[2]/div/form/div[2]/div/input");
	By submit=By.xpath("/html/body/div[4]/div/div[1]/div/div/div/div/div[2]/div/form/div[4]/button");
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	public WebElement clickSubmit()
	{
		return driver.findElement(submit);
	}
}
