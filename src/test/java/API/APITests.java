package API;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.given;

public class APITests {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    public void shouldBeApprovedAfterPaymentWithCard() {
        String status = given()
                .spec(requestSpec)
                .body(DataForAPI.getBodyForApiWithValidCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        assertEquals("APPROVED", status);
    }

    @Test
    public void shouldBeDeclinedAfterPaymentWithCard() {
        String status = given()
                .spec(requestSpec)
                .body(DataForAPI.getBodyForApiWithDeclinedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        assertEquals("DECLINED", status);
    }


    @Test
    public void shouldBeApprovedAfterPaymentWithCredit() {
        String status = given()
                .spec(requestSpec)
                .body(DataForAPI.getBodyForApiWithValidCard())
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        assertEquals("APPROVED", status);
    }

    @Test
    public void shouldBeDeclinedAfterPaymentWithCredit() {
        String status = given()
                .spec(requestSpec)
                .body(DataForAPI.getBodyForApiWithDeclinedCard())
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        assertEquals("DECLINED", status);
    }
}
