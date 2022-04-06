package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataGenerator.UserInfo userInfo) {
        login(userInfo);
        return new VerificationPage();
    }

    public void login(DataGenerator.UserInfo userInfo) {
        loginField.setValue(userInfo.getLogin());
        passwordField.setValue(userInfo.getPassword());
        loginButton.click();
    }

    public void blockNotice() {
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Система заблокирована"));
    }

    public void cleanFields() {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }
}
