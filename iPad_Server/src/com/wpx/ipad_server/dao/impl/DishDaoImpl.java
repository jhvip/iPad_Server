package com.wpx.ipad_server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.org.apache.regexp.internal.recompile;
import com.wpx.ipad_server.dao.DishDao;
import com.wpx.ipad_server.entity.Dish;
import com.wpx.ipad_server.utils.JdbcUtils;

public class DishDaoImpl implements DishDao {

	
	@Override
	public JSONArray findDish(String dish_class) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		Connection conn=JdbcUtils.getConnection();
		String sql="select dish.dish_no,dish_name,dish_price,dish_class,dish_discount,dish_pic,dish_detail from dish left join dish_detail on dish.dish_no=dish_detail.dish_no where dish_class=?";
		JSONArray jsonArray =new JSONArray();
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, dish_class);
			rs=pstm.executeQuery();
			while(rs.next()){
				String dish_no=rs.getString(1);
				String dish_name=rs.getString(2);
				String dish_price=rs.getString(3);
				//String dish_class=rs.getString(4);
				String dish_discount=rs.getString(5);
				String dish_pic=rs.getString(6);
				String dish_detail=rs.getString(7);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("dish_no", dish_no);
				jsonObject.put("dish_name", dish_name);
				jsonObject.put("dish_price",dish_price );
				jsonObject.put("dish_class", dish_class);
				jsonObject.put("dish_discount",dish_discount );
				jsonObject.put("dish_pic", dish_pic);
				jsonObject.put("dish_detail", dish_detail);
				jsonArray.put(jsonObject);
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error 查找失败");
		}finally{
			JdbcUtils.close(rs, pstm, conn);
		}
		
		return jsonArray;
	
	}

	@Override
	public JSONArray findComment(String dish_no) {
		// TODO Auto-generated method stub

		PreparedStatement pstm=null;
		ResultSet rs=null;
		Connection conn=JdbcUtils.getConnection();
		String sql="select dish_comment,guestName,time,iconImage from comment where dish_no=?";
		JSONArray jsonArray =new JSONArray();
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, dish_no);
			
			rs=pstm.executeQuery();
			while(rs.next()){
				
				String dish_comment=rs.getString(1);
				String guestName=rs.getString(2);
				String time=rs.getString(3);
				String iconImage=rs.getString(4);
				
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("commentMsg", dish_comment);
				jsonObject.put("guestName", guestName);
				jsonObject.put("time", time);
				jsonObject.put("iconImage", iconImage);
						
				jsonArray.put(jsonObject);
			
				System.out.println(jsonObject);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error 菜单评论查找失败");
		}finally{
			JdbcUtils.close(rs, pstm, conn);
		}
		
		return jsonArray;
	}

}
