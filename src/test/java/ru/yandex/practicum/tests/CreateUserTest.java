package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.practicum.generate.UserDataFactory;
import ru.practicum.practicum.steps.UserSteps;
import ru.practicum.practicum.model.User;

import static org.hamcrest.CoreMatchers.*;


/**
 * 1. Создание пользователя:
 * * создать уникального пользователя;,
 * * создать пользователя, который уже зарегистрирован;
 * * создать пользователя и не заполнить одно из обязательных полей.
 */
public class CreateUserTest  extends BaseTest {

    private UserSteps userSteps = new UserSteps();
    private User user;

    @Before
    public void setUp() {

        user = new User();
        user
                .setEmail(UserDataFactory.generateEmail())
                .setPassword(UserDataFactory.generateValidPassword())
                .setName(UserDataFactory.generateName());
    }

    @Test
    @DisplayName("Создание нового уникального пользователя. Ответ 200")
    @Description("Post запрос на ручку /api/auth/register")
    @Step("Создание пользователя")
    public void createUniqueUserTest() {

                userSteps
                .createUser(user)
                .statusCode(200).and().body("success", equalTo(true))
                .body("accessToken", startsWith("Bearer "))
                .body("refreshToken", notNullValue());


    }

    @Test
    @DisplayName("Создать пользователя, который уже зарегистрирован. Ответ 403")
    @Description("Post запрос на ручку /api/auth/register")
    @Step("Создание пользователя")
    public void createRegisteredUserTest() {
        user
                .setEmail("test-data@yandex.ru");
        userSteps
                .createUser(user)
                .statusCode(403)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Создать пользователя без email. Ответ 403")
    @Description("Post запрос на ручку /api/auth/register")
    @Step("Создание пользователя")

    public void createUserWithoutLoginTest() {
        user
                .setEmail("");
        userSteps
                .createUser(user)
                .statusCode(403)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создать пользователя без password. Ответ 403")
    @Description("Post запрос на ручку /api/auth/register")
    @Step("Создание пользователя")
    public void createUserWithoutPasswordTest() {
        user
                .setPassword("");
        userSteps
                .createUser(user)
                .statusCode(403)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создать пользователя без Name. Ответ 403")
    @Description("Post запрос на ручку /api/auth/register")
    @Step("Создание пользователя")
    public void createUserWithoutNameTest() {
        user
                .setName("");
        userSteps
                .createUser(user)
                .statusCode(403)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"));

    }
    @After
    public void tearDown() {
        if (!user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {
            String accessTokenWithBearer = UserSteps
                    .login(user)
                    .extract().body().path("accessToken");
            if (accessTokenWithBearer != null) {
                String accessToken = accessTokenWithBearer.replace("Bearer ", "");
                userSteps.deleteUser(accessToken);
            }

        }
    }
}
