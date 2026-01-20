package ru.practicum.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.practicum.constants.Endpoints;
import ru.practicum.practicum.model.Order;


import static io.restassured.RestAssured.given;
public class OrderSteps {

    @Step("Создание заказа без авторизации через API")
    public ValidatableResponse orderWithoutAuth(Order order) {
        return given()
                .body(order)
                .post(Endpoints.ORDER)
                .then();

    }
    @Step("Создание заказа с авторизацией через API")
    public ValidatableResponse orderWithAuth(String accessToken, Order order) {
        return given()
                .body(order)
                .auth().oauth2(accessToken)
                .post(Endpoints.ORDER)
                .then();
    }

}

