package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage {

    private WebDriver webDriver;

    public CategoryPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    private By productNamesLocator = By.cssSelector(".product-content .name");
    private By paginationNumbersLocator = By.cssSelector(".paging .vcenter");

    public List<WebElement> productNames(){
        return webDriver.findElements(productNamesLocator);
    }

    public List<WebElement> pages(){
        return webDriver.findElements(paginationNumbersLocator);
    }


}
