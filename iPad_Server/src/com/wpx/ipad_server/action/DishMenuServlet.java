package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wpx.ipad_server.dao.DishDao;
import com.wpx.ipad_server.dao.DishMenuDao;
import com.wpx.ipad_server.dao.impl.DishDaoImpl;
import com.wpx.ipad_server.dao.impl.DishMenuDaoImpl;
import com.wpx.ipad_server.entity.Dish;
import com.wpx.ipad_server.entity.DishMenu;
import com.wpx.ipad_server.utils.Token;

/**
 * Servlet implementation class DishMenuServlet
 */
@WebServlet("/DishMenuServlet")
public class DishMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static long orderNum = 0l;  
    private static String date ;
    public static synchronized String getOrderNo() {  
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());  
        if(date==null||!date.equals(str)){  
            date = str;  
            orderNum  = 0l;  
        }  
        orderNum ++;  
        long orderNo = Long.parseLong((date)) * 100;  
        orderNo += orderNum;;  
        return orderNo+"";  
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DishMenuDao dishMenuDao=new DishMenuDaoImpl();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");	
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = null;				
		JSONObject jsonObject=new JSONObject();
		
		
		DishMenu dishMenu=new DishMenu();
		dishMenu.setMenu_no(getOrderNo());
		dishMenu.setGuest_id(0);
		dishMenu.setRoom_no("0");
		dishMenu.setTable_no(request.getParameter("table_no"));
		Date now=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dishMenu.setMenuTime(dateFormat.format(now));
		String jsString=request.getParameter("info");
		
		JSONArray menuInfo=new JSONArray(jsString);
		
		boolean inSuccess=dishMenuDao.insertDish(dishMenu,menuInfo);
		
		if (inSuccess) {
			jsonObject.put("status", "success");
			jsonObject.put("message", dishMenu.getMenu_no());
		}else {
			jsonObject.put("status", "error");
			jsonObject.put("message", "提交订单失败");
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
	}
	
}


