package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.RestDishDao;
import com.chdw.loc.domain.RestDish;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RestDishDaoImpl implements RestDishDao {

	private DBConnection dbConn;

	public RestDishDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<RestDish> findAll(String condition) {
		List<RestDish>  dishes = new ArrayList<RestDish>();
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
						.prepareStatement("select * from restdish_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RestDish rd = new RestDish();
					rd.setRd_id(rs.getString(1));
					rd.setRd_icon(rs.getString(2));
					rd.setRd_name(rs.getString(3));
					rd.setRd_price(rs.getInt(4));
					rd.setRmt_id(rs.getString(5));
					rd.setRmt_name(rs.getString(6));
					rd.setR_id(rs.getString(7));
					rd.setR_name(rs.getString(8));

					dishes.add(rd);
				}
				return dishes;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return dishes;
	}

	@Override
	public int add(RestDish rd) {
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
						.prepareStatement("insert into tb_restdish values(?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, rd.getRd_name());
				pstmt.setInt(3, rd.getRd_price());
				pstmt.setString(4, rd.getRmt_id());
				pstmt.setString(5, rd.getRd_icon());

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
	public int update(RestDish rd) {
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
						.prepareStatement("update tb_restdish set rd_icon=?, rd_name=?, rd_price=? where rd_id=?;");
				pstmt.setString(1, rd.getRd_icon());
				pstmt.setString(2, rd.getRd_name());
				pstmt.setInt(3, rd.getRd_price());
				pstmt.setString(4, rd.getRd_id());

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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from restdish_view " + condition + ";");
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
	public PagingBean<RestDish> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<RestDish> pagingBean = new PagingBean<RestDish>();

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
						.prepareStatement("delete from tb_restdish where rd_id in(" + IDs + ");");
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
