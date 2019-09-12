package ee.sda.mackirill.controllers.EntityControllers;

import ee.sda.mackirill.controllers.ApplicationContext;
import org.hibernate.Session;

public abstract class AbstractEntityController {
    protected static Session session = ApplicationContext.getSession();

}
