package com.fmeal.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.SellerDao;
import com.chdw.loc.dao.impl.SellerDaoImpl;
import com.chdw.loc.domain.Seller;
import com.chdw.loc.util.Dist;
import com.chdw.loc.util.GsonUtil;

/**
 * Servlet implementation class FMealFindSpeciesSellerServlet
 */
@WebServlet("/FMealFindSpeciesSellerServlet")
public class FMealFindSpeciesSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		response.setContentType("application/json;charset=utf-8");
		double longitude= Double.parseDouble(request.getParameter("lng"));
		double latitude= Double.parseDouble(request.getParameter("lat"));
		String species=request.getParameter("species");
		SellerDao sellerDao;
		List<Seller> list=new ArrayList<>();
		try {
			sellerDao = new SellerDaoImpl();
			List<Seller> sellers=sellerDao.findAll("where species=+"+"\'"+species+"\'");
			for (Seller seller : sellers) {
				double i=Dist.GetDistance(longitude, latitude, seller.getSeller_longitude(), seller.getSeller_latitude());
				if (i<2000) {
					list.add(seller);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().print(GsonUtil.toJson(list));

	}

}
