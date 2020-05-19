package TestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import DataReader.ExcelDataReader;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.List;
import java.util.Map;


public class GoogleSearch {





    public static void main(String[] args) throws InterruptedException {

        ExcelDataReader excelReader = new ExcelDataReader();

        //for Instantiating the Browser
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // driver.get("https://www.google.com/");

        //Reading the excel file
        Map<String, String> rowMap = excelReader.getRowDataMap("src\\qaautomation.xlsx", 1, 1);

       //Getting the Value of 1st row
        driver.get(rowMap.get("URL"));
        Thread.sleep(1000);

        driver.manage().window().maximize();
         Actions builder = new Actions(driver);

         driver.findElement(By.name("q")).sendKeys(rowMap.get("Search String 1"));
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;


        //Finding the Link from Google search and navigating to the page

        List <WebElement> my_list = driver.findElements(By.xpath("//div[@class='r']//h3"));

        js.executeScript("window.scrollBy(0,1000)", "");
        Thread.sleep(2000);
        for (WebElement item:my_list)
        {
            if(item.getAttribute("innerHTML").contains("After Life (TV Series 2019– ) - IMDb")) {
                item.click();


               break;
            }
        }
        WebElement SeeCast = driver.findElement(By.linkText("See full cast & crew"));
        builder.contextClick(SeeCast).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

        List<WebElement> List= driver.findElements(By.xpath("//table[@class='cast_list']/tbody/tr"));


        for (WebElement items:List)
        {
          String name= items.getText();
            System.out.println(name);
        }



        //driver.findElement(By.name("q")).sendKeys("After Life");
        //driver.findElement(By.name("btnI")).click();
        //List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbl1']"));
        //System.out.println("Total num of suggestions::->" + list.size());

        //for (int i = 0; i < list.size(); i++)
        //{
            //System.out.println(list.get(i).getText());
          //  if (list.get(i).getText().contains("after Life")) {
            //    list.get(i).click();
              //  driver.quit();
           // }

        //}
    }
}
