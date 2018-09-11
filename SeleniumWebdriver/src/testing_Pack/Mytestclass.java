package testing_Pack;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Mytestclass {
WebDriver driver;
String computername;
@Test(priority=1)
 public void load_url() 
{		
	    //set geckodriver path.
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//initialize chrome driver object to open chrome browser.
		driver = new ChromeDriver();
		// maximize browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		//open URL in browser.
		driver.get("http://computer-database.gatling.io/computers");
		System.out.println("Open web site successful.");
  
}

@Test(priority=2) 
  public void add_record() 
{	   
	   computername = "Computer test";
  	   System.out.println("Add new computer with name: " + computername);
  	   delay();
  	   // Click [Add a new computer] button
	   driver.findElement(By.id("add")).click();
	   delay();
	   // Input value = Computer test to [Computer name] textbox
	   driver.findElement(By.xpath("//input[@name='name']")).sendKeys(computername);
	   delay();
	   // Click [Create this computer] button
	   driver.findElement(By.xpath("//*[@id=\"main\"]/form/div/input")).submit();
}

@Test(priority=3)
public void edit() 
{	   
	   System.out.println("Edit record:" + computername);
	   search();
	   driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[1]/a")).click();	
	   computername = "Computer Edit";
	   // Clear and Edit value = Computer Edit to [computer name] textbox
	   driver.findElement(By.xpath("//input[@name='name']")).clear();
	   driver.findElement(By.xpath("//input[@name='name']")).sendKeys(computername);
	   // Input value = 2018-05-05 to [Introduced date] textbox
  	   driver.findElement(By.xpath("//input[@name='introduced']")).sendKeys("2018-05-05");
  	   // Input value = 2019-05-05 to [Discontinued date] textbox
	   driver.findElement(By.xpath("//input[@name='discontinued']")).sendKeys("2019-05-05");
	  // Input value = Netronics to [company] textbox
	   driver.findElement(By.xpath("//select[@id='company']/option[@value='4']")).click();
	   delay();
	  // Click [save this computer] button
	   driver.findElement(By.xpath("//*[@id=\"main\"]/form/div/input")).submit();

}
@Test(priority=4)
public void check_value() 
{	
	   System.out.println("Test edited value record: " + computername);	
	   search();
	   delay();	
	   //compare value: Computer name, introduced date, discontinued date, company
	   Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[1]/a")).getText()),computername,"Computer name doesn't match");
	   Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[2]")).getText()),"05 May 2018","Introduced date doesn't match");	
	   Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[3]")).getText()),"05 May 2019","Discontinued date doesn't match");
	   Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[4]")).getText()),"Netronics","Company doesn't match");
	   driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[1]/a")).click();		
	   delay();
}

@Test(priority=5)
public void delete() 
{	   	
	   System.out.println("Delete record: " + computername);
	   // Click [Delete this computer] button
	   driver.findElement(By.xpath("//*[@id=\"main\"]/form[2]/input")).submit();
	   // Searching to make sure 'Computer edit' was already deleted
	   search();
	   delay();
}
@Test (priority=6)
public void finish() 
{
	   System.out.println("completed.");	
	   driver.close();
}

public void delay() 
{
	   try {
		    Thread.sleep(2000);
	       } 
	        catch (InterruptedException e)
	       {
		    e.printStackTrace();
	       }
}

public void search() 
{	   
	   // Enter value into Search textbox
	   driver.findElement(By.id("searchbox")).sendKeys(computername);
	   delay();
	   // Click search button
	   driver.findElement(By.id("searchsubmit")).submit();
	  	   
}

}



 
 