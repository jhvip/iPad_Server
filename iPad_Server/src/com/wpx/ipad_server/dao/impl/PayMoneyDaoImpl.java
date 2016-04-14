package com.wpx.ipad_server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wpx.ipad_server.dao.PayMoneyDao;
import com.wpx.ipad_server.utils.JdbcUtils;

public class PayMoneyDaoImpl implements PayMoneyDao {

	@Override
	public boolean payMoney(String orderNum) {
		// TODO Auto-generated method stub
		String sql = "update dish_menu set served = 1 where menu_no= ?";
		Connection connection = JdbcUtils.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNum);
			
			preparedStatement.executeUpdate();
			System.out.println("支付成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("error 支付失败");
				return false;
			} finally {
				JdbcUtils.close(null, preparedStatement, connection);
			}	
		return true;
	}

}
