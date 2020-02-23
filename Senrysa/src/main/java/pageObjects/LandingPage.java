package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;
	
	By signin=By.xpath("//*[@class='btn btn-outline-secondary btn-md']");
	
	public LandingPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public WebElement getLogIn()
	{
		return driver.findElement(signin);
	}
}
