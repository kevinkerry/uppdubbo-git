	
	var wxOpenId = "gh_4932e9876079";
	var openId = "oTyIUwSbxO3LCN0W3YKYwfkUqpDM";

var weChatPay = window.weChatPay || {};
(function(p){
	
	var noncestr="";
	var timestamp="";
	var signature="";
	var prepay_id="";
	var paySign="";
	
function onBridgeReady(){
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId" : "wx14fa9d10b56feaed",     //公众号名称，由商户传入     
           "timeStamp":""+timestamp,         //时间戳，自1970年以来的秒数     
           "nonceStr" : noncestr, //随机串     
           "package" : "prepay_id="+prepay_id,     
           "signType" : "MD5",         //微信签名方式：     
           "paySign" : paySign //微信签名
       },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request：ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       }
   ); 
}

function getWX(Uid,price,prepay_id1,noncestr1,timestamp1,signature1,paySign1) {
	
	noncestr = noncestr1;
	timestamp = timestamp1;
	signature = signature1;
	prepay_id = prepay_id1;
	paySign = paySign1;
	
	wx.config({
		debug : false,
		appId : "wx14fa9d10b56feaed",
		timestamp : ""+timestamp,
		nonceStr : noncestr,
		signature : signature,
		jsApiList : [ 'checkJsApi', 'chooseWXPay']
	});
	
	onBridgeReady();
	
//	var request = {   
//	           "requestHead":{"ACTION_SID":"ScanPayBusiness", "SESSION_SID":''},
//	           "reqData":{"total_fee":price,"trade_type":"JSAPI","openid":openId,"WxOpenId":wxOpenId,"UserId":Uid},
//	   	};
	
//	$.ajax({
//	  type: "post",
//	  async: false,
//	  url: "ScanPayBusiness.htm",
//	  dataType: "json",
//	  data : $.toJSON(request),
//	  beforeSend: function(XMLHttpRequest){
//	      XMLHttpRequest.setRequestHeader("RequestType", "ajax");
//	  },
//	  success: function (msg) {
//	  	if( typeof(msg.responseHead)!='undefined' && msg.responseHead.returnCode=="0" ){
//	  		noncestr = msg.noncestr;
//	  		timestamp = msg.timestamp;
//	  		signature = msg.signature;
//	  		prepay_id = msg.resData.prepay_id;
//	  		paySign = msg.paySign;
//	  		if (typeof WeixinJSBridge == "undefined") {
//	  			if (document.addEventListener) {
//	  				document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
//	  						false);
//	  			} else if (document.attachEvent) {
//	  				document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
//	  				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
//	  			}
//	  		} else {
//	  			onBridgeReady();
//	  		}
//	  	}else{
//	  		alert("支付金额不正确或已支付！");
//	  	}
//	  },
//	  error: function(data) {
//	   alert(exception.toString());
//	  }
//	});
}
p.getWX=getWX
})(weChatPay);