package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement error = $("[data-test-id='error-notification']");

    public LoginPage() {
        loginField.shouldBe(Condition.visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void blockLogin(DataHelper.AuthInfo info) {
        wrongLogin(info);
        wrongLogin(info);
        wrongLogin(info);
        error
                .shouldHave(exactText("Ошибка! Пароль введен неверно три раза - профиль заблокирован"))
                .shouldBe(Condition.visible);
    }

    public void wrongLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(DataHelper.getWrongPassword());
        loginButton.click();
        error
                .shouldHave(exactText("Ошибка\n" +
                        "Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
}