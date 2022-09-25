package courier;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LoginCourierTest {
    Courier courier;
    CourierClient courierClient;
    private int courierId;
    @Before
    public void setup() {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }
    @Test
    public void successLoginTest() {
        boolean isOk = courierClient.create(courier).extract().path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .extract().path("id");
        assertTrue(isOk);
        assertNotEquals(0, courierId);
        courierClient.delete(courierId);
    }
    @Test
    public void loginWithoutLoginTest() {
        CourierCredsWithoutLogin creds = CourierCredsWithoutLogin.from(courier);
        String message = courierClient.loginWithoutLogin(creds).extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    public void loginWithoutPasswordTest() {
        CourierCredsWithoutPassword creds = CourierCredsWithoutPassword.from(courier);
        String message = courierClient.loginWithoutPass(creds).extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    public void loginWrongCredsTest() {
        CourierWrongCreds creds = CourierWrongCreds.from(courier);
        String message = courierClient.loginWrongCreds(creds).extract().path("message");
        assertEquals("Учетная запись не найдена", message);
    }

}
//public void teardown() {                }
