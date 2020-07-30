Feature: Keytorc Test Automation case

  @clear_favorites @log_out
  Scenario: Login to the system and add product to the favourites
    Given I visit "https://www.evkur.com.tr/"
    And I closed cookie popup if it is displayed
    And I navigated to Login Page
    Then I should be redirected to "evkur.com.tr/uye-girisi"
    And I logged in with following credentials
    |kaanmertcakmak@gmail.com|Test1172|
    And I searched for "samsung"
    Then I should be redirected to "/arama?"
    Then I verified results are appeared after searched
    And I navigated to "2"nd page of search results
    Then I should be redirected to "searchTerm=samsung&pageNumber=2"
    And I verified results are appeared after searched
    And I navigated the "3"rd product detail page
    And I add product to the favourites
    Then I navigated to Favorites page and verified if product is added to favourites successfully
