package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.domain.Seller;
import com.chdw.loc.domain.SellerCollection;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.fmeal.dao.FMealCollectionDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FMealCollectionDaoImpl implements FMealCollectionDao {

	@Override
	public SellerCollection findCollection(String s_id, String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SellerCollection sellerCollection=null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from sellercollection_view where s_id=? AND u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, s_id);
			pst.setString(2, u_id);
			rs=pst.executeQuery();
			if (rs.next()) {
				sellerCollection=new SellerCollection();
				sellerCollection.setS_id(rs.getString("s_id"));
				sellerCollection.setScoll_id(rs.getString("scoll_id"));
				sellerCollection.setU_id(rs.getString("u_id"));
				sellerCollection.setSeller_name(rs.getString("seller_name"));
				sellerCollection.setUser_alias(rs.getString("user_alias"));
				return sellerCollection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerCollection;
	}

	@Override
	public SellerCollection addCollection(String s_id, String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SellerCollection sellerCollection=null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_sellercollection values(?, ?, ?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
			pst.setString(2, s_id);
			pst.setString(3, u_id);
			i=pst.executeUpdate();
			if (i!=0) {
				sellerCollection=findCollection(s_id, u_id);
				return sellerCollection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerCollection;
	}

	@Override
	public SellerCollection deleteCollection(String s_id, String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SellerCollection sellerCollection=null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="delete from tb_sellercollection where s_id=? AND u_id=?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, s_id);
			pst.setString(2, u_id);
			i=pst.executeUpdate();
			if (i!=0) {
				sellerCollection=findCollection(s_id, u_id);
				return sellerCollection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerCollection;
	}

	@Override
	public List<Seller> findUserCollection(String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Seller> sellers=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from usercollection where u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, u_id);
			rs=pst.executeQuery();
			while (rs.next()) {
				Seller seller=new Seller();
				seller.setS_id(rs.getString("s_id"));
				seller.setSeller_contact(rs.getString("seller_contact"));
				seller.setSeller_degree(rs.getInt("seller_degree"));
				seller.setSeller_deliverytime(rs.getInt("seller_deliverytime"));
				seller.setSeller_df(rs.getInt("seller_df"));
				seller.setSeller_endtime(String.valueOf(rs.getTime("seller_endtime")));
				seller.setSeller_icon(rs.getString("seller_icon"));
				seller.setSeller_intro(rs.getString("seller_intro"));
				seller.setSeller_latitude(rs.getDouble("seller_latitude"));
				seller.setSeller_longitude(rs.getDouble("seller_longitude"));
				seller.setSeller_name(rs.getString("seller_name"));
				seller.setSeller_notice(rs.getString("seller_notice"));
				seller.setSeller_sendprice(rs.getInt("seller_sendprice"));
				seller.setSeller_starttime(String.valueOf(rs.getTime("seller_starttime")));
				seller.setSeller_status(rs.getBoolean("seller_status"));
				sellers.add(seller);
			}
			return sellers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellers;
	}

}
