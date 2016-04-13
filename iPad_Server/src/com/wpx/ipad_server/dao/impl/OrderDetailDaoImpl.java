package com.wpx.ipad_server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wpx.ipad_server.dao.OrderDetailDao;
import com.wpx.ipad_server.utils.JdbcUtils;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public JSONArray searchOrderDetail(String orderNum) {
		// TODO Auto-generated method stub

		PreparedStatement pstm=null;
		ResultSet rs=null;
		Connection conn=JdbcUtils.getConnection();
		String sql="select dish.dish_name,num,mark,dish_price from dish left join menu_detail "
				+ "on dish.dish_no=menu_detail.dish_no "
				+ "where menu_no=?";
		JSONArray jsonArray=new JSONArray();
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, orderNum);
			
			rs=pstm.executeQuery();
			while(rs.next()){
				JSONObject jsonObject =new JSONObject();
				String dish_name=rs.getString(1);
				String num=rs.getString(2);
				String mark=rs.getString(3);
				String dish_price=rs.getString(4);
			
				jsonObject.put("dish_name", dish_name);
				jsonObject.put("num",num );
				jsonObject.put("mark",mark );
				jsonObject.put("dish_price",dish_price );
				
				jsonArray.put(jsonObject);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error 菜单详情查找失败");
		}finally{
			JdbcUtils.close(rs, pstm, conn);
		}
		return jsonArray;

	}

}
