package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.RestaurantDao;
import com.chdw.loc.domain.Restaurant;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RestaurantDaoImpl implements RestaurantDao {
	
	private DBConnection dbConn;
	
	public RestaurantDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<Restaurant> findAll(String condition) {
		List<Restaurant>  rests = new ArrayList<Restaurant>();
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
						.prepareStatement("select * from tb_restaurant " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Restaurant rest = new Restaurant();
					rest.setR_id(rs.getString(1));
					rest.setR_icon(rs.getString(2));
					rest.setR_name(rs.getString(3));
					rest.setR_degree(rs.getInt(4));
					rest.setR_address(rs.getString(5));
					rest.setR_starttime(rs.getTime(6));
					rest.setR_endtime(rs.getTime(7));
					rest.setR_advandays(rs.getInt(8));
					rest.setR_intro(rs.getString(9));
					rest.setR_contact(rs.getString(10));
					rest.setR_status(rs.getBoolean(11));
					rest.setR_notice(rs.getString(12));
					rest.setR_longitude(rs.getDouble(13));
					rest.setR_latitude(rs.getDouble(14));
					
					rests.add(rest);
				}
				return rests;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return rests;
	}


	@Override
	public int add(Restaurant rest) {
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
						.prepareStatement("insert into tb_restaurant values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, rest.getR_icon());
				pstmt.setString(3, rest.getR_name());
				pstmt.setInt(4, rest.getR_degree());
				pstmt.setString(5, rest.getR_address());
				pstmt.setTime(6, rest.getR_starttime());
				pstmt.setTime(7, rest.getR_endtime());
				pstmt.setInt(8, rest.getR_advandays());
				pstmt.setString(9, rest.getR_intro());
				pstmt.setString(10, rest.getR_contact());
				pstmt.setBoolean(11, rest.isR_status());
				pstmt.setString(12, rest.getR_notice());
				pstmt.setDouble(13, rest.getR_longitude());
				pstmt.setDouble(14, rest.getR_latitude());
				
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
	public int update(Restaurant rest) {
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
						.prepareStatement("update tb_restaurant set r_name=?, r_icon=?, r_degree=?, r_address=?," + 
								"r_starttime=?, r_endtime=?, r_advandays=?, r_intro=?, r_contact=?, r_status=?, r_notice=?, "
								+ "r_longitude=?, r_latitude=? where r_id=?;");
				pstmt.setString(1, rest.getR_name());
				pstmt.setString(2, rest.getR_icon());
				pstmt.setInt(3, rest.getR_degree());
				pstmt.setString(4, rest.getR_address());
				pstmt.setTime(5, rest.getR_starttime());
				pstmt.setTime(6, rest.getR_endtime());
				pstmt.setInt(7, rest.getR_advandays());
				pstmt.setString(8, rest.getR_intro());
				pstmt.setString(9, rest.getR_contact());
				pstmt.setBoolean(10, rest.isR_status());
				pstmt.setString(11, rest.getR_notice());
				pstmt.setDouble(12, rest.getR_longitude());
				pstmt.setDouble(13, rest.getR_latitude());
				pstmt.setString(14, rest.getR_id());
				
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from tb_restaurant " + condition + ";");
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
	public PagingBean<Restaurant> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<Restaurant> pagingBean = new PagingBean<Restaurant>();
		
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
						.prepareStatement("delete from tb_restaurant where r_id in(" + IDs + ");");
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
