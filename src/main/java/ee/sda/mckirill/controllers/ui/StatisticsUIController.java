package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.StatisticsString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StatisticsUIController extends AbstractUIController {
    public StatisticsUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> clientActions = new HashMap<>();
        clientActions.put(1, T -> System.out.println(StatisticsString.TODO));
        clientActions.put(2, T -> System.out.println(StatisticsString.TODO));
        clientActions.put(3, T -> System.out.println(StatisticsString.TODO));

        selectMenuAction(StatisticsString.STATISTICS_MAIN_ACTION, clientActions);
    }
}
