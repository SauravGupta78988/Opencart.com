package opencart_main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baselibrary.Base_Library;

public class Login_page extends Base_Library{
	static String Path = "C:\\Users\\hp\\eclipse-workspace\\Opencart-V1.0\\Test data\\Testdata.xlsx";

	public Login_page() {
		PageFactory.initElements(driver, this);
		
		
	}
	
	public void getTitle_Opencart() {
		String title = driver.getTitle();
		System.out.println("Title is "+title);
	}
	
	@FindBy(xpath="//span[text()=\"My Account\"]")
	WebElement myaccount;

	@FindBy(xpath= "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[2]/a")
	WebElement loginbtn;
	
	
	
	
	
	public void clickMyaccount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"My Account\"]")));
		myaccount.click();
	}
	public void clickLogin() {
		List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/child::li"));
		for(WebElement ele : options) {
			String str = ele.getText();
			
			if(str.equals("Login")) {
				ele.click();
			}
		}
		
	}
	
	public void getData() {
		driver.findElement(By.xpath("//input[@placeholder=\"E-Mail Address\"]")).sendKeys(getExcelData(Path, 0, 2, 0));
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(getExcelData(Path, 0, 4, 0));
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

	}
	
	

}
