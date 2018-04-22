$(document).ready(
		function() {
			$("#jqGridStudent").jqGrid(
					{
						url : 'studentList',
						mtype : "get",
						datatype : "json",
						styleUI : 'Bootstrap',// 设置jqgrid的全局样式为bootstrap样式
						colModel : [
								{
									label : 'ID',
									name : 'id',
									key : true,
									sortable : false,
								},{
									label : '名字',
									name : 'name',
									key : true,
									sortable : false,
									
								},
								{
									label : '年龄',
									name : 'age',
									key : true,
									sortable : false,
								
								},
								{
									label : '身高',
									name : 'height',
									key : true,
									sortable : false,
									
								},
								{
									label : '体重',
									name : 'weight',
									sortable : false,
								}],
						viewrecords : true,
						pager : "#jqGridPagerStudent",
						multiselect : false,
						rownumbers : true,
						autowidth : true,
						height : "100%",
						rowNum : 2,
						rownumbers : true, // 显示行号
						subGrid : false
					// 是否启用子表格
					});

		});

function formSubmirReset() {
	$("#name").val('');
    $("#age").val('');
}
function inputexe() {
    window.open("studentExport","_blank");
}
function searchTable() {
    var name = $('#name').val();// 姓名
    var age = $('#age').val();// 年龄
    $("#jqGridStudent").jqGrid("setGridParam", {
        datatype : 'json',
        postData : {
            'name' : name,
            'age' : age
        }, // 发送数据
        page : 1
    }).trigger("reloadGrid");
}

$(function(){
    var ouid='101';
    var myChart = echarts.init(document.getElementById('main'));
    $.getJSON("showChart",{ouid:ouid},
        function(data){
            //alert("data:"+data.xx);
            myChart.setOption({
                title: {
                    text: '学生年龄统计'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data:['年龄']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                	name:'姓名',
                    data: data.namelist
                },
                yAxis: [
                    {
                        type: 'value',
                        name: '年龄(岁)',
                        min: 0,

                        position: 'left',
                        axisLabel: {
                            formatter: '{value} 岁'
                        }
                    }
                ],
                series: [{
                    name: '年龄',
                    type: 'line',
                    yAxis: 1,
                    data: data.agelist
                }]

            });

        });
});