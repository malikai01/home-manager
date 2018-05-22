
function nextStep() {
    $.ajax({
        url :'/register/add', type : 'POST',
        cache : false, async: false, dataType : 'json',
        data: $("#register_form").serialize() , success : function(data) {
            if(data.success){
                //alert(data.msg);
                window.open("/register/nextStep");
            }else{
                alert(data.msg);
            }
        },error :function(){
            alert("网络异常，请稍后再试");
        }
    });
}
