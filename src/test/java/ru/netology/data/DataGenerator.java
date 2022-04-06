package ru.netology.data;

import com.github.javafaker.Faker;


import lombok.Value;

import java.util.Locale;

public class DataGenerator {
    public DataGenerator() {
    }

    @Value
    public static class UserInfo {
        String login;
        String password;
    }

    public static UserInfo generateUserInfo1() {
        return new UserInfo("vasya", "qwerty123");
    }

    public static UserInfo generateValidUserInfo2() {
        return new UserInfo("petya", "qwerty123");
    }

    public static UserInfo generateInvalidUserInfo() {
        Faker faker = new Faker(new Locale("en"));
        return new UserInfo(faker.name().username(), faker.internet().password());
    }

}
