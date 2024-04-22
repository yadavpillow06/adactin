package org.LoginpageAdactin;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Addtocart {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Implicit wait to handle dynamic elements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navigate to the DemoBlaze website
		driver.get("https://www.demoblaze.com/index.html");

		// Add three different products to the cart
		addProductToCart(driver, "Samsung galaxy s6");
		addProductToCart(driver, "Sony vaio i5");
		// addProductToCart(driver, "Apple monitor 24");

		addProductToCart(driver, "ASUS Full HD");
		// Verify the total price is correct
		verifyTotalPrice(driver, "totalp");
		removeproduct(driver);
		verifyTotalPrice(driver, "totalp");
		// Close the browser
		//driver.quit();
	}

	
	private static void addProductToCart(WebDriver driver, String productName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + productName + "']")));
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Dismiss the pop-up message
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ESCAPE).perform();
		Thread.sleep(10000);
		// driver.findElement(By.xpath("(//a[@class='nav-link'])[1]")).click();
		driver.switchTo().alert().accept();
		WebElement home = driver.findElement(By.xpath("//a[text()='Home ']"));
		js.executeScript("arguments[0].click()", home);
		WebElement monitor = driver.findElement(By.xpath("//a[text()='Monitors']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monitor);
		Thread.sleep(1000);
		monitor.click();
		// js.executeScript("window.scrollBy(0,1000)");
		// driver.findElement(By.xpath("//a[text()='" + CATEGORIES + "']"));
	}


	public static int sumOfTotalValue = 0;
	private static void verifyTotalPrice(WebDriver driver, String totalp) {
		WebElement l = driver.findElement(By.id("cartur"));
		l.click();
		WebElement table = driver.findElement(By.tagName("table"));
		WebElement tbody = driver.findElement(By.id("tbodyid"));
	List<WebElement> rows = tbody.findElements(By.tagName("tr"));
	System.out.println(rows.size());
	for (int i = 0; i <rows.size(); i++) {
		WebElement row = rows.get(i);
		//System.out.println(row.getText());
		List<WebElement> data = row.findElements(By.tagName("td"));
			WebElement dt = data.get(2);
			int value = Integer.parseInt(dt.getText());	
			sumOfTotalValue = sumOfTotalValue + value;	
	}
	
	System.out.println("The sum of total values are: " + sumOfTotalValue);
	}
	
	public static int totalvalue ;
	public static int sumOfTotalValue1 = 0;
	private static void removeproduct(WebDriver driver) {
		WebElement l = driver.findElement(By.xpath("(//a[text()='Delete'])[3]"));
		l.click();
		WebElement table = driver.findElement(By.tagName("table"));
		WebElement tbody = driver.findElement(By.id("tbodyid"));
	List<WebElement> rows = tbody.findElements(By.tagName("tr"));
	System.out.println(rows.size());
	for (int i = 1; i <rows.size(); i++) {
		WebElement row = rows.get(i);
		//System.out.println(row.getText());
		List<WebElement> data = row.findElements(By.tagName("td"));
			WebElement dt = data.get(2);
			dt.click();
			
			int value = Integer.parseInt(dt.getText());	
			sumOfTotalValue1 = sumOfTotalValue1 + value;
		
	}
	System.out.println("The sum of total values are: " + sumOfTotalValue1);	
		
	}
	
}
