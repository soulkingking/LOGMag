package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.FormatType;
import com.chdw.loc.bean.GroupInfo;
import com.chdw.loc.bean.SdkHttpResult;
import com.chdw.loc.dao.impl.FoodRingDaoImpl;
import com.chdw.loc.domain.FoodRing;
import com.chdw.loc.util.ApiHttpClient;
import com.chdw.loc.util.ParamUtils;
import com.chdw.loc.util.StringHandler;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/CreateFoodRingServlet")
public class CreateFoodRingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String key = "3argexb6rnk1e";
	private static String secret = "AgOexcuEA03Q";
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public CreateFoodRingServlet() throws Exception {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		
		Map<String, String> map = ParamUtils.getParams(request, "/upload/FoodRingIcon/");
		String u_id = map.get("u_id");
		String fr_name = map.get("fr_name");
		String fileName = map.get("image");
		
		FoodRingDaoImpl foodRingDaoImpl = new FoodRingDaoImpl();
		FoodRing fr = new FoodRing();
		String id = StringHandler.createSerialId(System.currentTimeMillis());
		fr.setFr_id(id);
		fr.setFr_icon("/upload/FoodRingIcon/" + fileName);
		fr.setFr_name(fr_name);
		fr.setFr_visible(true);
		fr.setU_id(u_id);
		
		int result = foodRingDaoImpl.add(fr);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			SdkHttpResult sdkHttpResult = null;
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			groups.add(new GroupInfo(id, "群聊"));
			sdkHttpResult = ApiHttpClient.syncGroup(key, secret, u_id, groups, FormatType.json);
			out.print("1");
			return ;
		}
		
	}
	
}
