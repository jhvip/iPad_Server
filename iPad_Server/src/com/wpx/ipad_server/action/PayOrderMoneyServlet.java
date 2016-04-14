package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.wpx.ipad_server.dao.PayMoneyDao;
import com.wpx.ipad_server.dao.impl.PayMoneyDaoImpl;

/**
 * Servlet implementation class PayOrderMoneyServlet
 */
@WebServlet("/PayOrderMoneyServlet")
public class PayOrderMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayOrderMoneyServlet() {
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
		
		JSONObject jsonStatus=new JSONObject();
		PayMoneyDao payMoneyDao=new PayMoneyDaoImpl();
		
		if (payMoneyDao.payMoney(orderNum)) {
			jsonStatus.put("status", "success");
		}else {
			jsonStatus.put("status", "error");
		}
		
		
		try {
		    out = response.getWriter();
		    out.write(jsonStatus.toString());
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
	}

}
