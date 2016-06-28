package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SeatOrderStatusDao;
import com.chdw.loc.domain.SeatOrderStatus;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SeatOrderStatusDaoImpl implements SeatOrderStatusDao {

	private DBConnection dbConn;
	
	public SeatOrderStatusDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}
	
	// 查询
	@Override
	public List<SeatOrderStatus> findAll(String condition) {
		List<SeatOrderStatus> seatOrderStatuses = new ArrayList<SeatOrderStatus>();
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
				pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM seatorderstatus_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SeatOrderStatus sos = new SeatOrderStatus();
					sos.setSos_id(rs.getString(1));
					sos.setSos_status(rs.getString(2));
					sos.setSos_time(rs.getDate(3));
					sos.setSo_id(rs.getString(4));
					sos.setU_id(rs.getString(5));
					sos.setUser_alias(rs.getString(6));
					
					seatOrderStatuses.add(sos);
				}
				return seatOrderStatuses;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return seatOrderStatuses;
	}

	// 插入
	@Override
	public int add(SeatOrderStatus sos) {
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
						.prepareStatement("INSERT INTO tb_seatorderstatus (sos_id, sos_status, so_id) VALUES (?, ?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, sos.getSos_status());
				pstmt.setString(3, sos.getSo_id());
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	// 更新
	@Override
	public int update(SeatOrderStatus sos) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("UPDATE tb_seatorderstatus SET sos_status=? WHERE sos_id=?;");
				pstmt.setString(1, sos.getSos_status());
				pstmt.setString(2, sos.getSos_id());
				
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(conn != null){
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("SELECT count(*) FROM seatorderstatus_view " + condition + ";");
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	@Override
	public PagingBean<SeatOrderStatus> getPageBean(int pageSize, int currPage,
			String queryCondition) {
PagingBean<SeatOrderStatus> pagingBean = new PagingBean<SeatOrderStatus>();
		
		pagingBean.setPageSize(pageSize);
		pagingBean.setCurrPage(currPage);
		pagingBean.setTotalRows(getTotalCount(queryCondition));
		
		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
		pagingBean.setTotalPages(allPages);	//设置总页数
		
		queryCondition = queryCondition + " limit " + ((currPage - 1)*pageSize) + "," + pageSize;
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
						.prepareStatement("delete from tb_seatorderstatus where sos_id in(" + IDs + ");");
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
