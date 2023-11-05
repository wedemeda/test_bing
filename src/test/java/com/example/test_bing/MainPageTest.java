package com.example.test_bing;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Запрос Selenium остается в строке поиска после вывода результатов.")
    public void search() {

        String input = "Selenium";
        String searchSelector = "#sb_form_q";

        searchAction(input, searchSelector);

        WebElement searchPageField = driver.findElement(By.cssSelector(searchSelector));
        assertEquals(input, searchPageField.getAttribute("value"),"Запрос Selenium отсутствует в строке поиска после вывода резултатов.");
    }

    @Test
    @DisplayName("Первая ссылка в выдаче ведет на сайт https://www.selenium.dev/")
    public void checkUrl() {

        String input = "Selenium";
        String searchSelector = "#sb_form_q";

        searchAction(input, searchSelector);

        List<WebElement> results = driver.findElements(By.cssSelector(".b_attribution[tabindex='0']>cite"));
        String searchUrl = getSearchUrl(results, 0);
        driver.get(searchUrl);
        assertEquals(searchUrl, driver.getCurrentUrl(),"Первая ссылка в выдаче не ведет на сайт https://www.selenium.dev/");

    }

        public void searchAction(String input, String searchSelector) {

            WebElement searchField = driver.findElement(By.cssSelector(searchSelector));
            searchField.sendKeys(input);
            searchField.submit();
        }

        public String getSearchUrl(List<WebElement> results, int num) {

           return results.get(num).getText()+"/";
        }
}