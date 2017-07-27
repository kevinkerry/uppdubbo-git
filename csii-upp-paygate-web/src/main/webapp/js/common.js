/////////////////////////////////////////////////////////////////
// 通用js方法库相关。
//
////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
// global vars defination
////////////////////鼠标及键盘事件控制////////////////////////////
		function OverKey(){
			 if((event.keyCode>=112&&event.keyCode<=133)) {
			   event.keyCode=0;
			   event.returnValue   =   false;
			}
			 if ((event.keyCode==116)|| //屏蔽 F5 刷新键
			   (event.ctrlKey && event.keyCode==82)){ //Ctrl + R
			   event.keyCode=0;
			   event.returnValue=false;
			}
			 if ((event.ctrlKey)&&(event.keyCode==78)){ //屏蔽 Ctrl+n
			   event.returnValue=false;
			   }
			 if ((event.ctrlKey)&&(event.keyCode==67)){ //屏蔽 Ctrl+c
				//   event.returnValue=false;
				   }

			   if ((event.shiftKey)&&(event.keyCode==121)){ //屏蔽 shift+F10
			   event.returnValue=false;
			   }

			 if ((window.event.altKey)&&
			     ((window.event.keyCode==37)|| //屏蔽 Alt+ 方向键 ←
			     (window.event.keyCode==39))){ //屏蔽 Alt+ 方向键 →
			  event.returnValue=false;
			}

			 if((event.ctrlKey)&&((window.event.keyCode==37)||(window.event.keyCode==39))){
				 event.keyCode=0;
				 event.returnValue=false;
			 }
			if (event.keyCode==13) {
				event.returnValue=true;
			}
		}

		document.onkeydown=OverKey;

		/*
		 *	阻止网页上的右键操作
		 *
		 */
		 function Click(){
		 	window.event.returnValue=false;
		 }

		document.oncontextmenu=Click;//阻止网页右键的脚本

		history.go(1);//阻止IE后退???

///////////////////////////////////////////////////////////////
// 将金额数字转换为标准的格式
function standMoney(inObject){
	// 首先检查字符串的所有字符是否均为数字、小数点或逗号分隔符形式
	var inStr = inObject.value;
    outStr=formatMoney(inStr);
	inObject.value = outStr;
	return;
}

function formatMoney(moneyValue){
	// 首先检查字符串的所有字符是否均为数字、小数点或逗号分隔符形式
	var inStr = moneyValue
	var i,charValue,outStr;
	for(i=0;i<inStr.length;i++) {
		charValue = inStr.charAt(i);
		if(isNaN(parseInt(charValue,10))&&(charValue!=".")&&(charValue!=",")) {
			return moneyValue
		}
	}

	// 以小数点为分界，分别处理整数和小数部分
	var valueArr;
	valueArr= inStr.split(".");
	if(valueArr.length>2) {
		return moneyValue
	}

	// 处理小数部分
	var dotStr,dotValue;
	if(valueArr.length==2) {
		dotValue = valueArr[1];
		if(dotValue.length==0) {
			dotStr = "00";
		}
		else {
			if(dotValue.length==1)
				dotStr = dotValue + "0";
			else
				dotStr = dotValue.substring(0,2);
		}
	}
	else {
		dotStr = "00";
	}

	// 处理整数部分
	var intArr;
	intArr = valueArr[0].split(",");
	// 无论整数部分是否已经用逗号分隔开，都将其合并成一个整体
	var intValue;
	intValue = "";
	for(i=0;i<intArr.length;i++) {
		intValue+=intArr[i];
	}

	// 将整数部分用逗号进行分隔
	var intStr = "";
	while(intValue.length>3) {
		intStr=","+intValue.substring(intValue.length-3,intValue.length) + intStr;
		intValue = intValue.substring(0,intValue.length-3);
	}
	intStr = intValue + intStr;

	//最后将处理后的整数部分与小数部分合并，作为输出
	if( intStr == "" || intStr == null ) {
		intStr = "0"
	}
	outStr = intStr + "." + dotStr;
/*	if( outStr == 0.0 ) {
		alert("金额不得输入为零!")
		inObject.focus()
		inObject.select()
		return
	} */

	return outStr;
}

