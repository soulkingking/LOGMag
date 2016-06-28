package com.fmeal.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chdw.loc.util.GsonUtil;
import com.chdw.loc.util.StringHandler;
import com.fmeal.dao.UserDao;
import com.fmeal.daoimpl.UserDaoImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class FMealUploadUserIconServlet
 */
@MultipartConfig()
@WebServlet("/FMealUploadUserIconServlet")
public class FMealUploadUserIconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FMealUploadUserIconServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		String filename="";
		String u_id="";
		//		String u_id=request.getParameter("u_id");
		//获得磁盘文件条目工厂。  
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		//获取文件上传需要保存的路径，upload文件夹需存在。  
		String path = request.getServletContext().getRealPath("/upload/UserIcon");  
		String path2="G:\\MyProject\\吃货联盟\\吃货联盟\\LOGMag\\WebContent\\upload\\UserIcon";
		//设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
		factory.setRepository(new File(path));  
		//设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
		factory.setSizeThreshold(1024*1024);  
		//上传处理工具类（高水平API上传处理？）  
		ServletFileUpload upload = new ServletFileUpload(factory);  

		try{  
			//调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
			for(FileItem item:list){  
				//获取表单属性名字。  
				String name = item.getFieldName(); 
				u_id=request.getParameter("u_id");
				//如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
				if(item.isFormField()){  
					//获取用户具体输入的字符串，  
					String value = item.getString();
				}  
				//如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
				else{   
					String value = item.getName();
					if(value == null){
						continue;
					}
					// 取到最后一个反斜杠。
					int start = value.lastIndexOf("\\");
					// 截取上传文件的 字符串名字。+1是去掉反斜杠。
					filename = value.substring(start + 1);
					filename = filename.substring(filename.lastIndexOf("."));
					long time = System.currentTimeMillis();
					Random rand = new Random();
					int num = rand.nextInt(10000);
					filename = time + "" + num + filename;
					//收到写到接收的文件中。  
					OutputStream out = new FileOutputStream(new File(path,filename)); 
					OutputStream out2 = new FileOutputStream(new File(path2,filename));
					InputStream in = item.getInputStream();  
					int length = 0;  
					byte[] buf = new byte[1024];  
					System.out.println("获取文件总量的容量:"+ item.getSize());  
					while((length = in.read(buf))!=-1){  
						out.write(buf,0,length);
						out2.write(buf,0,length);
					}  
					in.close();  
					out.close();
					out2.close();
				}  
			} 
			UserDao userDao=new UserDaoImpl();
			Boolean isSuccess=userDao.setUserIcon(u_id, "/upload/UserIcon/" + filename);
			response.getWriter().print(GsonUtil.toJson(isSuccess));
		}catch(Exception e){  
			e.printStackTrace();  
		}  

	}

}
