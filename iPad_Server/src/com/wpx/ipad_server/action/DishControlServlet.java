package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wpx.ipad_server.dao.DishDao;
import com.wpx.ipad_server.dao.UserDao;
import com.wpx.ipad_server.dao.impl.DishDaoImpl;
import com.wpx.ipad_server.dao.impl.UserDaoImpl;
import com.wpx.ipad_server.entity.Dish;

/**
 * Servlet implementation class DishControlServlet
 */
@WebServlet("/DishControlServlet")
public class DishControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		UserDao userDao=new UserDaoImpl();
		
		String status=request.getParameter("status");
		DishDao dishDao=new DishDaoImpl();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");				
		PrintWriter out = null;				
		JSONObject jsonObject=new JSONObject();
		//验证token是否正确
		String userName=request.getParameter("userName");
		String token =request.getParameter("token");
		if (!userDao.testToken(userName, token)) {
			jsonObject.put("error", "1001");
		    out = response.getWriter();
		    out.write(jsonObject.toString());
		    return;
		}
		
		
		Dish dish=new Dish();
		switch (status) {
		case "insert":
			
			dish.setDish_no(request.getParameter("dish_no"));
			dish.setDish_name(request.getParameter("dish_name"));
			dish.setDish_price(request.getIntHeader("dish_price"));
			dish.setDish_class(request.getIntHeader("dish_class"));
			dish.setDish_discount(request.getIntHeader("dish_discount"));
			
			boolean inSuccess=dishDao.insertDish(dish);
			
			jsonObject.put("control", "insert");
			if (inSuccess) {
				jsonObject.put("status", "success");
			}else {
				jsonObject.put("status", "error");
			}
			try {
			    out = response.getWriter();
			    out.write(jsonObject.toString());
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
			break;
		case "delete":
			String dish_no=request.getParameter("dish_no");
			boolean deSuccess=dishDao.deleteDish(dish_no);
			jsonObject.put("control", "delete");
			if (deSuccess) {
				jsonObject.put("status", "success");		    
			}else {
				jsonObject.put("status", "error");
			}
			try {
			    out = response.getWriter();
			    out.write(jsonObject.toString());
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}		
			break;
		case "change":
			dish.setDish_no(request.getParameter("dish_no"));
			dish.setDish_name(request.getParameter("dish_name"));
			dish.setDish_price(request.getIntHeader("dish_price"));
			dish.setDish_class(request.getIntHeader("dish_class"));
			dish.setDish_discount(request.getIntHeader("dish_discount"));
			
			boolean chSuccess=dishDao.insertDish(dish);
			
			jsonObject.put("control", "change");
			if (chSuccess) {
				jsonObject.put("status", "success");
			}else {
				jsonObject.put("status", "error");
			}
			try {
			    out = response.getWriter();
			    out.write(jsonObject.toString());
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
			break;
		case "find":
			String dish_class =request.getParameter("class");
			try {
			    out = response.getWriter();
			    out.write(dishDao.findDish(dish_class).toString());
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}	
			break;
		default:
			break;
		}

	}

}
