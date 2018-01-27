package com.talk.model;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import com.talk.util.Constant;
import com.talk.util.QueryHelper;

public class TeacherAnalysis {
	public static LinkedList<Map<String, String>> getTeacherInfo(String tid, String[] col) {

		String sql = "select id,real_name,is_full_time,t_level from teacher where id = " + tid;
		LinkedList<Map<String, String>> data = null;
		try {
			data = QueryHelper.freeQueryBySql(sql, Constant.DBNAME_TALKPLATFORM_TEA, col);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public static LinkedList<Map<String, String>> getLessonInfo(String st, String et, String c, String r,
			String[] col) {

		StringBuffer sql = new StringBuffer(
				"SELECT class_id, room_id, tea_id, start_time, end_time FROM base_course_lesson WHERE  start_time between '"
						+ st + "' and '" + et + "'");
		if (c != null && c.length() > 0) {
			sql.append(" and class_id=" + c);
		}
		if (r != null && r.length() > 0) {
			sql.append(" and room_id=" + r);
		}
		sql.append(" order by start_time");
		LinkedList<Map<String, String>> data = null;
		try {
			data = QueryHelper.freeQueryBySql(sql.toString(), Constant.DBNAME_COURSE, col);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public static TreeMap<String, String> getTeacherBookLessonInfo(String tid, String start_date, String end_date) {
		TreeMap<String, String> data = null;
		String sql = "select class_id,room_id,tea_id,start_time from base_course_lesson where tea_id = " + tid
				+ " and start_time BETWEEN '" + start_date + "' and '" + end_date + "'";
		try {
			data = QueryHelper.getMapFromSQL(sql, "start_time", "class_id", "room_id", Constant.DBNAME_COURSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// appoint_status = 1 and status ='on'
	public static TreeMap<String, String> getTeacherOpenLessonInfo(String tid, String start_date, String end_date) {
		String sql = "select teacher_id,date,time,time_slot,project_code from teacher_class_schedule where teacher_id ="
				+ tid + " and date between '" + start_date + "' and '" + end_date + "' order by date";
		TreeMap<String, String> data = null;
		try {
			data = QueryHelper.getMapFromSQL(sql, "time", "date", Constant.DBNAME_TALKPLATFORM_TEA);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
