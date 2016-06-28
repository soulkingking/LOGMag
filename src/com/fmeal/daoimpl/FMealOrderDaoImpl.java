package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.domain.BuyOrder;
import com.chdw.loc.domain.Car;
import com.chdw.loc.domain.DeliveryAddress;
import com.chdw.loc.domain.Seller;
import com.chdw.loc.domain.SellerDish;
import com.chdw.loc.domain.TakeoutOrder;
import com.chdw.loc.domain.TakeoutOrderStatus;
import com.chdw.loc.util.DBConnection;
import com.fmeal.dao.FMealOrderDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

public class FMealOrderDaoImpl implements FMealOrderDao {

	@Override
	public boolean addTakeOrder(TakeoutOrder takeoutOrder) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_takeoutorder values(?,?,?,?,?,?,?,?,?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, takeoutOrder.getTo_id());
			pst.setInt(2, takeoutOrder.getTo_deliveryFee());
			pst.setInt(3, takeoutOrder.getTo_boxFee());
			pst.setString(4, takeoutOrder.getTo_extra());
			pst.setString(5, takeoutOrder.getTo_name());
			pst.setString(6, takeoutOrder.getTo_phone());
			pst.setString(7, takeoutOrder.getTo_address());
			pst.setString(8, takeoutOrder.getS_id());
			pst.setString(9, takeoutOrder.getU_id());
			i=pst.executeUpdate();
			if (i!=0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return false;
	}

	@Override
	public boolean addTakeOrderStatus(TakeoutOrderStatus takeoutOrderStatus) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_takeoutorderstatus values(?,?,?,?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,takeoutOrderStatus.getTos_id());
			pst.setString(2,takeoutOrderStatus.getTos_status());
			pst.setString(3, takeoutOrderStatus.getTos_time());
			pst.setString(4, takeoutOrderStatus.getTo_id());
			i=pst.executeUpdate();
			if (i!=0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return false;
	}

	@Override
	public boolean addTakeOrderInfo(String to_id,Car car) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_takeoutorderinfo(to_id,sd_id,num) values(?,?,?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,to_id);
			pst.setString(2,car.getSellerDish().getSd_id());
			pst.setInt(3,car.getNum());
			i=pst.executeUpdate();
			if (i!=0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return false;
	}

	@Override
	public List<BuyOrder> findAllBuyOrder(String u_id) {
		List<BuyOrder> orders=new ArrayList<>();
		List<String> list=findUserTakeOrder(u_id);
		for (String string : list) {
			orders.add(findBuyOrder(string, u_id));
		}
		return orders;
	}

	@Override
	public BuyOrder findBuyOrder(String to_id,String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BuyOrder order=new BuyOrder();
		List<Car> cars=new ArrayList<>();
		TakeoutOrder takeoutOrder=new TakeoutOrder();
		TakeoutOrderStatus takeoutOrderStatus=new TakeoutOrderStatus();
		Seller seller=new Seller();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from buyorder where to_id=? AND u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, to_id);
			pst.setString(2, u_id);
			rs=pst.executeQuery();
			while(rs.next()){
				takeoutOrder.setTo_id(rs.getString("to_id"));
				takeoutOrder.setSeller_name(rs.getString("seller_name"));
				takeoutOrder.setTo_address(rs.getString("to_address"));
				takeoutOrder.setTo_boxFee(rs.getInt("to_bf"));
				takeoutOrder.setTo_deliveryFee(rs.getInt("to_df"));
				takeoutOrder.setTo_extra(rs.getString("to_extra"));
				takeoutOrder.setTo_name(rs.getString("to_name"));
				takeoutOrder.setTo_phone(rs.getString("to_phone"));
				takeoutOrder.setU_id(rs.getString("u_id"));
				takeoutOrder.setUser_alias(rs.getString("user_alias"));
				takeoutOrderStatus.setTo_id(rs.getString("to_id"));
				takeoutOrderStatus.setTos_id(rs.getString("tos_id"));
				takeoutOrderStatus.setTos_status(rs.getString("tos_status"));
				String tos_time=rs.getString("tos_time");
				takeoutOrderStatus.setTos_time(tos_time.substring(0, tos_time.lastIndexOf(".")));
				takeoutOrderStatus.setU_id(rs.getString("u_id"));
				takeoutOrderStatus.setUser_alias(rs.getString("user_alias"));
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
				Car car=new Car();
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
				car.setSellerDish(sellerDish);
				car.setNum(rs.getInt("num"));
				cars.add(car);
			}
			order.setCars(cars);
			order.setSeller(seller);
			order.setTakeoutOrder(takeoutOrder);
			order.setTakeoutOrderStatus(takeoutOrderStatus);
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return order;
	}

	@Override
	public List<String> findUserTakeOrder(String u_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> list=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_takeoutorder where u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,u_id);
			rs=pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("to_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return list;
	}

	@Override
	public int findsaledCount(String sd_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * FROM tb_sellerdish where sd_id=?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, sd_id);
			rs=pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("sd_salecount");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return 0;
	}

	@Override
	public boolean updatesaledCount(String sd_id, int saledCount) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		int num=findsaledCount(sd_id);
		try {
			conn=DBConnection.getConnection();
			String sql="update tb_sellerdish set sd_salecount=? where sd_id=?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1,num+saledCount);
			pst.setString(2, sd_id);
			i=pst.executeUpdate();
			if (i!=0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return false;
	}

	@Override
	public TakeoutOrderStatus getOrderStatus(String tos_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		TakeoutOrderStatus takeoutOrderStatus=null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_takeoutorderstatus where tos_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,tos_id);
			rs=pst.executeQuery();
			if (rs.next()) {
				takeoutOrderStatus=new TakeoutOrderStatus();
				takeoutOrderStatus.setTo_id(rs.getString("to_id"));
				takeoutOrderStatus.setTos_id(rs.getString("tos_id"));
				takeoutOrderStatus.setTos_status(rs.getString("tos_status"));
				String tos_time=rs.getString("tos_time");
				takeoutOrderStatus.setTos_time(tos_time.substring(0, tos_time.lastIndexOf(".")));
			}
			return takeoutOrderStatus;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return takeoutOrderStatus;
	}

	@Override
	public Boolean pay(String tos_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		int i=0;
		try {

			conn=DBConnection.getConnection();
			pst = (PreparedStatement) conn
					.prepareStatement("update tb_takeoutorderstatus set tos_status=? where tos_id=?;");
			pst.setString(1, "已支付");
			pst.setString(2, tos_id);
			i=pst.executeUpdate();
			if (i!=0) {
				return new Boolean(true);
			}else{
				return new Boolean(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return null;
	}



}
