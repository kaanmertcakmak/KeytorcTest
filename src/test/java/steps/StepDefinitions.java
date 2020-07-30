package steps;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_elements.*;
import utils.Base;
import utils.HelperMethods;

import java.util.List;

public class StepDefinitions extends Base {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait webDriverWait;
    private CategoryPage categoryPage;
    private ProductDetailPage productDetailPage;
    private LoginPage loginPage;
    private CustomerPage customerPage;
    private FavoritesPage favoritesPage;
    private String productNameInProductDetail;


    public StepDefinitions() {
        // initialized driver
        driver = initializeDriver();

        // created page objects with initialized driver
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        loginPage = new LoginPage(driver);
        customerPage = new CustomerPage(driver);
        favoritesPage = new FavoritesPage(driver);

        // additional helpers
        webDriverWait = new WebDriverWait(driver, 30);
    }
    @Given("^I visit \"([^\"]*)\"$")
    public void navigate_to_Site(String arg1) {
        // Write code here that turns the phrase above into concrete actions
        driver.get(arg1);
    }

    @When("^I navigated to Login Page$")
    public void iNavigatedToLoginPage() {
        homePage.getPersonIcon().click();
    }


    @And("^I logged in with following credentials$")
    public void iLoggedInWithFollowingCredentials(DataTable dataTable) {
        List<String> rows = dataTable.asList(String.class);
        String username = rows.get(0);
        String password = rows.get(1);

        loginPage.getEmailFieldInLoginPage().click();
        loginPage.getEmailFieldInLoginPage().sendKeys(username);

        loginPage.getPasswordFieldInLoginPage().click();
        loginPage.getPasswordFieldInLoginPage().sendKeys(password);

        HelperMethods.scrollToElement(loginPage.getLoginButtonInLoginPage(), driver);
        loginPage.getLoginButtonInLoginPage().click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getMyAccountText()));
        String userText = homePage.getMyAccountText().getText();
        Assert.assertEquals(userText, "HESABIM");
    }

    @Then("^I should be redirected to \"([^\"]*)\"$")
    public void iShouldBeRedirectedTo(String url) {
        webDriverWait.until(ExpectedConditions.urlContains(url));
    }


    @After(order = 2, value = "@clear_favorites")
    public void clearFavorites() {
        driver.get("https://www.evkur.com.tr/hesabim/favorilerim");
        favoritesPage.removeFromFavoritesButton().click();

        webDriverWait.until(ExpectedConditions.visibilityOf(productDetailPage.successPopupText()));
        productDetailPage.tamamButton().click();

        // Verifies if no products text in favorites page is displayed, means there is no product left
        Assert.assertTrue(favoritesPage.noProductsInFavoritesPage().isDisplayed());
    }

    // Hook for logging out after test
    @After(order = 1, value = "@log_out")
    public void logOut()
    {
        driver.get("https://www.evkur.com.tr/");
        homePage.getMyAccountText().click();

        webDriverWait.until(ExpectedConditions.urlContains("/hesabim"));
        customerPage.logoutButton().click();

        webDriverWait.until(ExpectedConditions.urlContains("/uye-girisi"));
    }

    @After(order = 0)
    public void teardown()
    {

        driver.close();
        driver=null;
    }

    @And("^I searched for \"([^\"]*)\"$")
    public void iSearchedFor(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        homePage.SearchEntryField().sendKeys(arg0);
        homePage.searchButton().click();
    }

    @Then("^I verified results are appeared after searched$")
    public void iVerifiedResultsAreAppearedAfterSearched() {
        // verified count of products are greater than zero
        Assert.assertTrue(categoryPage.productNames().size() > 0);

        // verify each product has "samsung" on its name
        categoryPage.productNames().forEach(product -> {
            String productName = product.getText();
            Assert.assertTrue(productName.contains("Samsung"));
        });
    }

    @And("^I navigated to \"([^\"]*)\"nd page of search results$")
    public void iNavigatedToNdPageOfSearchResults(int arg0) {
        categoryPage.pages().get(arg0 - 1).click();
    }

    @And("^I navigated the \"([^\"]*)\"rd product detail page$")
    public void iNavigatedTheRdProductDetailPage(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        categoryPage.productNames().get(arg0 - 1).click();
        productNameInProductDetail = productDetailPage.productNameInProductDetail().getText();
    }

    @And("^I add product to the favourites$")
    public void iAddProductToTheFavourites() {
        productDetailPage.addProductToFavoritesButton().click();
        webDriverWait.until(ExpectedConditions.visibilityOf(productDetailPage.successPopupText()));
        productDetailPage.tamamButton().click();
    }

    @Then("^I navigated to Favorites page and verified if product is added to favourites successfully$")
    public void iNavigatedToFavoritesPageAndVerifiedIfProductIsAddedToFavouritesSuccessfully() {
        homePage.getMyAccountText().click();
        webDriverWait.until(ExpectedConditions.urlContains("/hesabim"));

        customerPage.favoritesPageButton().click();
        webDriverWait.until(ExpectedConditions.urlContains("/hesabim/favorilerim"));

        String favoritedProductName = favoritesPage.productsNameInFavoritesPage().getText();

        Assert.assertEquals(favoritedProductName, productNameInProductDetail);

    }

    @And("^I closed cookie popup if it is displayed$")
    public void iClosedCookiePopupIfItIsDisplayed() {
        try{
            homePage.closeCookiePopupButton().click();
        }catch (NoSuchElementException e){
            System.out.println("cookie button did not display");
        }
    }
}
