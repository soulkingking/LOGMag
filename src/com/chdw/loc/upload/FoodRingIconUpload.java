package com.chdw.loc.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chdw.loc.dao.impl.FoodRingDaoImpl;
import com.chdw.loc.domain.FoodRing;
import com.chdw.loc.util.StringHandler;


/**
 * Servlet implementation class UserIconUpload
 */
@WebServlet("/FoodRingIconUpload")
@MultipartConfig()
public class FoodRingIconUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodRingIconUpload() {
        super();
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
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DiskFileItemFactory factory = new DiskFileItemFactory() ;
		ServletFileUpload upload = new ServletFileUpload(factory) ;
		//upload.setFileSizeMax(3 * 1024 * 1024) ;	// 只能上传3M
		List<FileItem> items = upload.parseRequest(request) ; // 接收全部内容
		String frId = null;
		String ext = null;
		String dir=getServletContext().getRealPath("/upload/FoodRingIcon");
		String picName = StringHandler.createSerialId(System.currentTimeMillis());
		for (FileItem item : items) {
			if (item.isFormField()) {
				//此时这个item项中的内容是普通的文本
				frId=item.getString();//取得参数内容
			} else {
				//这是对非文本内容的处理
				//图片要保存到/WebContent/UploadFiles目录下
				String filename=item.getName();
				//取得文件，不要原来的路径
				int index=filename.lastIndexOf(".");
				ext=filename.substring(index);
				//这个在路径下构建上传文件的对象
				File savefile=new java.io.File(dir, picName + ext);
				item.write(savefile);//写入文件,实现上传
			}
		}
		
		FoodRingDaoImpl foodRingDaoImpl = new FoodRingDaoImpl();
		FoodRing fr = foodRingDaoImpl.findAll("where fr_id='"+ frId +"'").get(0);
		fr.setFr_icon("/upload/FoodRingIcon/" + picName + ext);
		
		int result = foodRingDaoImpl.update(fr);
		if (result == 0) {
			request.setAttribute("message", "修改失败");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "修改成功");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		}
		
//		Part part = request.getPart("pic");
//		
//		String name = part.getSubmittedFileName();	//获取上传文件名
//		String suffix = name.substring(name.lastIndexOf("."),name.length());
//		String path = "/upload/UserIcon/"+ "123456" + suffix;
//		part.write(request.getContextPath() + path);
		
//		ui.setUser_icon(path);
//		
//		UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
//		int result = userInfoDaoImpl.add(ui);
//		if (result == 0) {
//			request.setAttribute("message", "添加失败");
//			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
//			return ;
//		} else if (result == 1) {
//			request.setAttribute("message", "添加成功");
//			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
//			return ;
//		}
	}

}
