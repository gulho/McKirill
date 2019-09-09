import ee.sda.mackirill.entities.*;
import ee.sda.mackirill.enums.MenuItemsTypeEnum;
import ee.sda.mackirill.enums.OrderStatusEnum;
import ee.sda.mackirill.enums.PaymentTypeEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Disabled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Disabled
public class Save_order {
    public static void main(String[] args) {
        Order order = new Order();

        //Create person
        PersonType clientType = new PersonType(PersonTypeEnum.CLIENT);
        Person client = new Person("client name", "email@asdf.com", "pass", "37235235", clientType);
        clientType.setPerson(client);

        //Create table
        Table table = new Table(5, false);
        table.getOrders().add(order);

        //Create payment type
        PaymentType cash = new PaymentType(PaymentTypeEnum.CASH);
        cash.setOrder(order);
        //Create order status
        OrderStatus open_order = new OrderStatus(OrderStatusEnum.OPEN);
        //Create order menu items
        Item beer_item = new Item("Beer", MenuItemsTypeEnum.BEER);
        Menu beer = new Menu(beer_item, new BigDecimal(30));
        beer_item.setMenu(beer);
        OrderedMenuItem items = new OrderedMenuItem(beer, 5, beer.getPrice().multiply(new BigDecimal(5)));
        items.setOrder(order);
        //Create review
        Review review = new Review(client, 5, "Some long review text", LocalDateTime.now());
        //Create waiter tip (in this example tip go to client this is wrong)
        WaiterTip waiterTip = new WaiterTip(client, new BigDecimal(100), order);
        //Create holliday
        Holiday holiday = new Holiday(LocalDate.now(), LocalTime.now().minusHours(1), LocalTime.now().plusHours(2));

        //Set orders person
        order.setPerson(client);
        //Set peoples count
        order.setPeoples(5);
        //Set time to order
        order.setTimeToOrder(LocalDateTime.now());
        //Set table
        order.setTable(table);
        //Set total sum
        order.setTotalSum(new BigDecimal(1000));
        //Set payment type
        order.setPaymentType(cash);
        //Set order status
        order.setStatus(open_order);
        //Set create date
        order.setCreateDate(LocalDateTime.now());
        //Set update date
        order.setUpdateDate(LocalDateTime.now());
        //Set menu items
        order.getOrderedMenuItems().add(items);

        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(order);
            session.saveOrUpdate(open_order);
            session.saveOrUpdate(cash);
            session.saveOrUpdate(table);
            session.saveOrUpdate(clientType);
            session.saveOrUpdate(client);
            session.saveOrUpdate(items);
            session.saveOrUpdate(beer);
            session.saveOrUpdate(beer_item);
            session.saveOrUpdate(review);
            session.saveOrUpdate(waiterTip);
            session.saveOrUpdate(holiday);

            session.getTransaction().commit();
        }
    }
}
