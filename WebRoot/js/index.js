layui.use('form', function(){
  var form = layui.form;
	
	form.on('submit(go)', function(data) {
		var v = $("#dataBase").val();
		var querySql = $("#querySql").val();
		var urls="http://"+_host+":"+_port+"/DataManager/queryData/freeQuery.do";
		$.ajax({
			url : urls,
			type : "post",
			dataType : "json",
			data : {
				'dbName' : v,
				'query' : querySql
			},
			catch:false,
			success : function(data) {
				var code = data.code;
				if(code==100){
					layer.open({
						  title: '查询失败',
						  content: '当前只支持查询操作 >_<'
						});  
				}
				if(code==200){
					layer.open({
						  title: '查询失败',
						  content: '请查询具体的信息  >_<'
						});  
				}
				if(code==300){
					layer.open({
						  title: '查询失败',
						  content: '每次最多只能查询1000条记录  >_<'
						});  
				}
				if(code==400){
					layer.open({
						  title: '查询失败',
						  content: '兄弟你的SQL写错了    >_<'
						});   
				}
				if(code==800){
				var d = eval(data.data);
				var c = eval(data.columns);
				var columnHead ="";
				for(var i=0;i<c.length;i++){
					columnHead+="<th>"+c[i]+"</th>";
				}
				var thead ="<tr><th>ID</th>"+columnHead+"</tr>";
				
				$("#t_head").empty().append(thead);
				var tbody ="";
				for (var i = 0; i < d.length; i++) {
					var td="";
					var dobject = d[i];
					for(var j = 0;j < c.length; j++){
						var columnName =c[j].toString();
						td+="<td>"+d[i][columnName]+"</td>";
					}
					tbody += "<tr><td>"+(i+1)+"</td>"+td+"</tr>";
				}
				$("#t_body").empty().append(tbody);
				}
			
				
				
			}
		});
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	
	
});

layui.use('element', function(){
	  var element = layui.element;
	  
	  //一些事件监听
	  element.on('tab(demo)', function(data){
	    console.log(data);
	  });
	});

