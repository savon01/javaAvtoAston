package lesson13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

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

    @BeforeMethod
    public void resetPage() {
        driver.navigate().refresh();
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
    public void testGetInputPlaceholdersService() {
        List<String> placeholders = mainPage.getInputPlaceholders(
                "//p[text()='Услуги связи']");
        assertEquals("Номер телефона", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test(priority = 5)
    public void testGetInputPlaceholdersHomeInternet() {
        List<String> placeholders = mainPage.getInputPlaceholders(
                "//p[text()='Домашний интернет']");
        assertEquals("Номер абонента", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test(priority = 6)
    public void testGetInputPlaceholdersCredit() {
        List<String> placeholders = mainPage.getInputPlaceholders(
                "//p[text()='Рассрочка']");
        assertEquals("Номер счета на 44", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test(priority = 6)
    public void testGetInputPlaceholdersDebt() {
        List<String> placeholders = mainPage.getInputPlaceholders(
                "//p[text()='Задолженность']");
        assertEquals("Номер счета на 2073", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test(priority = 7)
    public void testContinueButtonOpensIframeAndCheckContent() {
        mainPage.fillTopUpForm(data.phone, data.amount, data.email);

        PaymentIframeData iframeData = mainPage.getPaymentIframeData();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iframeData.titlePrice, iframeData.buttonPrice,
                "Сумма в заголовке и на кнопке не совпадает");
        softAssert.assertEquals(iframeData.buttonPrice, data.amount,
                "Сумма не совпадает с ожидаемой");
        softAssert.assertEquals(iframeData.phone, data.phone,
                "Номер телефона неверный");
        softAssert.assertEquals(iframeData.cardLabel, data.labelNumberCard,
                "Лейбл номера карты неверный");
        softAssert.assertEquals(iframeData.termCardLabel, data.labelTernCard,
                "Лейбл срока действия неверный");
        softAssert.assertEquals(iframeData.cvcLabel, data.labelCvcCard,
                "Лейбл CVC неверный");
        softAssert.assertEquals(iframeData.nameCardLabel, data.labelNameCardPay,
                "Лейбл имени держателя неверный");
        softAssert.assertTrue(iframeData.logosPresent,
                "Логотипы платёжных систем отсутствуют");

        softAssert.assertAll(); // Обязательно вызываем в конце!
    }
}
