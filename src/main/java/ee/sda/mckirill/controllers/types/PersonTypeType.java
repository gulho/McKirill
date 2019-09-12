package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.entity.AbstractEntityController;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;

public class PersonTypeType extends AbstractEntityController {
    private static PersonType manager;
    private static PersonType client;
    private static PersonType waiter;

    public static PersonType getManager() {
        if (manager == null) {
            manager = session.byNaturalId(PersonType.class).using("type", PersonTypeEnum.MANAGER).load();
        }
        return manager;
    }

    public static PersonType getClient() {
        if (client == null) {
            client = session.byNaturalId(PersonType.class).using("type", PersonTypeEnum.CLIENT).load();
        }
        return client;
    }

    public static PersonType getWaiter() {
        if (waiter == null) {
            waiter = session.byNaturalId(PersonType.class).using("type", PersonTypeEnum.WAITER).load();
        }
        return waiter;
    }
}
