package com.talk.control;

import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.talk.db.Tables;
import com.talk.model.TeacherAnalysis;
import com.talk.util.ConfigHelper;
import com.talk.util.Constant;
import com.talk.util.DateHelper;
import com.talk.util.QueryHelper;

@Controller
@RequestMapping(value = "/queryData")
public class control {
	private static Logger log = Logger.getLogger(control.class);

	/**
	 * 根据sql进行查询
	 * 
	 * @param dbName
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/freeQuery.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody JSONObject freeQuery(@RequestParam String dbName, @RequestParam String query) {
		JSONObject data = new JSONObject();
		try {
			int limitCount = 0;
			String[] columns = new String[10];
			String tName = "";
			query = query.toLowerCase();
			if (query.indexOf("delete") + query.indexOf("drop") + query.indexOf("truncate") + query.indexOf("create")
					+ query.indexOf("update") + query.indexOf("insert") > 0) {
				data.put("code", 100);
				return data;
			}
			if (query.indexOf("*") > 0) {
				tName = query.substring(query.indexOf("from"), query.length());
				tName = tName.split(" ")[1];
				LinkedList<String> TColumns = Tables.getTableColumnName(dbName, tName);
				columns = new String[TColumns.size()];
				for (int i = 0; i < TColumns.size(); i++) {
					System.out.println(TColumns.get(i));
					columns[i] = TColumns.get(i);
				}
			} else {
				columns = getColumnsBySql(query);

			}
			if (query.indexOf("limit") < 0) {
				query = query.replace(";", "");
				query += " limit 1000";
			}
			if (query.indexOf("limit") > 0) {
				try {
					limitCount = Integer.parseInt(query.substring(query.indexOf("limit"), query.length())
							.replace(" ", "").replace(";", "").replace("limit", ""));
				} catch (Exception e) {
					data.put("code", 400);
					return data;
				}
				if (limitCount > 1000) {
					data.put("code", 300);
					return data;
				}
			}
			LinkedList<Map<String, String>> dataList = QueryHelper.freeQueryBySql(query, dbName, columns);
			data.put("data", dataList);
			data.put("columns", columns);
			data.put("code", 800);
		} catch (Exception e) {
			log.error("自定义查询异常=====" + e);
			data.put("code", 400);
			e.printStackTrace();
		}
		return data;
	}

	public static String[] getColumnsBySql(String sql) {
		sql = sql.toLowerCase();
		String[] columns = new String[10];
		String[] col = new String[10];
		String colStr = sql.substring(sql.indexOf("select") + 6, sql.indexOf("from"));
		columns = colStr.split(",");
		col = new String[columns.length];
		for (int i = 0; i < columns.length; i++) {
			String c = columns[i];
			if (c.indexOf("as ") > 0) {
				c = c.split("as ")[1];
			}
			col[i] = c;
		}
		for (int j = 0; j < col.length; j++) {
			String cc = col[j];
			if (cc.indexOf(".") > 0) {
				cc = cc.substring(cc.indexOf(".") + 1, cc.length());
			}
			col[j] = cc.replace(" ", "");
		}

		return col;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dsShow.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String dsShow() {
		JSONObject data = new JSONObject();
		LinkedList<Map<String, String>> dataList = new LinkedList<>();
		TreeMap<String, String> jdbcParMap = ConfigHelper.GetParamsMap(Constant.CONFIG_FILE_NAME,
				Constant.CONFIG_SPLIT_TAG, false);
		Set set = jdbcParMap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			String k = entry.getKey();
			String v = entry.getValue();

		}
		data.put("data", dataList);

		return data.toString();
	}

	@RequestMapping(value = "/teaInfo.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody JSONObject teaInfo(@RequestParam String tid, @RequestParam String start_date,
			@RequestParam String end_date) throws ParseException {
		JSONObject data = new JSONObject();
		try {
			String[] tinfoCol = new String[] { "id", "real_name", "is_full_time", "t_level" };
			LinkedList<Map<String, String>> tinfo = TeacherAnalysis.getTeacherInfo(tid, tinfoCol);
			TreeMap<String, String> tlesson = TeacherAnalysis.getTeacherOpenLessonInfo(tid, start_date, end_date);
			TreeMap<String, String> tbook = TeacherAnalysis.getTeacherBookLessonInfo(tid, start_date, end_date);
			TreeMap<String, String> tb = new TreeMap<String, String>();
			for (Map.Entry<String, String> e : tbook.entrySet()) {
				String k = e.getKey();
				String v = e.getValue();
				String d = k.split(" ")[0].replace("-", "");
				String hour = k.split(" ")[1].split(":")[0];
				String h = Integer.parseInt(hour) * 2 + 1 + "";
				tb.put(d + "_" + h, v);
			}
			StringBuffer tr = new StringBuffer("<tr>");
			int line = 0;
			for (Map.Entry<String, String> entry : tlesson.entrySet()) {
				String d = entry.getKey();
				String v = entry.getValue();
				String wd = DateHelper.GetWeekDayNo0(v) + "";
				if (tb.containsKey(d)) {
					tr.append("<th style='color:green;font-weight:bold'>");
					tr.append(d + " wd:" + wd + " " + tb.get(d));
					tr.append("</th>");
					line = line + 1;
				} else {
					tr.append("<th>");
					tr.append(d + " wd:" + wd);
					tr.append("</th>");
					line = line + 1;
				}
				if (line - 7 == 0) {
					tr.append("</tr>");
					tr.append("<tr></tr>");
					line = 0;
				}
			}
			StringBuffer tinfo_tr = new StringBuffer("<tr>");
			StringBuffer tinfo_thead = new StringBuffer("<tr>");
			for (int i = 0; i < tinfo.size(); i++) {
				Map<String, String> m = tinfo.get(i);
				for (Map.Entry<String, String> e : m.entrySet()) {
					tinfo_thead.append("<th>" + e.getKey() + "</th>");
					if (e.getKey().compareTo("is_full_time") == 0) {
						tinfo_tr.append("<td>" + GetGroupName().get(e.getValue()) + "</td>");
					} else {
						tinfo_tr.append("<td>" + e.getValue() + "</td>");
					}
				}
				tinfo_thead.append("</tr>");
				tinfo_tr.append("</tr>");
			}

			JSONObject tinfoJson = new JSONObject();
			tinfoJson.put("columns", tinfo_thead);
			tinfoJson.put("res", tinfo_tr);
			data.put("tinfo", tinfoJson);
			data.put("tlesson", tr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get teaInfo or teaClassInfo error", e);
		}
		return data;
	}

	@RequestMapping(value = "/lessonInfo.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody JSONObject lessonInfo(@RequestParam String classId, @RequestParam String roomId,
			@RequestParam String start_date, @RequestParam String end_date) {
		JSONObject data = new JSONObject();
		try {
			String[] col = new String[] { "class_id", "room_id", "tea_id", "start_time", "end_time" };
			LinkedList<Map<String, String>> lessonInfo = TeacherAnalysis.getLessonInfo(start_date, end_date, classId,
					roomId, col);
			if (lessonInfo.size() < 0) {
				return null;
			}
			StringBuffer tr = new StringBuffer("<tr>");
			StringBuffer thead = new StringBuffer("<tr>");
			for (int i = 0; i < lessonInfo.size(); i++) {
				Map<String, String> m = lessonInfo.get(i);
				for (Map.Entry<String, String> e : m.entrySet()) {
					tr.append("<td>" + e.getValue() + "</td>");
				}
				if (i == 0) {
					for (Map.Entry<String, String> e : m.entrySet()) {
						thead.append("<th>" + e.getKey() + "</th>");
					}
				}
				tr.append("</tr>");
			}
			thead.append("</tr>");
			data.put("columns", thead.toString());
			data.put("data", tr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get lessonInfo error", e);
		}
		return data;
	}

	/**
	 * 老师分组设置
	 * 
	 * @return
	 */
	public static TreeMap<String, String> GetGroupName() {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("5", "PH-GROUP");
		map.put("6", "PH-GROUP");
		map.put("7", "PH-GROUP");
		map.put("8", "PH-GROUP");
		map.put("10", "PH-GROUP");
		map.put("11", "PH-GROUP");
		map.put("12", "NonFil-HBT");
		map.put("13", "NonFil-HBT");
		map.put("15", "NA-GROUP");
		map.put("16", "NA-GROUP");
		map.put("216", "NA-GROUP");
		map.put("18", "PH-GROUP");
		map.put("5,6,7,11,18", "PH-HBT");
		map.put("8,10", "PH-OBT");
		map.put("12,13", "NonFil-HBT");
		map.put("15,16,216", "NA-GROUP");
		map.put("5,6,7,8,10,11,18,19,20,21,22,12,13,201,204,205", "PH-GROUP");
		map.put("5,6,7,8,10,11,18,19,20,21,22", "PH-GROUP");
		return map;
	}

}
