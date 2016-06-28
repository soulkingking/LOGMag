package com.chdw.loc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SeatInfoDao;
import com.chdw.loc.domain.SeatInfo;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SeatInfoDaoImpl implements SeatInfoDao {

    private DBConnection dbConn;
	
	public SeatInfoDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	
	@Override
	public List<SeatInfo> findAll(String condition) {
		List<SeatInfo> seat = new ArrayList<SeatInfo>();
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
				pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM seatinfo_view " + condition + ";");
				rs = pstmt.executeQuery();
				while(rs.next()){
				     SeatInfo si = new SeatInfo();
				     si.setSi_id(rs.getString(1));
				     si.setU_id(rs.getString(2));
				     si.setSi_name(rs.getString(3));
				     si.setSi_phone(rs.getString(4));
				     si.setUser_alias(rs.getString(5));
				     
				     seat.add(si);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConnection.close(conn, pstmt, rs);
			}
		}

		return seat;
	}


	// 插入
	@Override
	public int add(SeatInfo si) {
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
						.prepareStatement("INSERT INTO tb_seatinfo (si_id,u_id,si_name,si_phone) VALUES ( ?,?,?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2,si.getU_id());
				pstmt.setString(3, si.getSi_name());
				pstmt.setString(4, si.getSi_phone());
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
	public int update(SeatInfo si) {
	    if(findAll(si.getSi_id())==null){
	    	return 2;// 表示订单信息不存在，不能更新
	    }
	    PreparedStatement pstmt = null;
	    Connection conn = null;
	    try {
			conn = dbConn.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    if(conn != null){
	    	try {
				pstmt = (PreparedStatement) conn.prepareStatement("UPDATE tb_seatinfo SET si_name=? ,si_phone=? WHERE  si_id =? ;");
				pstmt.setString(1, si.getSi_name());
				pstmt.setString(2, si.getSi_phone());
				pstmt.setString(3, si.getSi_id());
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from seatinfo_view " + condition + ";");
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
	public PagingBean<SeatInfo> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<SeatInfo> pagingBean = new PagingBean<SeatInfo>();
		
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
						.prepareStatement("delete from tb_seatinfo where si_id in(" + IDs + ");");
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