// 恢复成数字串的格式，删掉分节符
function revertMoney(inObject,flag)
{
	var inStr = inObject.value
	var outStr="";
	var ch;

	for(i=0;i<inStr.length;i++)
	{
		ch = inStr.charAt(i);
		if(ch!=',')
		{
			outStr += ch;
		}
		else {
			continue;
		}
	}
	inObject.value = outStr;
	if( flag )
		inObject.select()
	return;
}

// 恢复成数字串的格式，删掉分节符
function revertMoney2(inStr)
{
	var outStr="";
	var ch;
	for(i=0;i<inStr.length;i++)
	{
		ch = inStr.charAt(i);
		if(ch!=',')
		{
			outStr += ch;
		}
		else {
			continue;
		}
	}
	return outStr;
}

//将小写金额转换为大写
function revertMoney3(amount){
var ZDX = ""
var Prc
var Str = new Array(10);
var Cha = new Array(14);
var China;
China = "分角元拾佰仟万拾佰仟亿"
Str[0] = "零"
Str[1] = "壹"
Str[2] = "贰"
Str[3] = "叁"
Str[4] = "肆"
Str[5] = "伍"
Str[6] = "陆"
Str[7] = "柒"
Str[8] = "捌"
Str[9] = "玖"
Cha[0]="分"
Cha[1]="角"
Cha[3]="元"
Cha[4]="拾"
Cha[5]="佰"
Cha[6]="仟"
Cha[7]="万"
Cha[8]="拾"
Cha[9]="佰"
Cha[10]="仟"
Cha[11]="亿"
Cha[12]="拾"
Cha[13]="佰"
var outStr=""
var ch
for(i=0;i<amount.length;i++)
  {
	ch = amount.charAt(i);
	if(ch!=',')
	{
		outStr += ch;
	}
	else {
		continue;
	}
  }
var amstr = outStr.substring(0,outStr.length-3)
if (amstr.length>11){
alert("非法金额")
return ZDX
}
var lnP = outStr.length
for(i=lnP-1;i>=0;i--)
{
out = outStr.substring(lnP-i-1,lnP-i)
var chout = i
if (chout!="2"){
	ZDX=ZDX+Str[out]+Cha[chout]
}
}
return ZDX;
}

function standStringAmount(inStr)
{
	// 首先检查字符串的所有字符是否均为数字、小数点或逗号分隔符形式
	if(inStr.length == 0)
		return

	var i,charValue,outStr;
	for(i=0;i<inStr.length;i++) {
		charValue = inStr.charAt(i);
		if(isNaN(parseInt(charValue,10))&&(charValue!=".")&&(charValue!=",")) {
			alert(inStr+" 非法金额!")
			return
		}
	}

	// 以小数点为分界，分别处理整数和小数部分
	var valueArr;
	valueArr= inStr.split(".");
	if(valueArr.length>2) {
		alert(inStr+" 非法金额!")
		return
	}

	// 处理小数部分
	var dotStr,dotValue;
	if(valueArr.length==2) {
		dotValue = valueArr[1];
		if(dotValue.length==0) {
			dotStr = "00";
		}
		else {
			if(dotValue.length==1)
				dotStr = dotValue + "0";
			else
				dotStr = dotValue.substring(0,2);
		}
	}
	else {
		dotStr = "00";
	}

	// 处理整数部分
	var intArr;
	intArr = valueArr[0].split(",");
	// 无论整数部分是否已经用逗号分隔开，都将其合并成一个整体
	var intValue;
	intValue = "";
	for(i=0;i<intArr.length;i++) {
		intValue+=intArr[i];
	}

	// 将整数部分用逗号进行分隔
	var intStr = "";
	while(intValue.length>3) {
		intStr=","+intValue.substring(intValue.length-3,intValue.length) + intStr;
		intValue = intValue.substring(0,intValue.length-3);
	}
	intStr = intValue + intStr;

	//最后将处理后的整数部分与小数部分合并，作为输出
	if( intStr == "" || intStr == null ) {
		intStr = "0";
	}
	outStr = intStr + "." + dotStr;
	return outStr;
}

function popUpDlg(url)
{
   showx = event.screenX - event.offsetX - 4 - 100 ; // + deltaX;
   showy = event.screenY - event.offsetY + 18; // + deltaY;
   if(url==null)
   return;
   retval = window.showModalDialog(url,window, "dialogWidth:300px; dialogHeight:480px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;location=no; "  );
   return retval;
}

//页面校验信息显示在EEE位置
function throwValidationAtEEE(valiInfo,place)
{
	document.getElementById(place).innerHTML=valiInfo;
	//PEGetElement(place).innerHTML = valiInfo;
//	showError(place, valiInfo);
}

