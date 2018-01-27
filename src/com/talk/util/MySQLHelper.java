package com.talk.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Vector;


public class MySQLHelper {
//	public static LinkedList<Vector<String>> GetListFromSQL(String sql, Vector<String> fileds, MyMySQL news_mysql)
//	  {
//	    LinkedList<Vector<String>> res = new LinkedList<Vector<String>>();
//	    Statement stmt = null;
//	    ResultSet rs = null;
//	    int except = 0;
//		try 
//		{
//			if(news_mysql.conn == null || news_mysql.conn.isClosed())
//			{
//				news_mysql.Reconn();
//			}
//			stmt = news_mysql.CreateStatement();
//			rs = stmt.executeQuery(sql);
//		}
//		catch (SQLException e) 
//		{
//			//e.printStackTrace();
//			except++;
//			news_mysql.Reconn();
//		}
//		while(except > 0 && except < 4)
//		{
//			try 
//			{
//				if(news_mysql.conn == null || news_mysql.conn.isClosed())
//				{
//					news_mysql.Reconn();
//				}
//				stmt = news_mysql.CreateStatement();
//				rs = stmt.executeQuery(sql);
//				except =0;
//			}
//			catch (SQLException e) 
//			{
//				e.printStackTrace();
//				except++;
//				news_mysql.Reconn();
//			}
//		}
//	    
//	    
//	    if (rs == null)
//	    {
//	    	try
//	    	{
//	    		stmt.close();
//	    	} 
//	    	catch (SQLException e) 
//	    	{
//	    		e.printStackTrace();
//	    	}
//	    	return res;
//	    }
//	    try
//	    {
//			while(rs.next())
//			{
//				Vector<String> fs = new Vector<String>(); 
//				for(int i = 0; i < fileds.size(); i++)
//			  	{
//			  		fs.add(rs.getString(fileds.get(i)));
//			  	}
//				res.add(fs);
//			}
//	    }
//	    catch (SQLException e)
//	    {
//	    	e.printStackTrace();
//	    }
//	    try
//	    {
//	    	rs.close();
//	    } 
//	    catch (SQLException e) 
//	    {
//	    	e.printStackTrace();
//	    }
//	    try
//	    {
//	    	stmt.close();
//	    }
//	    catch (SQLException e)
//	    {
//	    	e.printStackTrace();
//	    }
//	    return res;
//	  }
}
