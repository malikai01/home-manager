/**
 * Created by malikai on 2018-7-10.
 */
var pageSize=10;


$(function(){
    queryAll();
})

function queryAll() {
    var name= $("#name").val();
    var type=$("#type").val();
    var memo=$("#memo").val();
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/home-web/v1/takeNames/query', //请求的url
        cache: false,
        data: {
            "name": name,
            "type": type,
            "memo": memo,
            "pageSize": pageSize
        },
        success: function (data) {
            /*
             * 返回的数据根据自己需要处理
             */
            filteDataAll(data.rows);
            $('#pagination').pagination({
                pageCount: data.total,
                jump: true,
                coping: true,
                homePage: '首页',
                endPage: '末页',
                prevContent: '上页',
                nextContent: '下页',
                keepShowPN: true,
                isHide: false,
                callback: function (api) {
                    var data = {
                        pageIndex: api.getCurrent(),
                        "name": name,
                        "type": type,
                        "memo": memo,
                        "pageSize": pageSize
                    };
                    $.ajax({
                        type: "GET",
                        url: '/home-web/v1/takeNames/query', //请求的url
                        cache: false,
                        dataType: "json",
                        data: data,
                        success: function (res) {
                            filteDataAll(res.rows);
                        }
                    });
                }
            });

        }
    });
}

/* 加载表格 */
function filteDataAll(result){
    $("#takeNamesListTable").html('');
    if(result != null && result.length > 0){
        var takeNames_body = '';
        var myDate = new Date();
        $.each(result,function(i,e){
            takeNames_body += '<tr>';
            takeNames_body+='<td>'+(i+1)+'</td>';
            takeNames_body += '<td style="word-wrap:break-word;word-break:break-all;text-align:center;">'+nullToString(e.name)+'</td>';
            takeNames_body += '<td style="word-wrap:break-word;word-break:break-all;text-align:center;">'+nullToString(e.type)+'</td>';
            takeNames_body += '<td style="word-wrap:break-word;word-break:break-all;text-align:center;">'+nullToString(e.memo)+'</td>';
            takeNames_body += '<td style="word-wrap:break-word;word-break:break-all;text-align:center;">'+nullToString(e.isShow)+'</td>';
            takeNames_body += '<td style="word-wrap:break-word;word-break:break-all;text-align:center;">'+dateFtt("yyyy-MM-dd hh:mm:ss",new Date(e.updateTime))+'</td>';
            takeNames_body += '<td><button style="text-align: center" class="btn btn-success"   type="button" onclick="editName('+e.id+')">修  改</button></td>';
            takeNames_body += '</tr>';
        });
        $("#takeNamesListTable").html(takeNames_body);
    }else{
        $("#takeNamesListTable").html("<tr><td colspan='7'>^_^暂无数据^_^</td></tr>");
    }
}

function empty(obj){
    if(obj != null && obj != '' && obj != 'undefined'){
        return obj ;
    }
    return '';
}

function nullToString(temp){
    if(temp==null){
        return "";
    }else return temp
}
function getStatus(temp){
    if(temp==null){
        return "";
    }else if (temp=="00"){
        return "待审批";
    }else if(temp=="01"){
        return "审批通过";
    }else if(temp=="02"){
        return "审批驳回";
    }else {
        return temp;
    }
}

function reset() {
    $("#name").val("");
    $("#type").val("");
    $("#memo").val("");
}

function addName() {
    window.location='/home-web/v1/takeNams/addHtml';
}
function editName(cId) {
    window.location='/home-web/v1/takeNams/editHtml'+cId;
}

function formatDate(temp){
    if(temp==null || temp==''){
        return "";
    }else{
        dateFtt("yyyy-MM-dd hh:mm:ss",new Date(temp));
    };
}
