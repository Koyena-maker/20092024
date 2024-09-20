package Automation.seleniumframeworkpart1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import koyenamukherjee.Abstractreusable.abstractComponents;
import koyenamukherjee.pageobjects.Landingpage;
import koyenamukherjee.pageobjects.cartpage;
import koyenamukherjee.pageobjects.checkoutpage;
import koyenamukherjee.pageobjects.finalpage;
import koyenamukherjee.pageobjects.productcatalog;

public class submitOrder {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Landingpage lpg=new Landingpage(driver);
		lpg.goTo();
		productcatalog pg=lpg.loginIntoApplication("mani145@gmail.com", "Host@1234");
		List <WebElement> names= pg.getAllProducts();
		String prodName="IPHONE 13 PRO";
		String prodNameAnother="ADIDAS ORIGINAL";
		pg.addToCart(prodName);
		pg.addToCart(prodNameAnother);
		cartpage cp=pg.goToCartPage();
		Boolean match=cp.verifyProductNames(prodName);
		Assert.assertTrue(match);
		Boolean matchAnother=cp.verifyProductNames(prodNameAnother);
		Assert.assertTrue(matchAnother);
		checkoutpage cop=cp.goToCheckout();
		String name="Koyena Mukherjee";
		String cvv="123";
		String countryShortForm="Bri";
		String thankyouMsg="Thankyou for the order.";
        cop.enterName(name);
	    cop.enterCVV(cvv);
	    cop.selectByVisibleText("02",driver.findElement(By.xpath("//*[@class=\"input ddl\"][1]")));
	    cop.selectByVisibleText("25",driver.findElement(By.xpath("//*[@class=\"input ddl\"][2]")));
	    cop.selectCountry(countryShortForm);
	    finalpage fp=cop.clickOnSubMit();
	    String message=fp.printIds();
	    Assert.assertTrue(message.equalsIgnoreCase(thankyouMsg));
	    driver.close();
	   
	   
	    
	    
	    
	  
	   
	}

}
