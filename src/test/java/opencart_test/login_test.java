package opencart_test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baselibrary.Base_Library;
import opencart_main.Login_page;

public class login_test extends Base_Library{
	
	Login_page ob;
	
	@BeforeTest
	public void getlaunchUrl() {
		setDriver("chrome");
		getlaunchurl("https://awesomeqa.com/ui/");
		driver.navigate().refresh();
		ob = new Login_page();
		
	}
	
	@Test (priority = 0)
	public void getTitle() {
		ob.getTitle_Opencart();
	}
	
	@Test (priority = 1)
	public void myAccountBtn(){
		
		ob.clickMyaccount();
	}
	
	@Test (priority = 2)
	public void login_Btn(){
		
		ob.clickLogin();
	}
	
	@Test (priority = 3)
	public void getData_Exl(){
		
		ob.getData();
	}

}
