package lesson13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class MtsTest {
    private WebDriver driver;
    private MainPage mainPage;

    private Data data;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        data = new Data();
        driver.get(data.getUrl);
        mainPage = new MainPage(driver);
        // Закрываем окно cookie, если оно есть
        mainPage.acceptCookiesIfPresent();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quit();
    }

    @Test(priority = 1)
    public void testBlockTitle() {
        Assert.assertTrue(mainPage.nameBlock(), "Заголовок блока не отображается");
    }

    @Test(priority = 2)
    public void testPaymentLogo() {
        Assert.assertTrue(mainPage.getPaymentLogos().size() >= 2);
    }

    @Test(priority = 3)
    public void testDetailLink() {
        mainPage.clickPaymentLink();
        Assert.assertTrue(driver.getCurrentUrl().contains(
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Неверная ссылка");
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void testContinueButtonOpensIframe() {
        mainPage.fillTopUpForm(data.phone, data.amount, data.email);
        Assert.assertTrue(mainPage.isPaymentIframeVisible(), "Iframe оплаты не появился. Кнопка 'Продолжить' не сработала.");
    }
}
