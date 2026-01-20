package ru.yandex.practicum.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import org.junit.Before;
import ru.practicum.practicum.constants.Endpoints;

public class BaseTest {

    @Before
    public void startUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Endpoints.HOST)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.config = RestAssured
                .config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
