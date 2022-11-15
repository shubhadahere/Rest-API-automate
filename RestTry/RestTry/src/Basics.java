import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsableMethods;

public class Basics {

	@Test
	public static void main(String[] args) throws InterruptedException {

		RestAssured.baseURI = "https://randomuser.me/";
		String getPlaceResponse = given().log().all().when().get("api/").then().assertThat().log().all().statusCode(200)
				.extract().response().asString();

		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);

		String firstName = js1.getString("results[0].name.first");
		String lastName = js1.getString("results[0].name.last");
		String email = js1.getString("results[0].email");
		String password = js1.getString("results[0].login.password");

		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(password);

		System.setProperty("webdriver.chrome.driver", "D:\\Prac\\SeleniumTool\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Create an Account")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.id("firstname")).sendKeys(firstName + "YO");
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password + "123@#AS");
		driver.findElement(By.id("password-confirmation")).sendKeys(password + "123@#AS");
		driver.findElement(By.linkText("Create an Account")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.id("ui-id-8")).click();
		driver.findElement(By.linkText("Tees")).click();
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img"))
				.click();
		driver.findElement(
				By.xpath("/html/body/div[2]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[1]/div/div[1]"))
				.click();

		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-49\"]")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.cssSelector("#product-addtocart-button > span")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div > div > a"))
				.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(
				"#maincontent > div.columns > div > div.cart-container > div.cart-summary > ul > li:nth-child(1) > button > span"))
				.click();
		System.out.println("BUTTON CLICK");
		driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[1]/fieldset/div/div/input"))
				.sendKeys(email);
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[1]/div/input"))
				.sendKeys(firstName);
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[2]/div/input"))
				.sendKeys(lastName);
		driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/fieldset/div/div[1]/div/input"))
				.sendKeys("address");
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[4]/div/input"))
				.sendKeys("city");
		Select state = new Select(driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[5]/div/select")));

		state.selectByIndex(1);
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[7]/div/input"))
				.sendKeys("284858");
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[9]/div/input"))
				.sendKeys("215648");

		WebElement radio1 = driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[1]/table/tbody/tr[1]/td[1]/input"));
		radio1.click();
		System.out.println("addrs done");
		driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[3]/div/button/span"))
				.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html/body/div[3]/main/div[2]/div/div[2]/div[4]/ol/li[3]/div/form/fieldset/div[1]/div/div/div[2]/div[2]/div[4]/div/button"))
				.click();

		String confirmation = driver.findElement(By.xpath("/html/body/div[2]/main/div[1]/h1/span")).getText();
		String expectedTitle = "Thank you for your purchase!";
		Assert.assertEquals(confirmation, expectedTitle);

		driver.quit();

	}

}