function restoreMoney(inStr)
{
	var outStr="";
	var ch;

	for(i=0;i<inStr.length;i++)
	{
		ch = inStr.charAt(i);
		if(ch!=',')
		{
			outStr += ch;
		}
		else {
			continue;
		}
	}
	if(outStr.indexOf(".")==-1){
		outStr+=".00";
	}
	return outStr;
}

//显示大写中文金额函数  整数、小数部分的显示功能都已完善
function changetobig(str){
var str1=restoreMoney(str + "");
var i=0;
var ss="";
i=str1.indexOf(".");
var t=0;

for(var k=i;k>=0;k--){

if(str1.charAt(k)=="0"){
    if(t!=0){
    ss+="零";
    }

  }
  if(str1.charAt(k)=="1"){
    ss+="壹";
  }
  if(str1.charAt(k)=="2"){
    ss+="贰";
  }
  if(str1.charAt(k)=="3"){
    ss+="叁";
  }
  if(str1.charAt(k)=="4"){
    ss+="肆";
  }
  if(str1.charAt(k)=="5"){
    ss+="伍";
  }
  if(str1.charAt(k)=="6"){
    ss+="陆";
  }
  if(str1.charAt(k)=="7"){
    ss+="柒";
  }
  if(str1.charAt(k)=="8"){
    ss+="捌";
  }
  if(str1.charAt(k)=="9"){
    ss+="玖";
  }

  if(t==1){  ss+="元";}
  if(t==2){  ss+="拾";}
  if(t==3){  ss+="佰";}
  if(t==4){  ss+="仟";}
  if(t==5){  ss+="万";}
  if(t==6){  ss+="拾";}
  if(t==7){  ss+="佰";}
  if(t==8){  ss+="仟";}
  if(t==9){  ss+="亿";}
  if(t==10){ ss+="拾";}
  if(t==11){ ss+="佰";}
  if(t==12){ ss+="仟";}
  if(t==13){ ss+="万";}
  ss+=",";t++;

}
var jm=ss.split(",");
var re="";
var mu="";
var u=jm.length-1;
for(u;u>=0;u--){
re+=jm[u];
}
var f=0;
var mm=i+1;
for(mm;mm<str1.length;mm++){
 if(str1.charAt(mm)=="0"){
    mu+="零";
  }
  if(str1.charAt(mm)=="1"){
    mu+="壹";
  }
  if(str1.charAt(mm)=="2"){
    mu+="贰";
  }
  if(str1.charAt(mm)=="3"){
    mu+="叁";
  }
  if(str1.charAt(mm)=="4"){
    mu+="肆";
  }
  if(str1.charAt(mm)=="5"){
    mu+="伍";
  }
  if(str1.charAt(mm)=="6"){
    mu+="陆";
  }
  if(str1.charAt(mm)=="7"){
    mu+="柒";
  }
  if(str1.charAt(mm)=="8"){
    mu+="捌";
  }
  if(str1.charAt(mm)=="9"){
    mu+="玖";
  }
  if(f==0){mu+="角";}
  if(f==1){mu+="分";}
  f++;
}
if(mu=="零角零分"){
re+="整";
}else{
re+=mu;
}
var results="";
var mresults="";
var temp="";
var lflags='false';
var lflag1='false';

for(var ink=0;ink<re.length;ink++){
  if(lflags=='true'){

	  if(re.charAt(ink)=="亿"||re.charAt(ink)=="万"||re.charAt(ink)=="元")


	    results+=re.charAt(ink);


     lflags="false";
      continue;
  }
  if(re.charAt(ink)!="零"){
	  if(lflag1=='true'&&re.charAt(ink)!="整"){
	  results+="零";
	   }
	results+=re.charAt(ink);
	lflags="false";
	lflag1="false";

  }else {

     lflags="true";
     lflag1="true";
  }

}
  for(var mts=0;mts<results.length;mts++){
   mresults+=results.charAt(mts);
   if(results.charAt(mts)=="亿"){

   if(results.charAt(mts+1)=="万"){

   mts++;
   }

   }

   }

 if(mresults.indexOf("元零")==0 )
     mresults=mresults.substring(2);
 if(mresults.indexOf("元整")==0 || mresults.indexOf("万零")==0 || mresults.indexOf("亿零")==0)
     mresults=mresults.substring(2);
 if(mresults.indexOf("零")==0 )
     mresults=mresults.substring(1);
 if(mresults.indexOf("万元整")==0 || mresults.indexOf("亿元整")==0 )
     mresults=mresults.substring(3);


 return mresults;
}
// 获取大写金额
function getBigStringAmount() {
	var v1;
	var v2;
	if (document.form1.Amount.value!=''){
		var v1 = standStringAmount(document.form1.Amount.value); //标准金额格式
		var v2 = changetobig(v1); //金额大写格式
	}
	else {
		v1 = '';
		v2 = '';
	}
	if($("#ggg").size()>0){
		$("#ggg").html(v1);
	}
	document.getElementById("fff").innerHTML=v2;
}

