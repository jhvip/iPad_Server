package com.wpx.ipad_server.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wpx.ipad_server.dao.UserDao;
import com.wpx.ipad_server.dao.impl.UserDaoImpl;
import com.wpx.ipad_server.entity.User;
import com.wpx.ipad_server.utils.Token;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserLoginServlet" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		User user=new User();
//		user.setGuest_name(request.getParameter("userName"));
//		user.setGuest_pw(request.getParameter("password"));
//		
//		Token token=new Token();
//		user.setToken(token.getToken());
//		
//		Date now=new Date();
//		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		user.setGuest_time(dateFormat.format(now));
//		UserDao userDao = new UserDaoImpl();
//		boolean state = userDao.userRegist(user);
//		if (state) {
//			//System.out.println("注册成功");
//			
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("application/json; charset=utf-8");
//			
//			PrintWriter out = null;
//			
//			JSONObject jsonObject=new JSONObject();
//			jsonObject.put("name", user.getGuest_name());
//			jsonObject.put("token", user.getToken());
//			
//			try {
//			    out = response.getWriter();
//			    out.write(jsonObject.toString());
//			} catch (IOException e) {
//			    e.printStackTrace();
//			} finally {
//			    if (out != null) {
//			        out.close();
//			    }
//			}
//			
//		} else {
//			//System.out.println("注册失败");
//		}
		
		
		String userName=request.getParameter("userName");
		String pw=request.getParameter("password");
		UserDao userDaoLogin=new UserDaoImpl();
		JSONObject loginInfo=userDaoLogin.userLogin(userName, pw);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = null;
		out=response.getWriter();
		out.write(loginInfo.toString());
		
	}
		
		

}
