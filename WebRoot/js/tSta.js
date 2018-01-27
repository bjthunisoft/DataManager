layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(run)', function(data) {
		var tid = $("#tid").val();
		var st = $("#start_date").val();
		var et = $("#end_date").val();
		var urls = "http://" + _host + ":" + _port
				+ "/DataManager/queryData/teaInfo.do";
		$.ajax({
			url : urls,
			type : 'post',
			dataType : 'json',
			data : {
				'tid' : tid,
				'start_date' : st,
				'end_date' : et
			},
			cache : false,
			success : function(data) {
				var teaInfo = data.tinfo;
				var tlesson = data.tlesson;
				var c = teaInfo.columns;
				var d = teaInfo.res;
				$("#tinfo_title").show();
				$("#tlesson_title").show();
				$("#info_t_head").empty().append(c);
				$("#info_t_body").empty().append(d);
				$("#lesson_t_head").empty().append(tlesson);
			}
		});
		return false;
	});
});

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
