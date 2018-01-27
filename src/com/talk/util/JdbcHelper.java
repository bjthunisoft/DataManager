package com.talk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import java.sql.Statement;

/**
 * mysql jdbc������
 * 
 * @author gaoqiang01
 *
 */
public class JdbcHelper {

	private static Logger log = Log4jHelper.getLogger(JdbcHelper.class);
	private static String mysqlDriver = "com.mysql.jdbc.Driver";
	private static String mysqlUrl = "jdbc:mysql://172.16.0.20";
	private static String mysqlDBName = "talkplatform_course";
	private static String mysqlPort = "3306";
	private static String mysqlUser = "root";
	private static String mysqlPwd = "123456";
	private static String splitSymbol = "/";
	private static String maohao = ":";
	
	
	
	public static void setMysqlParams(String dbName){
		TreeMap<String, String> jdbcParMap = ConfigHelper.GetParamsMap(Constant.CONFIG_FILE_NAME,
				Constant.CONFIG_SPLIT_TAG, false);
		mysqlUrl = jdbcParMap.get("mysql_url_"+dbName) == null ? mysqlUrl : jdbcParMap.get("mysql_url_"+dbName);
		mysqlDBName = jdbcParMap.get("mysql_dbname_"+dbName) == null ? mysqlDBName : jdbcParMap.get("mysql_dbname_"+dbName);
		mysqlPort = jdbcParMap.get("mysql_port_"+dbName) == null ? mysqlPort : jdbcParMap.get("mysql_port_"+dbName);
		mysqlUser = jdbcParMap.get("mysql_user_"+dbName) == null ? mysqlUser : jdbcParMap.get("mysql_user_"+dbName);
		mysqlPwd = jdbcParMap.get("mysql_pass_"+dbName) == null ? mysqlPwd : jdbcParMap.get("mysql_pass_"+dbName);

	}


	/**
	 * ��ȡconnection
	 * 
	 * @return
	 */
	public static Connection getConnection(String dbName) {
		setMysqlParams(dbName);
		Connection conn = null;
		try {
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl + maohao + mysqlPort + splitSymbol + mysqlDBName, mysqlUser,
					mysqlPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error("jdbc classNotFoundException", e);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("dbInfos=====>"+mysqlUrl+"\t"+mysqlDBName+"\t"+mysqlPort+"\t"+mysqlUrl);
			log.error("getConnection exception", e);
		}
		return conn;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void CloseJdbc(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("jdbc ResultSet ��Դ�ر�ʧ��", e);
		} finally {
			rs = null;
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("jdbc Statment ��Դ�ر�ʧ��", e);
		} finally {
			st = null;
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("jdbc Connection ��Դ�ر�ʧ��", e);
		} finally {
			conn = null;
		}
	}

	public static void main(String[] args) {
		Connection c = getConnection("course");
		System.out.println(c);
	}
}
