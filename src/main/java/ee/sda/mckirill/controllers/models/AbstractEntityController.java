package ee.sda.mckirill.controllers.models;

import ee.sda.mckirill.controllers.ApplicationContext;
import org.hibernate.Session;

public abstract class AbstractEntityController {
    protected static Session session = ApplicationContext.getSession();

}
