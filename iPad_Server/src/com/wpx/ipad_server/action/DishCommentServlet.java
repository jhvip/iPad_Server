package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpx.ipad_server.dao.DishDao;
import com.wpx.ipad_server.dao.DishDetailDao;
import com.wpx.ipad_server.dao.impl.DishDaoImpl;
import com.wpx.ipad_server.dao.impl.DishDetailImpl;

/**
 * Servlet implementation class DishCommentServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DishCommentServlet" })
public class DishCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");				
		PrintWriter out = null;	
		String dish_no =request.getParameter("dish_no");
		
		DishDao dishCommentDao=new DishDaoImpl();
		
		try {
		    out = response.getWriter();
		    out.write(dishCommentDao.findComment(dish_no).toString());
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