/**
*得到币种的中文描述
*@param currency 输入币种
*/
function fmtCurrencyCN(currency) {
	var rtn="";
	switch(currency){
		case "AUD": rtn="澳大利亚元";break;
		case "CAD": rtn="加拿大元";break;
		case "CHF": rtn="瑞士法郎";break;
		case "CNY": rtn="人民币";break;
		case "EUR": rtn="欧元";break;
		case "GBP": rtn="英镑";break;
		case "HKD": rtn="港币";break;
		case "JPY": rtn="日元";break;
		case "NZD": rtn="新西兰元";break;
		case "SGD": rtn="新加坡元";break;
		case "USD": rtn="美元";break;
	}
	return rtn;
}

//去掉首尾空格
function trim(s) {
	return s.replace(/^\s+/,"").replace(/\s+$/, "");
}

function powerConfig(args) {	
	var defaults = { "width":150, "height":22, "maxLength":12, "minLength":6, "maskChar":"#", "backColor":"#ECE9D8", "textColor":"#FF0000", "borderColor":"#7F9DB9", "accepts":"\\w{0,}","caption":"南海农商银行" };
	for (var p in args)
		if (args[p] != null) defaults[p] = args[p];
	return defaults;
};


//新 3 页面交易相关js
/*window.onerror = function()
{
	return true;//不显示脚本错误信息
};
*/

function fillHiddenValue(vHiddenName,vValue){
    var vHiddenObj = document.getElementsByName(vHiddenName);
	vHiddenObj[0].value=vValue;
}
/*
 *value值为账户账号
 *step是传递分割单位数
 */
function formAtAcNo(value,step){
	 var k=value.length;
	 var stringbuffer="";
	 for(var i=0;i<k;i++){
		 if((i!=0)&&(i%step==0)&&(step!=0)){
			 if(parseInt(i/step)==parseInt(k/step)){
				 if(!(k-i==1)){
					 stringbuffer=stringbuffer+" ";
				 }
			 }else{
				 stringbuffer=stringbuffer+" ";
			 }
		 }
		 stringbuffer=stringbuffer+value.charAt(i);
	 }
	 return stringbuffer;
} 

function standStringAmount(inStr)
{
	// 首先检查字符串的所有字符是否均为数字、小数点或逗号分隔符形式
	if(inStr.length == 0)
		return

	var i,charValue,outStr;
	for(i=0;i<inStr.length;i++) {
		charValue = inStr.charAt(i);
		if(isNaN(parseInt(charValue,10))&&(charValue!=".")&&(charValue!=",")) {
			alert(inStr+" 非法金额!")
			return
		}
	}

	// 以小数点为分界，分别处理整数和小数部分
	var valueArr;
	valueArr= inStr.split(".");
	if(valueArr.length>2) {
		alert(inStr+" 非法金额!")
		return
	}

	// 处理小数部分
	var dotStr,dotValue;
	if(valueArr.length==2) {
		dotValue = valueArr[1];
		if(dotValue.length==0) {
			dotStr = "00";
		}
		else {
			if(dotValue.length==1)
				dotStr = dotValue + "0";
			else
				dotStr = dotValue.substring(0,2);
		}
	}
	else {
		dotStr = "00";
	}

	// 处理整数部分
	var intArr;
	intArr = valueArr[0].split(",");
	// 无论整数部分是否已经用逗号分隔开，都将其合并成一个整体
	var intValue;
	intValue = "";
	for(i=0;i<intArr.length;i++) {
		intValue+=intArr[i];
	}

	// 将整数部分用逗号进行分隔
	var intStr = "";
	while(intValue.length>3) {
		intStr=","+intValue.substring(intValue.length-3,intValue.length) + intStr;
		intValue = intValue.substring(0,intValue.length-3);
	}
	intStr = intValue + intStr;

	//最后将处理后的整数部分与小数部分合并，作为输出
	if( intStr == "" || intStr == null ) {
		intStr = "0"
	}
	outStr = intStr + "." + dotStr;
	return outStr;
}
//截取过长字符串用特殊字符代替
function getOmissionStr(value,length,symbol){
	if(null!=value&&value!='undefined'){
		//如果字符串不大于设定长度，则直接显视原值
		if(value.length > length){
			value = value.substring(0,length) + symbol;
		}		
		return value;
	}
}
//限定输入数字
function mustDigit()
{
	if(event.keyCode<48||event.keyCode>57)
	{
		event.returnValue=false;
	}
//	if(event.keyCode==46)
//	{
//	event.returnValue=true;
//	}
}

