package lesson13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private final By cookieBanner =  By.xpath("//div[@class='cookie__wrapper']"); // когда баннер активен
    private final By acceptCookieButton = By.xpath("//button[@id='cookie-agree']");

    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']//h2");
    private final By paymentLogo = By.xpath("//div[@class='pay__partners']//li");
    private final By detailService = By.xpath("//a[text()='Подробнее о сервисе']");

    private final By inputPhone = By.xpath("//input[@id='connection-phone']");
    private final By inputSum = By.xpath("//input[@id='connection-sum']");
    private final By inputEmail = By.xpath("//input[@id='connection-email']");
    private final By nextButton = By.xpath("//form[@id='pay-connection']/button[@class='button button__default ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для принятия cookie
    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(cookieBanner));

            WebElement button = driver.findElement(acceptCookieButton);
            if (button.isDisplayed()) {
                button.click();
                System.out.println("Cookie принято.");
            }
        } catch (Exception e) {
            System.out.println("Окно cookie не появилось или кнопка 'Принять' отсутствует.");
        }
    }

    public boolean nameBlock() {
        return driver.findElement(blockTitle).isDisplayed();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogo);
    }

    public void clickPaymentLink() {
        driver.findElement(detailService).click();
    }

    public void fillTopUpForm(String phone, String amount, String email) {
        WebElement phoneField = driver.findElement(inputPhone);
        phoneField.click();
        phoneField.sendKeys(phone);

        WebElement amountField = driver.findElement(inputSum);
        amountField.click();
        amountField.sendKeys(amount);

        WebElement emailField = driver.findElement(inputEmail);
        emailField.click();
        emailField.sendKeys(email);

        driver.findElement(nextButton).click();
    }

    // Метод проверяет, появился ли iframe оплаты после нажатия "Продолжить"
    public boolean isPaymentIframeVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("iframe[src*='https://checkout.bepaid.by/widget_v2/index.html']")
            ));
            return iframe.isDisplayed();
        } catch (Exception e) {
            System.out.println("Форма оплаты (iframe) не появилась: " + e.getMessage());
            return false;
        }
    }
}
