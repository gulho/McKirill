package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.StatisticsString;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticsUIController extends AbstractUIController {
    public StatisticsUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {

        System.out.println(StatisticsString.ORDERS_COUNT_IN_ALL_TIME
                + getListFromNamedQueryWithParameters("orders_count_older_than_date",
                Object.class, Map.of("timeToOrder", LocalDateTime.MIN)).get(0));

        System.out.println(StatisticsString.ORDER_FOR_TODAY
                + getListFromNamedQueryWithParameters("orders_count_by_day", Object.class, Map.of("timeToOrder", LocalDateTime.now())).get(0));

        System.out.println(StatisticsString.CLIENTS_COUNT
                + getListFromNamedQuery("clients_count", Object.class).get(0));
    }
}