//新加js
//JavaScript Document

$(document).ready(function(){

	// 选卡
	$(".tradeTab li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
	});
	
	// 步骤根据数量宽度自适应
	var setpNum  = $(".setpBox dd").length;
	$(".setpBox").css("width",$(".setpBox dd").width() * setpNum + 60 * setpNum); 
	
	//弹出层
	$("#person").click(function(){
		var windowHeight = $(window).height(); 
		var scrollHeight = $(document).scrollTop();
		$(".fade").css("height",$(document).height());
		$(".openwinOtherBank").css("left",($(document).width() - $(".openwinOtherBank").width() - 20)/2); 
		$(".openwinOtherBank").css("top",(windowHeight - $(".openwinOtherBank").height() - 20) / 2 + scrollHeight ); 
    	$(".openwinOtherBank,.fade").slideDown();
		$("body").css("overflow","hidden");
	});
	$(".openwinOtherBank .tab .closeBox,.openwinOtherBank .btn_box input").click(function(){
    	$(".openwinOtherBank,.fade").slideUp();
		$("body").css("overflow","auto");
	});
	$(".openwinOtherBank .contain ul:gt(0)").hide();
	$(".openwinOtherBank .tab a").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var TabIndex = $(this).index();
		$(".openwinOtherBank .contain ul:eq("+TabIndex+")").slideDown().siblings("ul").slideUp();
	});
	
	// 选择银行卡
	$(".openwinOtherBank .contain .otherBankCheck dl dd").click(function(){
		$(this).addClass("on").siblings().removeClass("on").parent().parent().siblings().find("dd").removeClass("on");
	});
	

	// 左右高度一致（放在最后）
	$(".leftBox").css("height",$(".rightBox").height() - 2);
	
});
//JavaScript Document

$(document).ready(function(){

	// 选卡
	$(".tradeTab li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
	});
	
	// 步骤根据数量宽度自适应
	var setpNum  = $(".setpBox dd").length;
	$(".setpBox").css("width",$(".setpBox dd").width() * setpNum + 60 * setpNum); 
	
	//弹出层
	$("#person").click(function(){
		var windowHeight = $(window).height(); 
		var scrollHeight = $(document).scrollTop();
		$(".fade").css("height",$(document).height());
		$(".openwinOtherBank").css("left",($(document).width() - $(".openwinOtherBank").width() - 20)/2); 
		$(".openwinOtherBank").css("top",(windowHeight - $(".openwinOtherBank").height() - 20) / 2 + scrollHeight ); 
    	$(".openwinOtherBank,.fade").slideDown();
		$("body").css("overflow","hidden");
	});
	$(".openwinOtherBank .tab .closeBox,.openwinOtherBank .btn_box input").click(function(){
    	$(".openwinOtherBank,.fade").slideUp();
		$("body").css("overflow","auto");
	});
	$(".openwinOtherBank .contain ul:gt(0)").hide();
	$(".openwinOtherBank .tab a").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var TabIndex = $(this).index();
		$(".openwinOtherBank .contain ul:eq("+TabIndex+")").slideDown().siblings("ul").slideUp();
	});
	
	// 选择银行卡
	$(".openwinOtherBank .contain .otherBankCheck dl dd").click(function(){
		$(this).addClass("on").siblings().removeClass("on").parent().parent().siblings().find("dd").removeClass("on");
	});
	

	// 左右高度一致（放在最后）
	$(".leftBox").css("height",$(".rightBox").height() - 2);
	
});
	
