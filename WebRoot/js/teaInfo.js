$(document).ready(function() {
		var tid =window.opener.document.getElementById("tid").value;
		var urls="http://"+_host+":"+_port+"/DataManager/queryData/teaInfo.do";
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
					$("#dataInfo").append("<div style=\"width: auto; text-align: center;\">" +
							"<h4>category:老师类别; 1:中教老师    2:外教老师   3:菲教老师</h4>" +
							"<h4>subcategory:老师所在部门; 32122:青少事业部, 32121:美小事业部, 32123:课程产品中心, 32120:B2S事业部</h4>" +
							"<h4>term_type：授课班型 ;1:短期班, 2:长期班</h4>" +
							"<h4>project_type：老师类型;  1 仅为特色班课老师, 2 仅为1对多班课老师</h4>" +
							"</div>");
					
				}
			}
		});
});
