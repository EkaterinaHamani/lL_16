package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;

public class PostmanTest {

    @Test
    public void getRequestWoops(){
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .extract().response();
        response.prettyPrint();
        String headersHost = response.path("headersHost");
        System.out.println(headersHost);
    }
    @Test
    public void testPostRawText(){
        RestAssured.baseURI = "https://postman-echo.com";
        JSONObject requestBody = new JSONObject()
                .put("first name", "Jonn")
                .put("last name", "Black")
                .put("age", 30);
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", containsString("Jonn"))
                .extract().response();
        response.prettyPrint();
        String origin = response.path("origin");
        System.out.println(origin);
}
    @Test
    public void testPutRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        JSONObject requestBody = new JSONObject()
                .put("first name", "Black");
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", containsString("Black"))
                .extract().response();
        response.prettyPrint();
        String origin = response.path("origin");
        System.out.println(origin);
    }
    @Test
    public void testPostFormData(){
        RestAssured.baseURI = "http://postman-echo.com";
        Response response = given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1",equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .extract().response();
        response.prettyPrint();
        String origin = response.path("origin");
        System.out.println(origin);}

}





