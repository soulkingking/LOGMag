package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.domain.DeliveryAddress;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.fmeal.dao.FMealDeliveryAddressDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FMealDeliveryAddressDaoImpl implements FMealDeliveryAddressDao {

	@Override
	public List<DeliveryAddress> findUserAddress(String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DeliveryAddress> deliveryAddresses=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from deliveryaddress_view where u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, u_id);
			rs=pst.executeQuery();
			while(rs.next()){
				DeliveryAddress deliveryAddress=new DeliveryAddress();
				deliveryAddress.setDa_id(rs.getString("da_id"));
				deliveryAddress.setDa_name(rs.getString("da_name"));
				deliveryAddress.setDa_phone(rs.getString("da_phone"));
				deliveryAddress.setDa_address(rs.getString("da_address"));
				deliveryAddress.setU_id(rs.getString("u_id"));
				deliveryAddress.setUser_alias(rs.getString("user_alias"));
				deliveryAddresses.add(deliveryAddress);
			}
			return deliveryAddresses;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}

		return deliveryAddresses;
	}

	@Override
	public List<DeliveryAddress> addDeliveryAddress(DeliveryAddress deliveryAddress) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DeliveryAddress> deliveryAddresses=new ArrayList<>();
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_deliveryaddress values(?,?,?,?,?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,deliveryAddress.getDa_id());
			pst.setString(2, deliveryAddress.getDa_name());
			pst.setString(3, deliveryAddress.getDa_phone());
			pst.setString(4,deliveryAddress.getDa_address());
			pst.setString(5,deliveryAddress.getU_id());
			i=pst.executeUpdate();
			if (i!=0) {
				deliveryAddresses=findUserAddress(deliveryAddress.getU_id());
				return deliveryAddresses;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}

		return deliveryAddresses;
	}

	@Override
	public List<DeliveryAddress> deleteUserAddress(String da_id,String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DeliveryAddress> deliveryAddresses=new ArrayList<>();
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="delete from tb_deliveryaddress where da_id =?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,da_id);
			i=pst.executeUpdate();
			if (i!=0) {
				deliveryAddresses=findUserAddress(u_id);
				return deliveryAddresses;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}

		return deliveryAddresses;
	}

	@Override
	public List<DeliveryAddress> updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DeliveryAddress> deliveryAddresses=new ArrayList<>();
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="update tb_deliveryaddress set da_name=?,da_phone=?,da_address=? where da_id=?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,deliveryAddress.getDa_name());
			pst.setString(2, deliveryAddress.getDa_phone());
			pst.setString(3, deliveryAddress.getDa_address());
			System.out.println(deliveryAddress.getDa_id());
			pst.setString(4,deliveryAddress.getDa_id());
			i=pst.executeUpdate();
			if (i!=0) {
				deliveryAddresses=findUserAddress(deliveryAddress.getU_id());
				return deliveryAddresses;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}

		return deliveryAddresses;
	}

}
