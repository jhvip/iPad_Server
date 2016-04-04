package com.wpx.ipad_server.dao;

import org.json.JSONArray;

import com.wpx.ipad_server.entity.Dish;

public interface DishDao {
	
	public JSONArray findDish(String dish_class);
	
	public JSONArray findComment(String dish_no);
	
}
