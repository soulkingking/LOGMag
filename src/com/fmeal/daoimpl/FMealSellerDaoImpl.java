package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.domain.SellerData;
import com.chdw.loc.domain.SellerDish;
import com.chdw.loc.domain.SellerDishData;
import com.chdw.loc.domain.SellerMenuType;
import com.chdw.loc.util.DBConnection;
import com.fmeal.dao.FMealSellerDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class FMealSellerDaoImpl implements FMealSellerDao {

	@Override
	public SellerData findSellerData(String s_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SellerData sellerData=new SellerData();
		List<SellerDishData> sellerDishDatas=findSellerDishData(s_id);
		try {
			conn=DBConnection.getConnection();
			String sql="select * from sellerdish_view where s_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				sellerData.setS_id(rs.getString("s_id"));
				sellerData.setSeller_name(rs.getString("seller_name"));
				for (SellerDishData sellerDishData : sellerDishDatas) {
					if (sellerDishData.getSellerMenuType().getSmt_id().equals(rs.getString("smt_id"))) {
						SellerDish sellerDish=new SellerDish();
						sellerDish.setS_id(rs.getString("s_id"));
						sellerDish.setSd_icon(rs.getString("sd_icon"));
						sellerDish.setSd_id(rs.getString("sd_id"));
						sellerDish.setSd_name(rs.getString("sd_name"));
						sellerDish.setSd_price(rs.getInt("sd_price"));
						sellerDish.setSd_saledCount(rs.getInt("sd_salecount"));
						sellerDish.setSeller_name(rs.getString("seller_name"));
						sellerDish.setSmt_id(rs.getString("smt_id"));
						sellerDish.setSmt_name(rs.getString("smt_name"));
						sellerDishData.getSellerDishs().add(sellerDish);
					}
				}
			}
			sellerData.setSellerDishDatas(sellerDishDatas);
			return sellerData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerData;
	}

	@Override
	public List<SellerDishData> findSellerDishData(String s_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SellerDishData> sellerDishDatas=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_sellermenutype where s_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				SellerDishData sellerDishData=new SellerDishData();
				SellerMenuType sellerMenuType=new SellerMenuType();
				sellerMenuType.setS_id(rs.getString("s_id"));
				sellerMenuType.setSmt_id(rs.getString("smt_id"));
				sellerMenuType.setSmt_name(rs.getString("smt_name"));
				sellerDishData.setSellerMenuType(sellerMenuType);
				List<SellerDish> list=new ArrayList<>();
				sellerDishData.setSellerDishs(list);
				sellerDishDatas.add(sellerDishData);
			}
			return sellerDishDatas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerDishDatas;
	}

}
