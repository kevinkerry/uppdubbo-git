/**
 * 公共JS文件 通过tyjs.aaa()调用
 */
var tyjs = window.tyjs || {};
(function(p){
	function aaa(){
		alert(3)
	}
	p.aaa = aaa;
	
	/**
	 * 取地址栏参数
	 */
	function getUrlParam(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		if (r!=null) return unescape(r[2]); return ""; //返回参数值
	}
	p.getUrlParam = getUrlParam;
	
	/**
	 * 取SESSION_SID
	 */
	function getSid(){
		return getUrlParam("SID");
	}
	p.getSid = getSid;
	
	function ajax(request,success1,error1,returnLogin,tmpUrl){
		var reqUrl = "ajax.htm?js="+Math.random();
		if (typeof(tmpUrl) != "undefined") { 
			reqUrl = tmpUrl;
			if(reqUrl.indexOf("?")!=-1){
				reqUrl = reqUrl + "&js="+Math.random();
			}else{
				reqUrl = reqUrl + "?js="+Math.random();
			}
		}
		$.ajax({
	        type: "post",
	        async: false,
	        url: reqUrl,
	        dataType: "json",
	        data : $.toJSON(request),
	        beforeSend: function(){
	            
	        },
	        success: function (msg) {
	        	if( typeof(msg.responseHead)!='undefined' && msg.responseHead.returnCode=="0" ){
	        		success1(msg);
	        	}else{
	        		if(typeof(error1) != 'undefined'){
	        			error1(msg);
	        		}
	        		if (typeof(returnLogin) == "undefined" || returnLogin) {
	        			alert("超时，请重新登录！")
	        			window.location.replace("./adminLogin.html");
	        		}
	        	}
	        },
	        error: function(msg) {
	        	if (typeof(exception) == "undefined") { 
	        		alert(exception.toString());
	        	}else{
	        		alert("系统错误！");
	        	}  
	     	}
	     });
	}
	p.ajax = ajax;
	
	/**
	 * 通过NAME获取第一个对象值
	 */
	function getNameVal(nameStr){
		if(nameStr!=null && nameStr != ""){
			return $("[name='"+nameStr+"']:eq(0)").val()
		}
	}
	
	/**
	 * 检查金额
	 */
	function checkPrice(price){
	  return (/^(?!0(\.0+)?$)([1-9][0-9]*|0)(\.[0-9]+)?$/).test(price.toString());
	}
	p.checkPrice = checkPrice;
	
	/**
	 * 取页面高度
	 */
	function getTop(){
		var bodyTop = 0;
		if (typeof window.pageYOffset != 'undefined') {
			bodyTop = window.pageYOffset;
		} else if (
			typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat'){
			bodyTop = document.documentElement.scrollTop;
		} else if (typeof document.body != 'undefined') {
			bodyTop = document.body.scrollTop;
		}
		return bodyTop;
	}
	p.getTop = getTop;
	
	var dataTableLanage = {
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sZeroRecords": "抱歉， 没有数据！",
			"sSearch": "搜索",
			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			"sInfoEmpty": "没有数据",
			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
			}
	}
	p.dataTableLanage=dataTableLanage;
})(tyjs);



/**
 * $("form").serializeObject() 可以将FORM中的值转为JSON串
 */
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
        	if(!this.value == "" && this.value !== undefined){
        		o[this.name] = this.value || '';
        	}
        }
    });
    return o;
}
