package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.DeliveryAddressDao;
import com.chdw.loc.domain.DeliveryAddress;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
	private DBConnection dbConn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	

	public DeliveryAddressDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	
	@Override
	public List<DeliveryAddress> findAll(String condition) {
		List<DeliveryAddress>  lda = new ArrayList<DeliveryAddress>();
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//System.out.println("findAll查询条件 " + condition + ";");
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from deliveryaddress_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					DeliveryAddress da = new DeliveryAddress();
					da.setDa_id(rs.getString(1));
					da.setDa_name(rs.getString(2));
					da.setDa_phone(rs.getString(3));
					da.setDa_address(rs.getString(4));
					da.setU_id(rs.getString(5));
					da.setUser_alias(rs.getString(6));
					lda.add(da);
				}
				return lda;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}
	
	@Override
	public int add(DeliveryAddress da) {
//		if (findByAddress(da.getDa_address()) != null) {
//			return 2;	
//		}		
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_deliveryaddress values(?,?,?,?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, da.getDa_name());
				pstmt.setString(3, da.getDa_phone());
				pstmt.setString(4,da.getDa_address());
				pstmt.setString(5,da.getU_id());
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
	public int update(DeliveryAddress da) {
//		if (findByAddress(da.getDa_address()) == null) {
//			return 2;	
//		}
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("update tb_deliveryaddress set da_name=?,da_phone=?,da_address=? where da_id=?;");
				pstmt.setString(1, da.getDa_name());
				pstmt.setString(2, da.getDa_phone());
				pstmt.setString(3,da.getDa_address());
				pstmt.setString(4,da.getDa_id());
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
	public int getTotalCount(String condition) {
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from deliveryaddress_view " + condition +";");
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
	public PagingBean<DeliveryAddress> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		
		PagingBean<DeliveryAddress> pagingBean = new PagingBean<DeliveryAddress>();
		
		pagingBean.setPageSize(pageSize);	//设置页面大小
		pagingBean.setCurrPage(currPage);	//设置当前页数
		pagingBean.setTotalRows(getTotalCount(queryCondition));		//设置总记录数
		
		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
		pagingBean.setTotalPages(allPages);	//设置总页数
		
		queryCondition = queryCondition + " limit " + ((currPage - 1) * pageSize) + "," + pageSize;
		System.out.println("查询条件" + queryCondition);
		System.out.println("集合大小" + findAll(queryCondition).size());
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
						.prepareStatement("delete from tb_deliveryaddress where da_id in(" + IDs + ");");
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

}
