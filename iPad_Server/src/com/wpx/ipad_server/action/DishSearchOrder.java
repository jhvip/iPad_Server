package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.wpx.ipad_server.dao.DishSearchDao;
import com.wpx.ipad_server.dao.impl.DishSearchDaoImpl;
/**
 * Servlet implementation class DishSearchOrder
 */
@WebServlet("/DishSearchOrder")
public class DishSearchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishSearchOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");	
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		
		String jsString=request.getParameter("orderList");
		
		JSONArray orderList=new JSONArray(jsString);
		
		DishSearchDao dishSearchDao=new DishSearchDaoImpl();
		try {
		    out = response.getWriter();
		    out.write(dishSearchDao.searchOrder(orderList).toString());
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
		
	}

}
