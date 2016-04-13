package com.wpx.ipad_server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wpx.ipad_server.dao.DishSearchDao;
import com.wpx.ipad_server.utils.JdbcUtils;

public class DishSearchDaoImpl implements DishSearchDao {

	@Override
	public JSONArray searchOrder(JSONArray orderList) {
		// TODO Auto-generated method stub
		PreparedStatement pstm=null;
		ResultSet rs=null;
		Connection conn=JdbcUtils.getConnection();
		String sql="select menutime,served,menu_money from dish_menu where menu_no=?";
		JSONArray jsonArray=new JSONArray();	
		try {
			
			for (int i = 0; i < orderList.length(); i++) {
				JSONObject jsonObject =new JSONObject();
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, orderList.getString(i));		
				rs=pstm.executeQuery();
				
				while(rs.next()){			
					String menutime=rs.getString(1);
					String served=rs.getString(2);
					String menu_money=rs.getString(3);
					
					jsonObject.put("menutime", menutime);
					jsonObject.put("served", served);
					jsonObject.put("menu_money",menu_money );
					jsonObject.put("menu_num", orderList.get(i));
					jsonArray.put(jsonObject);
					
				}
				
			}	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error 订单详情查找失败");
		}finally{
			JdbcUtils.close(rs, pstm, conn);
		}
		return jsonArray;
	}

}
