package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SeatOrderDao;
import com.chdw.loc.domain.SeatOrder;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SeatOrderDaoImpl implements SeatOrderDao {

	private DBConnection dbConn;

	public SeatOrderDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}
	

	@Override
	public List<SeatOrder> findAll(String condition) {
		List<SeatOrder> seats = new ArrayList<SeatOrder>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn != null){
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM seatorder_view " + condition + ";");
				rs = pstmt.executeQuery();
				while(rs.next()){
					SeatOrder so = new SeatOrder();
					so.setSo_id(rs.getString(1));
					so.setSo_eatTime(rs.getTimestamp(2));
					so.setSo_count(rs.getInt(3));
					so.setSo_extra(rs.getString(4));
					so.setSo_name(rs.getString(5));
					so.setSo_phone(rs.getString(6));
					so.setR_id(rs.getString(7));
					so.setU_id(rs.getString(8));
					so.setR_name(rs.getString(9));
					so.setUser_alias(rs.getString(10));
				     
					seats.add(so);
				}
				return seats;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
					DBConnection.close(conn, pstmt, rs);
			}
		}

		return seats;
	}


	// 插入
	@Override
	public int add(SeatOrder so) {
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
						.prepareStatement("INSERT INTO tb_seatorder (so_id,so_time, so_count, so_extra, so_name, so_phone, r_id, u_id) VALUES (?,?,?,?,?,?,?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setTimestamp(2, so.getSo_eatTime());
				pstmt.setInt(3, so.getSo_count());
				pstmt.setString(4, so.getSo_extra());
				pstmt.setString(5, so.getSo_name());
				pstmt.setString(6, so.getSo_phone());
				pstmt.setString(7, so.getR_id());
				pstmt.setString(8, so.getU_id());

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	// 修改
	@Override
	public int update(SeatOrder so) {
		if (findAll(so.getSo_id()) == null) {
			return 2; // 表示订座订单不存在，不能更新
		}
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
						.prepareStatement("UPDATE tb_seatorder SET so_count=?, so_extra=?, so_name=?, so_phone=?  WHERE so_id=? ;");
				pstmt.setInt(1, so.getSo_count());
				pstmt.setString(2, so.getSo_extra());
				pstmt.setString(3, so.getSo_name());
				pstmt.setString(4, so.getSo_phone());
				pstmt.setString(5, so.getSo_id());

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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from seatorder_view " + condition + ";");
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
	public PagingBean<SeatOrder> getPageBean(int pageSize, int currPage,
			String queryCondition) {
        PagingBean<SeatOrder> pagingBean = new PagingBean<SeatOrder>();
		
		pagingBean.setPageSize(pageSize); // 设置页面大小
		pagingBean.setCurrPage(currPage); // 设置当前页数
		pagingBean.setTotalRows(getTotalCount(queryCondition)); // 设置总记录数

		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
		pagingBean.setTotalPages(allPages);	//设置总页数
		
		queryCondition = queryCondition + " limit " + ((currPage - 1) * pageSize) + "," + pageSize; 
		System.out.println("查询条件" + queryCondition);
		System.out.println("集合条件" + findAll(queryCondition).size());
		pagingBean.setList(findAll(queryCondition)); // 设置需要分页显示的集合
		
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
						.prepareStatement("delete from tb_seatorder where so_id in(" + IDs + ");");
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
