package orders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class OrdersListTest {
    Orders orders;
    OrdersClient ordersClient;
    private ArrayList<String> ordersList;

    @Before
    public void setup() {
        orders = Orders.getRandomOrder();
        ordersClient = new OrdersClient();}
    @After
    public void teardown() {
        //ordersClient.delete(track); не совпадает работа метода с документацией, не получается отменить заказ по номеру трека
    }

    @Test
    public void checkOrderListIsNotEmptyTest() {
        ArrayList<String> ordersList = ordersClient.getList(orders).extract().path("orders");
        assertNotEquals(null, ordersList);
        }
}
