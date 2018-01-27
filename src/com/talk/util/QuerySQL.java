package com.talk.util;

public interface QuerySQL {
	/**
	 * 所有中教老师
	 */
	public static final String cnTeacherSQL = "SELECT id, sex, entry_time, departure_time, "
			+ "category, project_type, sso_id, subcategory, real_name "
			+ "FROM mc_teacher WHERE category = 1 AND working_state = 1 "
			+ "AND teaching_state = 1 AND project_type = 2";

	/**
	 * 已经排课的老师 不分中外教
	 */
	public static final String DispatchedTeacherSQL = "SELECT room_id, tea_id, tea_type,  start_time, end_time, "
			+ "class_id FROM base_course_lesson WHERE room_id > 0 AND tea_id > 0 AND "
			+ "start_time >= DATE_FORMAT(NOW(),'%Y-%m-%d') AND " + " (base_course_lesson.status = 1 or"
			+ " base_course_lesson.status = 7 or" + " base_course_lesson.status = 3 or"
			+ " base_course_lesson.status = 4)" + " ORDER BY end_time DESC";
	/**
	 * 中教请教老师
	 */
	public static final String leaveTeacherSQL = "SELECT sso_id, DATE, slot FROM otm_open_slot";
}
