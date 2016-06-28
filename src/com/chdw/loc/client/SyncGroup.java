package com.chdw.loc.client;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chdw.loc.bean.FormatType;
import com.chdw.loc.bean.GroupInfo;
import com.chdw.loc.bean.SdkHttpResult;
import com.chdw.loc.util.ApiHttpClient;

/**
 * Servlet implementation class SyncGroup
 */
@WebServlet("/SyncGroup")
public class SyncGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String key = "bmdehs6pdv4ms";
	private static String secret = "ykmTx3g7HDNa";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SyncGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String u_id = request.getParameter("r_id");
//		String[] frIds = request.getParameterValues("frIds");
//		String[] frName = request.getParameterValues("frName");
		
		SdkHttpResult result = null;
		List<GroupInfo> groups = new ArrayList<GroupInfo>();
//		for (int i = 0; i < frIds.length; i++) {
//			groups.add(new GroupInfo(frIds[i], frName[i]));
//		}
		groups.add(new GroupInfo("11", "群聊"));
//		groups.add(new GroupInfo("id2", "name2"));
//		groups.add(new GroupInfo("id3", "name3"));
		result = ApiHttpClient.syncGroup(key, secret, u_id, groups,
				FormatType.json);
		System.out.println("syncGroup=" + result);

	}

}
