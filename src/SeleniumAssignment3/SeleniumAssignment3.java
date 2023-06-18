package SeleniumAssignment3;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;
public class SeleniumAssignment3 {
public static void main(String[] args) throws InterruptedException {

//WebDriverManager.firefoxdriver().setup();
WebDriverManager.chromedriver().setup();
//WebDriverManager.edgedriver().setup();

WebDriver driver = new ChromeDriver();
//WebDriver driver = new FirefoxDriver();
//WebDriver driver = new EdgeDriver();

driver.get("https://opensource-demo.orangehrmlive.com/");         // Navigating to orangehrmlive.com application
WebElement username = driver.findElement(By.id("txtUsername"));   // Setting UserName as "Admin"
username.sendKeys("Admin");

WebElement password = driver.findElement(By.id("txtPassword"));   // Setting Password as "admin123"
password.sendKeys("admin123");

WebElement login = driver.findElement(By.id("btnLogin"));         // Clicking on login button
login.click();

String current_url = driver.getCurrentUrl();                      // Getting Current url
System.out.println(current_url);
                                                                  //Verifying Current url contains "dashboard" or not
if(current_url.contains("dashboard")) {
	System.out.println("Yes Current url contains dashboard");
}
else {
	System.out.println("Doesn't contains dashboard");
}
                                                                   // Clicking on Admin Tab
WebElement admin_tab = driver.findElement(By.id("menu_admin_viewAdminModule"));
admin_tab.click();
                                                                   //Go to the Job tab and check ‘Job Titles’ is there or not

WebElement job_tab = driver.findElement(By.id("menu_admin_Job"));
job_tab.click();

                                                                   // Getting all list of anchor tag and checking if there "Job Title" present or not
List<WebElement> list = driver.findElements(By.cssSelector("menu_admin_Job").tagName("a"));

for (WebElement option : list) {
//	System.out.println(option.getText());
	String s = option.getText();
	if(s.contains("Job Title")) {
		System.out.println("Yes, Job Title is Present inside Job Dropdown");
	}
}
                                                                     // Clicking on Job Title from Job Dropdown
WebElement job_title = driver.findElement(By.id("menu_admin_viewJobTitleList"));
job_title.click();

                                                                     //Get the List of All Jobs Available and display on console
List<WebElement> listOfAvailaleJob = driver.findElements(By.cssSelector("#resultTable > tbody"));
for (WebElement listOfJob : listOfAvailaleJob) {
	String lists = listOfJob.getText();
	System.out.println(lists);
}

                                                                      //Logout and Close the browser
WebElement welcomeScreen = driver.findElement(By.id("welcome"));
welcomeScreen.click();
                                                                      //System.out.println("Printing Till logout");

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
               .withTimeout(Duration.ofSeconds(2)).ignoring(NoSuchFieldException.class)
			   .ignoring(TimeoutException.class).pollingEvery(Duration.ofMillis(100));


WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
logout.click();

Thread.sleep(3000);
driver.quit();

}
}