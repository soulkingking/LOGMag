package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.TakeoutOrderDao;
import com.chdw.loc.domain.TakeoutOrder;
import com.chdw.loc.util.DBConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TakeoutOrderDaoImpl implements TakeoutOrderDao {
	private DBConnection dbConn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	public TakeoutOrderDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<TakeoutOrder> findAll(String condition) {
		List<TakeoutOrder>  takeor = new ArrayList<TakeoutOrder>();
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("findAll查询条件 " + condition + ";");
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from takeoutorder_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					TakeoutOrder takeoutOrder = new TakeoutOrder();
					takeoutOrder.setTo_id(rs.getString(1));
					takeoutOrder.setTo_deliveryFee(rs.getInt(2));
					takeoutOrder.setTo_boxFee(rs.getInt(3));
					takeoutOrder.setTo_extra(rs.getString(4));
					takeoutOrder.setTo_name(rs.getString(5));
					takeoutOrder.setTo_phone(rs.getString(6));
					takeoutOrder.setTo_address(rs.getString(7));
					takeoutOrder.setS_id(rs.getString(8));
					takeoutOrder.setSeller_name(rs.getString(9));
					takeoutOrder.setU_id(rs.getString(10));
					takeoutOrder.setUser_alias(rs.getString(11));

					takeor.add(takeoutOrder);
				}
				return takeor;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}

	@Override
	public int add(TakeoutOrder takeoutOrder) {
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_takeoutorder values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, takeoutOrder.getTo_id());
				pstmt.setInt(2,takeoutOrder.getTo_deliveryFee());
				pstmt.setInt(3,takeoutOrder.getTo_boxFee());
				pstmt.setString(4, takeoutOrder.getTo_extra());
				pstmt.setString(5, takeoutOrder.getTo_name());
				pstmt.setString(6, takeoutOrder.getTo_phone());
				pstmt.setString(7, takeoutOrder.getTo_address());
				pstmt.setString(8,takeoutOrder.getS_id());
				pstmt.setString(9,takeoutOrder.getU_id());

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
	public int update(TakeoutOrder takeoutOrder) {
		//		if (findById(takeoutOrder.getTo_id()) == null) {
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
						.prepareStatement("update tb_takeoutorder set to_df=?, to_bf=?, to_extra=?, to_name=?, to_phone=?, to_address=? where to_id=?;");
				pstmt.setInt(1, takeoutOrder.getTo_deliveryFee());
				pstmt.setInt(2, takeoutOrder.getTo_boxFee());
				pstmt.setString(3, takeoutOrder.getTo_extra());
				pstmt.setString(4, takeoutOrder.getTo_name());
				pstmt.setString(5, takeoutOrder.getTo_phone());
				pstmt.setString(6, takeoutOrder.getTo_address());
				pstmt.setString(7, takeoutOrder.getTo_id());
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from takeoutorder_view " + condition +";");
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
	public PagingBean<TakeoutOrder> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<TakeoutOrder> pagingBean = new PagingBean<TakeoutOrder>();

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
		int i=0;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				i=delete1(IDs);
				if (i!=0) {
					pstmt = (PreparedStatement) conn
							.prepareStatement("delete from tb_takeoutorder where to_id in(" + IDs + ");");
					i= pstmt.executeUpdate();
				}
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	public int delete1(String IDs) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int i=0;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				i=delete2(IDs);
				if (i!=0) {
					pstmt = (PreparedStatement) conn
							.prepareStatement("delete from tb_takeoutorderinfo where to_id in(" + IDs + ");");
					return pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}
	public int delete2(String IDs) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int i=0;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("delete from tb_takeoutorderstatus where to_id in(" + IDs + ");");
				i=pstmt.executeUpdate();
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

}
