var i=1;
function nextStep() {
    if($('#loginName').val()=="" ){
        alert("家庭名称不能为空");
        return false;
    }
    if($('#password').val()=="" ){
        alert("密码不能为空");
        return false;
    }
    checkPassword();
    checkCode()
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
                var loginId=data.obj;
                window.location="/home-web/manager/register/nextStep/"+loginId;
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

function saveBinding(e){
    var name=$('#name').val();
    var age=$('#age').val();
    var memo=$('#memo').val();
    var gender=$('#gender').val();
    var relation=$('#relation').val();
    var loginId=$('#loginId').val();
    var id = $('#id').val();

    var $btn = $(e);
    $btn.button('loading');

    setTimeout(function() {
        $.ajax({
            url:"/home-web/manager/register/binding",
            contentType: "application/json;charset=utf-8",
            clearForm : false,
            resetForm : false,
            type : 'post',
            data: JSON.stringify({ 'id' : id,'name' : name ,'age' : age,'memo' : memo,'gender' : gender,'relation' : relation,'loginId':loginId }),
            success : function(data) {
                $btn.button('reset');
                if(data.success){
                    alert(data.msg);
                    getBinding(data);
                    emptyBinding();
                }else{
                    alert(data.msg);
                }
            },error:function(data){
                alert("网络异常，请稍后再试");
                $btn.button('reset');
            }
        });
    }, 100);
}

function getBinding(data){
    $("#bindingTable tbody").html('');
    var htmlt="";
    var data = data.obj;//得到一个LIST
    if(data){
        for(var i = 0;i<data.length;i++){  //循环LIST
            var binding = data[i];//获取LIST里面的对象
            var gender= binding.gender=="00"?"男":"女";
            var name = binding.name == null?"": binding.name;
            var age = binding.age == null ? "":binding.age;
            var relation = binding.relation == null ? "" : binding.relation;
            var memo = binding.memo == null || binding.memo == "null" ? "":binding.memo;
            htmlt=htmlt+'<tr>';
            htmlt=htmlt+'<td style="word-wrap:break-word;word-break:break-all;">'+name+'</td>';
            htmlt=htmlt+'<td>'+age+'</td>';
            htmlt=htmlt+'<td style="word-wrap:break-word;word-break:break-all;">'+relation+'</td>';
            htmlt=htmlt+'<td>'+gender+'</td>';
            htmlt=htmlt+'<td style="word-wrap:break-word;word-break:break-all;">'+memo+'</td>';
            htmlt=htmlt+'<td style="word-wrap:break-word;word-break:break-all;">';
            htmlt=htmlt+'<a href="javascript:;"  class="blue" style="padding-right:5px" onclick="editBinding(\''+binding.id+'\',\''+binding.name+'\',\''+binding.age+'\',\''+binding.relation+'\',\''+binding.gender+'\',\''+binding.memo+'\')">编辑</a>';
            htmlt=htmlt+'<a href="javascript:;"  class="blue"  onclick="delBinding(\''+binding.id+'\')">删除</a>';

            htmlt=htmlt+'</td></tr>';
        }
    }
    $("#bindingTable tbody").html(htmlt);
}

function editBinding(id,name,age,relation,gender,memo){
    emptyBinding();
    $("#id").val(id);
    $("#name").val(name);
    $("#age").val(age);
    $("#relation").val(relation);
    $("#gender").val(gender);
    $("#memo").val(memo);
    $("#name").focus()
}

function emptyBinding(){
    $("#id").val("");
    $("#name").val("");
    $("#age").val("");
    $("#relation").val("");
    $("#gender").val("");
    $("#memo").val("");

}

function delBinding(id){

    if(confirm("您确定要删除这条绑定信息吗")){
        $.ajax({
            type : "post",
            async:false,
            cache:false,
            url :"/home-web/manager/register/cancelBinding/"+id,
            dataType : "json",
            success : function(data) {
                if(data.success){
                    alert(data.msg);
                    getBinding(data);
                    emptyBinding();
                }else{
                    alert(data.msg);
                }
            },error:function(data){
               alert("网络异常,请稍后再试!");
            }
        });

    }
}

function toLogin() {
    window.location="/home-web/manager/login";
}

function checkPassword() {
    var password = $('#password').val();
    var confirm = $('#confirm').val();
    if(password != confirm){
        alert("密码不一致");
    }
}
// 验证码验证
function checkCode() {
    var code = $("#veryCode").val();
    // alert(code);
    $.ajax({
        type : "POST",
        url : "/home-web/veryCode/checkCode",
        data : {"code":code},
        success : function (data) {
            if(data==0){
                alert("验证码失效");
                return false;
            }
        }
    });
}