package com.example.test_bing.tests;
import com.example.test_bing.pages.MainPage;
import com.example.test_bing.pages.ResultsPage;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class BingSearchTest {
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

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage res = new ResultsPage(driver);

        assertEquals(input, res.getValue(),"Запрос Selenium отсутствует в строке поиска после вывода результатов.");
    }

    @Test
    @DisplayName("Первая ссылка в выдаче ведет на сайт https://www.selenium.dev/")
    public void checkUrl() {

        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage res = new ResultsPage(driver);

        driver.get(res.getSearchUrl(0));

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(),"Первая ссылка в выдаче не ведет на сайт https://www.selenium.dev/");

    }

}