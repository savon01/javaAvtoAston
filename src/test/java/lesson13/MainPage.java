package lesson13;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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

    private final By select = By.xpath("//button[@class='select__header']");
    private final By form = By.xpath("//form[@id='pay-connection']");
    private final By formOpen = By.xpath("//form[@class='pay-form opened']");
    private final By formInput = By.cssSelector("input[placeholder]");

    private final By amount = By.xpath("//div[@class='pay-description__cost']/span");


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
    public PaymentIframeData getPaymentIframeData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PaymentIframeData iframeData = new PaymentIframeData();

        try {
            // Ждём iframe и переключаемся
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                    By.cssSelector("iframe[src*='checkout.bepaid.by/widget_v2/index.html']")));

            // Сумма вверху
            iframeData.titlePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'BYN')]"))).getText().replaceAll("[^0-9.]", "");

            // Сумма на кнопке
            iframeData.buttonPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@class,'colored')]"))).getText().replaceAll("[^0-9.]", "");

            // Номер телефона
            String number = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Номер:')]"))).getText().replaceAll("[^0-9]", "");
            iframeData.phone = number.substring(number.length() - 9);

            // Лейблы
            iframeData.cardLabel = driver.findElement(By.xpath("//label[contains(text(),'Номер карты')]")).getText();
            iframeData.termCardLabel = driver.findElement(By.xpath("//label[contains(text(),'Срок действия')]")).getText();
            iframeData.cvcLabel = driver.findElement(By.xpath("//label[contains(text(),'CVC')]")).getText();
            iframeData.nameCardLabel = driver.findElement(By.xpath("//label[contains(text(),'Имя и фамилия на карте')]")).getText();

            // Логотипы
            iframeData.logosPresent = !driver.findElements(By.xpath("//img[contains(@class, 'ng-star-inserted')]")).isEmpty();

        } catch (Exception e) {
            System.out.println("Ошибка при получении данных из iframe: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }

        return iframeData;
    }

    //метод для проверки плейсхолдеров
    public List<String> getInputPlaceholders(String selectedOption) {
        List<String> placeholders = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ждём появления кнопки выбора и кликаем
        WebElement selectButton = wait.until(ExpectedConditions.elementToBeClickable(select));
        selectButton.click();

        // Ждём появления списка и кликаем по нужной опции
        By selectOption = By.xpath(selectedOption);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(selectOption));

        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            // Если клик перехвачен — кликаем через JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }

        // Ждём появления формы после выбора
        wait.until(ExpectedConditions.visibilityOfElementLocated(form));

        // Находим форму и все input с placeholder
        WebElement formElement = driver.findElement(formOpen);
        List<WebElement> inputFields = formElement.findElements(formInput);

        // Получаем значения атрибутов placeholder
        for (WebElement inputField : inputFields) {
            placeholders.add(inputField.getAttribute("placeholder"));
        }

        return placeholders;
    }
}
