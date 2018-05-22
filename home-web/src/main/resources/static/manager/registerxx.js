var i=1;
function nextStep() {
    var loginName = $("#loginName").val();
    var password = $("#password").val();
    $.ajax({
        url :'/home-web/manager/register/add',
        type : 'POST',
        cache : false,
        async: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({ 'loginName' : loginName ,'password' : password }),
        success : function(data) {
            if(data.success){
                //alert(data.msg);
                window.location="/home-web/manager/register/nextStep";
            }else{
                alert(data.msg);
            }
        },error :function(){
            alert("网络异常，请稍后再试");
        }
    });
}

function addList() {
    alert($("#bankListTable").html());
}

function add_tr(obj) {
    var tr = $(obj).parent().parent();
    tr.after(tr.clone());
}
function del_tr(obj) {
    $(obj).parent().parent().remove();
}
