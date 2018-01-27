package com.talk.db;

import java.util.LinkedList;

import com.talk.util.QueryHelper;

public class Tables {
	public static LinkedList<String> getTableColumnName(String dbName, String tName) {
		String sql = "select column_name from information_schema.columns where table_name = '" + tName
				+ "' and table_schema = '" + dbName + "'";
		LinkedList<String> data = QueryHelper.easyQuery(sql, dbName);
		return data;

	}
	
	public static void main(String[] args) {
		System.out.println("base_course_lesson.class_id".indexOf("."));
		String a= "base_course_lesson.class_id";
		String v =a.substring(a.indexOf(".")+1, a.length());
		System.out.println(v);
	}
}
