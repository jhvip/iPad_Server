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
		
		DishDao dishDao=new DishDaoImpl();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");				
		PrintWriter out = null;				
		
		
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

	}

}
