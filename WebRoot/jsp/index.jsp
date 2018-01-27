<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>DataManager</title>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/jquery.min.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath() %>/js/common/jquery.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath() %>/js/queryBySQL.js"></script>
</head>
<body>

<h3 style="width: 100%;text-align: center">自定义查询</h3>
	<div style="text-align: center">
	<select id="dataBase">
	<option value="teanew">中教库(teanew)</option>
	<option value="course">课程库(talkplatfrom_course)</option>
	<option value="multi_class">老师库(multi_class)</option>
	<option value="tea">外教老师库(talkplatform_teacher)</option>
	<option value="talk">talk</option>
	</select>
	</div>
	
	<div style="text-align: center;padding-top: 15px;">
	<div style="padding-right: 870px;">查询语句</div>
	<textarea rows="2" cols="120" id="querySql">SELECT sso_id, DATE, slot FROM otm_open_slot limit 10;</textarea>
	<button id="freeQuery">提交查询</button>
	</div>
	<br/>
	
	
	<h3 style="width: 100%;text-align: center">老师信息查询</h3>
	<div style="text-align: center">
	老师id:  <input id="tid"></input>
	</div>
	<div style="text-align: center;padding-top: 15px;">
	<button id="leavelQuery">老师请假信息</button>
	<button id="teaInfoQuery">老师详细信息</button>
	<button id="cousreLesson">老师排课信息</button>
	</div>
	<br/>
	
	
</body>
</html>