/**
 * Created by malikai on 2018-5-22.
 */
function add() {
    $.ajax({
        url : $('body').attr('srmctx') + '/manager/register/add', type : 'POST',
        cache : false, async: false, dataType : 'json',
        data: $("#register_form").serialize() , success : function(data) {
            if(data.success){
                alert(data.msg);
            }else{
                alert(data.msg);
            }
            $btn.button('reset');
        },error :function(){
            alert("网络异常，请稍后再试");
            $btn.button('reset');
        }
    });
}
