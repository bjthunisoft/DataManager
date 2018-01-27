package com.talk.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;



/**
 * ���ݿ����ݲ���������
 * 
 * @author gaoqiang01
 *
 */
public class QueryHelper {
//	private static Logger log = Log4jHelper.getLogger(QueryHelper.class);

	/**
	 * ����sql��ѯ����
	 * 
	 * @param sql
	 *            query���
	 * @param dbName
	 *            ���ݿ���
	 * @return ���ݼ���
	 */
	public static LinkedList<Vector<String>> queryBySql(String sql, String dbName) {
		LinkedList<Vector<String>> resultData = new LinkedList<Vector<String>>();
		Connection conn = JdbcHelper.getConnection(dbName);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData r = rs.getMetaData();
				int lineCount = r.getColumnCount();
				Vector<String> fs = new Vector<String>(); 
				if (lineCount >= 1) {
//					StringBuffer line = new StringBuffer();
					for (int i = 1; i <= lineCount; i++) {
						fs.add(rs.getString(r.getColumnName(i)));
//						line.append(rs.getString(r.getColumnName(i)));
					}
					resultData.add(fs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql);
//			log.error("SQL��ѯʧ��:" + sql, e);
		} finally {
			JdbcHelper.CloseJdbc(rs, st, conn);
		}
		return resultData;
	}
	
	
	public static LinkedList<String> easyQuery(String sql, String dbName){
		LinkedList<String> data = new LinkedList<String>();
		Connection conn = JdbcHelper.getConnection(dbName);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData r = rs.getMetaData();
				int lineCount = r.getColumnCount();
				if (lineCount >= 1) {
					for (int i = 1; i <= lineCount; i++) {
						data.add(rs.getString(r.getColumnName(i)));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql);
		} finally {
			JdbcHelper.CloseJdbc(rs, st, conn);
		}
		return data;
	}
	
	
	public static LinkedList<Map<String,String>> freeQueryBySql(String sql, String dbName,String[] v) throws Exception{
		LinkedList<Map<String,String>> resultData = new LinkedList<Map<String,String>>();
		Connection conn = JdbcHelper.getConnection(dbName);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData r = rs.getMetaData();
				int lineCount = r.getColumnCount();
				Map<String,String> fs = new HashMap<String,String>(); 
				if (lineCount >= 1) {
					for (int i = 1; i <= lineCount; i++) {
						String c = v[i-1];
						String val = rs.getString(c);
						fs.put(c, val);
					}
					resultData.add(fs);
				}
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql);
//			log.error("SQL执行异常：" + sql, e);
			throw new Exception("查询异常");
		} finally {
			JdbcHelper.CloseJdbc(rs, st, conn);
		}
		return resultData;
	}
	
	public static TreeMap<String, String> getMapFromSQL(String sql, String fieldKey, String fieldValue,String dbName) {
		TreeMap<String, String> res = new TreeMap<String, String>();
		Statement st = null;
		java.sql.ResultSet rs = null;
		Connection conn = JdbcHelper.getConnection(dbName);
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			while (rs.next()) {
				String k = rs.getString(fieldKey);
				String v = rs.getString(fieldValue);
				res.put(k, v);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static TreeMap<String, String> getMapFromSQL(String sql, String fieldKey, String fieldValue,String fieldValue2,String dbName) {
		TreeMap<String, String> res = new TreeMap<String, String>();
		Statement st = null;
		java.sql.ResultSet rs = null;
		Connection conn = JdbcHelper.getConnection(dbName);
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			while (rs.next()) {
				String k = rs.getString(fieldKey);
				String v = rs.getString(fieldValue);
				String v2 = rs.getString(fieldValue2);
				res.put(k, v+"/"+v2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
