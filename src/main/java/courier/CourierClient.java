package courier;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {

    private final String ROOT = "/courier";
    private final String COURIER = "/courier/{courierId}";
    private final String LOGIN = ROOT + "/login";
    public ValidatableResponse create(Courier courier) {

        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse createFailed(Courier courier) {

        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .statusCode(400);
    }

    public ValidatableResponse login(CourierCredentials creds) {

        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    public ValidatableResponse loginWithoutLogin(CourierCredsWithoutLogin creds) {

        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(400);
    }
    public ValidatableResponse loginWithoutPass(CourierCredsWithoutPassword creds) {

        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(400);
    }
    public ValidatableResponse loginWrongCreds(CourierWrongCreds creds) {

        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(404);
    }


    public void delete(int courierId) {

        getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}