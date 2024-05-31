package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.internal.protocols.Input;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class jsonSchemaValidation {
    @Test
    public void getTest(){
        InputStream jsonSchema=getClass().getClassLoader().getResourceAsStream("schema.json");
        RestAssured.baseURI="http://localhost:3000/persons";
       given()
                .get()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema))
                .statusCode(200);
    }
}
