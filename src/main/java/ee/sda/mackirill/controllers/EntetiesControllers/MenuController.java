package ee.sda.mackirill.controllers.EntetiesControllers;

import ee.sda.mackirill.entities.MenuItem;
import ee.sda.mackirill.strings.BaseString;

public class MenuController extends AbstractEntitiController {
    public static void saveMenuItem(MenuItem menuItem) {
        session.beginTransaction();
        session.saveOrUpdate(menuItem);
        session.getTransaction().commit();
    }
}
