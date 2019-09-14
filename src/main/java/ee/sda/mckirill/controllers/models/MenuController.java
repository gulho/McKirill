package ee.sda.mckirill.controllers.models;

import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.OrderedMenuItem;

import java.util.List;
import java.util.Optional;

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

    public void saveOrderedMenuItem(OrderedMenuItem orderedMenuItem) {
        session.beginTransaction();
        session.save(orderedMenuItem);
        session.getTransaction().commit();
    }

    public List<MenuItem> getListOfMenuItems() {
        return session.createNamedQuery("get_all_menuItems", MenuItem.class).getResultList();
    }

    public Optional<MenuItem> findById(int menuItemId) {
        return Optional.ofNullable(session.get(MenuItem.class, menuItemId));
    }
}
