package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.entities.MenuItem;

import java.util.List;

public class MenuController extends AbstractEntityController {
    private static MenuController menuController;
    private MenuController() {
    }

    public static MenuController of(){
        if(menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    public void saveMenuItem(MenuItem menuItem) {
        session.beginTransaction();
        session.saveOrUpdate(menuItem);
        session.getTransaction().commit();
    }

    public List<MenuItem> getListOfMenuItems() {
        return session.createNamedQuery("get_all_menuItems", MenuItem.class).getResultList();
    }
}
