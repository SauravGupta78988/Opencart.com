package opencart_test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baselibrary.Base_Library;
import opencart_main.Login_page;
import opencart_main.MyAccount_page;

public class MyAccount_test extends Base_Library{
	Login_page ob;
	MyAccount_page map;
	
	@BeforeTest
	public void getlaunchUrl() {
		setDriver("chrome");
		getlaunchurl("https://awesomeqa.com/ui/");
		driver.navigate().refresh();
		ob = new Login_page();
		map = new MyAccount_page();
	}
	
	@Test (priority = 0)
	public void getTitle() {
		ob.getTitle_Opencart();
	}
	
	@Test (priority = 1)
	public void myAccountBtn(){
		
		map.clickMyaccount();
	}
	
	@Test (priority = 2)
	public void Register_Btn(){
		
		map.clickRegister();
	}
	
	@Test (priority = 3)
	public void inputDetailsfromExcel() {
		map.inputDetails();
	}

}
