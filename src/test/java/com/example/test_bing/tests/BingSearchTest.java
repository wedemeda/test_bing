package com.example.test_bing.tests;
import com.example.test_bing.pages.MainPage;
import com.example.test_bing.pages.ResultsPage;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BingSearchTest {
    private WebDriver driver;
    private final String input = "Selenium";

    MainPage mp;
    ResultsPage res;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
        mp = new MainPage(driver);
        res = new ResultsPage(driver);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Запрос Selenium остается в строке поиска после вывода результатов.")
    public void search() {
        mp.sendText(input);
        assertEquals(input, res.getValue(),"Запрос Selenium отсутствует в строке поиска после вывода результатов.");
    }

    @Test
    @DisplayName("Первая ссылка в выдаче ведет на сайт https://www.selenium.dev/")
    public void checkUrl() {
        mp.sendText(input);
        res.goToUrl(1);
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(),
                "Первая ссылка в выдаче не ведет на сайт https://www.selenium.dev/");
    }

}