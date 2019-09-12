package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.controllers.ApplicationContext;
import org.hibernate.Session;

public abstract class AbstractEntityController {
    protected static Session session = ApplicationContext.getSession();

}
