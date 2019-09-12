package ee.sda.mackirill.controllers.entity;

import ee.sda.mackirill.entities.MenuItem;

public class MenuController extends AbstractEntityController {
    public static void saveMenuItem(MenuItem menuItem) {
        session.beginTransaction();
        session.saveOrUpdate(menuItem);
        session.getTransaction().commit();
    }
}
