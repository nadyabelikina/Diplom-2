package ru.practicum.practicum.steps;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.practicum.constants.Endpoints;
import ru.practicum.practicum.model.User;
import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Создание пользователя через API")
    public ValidatableResponse createUser(User user) {
        return given()
                .body(user)
                .when()
                .post(Endpoints.REGISTER)
                .then();
    }


    @Step("Авторизация пользователя через API")
    public static ValidatableResponse login(User user) {
        return given()
                .body(user)
                .when()
                .post(Endpoints.LOGIN)
                .then();
    }

    @Step("Удаление пользователя через API")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .auth().oauth2(accessToken)
                .when()
                .delete(Endpoints.DELETE_USER)
                .then();
    }
}

