package com.tydic.bcp.common.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ClearDBResource {
	private static Logger logger = Logger.getLogger(ClearDBResource.class);

	private ClearDBResource() {
	}

	/**
	 * 关闭数据库连�?对于数据库连接池而言是将连接还给连接�?
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				logger.error("Close Connection Error!", ex);
			}
		}
	}

	/**
	 * 关闭连接，当关闭连接出错时，该方法可用于快�?定位到出错的�?
	 * @param conn
	 * @param classObject  调用该方法的�?
	 *           
	 */
	public static void closeConnection(Connection conn, Object clazz) {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				StringBuffer sb = new StringBuffer();
				sb.append(clazz.getClass().getName());
				sb.append(clazz.getClass().getName());
				sb.append("\nUnable to close database connection!\n");
				sb.append(ex.getMessage());
				logger.error(sb.toString(), ex);
			}
		}
	}

	/**
	 * 释放记录集对�?
	 * 
	 * @param rs
	 */
	public static void closeResultSet(java.sql.ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.error("Close database ResultSet Error!a", ex);
			}
		}
	}

	/**
	 * 释放记录集对象，当关闭连接出错时，该方法可用于快速定位到出错的类
	 * 
	 * @param rs
	 */
	public static void closeResultSet(java.sql.ResultSet rs, Object clazz) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				StringBuffer sb = new StringBuffer();
				sb.append(clazz.getClass().getName());
				sb.append("\nUnable to close database ResultSet");
				logger.error(sb, ex);
			}
		}
	}

	/**
	 * 释放Statement对象占用资源
	 * @param stmt
	 */
	public static void closeStatment(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				logger.error("Close Statement Error!", ex);
			}
		}
	}

	/**
	 * 释放Statement对象占用资源,出错时定位错误的�?
	 * @param stmt
	 * @param clazz
	 */
	public static void closeStatment(Statement stmt, Object clazz) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				StringBuffer sb = new StringBuffer();
				sb.append(clazz.getClass().getName());
				sb.append("\nUnable to close database Statement!\n");
				logger.error(sb, ex);
			}
		}
	}
}
