package ee.sda.mackirill.controllers.EntetiesControllers;

import ee.sda.mackirill.controllers.ApplicationContext;
import org.hibernate.Session;

public class AbstractEntitiController {
    protected static Session session = ApplicationContext.getSession();


}
