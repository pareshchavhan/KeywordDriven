package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	public static WebDriver driver;
	public static String URL = "https://accounts.google.com";
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		
		//login gmail application
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement userName = driver.findElement(By.xpath("//input[contains(@id,'identifierId')]"));
		WebElement emailNextButton = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		userName.sendKeys("pareshnit@gmail.com");
		emailNextButton.click();
		
		Thread.sleep(3000);
		WebElement pass = driver.findElement(By.xpath("//input[contains(@name,'password')]"));
		WebElement passNextButton = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		pass.sendKeys("chavhanp");
		passNextButton.click();
		
		Thread.sleep(3000);
		WebElement googleApps = driver.findElement(By.xpath("//a[contains(@class,'gb_b') and contains(@href,'https://www.google.co.in/intl/en/options/')]"));
		wait.until(ExpectedConditions.visibilityOf(googleApps));
		googleApps.click();
		
		Thread.sleep(2000);
		WebElement gmailLogo = driver.findElement(By.xpath("//span[contains(text(),'Gmail')]"));
		wait.until(ExpectedConditions.visibilityOf(gmailLogo));
		gmailLogo.click();
		
		
	}
}
