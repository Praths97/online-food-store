package com.foodstore.services.menuitems;

import com.foodstore.model.MenuItem;
import com.foodstore.model.Order;

import java.util.List;

public interface MenuItemService {

    public List<MenuItem> listMenu();  // returns all existing menu items

    public MenuItem getMenu(String id);
}