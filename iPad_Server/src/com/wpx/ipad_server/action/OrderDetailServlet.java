package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wpx.ipad_server.dao.OrderDetailDao;

import com.wpx.ipad_server.dao.impl.OrderDetailDaoImpl;

/**
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
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
		
		String orderNum=request.getParameter("orderNum");
		
		
		OrderDetailDao orderDetailDao=new OrderDetailDaoImpl();
		
		try {
		    out = response.getWriter();
		    out.write(orderDetailDao.searchOrderDetail(orderNum).toString());
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
		
	}

}
