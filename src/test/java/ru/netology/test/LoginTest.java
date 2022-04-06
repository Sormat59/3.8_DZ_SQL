package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.AppUtils;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanUp() {
        AppUtils.clearTables();
    }

    @Test
    public void shouldLogin1() {
        var validUser = DataGenerator.generateUserInfo1();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(validUser);
        verificationPage.validVerify(AppUtils.getVerificationCode());
    }
    @Test
    public void shouldLogin2() {
        var validUser = DataGenerator.generateValidUserInfo2();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(validUser);
        verificationPage.validVerify(AppUtils.getVerificationCode());
    }

    @Test
    public void shouldInputInvalidUserThreeTimes(){
        var invalidUser = DataGenerator.generateInvalidUserInfo();
        var loginPage = new LoginPage();
        loginPage.login(invalidUser);
        loginPage.cleanFields();
        loginPage.login(invalidUser);
        loginPage.cleanFields();
        loginPage.login(invalidUser);
        loginPage.blockNotice();
    }
}
