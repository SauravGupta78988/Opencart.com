package opencart_main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baselibrary.Base_Library;

public class MyAccount_page extends Base_Library{
	

	static String Path = "C:\\Users\\hp\\eclipse-workspace\\Opencart-V1.0\\Test data\\Testdata.xlsx";
	public MyAccount_page() {
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(xpath="//span[text()=\"My Account\"]")
	WebElement myaccount;
	
	@FindBy(xpath="//*[@id=\"carousel-banner-0\"]/div[2]/div[1]/div/div/a/img")
	WebElement Product;
	
	@FindBy(xpath= "//*[text()=\"Wish List (0)\"]")
	WebElement Wishlist;
	
	
	
	public void clickMyaccount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"My Account\"]")));
		myaccount.click();
	}
	public void clickRegister() {
		List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/child::li"));
		for(WebElement ele : options) {
			String str = ele.getText();
			
			if(str.equals("Register")) {
				ele.click();
				break;
			}
		}
		
	}
	
	public void clickWishlist() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Wish List (0)\"]")));
		Wishlist.click();
	}
	
	public void clickOnProduct() {
		Product.click();
	}
	
	public void inputDetails() {
		
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(getExcelData(Path, 0, 0, 0));
		driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys(getExcelData(Path, 0, 1, 0));
		driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(getExcelData(Path, 0, 2, 0));
		driver.findElement(By.xpath("//input[@name=\"telephone\"]")).sendKeys(getExcelData(Path, 0, 3, 0));
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(getExcelData(Path, 0, 4, 0));
		driver.findElement(By.xpath("//input[@name=\"confirm\"]")).sendKeys(getExcelData(Path, 0, 5, 0));
		//click on privacy policy check
		driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
		//click on continue button
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		
	}
	
	
}
