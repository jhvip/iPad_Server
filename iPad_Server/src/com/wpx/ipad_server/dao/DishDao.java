package com.wpx.ipad_server.dao;

import org.json.JSONArray;

import com.wpx.ipad_server.entity.Dish;

public interface DishDao {
	public boolean insertDish(Dish dish);
	
	public boolean deleteDish(String dish_no);
	
	public boolean changeDish(Dish dish);
	
	public JSONArray findDish(String dish_class);
	
	public JSONArray findComment(String dish_no);
	
}
