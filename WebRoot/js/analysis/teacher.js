$(document).ready(function() {
	indexShow();//初始加载页面
});

function indexShow(){
	var dom = document.getElementById("chart");
	var myChart = echarts.init(dom);
	var option ={};
	option = {
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line',
		        areaStyle: {}
		    }]
		};
	myChart.setOption(option);
}