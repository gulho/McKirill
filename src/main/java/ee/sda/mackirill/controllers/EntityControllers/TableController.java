package ee.sda.mackirill.controllers.EntityControllers;

import ee.sda.mackirill.entities.Table;

public class TableController extends AbstractEntityController {
    public static void saveTable(Table table) {
        session.beginTransaction();
        session.saveOrUpdate(table);
        session.getTransaction().commit();
    }
}
