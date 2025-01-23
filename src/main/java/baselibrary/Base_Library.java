package baselibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelUtility.ExcelReader;

public class Base_Library implements ExcelReader {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            // Set the Chrome driver system property
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\eclipse-workspace\\Opencart-V1.0\\Drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            // Any additional options can be added here (e.g., headless mode)
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Set the Firefox driver system property
            System.setProperty("webdriver.gecko.driver", "");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            // Set the Edge driver system property
            System.setProperty("webdriver.edge.driver", "C:\\Users\\hp\\eclipse-workspace\\Opencart-V1.0\\Drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

	public void getlaunchurl(String url) {
		if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Please call setDriver() first.");
        }
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));
	}

	@Override
	public String getExcelData(String path, int sheetno, int rowno, int colno) {
		String value = "";
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(sheetno);
			XSSFRow row = sh.getRow(rowno);
			XSSFCell cell = row.getCell(colno);
			wb.close();

			// Check if cell is null

			if (cell == null) {
				return value; // Return empty string if cell is null
			}
			// Use if-else to check cell type
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
				
			} else if (cell.getCellType() == CellType.NUMERIC) {
			    // If the number is a mobile number, handle it as a string to prevent scientific notation
			    String cellValue = String.valueOf(cell.getNumericCellValue());
			    
			    // Check if the value is in scientific notation and format it properly
			    if (cellValue.contains("E")) {
			        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
			        cellValue = bd.toPlainString();  // This removes scientific notation
			    }
			    
			    return cellValue;
				
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
				
			} else if (cell.getCellType() == CellType.FORMULA) {
				return cell.getCellFormula(); // You can evaluate the formula if needed
				
			} else {
				return value; // Handle other cases (like empty cells)
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value; 
		// Return the default value if there's an error
	}
	
	public void quitDriver() {
	    if (driver != null) {
	        driver.quit();
	        driver = null;
	    }
	}

}
