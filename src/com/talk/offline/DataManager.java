package com.talk.offline;

import java.util.ArrayList;
import java.util.List;

import com.talk.util.Constant;
import com.talk.util.QueryHelper;
import com.talk.util.QuerySQL;

/**
 * ���ݹ���
 * 
 * @author gaoqiang01
 *
 */
public class DataManager {
	/**
	 * ��ȡ�����н���ʦ����
	 * 
	 * @return
	 */
//	public List<String> getAllCnTeacher() {
//		List<String> cnTeacherList = QueryHelper.queryBySql(QuerySQL.cnTeacherSQL, Constant.DBNAME_MUTILCLASS);
//		System.out.println(cnTeacherList.size());
//		return cnTeacherList;
//	}
//
//	/**
//	 * ��ȡ�н������ʦ����
//	 * 
//	 * @return
//	 */
//	public List<String> getCnLeaveTeacher() {
//		List<String> leaveTeacherList = QueryHelper.queryBySql(QuerySQL.leaveTeacherSQL, Constant.DBNAME_TEANEW);
//		return leaveTeacherList;
//	}
//
//	/**
//	 * ��ȡ�Ѿ��ſε���ʦ����
//	 * 
//	 * @return
//	 */
//	public List<String> getDispatchedTeacher() {
//		List<String> dispatchedTeacherList = QueryHelper.queryBySql(QuerySQL.DispatchedTeacherSQL,
//				Constant.DBNAME_COURSE);
//		return dispatchedTeacherList;
//	}
	
	/**
	 * ��������ſε��н���ʦ
	 * @param cnTeacherList �����н���ʦ
	 * @param leaveTeacherList ����н���ʦ
	 * @param dispatchedTeacherList �Ѿ��ſε��н���ʦ
	 * @return
	 */
	public List<String> getUsableTeacher(List<String> cnTeacherList,List<String> leaveTeacherList,List<String> dispatchedTeacherList){
		List<String> usableTeacherList  =new ArrayList<String>();
		for (int i = 0; i < cnTeacherList.size(); i++) {
			System.out.println(cnTeacherList.get(i));
		}
		return usableTeacherList;
	}
	
//	public static void main(String[] args) {
//		DataManager d = new DataManager();
//		List<String> all = d.getAllCnTeacher();
//		List<String>  p = d.getDispatchedTeacher();
//		List<String>  leave = d.getCnLeaveTeacher();
//		d.getUsableTeacher(all, leave, p);
//		
//	}
}
