package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.models.AbstractEntityController;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;

import java.util.Optional;

public class PersonTypeType extends AbstractEntityController {
    private static PersonTypeType personTypeType;

    private PersonType manager;
    private PersonType client;
    private PersonType waiter;

    private PersonTypeType() {
    }

    public static PersonTypeType of() {
        if (personTypeType == null) {
            personTypeType = new PersonTypeType();
        }
        return personTypeType;
    }

    public PersonType getManager() {
        if (manager == null) {
            manager = getByType(PersonTypeEnum.MANAGER);
        }
        return manager;
    }

    public PersonType getClient() {
        if (client == null) {
            client = getByType(PersonTypeEnum.CLIENT);
        }
        return client;
    }

    public PersonType getWaiter() {
        if (waiter == null) {
            waiter = getByType(PersonTypeEnum.WAITER);
        }
        return waiter;
    }

    private PersonType getByType(PersonTypeEnum personTypeEnum) {
        Optional<PersonType> personType = session.byNaturalId(PersonType.class).using("type", personTypeEnum).loadOptional();
        if (personType.isEmpty()) {
            save(new PersonType(personTypeEnum));
            personType = Optional.of(getByType(personTypeEnum));
        }
        return personType.get();
    }

    private void save(PersonType personType) {
        session.beginTransaction();
        session.saveOrUpdate(personType);
        session.getTransaction().commit();
    }
}
