package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SellerDao;
import com.chdw.loc.domain.Seller;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SellerDaoImpl implements SellerDao {

	private DBConnection dbConn;

	public SellerDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public int add(Seller seller) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_seller values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, seller.getSeller_name());
				pstmt.setInt(3, seller.getSeller_degree());
				pstmt.setInt(4, seller.getSeller_sendprice());
				pstmt.setInt(5, seller.getSeller_deliverytime());
				pstmt.setString(6, seller.getSeller_contact());
				pstmt.setBoolean(7, seller.isSeller_status());
				pstmt.setString(8, seller.getSeller_notice());
				pstmt.setString(9, seller.getSeller_icon());
				pstmt.setString(10, seller.getSeller_intro());
				pstmt.setString(11, seller.getSeller_starttime());
				pstmt.setString(12, seller.getSeller_endtime());
				pstmt.setInt(13, seller.getSeller_df());
				pstmt.setDouble(14, seller.getSeller_longitude());
				pstmt.setDouble(15, seller.getSeller_latitude());

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	@Override
	public int update(Seller seller) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("update tb_seller set seller_name=?, seller_degree=?, seller_sendprice=?, seller_deliverytime=?," + 
								"seller_contact=?, seller_status=?, seller_notice=?, seller_icon=?, seller_intro=?, seller_starttime=?, seller_endtime=?, seller_df=?,"
								+ "seller_longitude=?, seller_latitude=? where s_id=?;");
				pstmt.setString(1, seller.getSeller_name());
				pstmt.setInt(2, seller.getSeller_degree());
				pstmt.setInt(3, seller.getSeller_sendprice());
				pstmt.setInt(4, seller.getSeller_deliverytime());
				pstmt.setString(5, seller.getSeller_contact());
				pstmt.setBoolean(6, seller.isSeller_status());
				pstmt.setString(7, seller.getSeller_notice());
				pstmt.setString(8, seller.getSeller_icon());
				pstmt.setString(9, seller.getSeller_intro());
				pstmt.setString(10, seller.getSeller_starttime());
				pstmt.setString(11, seller.getSeller_endtime());
				pstmt.setInt(12, seller.getSeller_df());
				pstmt.setDouble(13, seller.getSeller_longitude());
				pstmt.setDouble(14, seller.getSeller_latitude());
				pstmt.setString(15, seller.getS_id());

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	@Override
	public List<Seller> findAll(String condition) {
		List<Seller>  sellers = new ArrayList<Seller>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from tb_seller " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Seller seller = new Seller();
					seller.setS_id(rs.getString(1));
					seller.setSeller_name(rs.getString(2));
					seller.setSeller_degree(rs.getInt(3));
					seller.setSeller_sendprice(rs.getInt(4));
					seller.setSeller_deliverytime(rs.getInt(5));
					seller.setSeller_contact(rs.getString(6));
					seller.setSeller_status(rs.getBoolean(7));
					seller.setSeller_notice(rs.getString(8));
					seller.setSeller_icon(rs.getString(9));
					seller.setSeller_intro(rs.getString(10));
					seller.setSeller_starttime(String.valueOf(rs.getTime(11)));
					seller.setSeller_endtime(String.valueOf(rs.getTime(12)));
					seller.setSeller_df(rs.getInt(13));
					seller.setSeller_longitude(rs.getDouble(14));
					seller.setSeller_latitude(rs.getDouble(15));

					sellers.add(seller);
				}
				return sellers;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return sellers;
	}

	@Override
	public int getTotalCount(String condition) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from tb_seller " + condition + ";");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	@Override
	public PagingBean<Seller> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<Seller> pagingBean = new PagingBean<Seller>();
		pagingBean.setPageSize(pageSize);	//设置页面大小
		pagingBean.setCurrPage(currPage);	//设置当前页数
		pagingBean.setTotalRows(getTotalCount(queryCondition));		//设置总记录数

		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
				pagingBean.setTotalPages(allPages);	//设置总页数

				queryCondition = queryCondition + " limit " + ((currPage - 1) * pageSize) + "," + pageSize;
				pagingBean.setList(findAll(queryCondition));	//设置需要分页显示的集合

				return pagingBean;
	}

	@Override
	public int delete(String IDs) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("delete from tb_seller where s_id in(" + IDs + ");");
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

}
