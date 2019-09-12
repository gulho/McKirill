package ee.sda.mackirill.controllers.EntetiesControllers;

import ee.sda.mackirill.entities.Table;

public class TableController extends AbstractEntitiController {
    public static void saveTable(Table table) {
        session.beginTransaction();
        session.saveOrUpdate(table);
        session.getTransaction().commit();
    }
}
