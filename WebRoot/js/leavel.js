$(document).ready(function() {
		var tid =window.opener.document.getElementById("tid").value;
		var urls="http://"+_host+":"+_port+"/DataManager/queryData/leavel.do";
		$.ajax({
			url : urls,
			type : "post",
			dataType : "json",
			data : {
				'tid' : tid
			},
			success : function(data) {
				var code = data.code;
				if(code==400){
					alert("sorry,查询异常   >_<");
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
					$("#dataInfo").append("<div style=\"width: auto; text-align: center;\"><h4>teacher_tye:老师类型;1:中教老师    2:外教老师</h4></div>");
					
				}
			}
		});
});

