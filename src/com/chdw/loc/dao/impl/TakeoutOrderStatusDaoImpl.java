package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.TakeoutOrderStatusDao;
import com.chdw.loc.domain.TakeoutOrderStatus;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TakeoutOrderStatusDaoImpl implements TakeoutOrderStatusDao {
	private DBConnection dbConn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	public TakeoutOrderStatusDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<TakeoutOrderStatus> findAll(String condition) {
		List<TakeoutOrderStatus>  takeors = new ArrayList<TakeoutOrderStatus>();
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("findAll查询条件 " + condition + ";");
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from takeoutorderstatus_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					TakeoutOrderStatus tos = new TakeoutOrderStatus();
					tos.setTos_id(rs.getString(1));
					tos.setTos_status(rs.getString(2));
					tos.setTos_time(rs.getString(3));
					tos.setTo_id(rs.getString(4));
					tos.setU_id(rs.getString(5));
					tos.setUser_alias(rs.getString(6));

					takeors.add(tos);
				}
				return takeors;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}


	@Override
	public int add(TakeoutOrderStatus takeos) {
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_takeoutorderstatus(tos_id, tos_status,to_id) values(?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, takeos.getTos_status());
				pstmt.setString(3, takeos.getTo_id());
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
	public int update(TakeoutOrderStatus takeos) {
		//		if (findById(takeos.getTos_id()) == null) {
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
						.prepareStatement("update tb_takeoutorderstatus set tos_status=? where to_id=?;");
				pstmt.setString(1, takeos.getTos_status());
				pstmt.setString(2, takeos.getTo_id());
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from takeoutorderstatus_view " + condition +";");
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
	public PagingBean<TakeoutOrderStatus> getPageBean(int pageSize,
			int currPage, String queryCondition) {
		PagingBean<TakeoutOrderStatus> pagingBean = new PagingBean<TakeoutOrderStatus>();

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
						.prepareStatement("delete from tb_takeoutorderstatus where tos_id in(" + IDs + ");");
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
