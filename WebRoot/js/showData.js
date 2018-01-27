$(document).ready(function() {
		var v =window.opener.document.getElementById("dataBase").value;
		var querySql = window.opener.document.getElementById("querySql").value;
		var urls="http://"+_host+":"+_port+"/DataManager/queryData/freeQuery.do";
		$.ajax({
			url : urls,
			type : "post",
			dataType : "json",
			data : {
				'dbName' : v,
				'query' : querySql
			},
			success : function(data) {
				var code = data.code;
				if(code==100){
					alert("兄弟你的SQL是非法的   >_<");
				}
				if(code==200){
					alert("请查询具体的信息  >_<");
				}
				if(code==300){
					alert("每次最多只能查询1000条记录  >_<");
				}
				if(code==400){
					alert("兄弟你的SQL写错了    >_<");
				}
				if(code==800){
					var d = eval(data.data);
					var c = eval(data.columns);
					var tr ="";
					var columnHead ="";
					for(var i=0;i<c.length;i++){
						columnHead+="<td>"+c[i]+"</td>";
					}
					var firstTr ="<tr>"+columnHead+"</tr>";
					for (var i = 0; i < d.length; i++) {
						var td="";
						var dobject = d[i];
						for(var j = 0;j < c.length; j++){
							var columnName =c[j].toString();
							td+="<td>"+d[i][columnName]+"</td>";
						}
						 tr += "<tr>"+td+"</tr>";
					}
					$("#dataInfo").empty().append("<table style=\"width:60%;height:50%\">"+firstTr+tr+"</table>");
				
				}
			}
		});
});
