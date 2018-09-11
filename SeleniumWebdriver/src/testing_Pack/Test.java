package testing_Pack;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) {
		//set geckodriver path.
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				//initialize chrome driver object to open chrome browser.
				ChromeDriver driver = new ChromeDriver();
				
				//open URL in browser.
			    driver.get("http://google.com");
			    String i = driver.getCurrentUrl();
			    System.out.println(i);
			    driver.close();
}
}