package com.wpx.ipad_server.dao;

import org.json.JSONArray;

import com.wpx.ipad_server.entity.DishMenu;

public interface DishMenuDao {
	public boolean insertDish(DishMenu dishMenu,JSONArray menuInfo);
	
	public int changeServed();
}
