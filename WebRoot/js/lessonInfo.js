layui.use('form', function() {
	var form = layui.form;
	form.on('submit(go)', function(data) {
		var classId = $("#classId").val();
		var roomId = $("#roomId").val();
		var start_time = $("#start_date").val();
		var end_time = $("#end_date").val();
		query(classId, roomId,start_time,end_time);
		return false; 
	});
});

function query(classId,roomId,start_time,end_time){
	alert(_host);
//	var urls="http://"+_host+":"+_port+"/DataManager/queryData/lessonInfo.do";
	var urls="http://172.16.100.162:8080/DataManager/queryData/lessonInfo.do";
	$.ajax({
		url : urls,
		type : "post",
		dataType : "json",
		data : {
			'classId' : classId,
			'roomId' : roomId,
			'start_date':start_time,
			'end_date':end_time
		},
		catch:false,
		success : function(data) {
			if(data==null){
				layer.open({
					  title: '查询成功',
					  content: '没有匹配数据'
					});  
			}
			else{
				var d = data.data;
				var c = data.columns;
				$("#t_head").empty().append(c);
				$("#t_body").empty().append(d);
			}
		}
	});
}

layui.use('laydate', function() {
	var laydate = layui.laydate;
	laydate.render({
		elem : '#start_date',
		value : '2018-01-01'

	});
	laydate.render({
		elem : '#end_date',
		value : '2018-01-31'

	});
});

layui.use('element', function() {
	var element = layui.element;
});
