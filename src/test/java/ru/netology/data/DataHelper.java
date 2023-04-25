package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    static Faker faker = new Faker();

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
        String id;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123", "eba0e6ca-0e8b-481d-8a7b-208bd7188d4a");
    }

    public static String getWrongPassword() {
        return faker.internet().password();
    }
}