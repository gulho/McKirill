package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.entities.Table;

public class TableController extends AbstractEntityController {
    public static void saveTable(Table table) {
        session.beginTransaction();
        session.saveOrUpdate(table);
        session.getTransaction().commit();
    }
}
