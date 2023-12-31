package com.example.test_bing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {
    WebDriver driver;
    @FindBy(css="#sb_form_q")
    private WebElement searchPageField;

    @FindBy(css=".b_attribution[tabindex='0']>cite")
    private List<WebElement> results;

    public String getValue(){
        return searchPageField.getAttribute("value");
    }

    public String getSearchUrl(int num) {
        String result = results.get(num).getText() + "/";
        System.out.println("Адрес ссылки из поисковой выдачи под номером " + ++num + ": " + result);
        return result;
    }
    public void goToUrl(int num){
        String url = getSearchUrl(--num);
        driver.get(url);
        System.out.println("Перешли по ссылке: " + url);
    }
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
