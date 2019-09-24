package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Order;
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
/*        Map<Integer, Consumer> clientActions = new HashMap<>();
        clientActions.put(1, T -> System.out.println(StatisticsString.TODO));
        clientActions.put(2, T -> System.out.println(StatisticsString.TODO));
        clientActions.put(3, T -> System.out.println(StatisticsString.TODO));

        selectMenuAction(StatisticsString.STATISTICS_MAIN_ACTION, clientActions);*/

        System.out.println(StatisticsString.ORDERS_COUNT_IN_ALL_TIME
                + getListFromNamedQueryWithParameters("orders_count_by_date",
                Object.class, Map.of("timeToOrder", LocalDateTime.MIN)).get(0));
    }

    private void ordersStatistics() {

    }
}
