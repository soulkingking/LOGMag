package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.DishTableDao;
import com.chdw.loc.domain.DishTable;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DishTableDaoImpl implements DishTableDao {
	private DBConnection dbConn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public DishTableDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<DishTable> findAll(String condition) {
		List<DishTable>  dts = new ArrayList<DishTable>();
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("findAll查询条件 " + condition + ";");
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from dishtable_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					DishTable dt = new DishTable();
					dt.setDt_id(rs.getString(1));
					dt.setDt_count(rs.getInt(2));
					dt.setDt_total(rs.getInt(3));
					dt.setTo_id(rs.getString(4));
					dt.setU_id(rs.getString(5));
					dt.setUser_alias(rs.getString(6));
					dt.setSd_id(rs.getString(7));
					dt.setSd_name(rs.getString(8));
					dts.add(dt);
				}
				return dts;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}
	
	@Override
	public int add(DishTable dt) {
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_dishtable values(?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setInt(2,dt.getDt_count());
				pstmt.setInt(3,dt.getDt_total());
				pstmt.setString(4,dt.getTo_id());
				pstmt.setString(5,dt.getSd_id());
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
	public int update(DishTable dt) {
//		if (findAll("where da_id="+dt.getDt_id()).size() == 1) {
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
						.prepareStatement("update tb_dishtable set dt_count=?, dt_total=? where dt_id=?;");
				pstmt.setInt(1, dt.getDt_count());
				pstmt.setInt(2, dt.getDt_total());
				pstmt.setString(3, dt.getDt_id());
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from dishtable_view " + condition +";");
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
	public PagingBean<DishTable> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<DishTable> pagingBean = new PagingBean<DishTable>();
		
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
						.prepareStatement("delete from tb_dishtable where dt_id in(" + IDs + ");");
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
