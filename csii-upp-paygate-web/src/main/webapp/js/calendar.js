//Generate Calendar 
function fPopUpCalendarDlg(ctrlobj)
         {
	       showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	       showy = event.screenY - event.offsetY + 18; // + deltaY;
	       newWINwidth = 210 + 4 + 18;

	       retval = window.showModalDialog("/ent/CalendarDlg.htm", "", "edge:raised;help:no;dialogWidth:176px; dialogHeight:270px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );

       if( retval != null ){
       retval1=retval.substring(0,4);
       retval2=retval.substring(4,6);
       retval3=retval.substring(6,8);
		   ctrlobj.value = retval1+"-"+retval2+"-"+retval3;
	       }else{
		    //alert("canceled");
	       }
         }
         
         

document.write("<div id=meizzCalendarLayer style='position: absolute; z-index: 9999; width: 244; height: 190; display: none'>");
document.write("<iframe src='' name=meizzCalendarIframe scrolling=no frameborder=0 width=100% height=100%></iframe></div>");
function writeIframe()
{
    var strIframe = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=gb2312'><style>"+
    "*{font-size: 12px; font-family: 宋体}"+
    ".bg{  color: "+ WebCalendar.lightColor +"; cursor: default; background-color: "+ WebCalendar.darkColor +";}"+
    "table#tableMain{ width: 240;}"+
    "table#tableWeek td{ color: "+ WebCalendar.WeekFontCl + "; background-color:" + WebCalendar.lightColor +";}"+
    "table#tableDay  td{ font-color:#000000;}"+
    "td#meizzYearHead, td#meizzYearMonth{color: "+ WebCalendar.wordColor +"}"+
    ".out { text-align: center; border-top: 1px solid "+ WebCalendar.DarkBorder +
    "; border-left: 1px solid "+ WebCalendar.DarkBorder +
    "; border-right: 1px solid "+ WebCalendar.DarkBorder +"; border-bottom: 1px solid "+ WebCalendar.DarkBorder +";}"+
    ".over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "border-bottom: 1px solid "+ WebCalendar.DarkBorder +"; border-right: 1px solid "+ WebCalendar.DarkBorder +"}"+
    "input{ border: 1px solid "+ WebCalendar.darkColor +"; padding-top: 1px; height: 18; cursor: hand;"+
    "       color:"+ WebCalendar.wordColor +"; background-color: "+ WebCalendar.btnBgColor +"}"+
    "</style></head><body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'><form name=meizz>";

    if (WebCalendar.drag){ strIframe += "<scr"+"ipt language=javascript>"+
    "var drag=false, cx=0, cy=0, o = parent.WebCalendar.calendar; function document.onmousemove(){"+
    "if(parent.WebCalendar.drag && drag){if(o.style.left=='')o.style.left=0; if(o.style.top=='')o.style.top=0;"+
    "o.style.left = parseInt(o.style.left) + window.event.clientX-cx;"+
    "o.style.top  = parseInt(o.style.top)  + window.event.clientY-cy;}}"+
    "function document.onkeydown(){ switch(window.event.keyCode){  case 27 : parent.hiddenCalendar(); break;"+
    "case 37 : parent.prevM(); break; case 38 : parent.prevY(); break; case 39 : parent.nextM(); break; case 40 : parent.nextY(); break;"+
    "case 84 : document.forms[0].today.click(); break;} window.event.keyCode = 0; window.event.returnValue= false;}"+
    "function dragStart(){cx=window.event.clientX; cy=window.event.clientY; drag=true;}</scr"+"ipt>"}

    strIframe += "<select name=tmpYearSelect  onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:2;left:45;display:none'"+
    " onchange='parent.WebCalendar.thisYear =this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    "<select name=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:2;left:144;display:none'"+
    " onchange='parent.WebCalendar.thisMonth=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+

    "<table id=tableMain border=0 cellspacing=1 cellpadding=0 bgcolor='black'>"+
    "<tr><td width=250 height=19 bgcolor='"+ WebCalendar.lightColor +"' align='center'>"+
    "    <table width=100% id=tableHead border=0 cellspacing=1 cellpadding=0 bgcolor='#2191F5'><tr align=center>"+
    "    <td width=25 height=19 bgcolor='#2191F5' title='向前翻 1 月&#13;快捷键：←' style='cursor: hand' onclick='parent.prevM()'><b>&lt;</b></td>"+
    "    <td width=95 align=center id=meizzYearHead  title='点击此处选择年份' onclick='parent.funYearSelect(parseInt(this.innerText, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.WeekTdColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=95 align=center id=meizzYearMonth title='点击此处选择月份' onclick='parent.funMonthSelect(parseInt(this.innerText, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.WeekTdColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=25 bgcolor='#2191F5' title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextM()' style='cursor: hand'><b>&gt;</b></td></tr>"+
	"		</table>"+
    "</td></tr><tr><td height=18>"+
	"	<table id=tableWeek border=0 width=100% cellpadding=0 cellspacing=0 ";
    if(WebCalendar.drag){strIframe += "onmousedown='dragStart()' onmouseup='drag=false' onmouseout='drag=false'";}
    strIframe += " borderColorLight='"+ WebCalendar.darkColor +"' borderColorDark='"+ WebCalendar.lightColor +"'>"+
    "    <tr align=center><td height=18>周日</td><td>周一</td><td>周二</td><td>周三</td><td>周四</td><td>周五</td><td>周六</td></tr></table>"+
    "</td></tr><tr><td valign=top width=240 bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table id=tableDay height=100 width=240 border=0 cellspacing=0 cellpadding=0>";
         for(var x=0; x<5; x++){ strIframe += "<tr>";
         for(var y=0; y<7; y++)  strIframe += "<td class=out id='meizzDay"+ (x*7+y) +"'></td>"; strIframe += "</tr>";}
         strIframe += "<tr>";
         for(var x=35; x<42; x++) {strIframe += "<td class=out id='meizzDay"+ x +"'></td>"};
         strIframe +="</tr></table>"+
    "</td></tr><tr><td height=20 bgcolor='#ffffff'>"+
	"		<table border=1 cellpadding=0 cellspacing=0 width=240>"+
    "        <tr>"+
    "          <td align=center>"+
    "            <input name=today type=button value='今天：" + new Date().getFullYear() +"-"+ (new Date().getMonth() +1)+"-"+ new Date().getDate() + 
    "' onfocus='this.blur()' style='width:240;color: #000000; font-weight: bold; border: 0px outset #FFFFFF; background-color: #FFFFFF' title='当前日期&#13;快捷键：T'"+
    "            onclick=\"parent.returnDate(new Date().getDate() +'/'+ (new Date().getMonth() +1) +'/'+ new Date().getFullYear())\">"+
    "          </td>"+
    "        </tr>"+
    "        <tr>"+
    "          <td align=center>"+ 
    " <input type=button name=Submit32 value='确定' onclick=\"return parent.checkCalendar();\" >"+
    "<input type=button name=Submit32 value='清除' onclick=\"parent.returnDate('')\" >"+
    "          </td>"+
    "        </tr>"+    
    
    "      </table>"+		 
	"</td></tr></table>"+
	"</form></body></html>";
	

	
    with(WebCalendar.iframe)
    {
        document.writeln(strIframe); document.close();
        for(var i=0; i<42; i++)
        {
            WebCalendar.dayObj[i] = eval("meizzDay"+ i);
            WebCalendar.dayObj[i].onmouseover = dayMouseOver;
            WebCalendar.dayObj[i].onmouseout  = dayMouseOut;
            WebCalendar.dayObj[i].onclick     = returnDate;
        }
    }
}

function WebCalendar() //初始化日历的设置
{
    this.regInfo    = "关闭的快捷键：[Esc]";
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    this.day        = new Array(42);            //定义日历展示用的数组
    this.dayObj     = new Array(42);            //定义日期展示控件数组
    this.dateStyle  = null;                     //保存格式化后日期数组
    this.objExport  = null;                     //日历回传的显示控件
    this.eventSrc   = null;                     //日历显示的触发控件
    this.inputDate  = null;                     //转化外的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear(); //定义年的变量的初始值
    this.thisMonth  = new Date().getMonth()+ 1; //定义月的变量的初始值
    this.thisDay    = new Date().getDate();     //定义日的变量的初始值
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    this.iframe     = window.frames("meizzCalendarIframe"); //日历的 iframe 载体
    this.calendar   = getObjectById("meizzCalendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式

    this.yearFall   = 2;           //定义年下拉框的年差值
    this.format     = "yyyy-mm-dd"; //回传日期的格式
    this.timeShow   = false;        //是否返回时间
    this.drag       = true;         //是否允许拖动
    this.darkColor  = "#2191F5";    //控件的暗色
    this.lightColor = "#FFFFFF";    //控件的亮色
    this.btnBgColor = "#FFFFFF";//"#E6E6FA";    //控件的按钮背景色
    this.wordColor  = "#000000";    //控件的文字颜色
    this.wordDark   = "#DCDCDC";    //控件的暗文字颜色
    this.dayBgColor = "#ffffff";    //日期数字背景色
    this.todayColor = "#0000D0";    //今天在日历上的标示背景色
    this.DarkBorder = "#F5F5FA";//"#D4D0C8";    //日期显示的立体表达色F5F5FA
	this.WeekTdColor = "#2191F5";          //周表格背景
	this.WeekFontCl = "#0054E3";
}   

var WebCalendar = new WebCalendar();

function checkCalendar() //判断日期格式
{
    if  (!WebCalendar.timeShow) WebCalendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    else WebCalendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    try{
        if (WebCalendar.objExport.value.trim() != ""){
		var dataStrTem=WebCalendar.objExport.value.replace(/(\d{4})(\d{2})(\d{2})/,"$1-$2-$3");
            WebCalendar.dateStyle = dataStrTem.match(WebCalendar.dateReg);
            if (WebCalendar.dateStyle == null)
            {
                alert("文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                return false;
            }
            else
            {
             hiddenCalendar('');
            }
        }else { 
        hiddenCalendar('');
        }
    }  catch(e){
                alert("文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                return false;
    }
}

function calendar() //主调函数
{
    var e = window.event.srcElement;   writeIframe();
    var o = WebCalendar.calendar.style; WebCalendar.eventSrc = e;
	if (arguments.length == 0) WebCalendar.objExport = e;
    else WebCalendar.objExport = eval(arguments[0]);

    WebCalendar.iframe.tableWeek.style.cursor = WebCalendar.drag ? "move" : "default";
	var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
	while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; 
    //WebCalendar.iframe.document.body.focus();
    var cw = WebCalendar.calendar.clientWidth, ch = WebCalendar.calendar.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.clientHeight + dt - t - h >= ch) o.top = (p=="image")? t + h : t + h + 6;
    else o.top  = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
    if (dw + dl - l >= cw) o.left = l; else o.left = (dw >= cw) ? dw - cw + dl : dl;

    if  (!WebCalendar.timeShow) WebCalendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    else WebCalendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;

    try{
        if (WebCalendar.objExport.value.trim() != ""){
		var dataStrTem=WebCalendar.objExport.value.replace(/(\d{4})(\d{2})(\d{2})/,"$1-$2-$3");
            WebCalendar.dateStyle = dataStrTem.match(WebCalendar.dateReg);
            if (WebCalendar.dateStyle == null)
            {
                WebCalendar.thisYear   = new Date().getFullYear();
                WebCalendar.thisMonth  = new Date().getMonth()+ 1;
                WebCalendar.thisDay    = new Date().getDate();
                alert("原文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                writeCalendar(); return false;
            }
            else
            {
                WebCalendar.thisYear   = parseInt(WebCalendar.dateStyle[1], 10);
                WebCalendar.thisMonth  = parseInt(WebCalendar.dateStyle[3], 10);
                WebCalendar.thisDay    = parseInt(WebCalendar.dateStyle[4], 10);
                WebCalendar.inputDate  = parseInt(WebCalendar.thisDay, 10) +"/"+ parseInt(WebCalendar.thisMonth, 10) +"/"+ 
                parseInt(WebCalendar.thisYear, 10); writeCalendar();
            }
        }  else writeCalendar();
    }  catch(e){writeCalendar();}
}
function funMonthSelect() //月份的下拉框
{
    var m = isNaN(parseInt(WebCalendar.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(WebCalendar.thisMonth);
    var e = WebCalendar.iframe.document.forms[0].tmpMonthSelect;
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function funYearSelect() //年份的下拉框
{
    var n = WebCalendar.yearFall;
    var e = WebCalendar.iframe.document.forms[0].tmpYearSelect;
    var y = isNaN(parseInt(WebCalendar.thisYear, 10)) ? new Date().getFullYear() : parseInt(WebCalendar.thisYear);
        y = (y <= 1000)? 1000 : ((y >= 9999)? 9999 : y);
    var min = (y - n >= 1000) ? y - n : 1000;
    var max = (y + n <= 9999) ? y + n : 9999;
        min = (max == 9999) ? max-n*2 : min;
        max = (min == 1000) ? min+n*2 : max;
    for (var i=min; i<=max; i++) e.options.add(new Option(i +"年", i));
    e.style.display = ""; e.value = y; e.focus();
}
function prevM()  //往前翻月份
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==1)
    {
        WebCalendar.thisYear--;
        WebCalendar.thisMonth=13;
    }
    WebCalendar.thisMonth--; writeCalendar();
}
function nextM()  //往后翻月份
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==12)
    {
        WebCalendar.thisYear++;
        WebCalendar.thisMonth=0;
    }
    WebCalendar.thisMonth++; writeCalendar();
}
function prevY(){WebCalendar.thisDay = 1; WebCalendar.thisYear--; writeCalendar();}//往前翻 Year
function nextY(){WebCalendar.thisDay = 1; WebCalendar.thisYear++; writeCalendar();}//往后翻 Year
function hiddenSelect(e){for(var i=e.options.length; i>-1; i--)e.options.remove(i); e.style.display="none";}
function getObjectById(id){ if(document.all) return(eval("document.all."+ id)); return(eval(id)); }
function hiddenCalendar(){getObjectById("meizzCalendarLayer").style.display = "none";};
function appendZero(n){return(("00"+ n).substr(("00"+ n).length-2));}//日期自动补零程序
//function String.prototype.trim(){return this.replace(/(^\s*)|(\s*$)/g,"");}


function dayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = WebCalendar.darkColor;
    if(WebCalendar.day[this.id.substr(8)].split("/")[1] == WebCalendar.thisMonth)
    this.style.color = WebCalendar.lightColor;
}
function dayMouseOut()
{
    this.className = "out"; var d = WebCalendar.day[this.id.substr(8)], a = d.split("/");
    this.style.removeAttribute('backgroundColor');
    if(a[1] == WebCalendar.thisMonth && d != WebCalendar.today)
    {
        if(WebCalendar.dateStyle && a[0] == parseInt(WebCalendar.dateStyle[4], 10))
        this.style.color = WebCalendar.lightColor;
        this.style.color = WebCalendar.wordColor;
    }
}
function writeCalendar() //对日历显示的数据的处理程序
{
    var y = WebCalendar.thisYear;
    var m = WebCalendar.thisMonth; 
    var d = WebCalendar.thisDay;
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！");
        WebCalendar.thisYear   = new Date().getFullYear();
        WebCalendar.thisMonth  = new Date().getMonth()+ 1;
        WebCalendar.thisDay    = new Date().getDate(); }
    y = WebCalendar.thisYear;
    m = WebCalendar.thisMonth;
    d = WebCalendar.thisDay;
    WebCalendar.iframe.meizzYearHead.innerText  = y +" 年";
    WebCalendar.iframe.meizzYearMonth.innerText = parseInt(m, 10) +" 月";
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? WebCalendar.daysMonth[11] : WebCalendar.daysMonth[m-2];
    for(var i=(w-1); i>=0; i--) //这三个 for 循环为日历赋数据源（数组 WebCalendar.day）格式是 d/m/yyyy
    {
        WebCalendar.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
        if(m==1) WebCalendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
        prevDays--;
    }
    for(var i=1; i<=WebCalendar.daysMonth[m-1]; i++) WebCalendar.day[i+w-1] = i +"/"+ m +"/"+ y;
    for(var i=1; i<42-w-WebCalendar.daysMonth[m-1]+1; i++)
    {
        WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
        if(m==12) WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
    }
    for(var i=0; i<42; i++)    //这个循环是根据源数组写到日历里显示
    {
        var a = WebCalendar.day[i].split("/");
        WebCalendar.dayObj[i].innerText    = a[0];
        WebCalendar.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
        WebCalendar.dayObj[i].bgColor      = WebCalendar.dayBgColor;
        WebCalendar.dayObj[i].style.color  = WebCalendar.wordColor;
        if ((i<10 && parseInt(WebCalendar.day[i], 10)>20) || (i>27 && parseInt(WebCalendar.day[i], 10)<12))
            WebCalendar.dayObj[i].style.color = WebCalendar.wordDark;
        if (WebCalendar.inputDate==WebCalendar.day[i])    //设置输入框里的日期在日历上的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.darkColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
        if (WebCalendar.day[i] == WebCalendar.today)      //设置今天在日历上反应出来的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.todayColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
    }
}
function returnDate() //根据日期格式等返回用户选定的日期
{
    if(WebCalendar.objExport)
    {
        var returnValue;
        var a;
        
      
        if(arguments.length==0)
        {
        
         a = WebCalendar.day[this.id.substr(8)].split("/");
        }
        else
        {
      
        if(arguments[0].length==0)
        {
        
          WebCalendar.objExport.value = returnValue = '';
          hiddenCalendar();
          return;
        }
         a = arguments[0].split("/");
        }
         
        var d = WebCalendar.format.match(/^(\w{4})(-|\/)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("??????è??¨????????????????????????????????????????\r\n\r\n??????????????¨???? WebCalendar.format ????"); return false;}
        var dataFlag = d[3].length==2 || d[4].length==2; //????????·??????????????????????????????·??????????????
        returnValue = dataFlag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
        if(WebCalendar.timeShow)
        {
            var h = new Date().getHours(), m = new Date().getMinutes(), s = new Date().getSeconds();
            returnValue += dataFlag ? " "+ appendZero(h) +":"+ appendZero(m) +":"+ appendZero(s) : " "+  h  +":"+ m +":"+ s;
        }
		returnValue=returnValue.replace(/(\d{4})\-(\d{2})\-(\d{2})/,"$1-$2-$3");

        WebCalendar.objExport.value = returnValue;
        hiddenCalendar();
    }
}
/*function document.onclick()
{
    if(WebCalendar.eventSrc != window.event.srcElement) hiddenCalendar();
}*/

function $(s){return document.getElementById(s);}
function hide(s){$(s).style.display=$(s).style.display=="none"?"":"none";}
function swap(s,a,b,c){$(s)[a]=$(s)[a]==b?c:b;}
function regCheckFunc(_sType, _sVal){
	var bPass				= true;
	var aError				= [];
	var aType				= {};
		aType['username']	= '0,1,3,4,19,21,22,23';
		aType['loginname']	= '0,1,3,4,10';
		aType['password']	= '1,13';
		aType['password2']	= '1,7,13';
		aType['passwordsina']= '1,13';
		aType['pwdA']		= '6';
		aType['uname']		= '0,2,3';
		aType['email']		= '8';
		aType['checkwd']	= '9';
		aType['pwdQ']		= '11';
		aType['sex']		= '12';
		aType['sex2']		= '12';
		aType['xychk']		= '12';
		aType['xy']			= '12';
		aType['pid']		= '18';
		aType['cid']		= '0,20';
		aType['bid']		= '1,14';
		aType['bdat']		= '14,15';
		aType['bpass']		= '1,13';
		aType['mbiz']		= '16';
		aType['ybiz']		= '17';


	var aRule				= aType[_sType].split(',');
	var aMsg				= [];
		aMsg['username']	= '登录名不符合规则，请重新输入。';
		aMsg['loginname']	= '登录名不符合规则，请重新输入。';
		aMsg['password']	= '密码不符合规则，请重新输入。';
		aMsg['password2']	= '密码与上次输入的密码不符，请重新输入';
		aMsg['passwordsina']= '密码不符合规则，请重新输入。';
		aMsg['pwdA']		= '密码查询答案不符合规则，请输入6--20个字符的答案。';
		aMsg['uname']		= '您输入昵称不符合规则，请重新输入。';
		aMsg['email']		= '您输入的邮箱不可用，请输入正确的电子邮箱。';
		aMsg['pwdQ']		= '请选择密码查询问题。';
		aMsg['pid']		    = '请选择您的证件类型。';
		aMsg['cid']		    = '请输入正确的证件号码。';
		aMsg['bid']		    = '商务卡主卡号码应全部为数字，不允许有其他符号及字符。';
		aMsg['bdat']		= '商务卡主卡期限为数字，不允许有其他符号及字符。';
		aMsg['bpass']		= '商务卡主卡密码，注意区分大小写。';
		aMsg['mbiz']		= '请选择商务卡有效期。';
		aMsg['ybiz']		= '请选择商务卡有效期。';


	var aPass				= [];
		aPass[0]			= !(/>|<|,|\[|\]|\{|\}|\?|\/|\+|=|\||\'|\\|\"|:|;|\~|\!|\@|\#|\*|\$|\%|\^|\&|\(|\)|`/i).test(_sVal);
		aPass[1]			= !(regStrLength(_sVal)<6||regStrLength(_sVal)>20);
		aPass[2]			= !(regStrLength(_sVal)<6||regStrLength(_sVal)>20);
		aPass[3]			= !(_sVal.indexOf(' ') > -1);
		aPass[4]			= !(!isNaN(_sVal) && _sVal != '');
		aPass[5]			= /^[a-zA-Z0-9_]*$/.test(_sVal);
		aPass[6]			= regStrLength(_sVal)>5;

	if($('password_pMsg') && $('password2_p2Msg')){
	    if(! $('password2_p2Msg').value){
	        aPass[7]		= true;  
	    }else{
		    aPass[7]		= $('password_pMsg').value == $('password2_p2Msg').value;
	    }
	}else{
		aPass[7]			= true;
	}

		aPass[8]			= /^[a-z0-9_@\.-]+@([a-z0-9_-]+\.)+[a-z0-9_-]{2,3}$/.test(_sVal);
		aPass[9]			= /^[0-9]{4}$/.test(_sVal);
		aPass[10]			= !(/[A-Z]/g).test(_sVal);

		aPass[11]			= true;
		aPass[12]			= true;

		aPass[13]			= /^[a-zA-Z0-9]*$/.test(_sVal);
		aPass[14]			= /^[0-9]*$/.test(_sVal);
		aPass[15]			= !(regStrLength(_sVal)<1||regStrLength(_sVal)>2);
		aPass[16]			= true;
		aPass[17]			= true;
		aPass[18]			= true;		
		aPass[19]			= !(/[0-9]+[a-z]/g).test(_sVal);

if($('pwdQ_pwdMsg')&&$('pwdQ_pwdMsg').value ==''){
		aPass[11]			= false;
	}

//======================================================================================
	if($('pid_ppmsg')&&$('pid_ppmsg').value ==''){
		aPass[18]			= false;
	}
	if($('mbiz_mmmsg')&&$('mbiz_mmmsg').value ==''){
		aPass[16]			= false;
	}
	if($('ybiz_yymsg')&&$('ybiz_yymsg').value ==''){
		aPass[17]			= false;
	}
	if($('pid_ppmsg').value =='A'){
		aPass[20]			=   (/^[0-9a-zA-Z]{15}$/).test(_sVal)||(/^[0-9a-zA-Z]{18}$/).test(_sVal);}
		else{
		aPass[20]			= !(regStrLength(_sVal)<4||regStrLength(_sVal)>30);
		
		}	
		aPass[21]          =!(/[\u4e00-\u9fa5\uf900-\ufa2d]/g).test(_sVal);
		aPass[22]			= !(/^[0-9]*$/).test(_sVal);
		aPass[23]			= !(/[0-9]+[A-Z]/g).test(_sVal);
//======================================================================================

	for(sKey in aRule){
		bPass = bPass && aPass[aRule[sKey]];
		if(!aPass[aRule[sKey]]){
			aError			= aMsg[_sType];
			break;
		}
	}
	return {pass:bPass, msg:aError};
}

function regCheck(_oObj){
	var sType				= _oObj.id.split('_');
	var sMsg				= regCheckFunc(sType[0], _oObj.value);

	var aMsg				= [];
		aMsg['username']	= '您已输入登录名，请查看此 <a href="#" onClick="openwin();">登录名是否可用</a>。';
		aMsg['password']	= '输入密码可用。';
		aMsg['password2']	= '输入密码一致。';
		aMsg['pwdA']		= '已输入密码查询答案。';
		aMsg['uname']		= '输入的昵称可用。';
		aMsg['email']		= '已输入正确的电子邮箱。';
		aMsg['pwdQ']		= '已选择密码查询问题。';
		aMsg['pid']		    = '已选择证件类型。';
		aMsg['cid']		    = '已输入证件号码。';
		aMsg['bid']		    = '已输入商务卡主卡号码。';
		aMsg['bdat']		= '已输入商务卡主卡期限。';
		aMsg['bpass']		= '已输入商务卡主卡有效密码。';
		aMsg['mbiz']		= '已选择商务卡主卡有效期。';
		aMsg['ybiz']		= '已选择商务卡主卡有效期。';



	var bMsg				= {};
		bMsg['username']	= '请输入您的登录名。';
		bMsg['password']	= '请输入您的登录密码。';
		bMsg['password2']	= '请再次输入您的登录密码。';
		bMsg['passwordsina']= '请输入会员密码';
		bMsg['pwdA']		= '请输入密码查询答案。';
		bMsg['uname']		= '请输入您的昵称。';
		bMsg['email']		= '请输入正确的电子邮箱。';
		bMsg['pwdQ']		= '请选择密码查询问题。';
		bMsg['pid']		    = '请选择证件类型。';
		bMsg['cid']		    = '请输入证件号码。';
		bMsg['bid']		    = '请输入正确的商务卡主卡号码。';
		bMsg['bdat']		= '请输入商务卡主卡期。';
		bMsg['bpass']		= '请输入商务卡主卡有效密码。';
		bMsg['mbiz']		= '请选择商务卡主卡有效期。';
		bMsg['ybiz']		= '请选择商务卡主卡有效期。';
		
	if(sMsg.pass == false){
		if(_oObj.value == ""){
			$(sType[1]).innerHTML	= bMsg[sType[0]];
		}else{
			$(sType[1]).innerHTML	= sMsg.msg;
		}
		$(sType[1]).className		= "tb2";
		return false;
	}else{
		$(sType[1]).innerHTML		= aMsg[sType[0]];
		$(sType[1]).className		= "tb1";
	}
	return true;
}



function regCheckSubmit(_iMark){
	var aSort		= [];
		aSort[0]	= ['username_uMsg','password_pMsg','password2_p2Msg','pwdQ_pwdMsg','pwdA_pwdAMsg','pid_ppmsg','cid_ccmsg','bid_bbmsg','bpass_ssmsg'];
//		aSort[0]	= ['username_uMsg','password_pMsg','password2_p2Msg','pwdQ_pwdMsg','pwdA_pwdAMsg','pid_ppmsg','cid_ccmsg','bid_bbmsg','bdat_ddmsg','bpass_ssmsg','mbiz_mmmsg','ybiz_yymsg'];

    var bCheck		= true;
	var bSelect		= false;
	var sType		= '';
	var aShow		= [];
	var aMsg		= {};
	var bMsg		= {};
	var oCheck		= {};
	var aCheck		= aSort[_iMark];


	for(var i = 0; i < aCheck.length; i++){

		aShow	= aCheck[i].split('_');

		sType	= $(aCheck[i]).type;

		if(sType == 'text' || sType == 'password' || sType == 'select-one'){
			if(!regCheck($(aCheck[i]))){
				bCheck	= false;
			}
		}

		if(sType == 'checkbox' || sType == 'radio'){
			bSelect	= false;
            oCheck	= document.getElementsByName($(aCheck[i]).name);
            for(var j = 0; j < oCheck.length; j++){
				if(oCheck[j].checked == true){
					bSelect = true;
				}
			}

			if(bSelect == false){
				bCheck	= false;
				$(aShow[1]).innerHTML = aMsg[aShow[0]];
				$(aShow[1]).className = "tb2";
			}else{
				$(aShow[1]).innerHTML = bMsg[aShow[0]];
				$(aShow[1]).className = "tb1";
			}
		}
	}
	return bCheck;
}

function regStrLength(_sVal){
	var iLen;
	var sVal	= escape(_sVal);
	iLen		= sVal.length - (sVal.length - sVal.replace(/\%u/g,"u").length) * 4;
	sVal		= sVal.replace(/\%u/g,"uu");
	iLen		= iLen - (sVal.length - sVal.replace(/\%/g,"").length) * 2;
	return iLen;
}

function sl(sid){
	var aMsg				= {};
		aMsg['uMsg']		= "6-20个字符，英文小写、汉字、数字、下划线，不能全部是数字。";
		aMsg['pMsg']		= "密码字母有大小写之分。6—20位（包括6、20），限用英文、数字。";
		aMsg['unameMsg']	= "6-20个字符，英文小写、汉字、数字、下划线，不支持空格。";
		aMsg['uMessage']	= "请输入您的新浪会员名。";
		aMsg['unameMessage']= "6-20个字符，英文小写、汉字、数字、下划线，不支持空格。";
		aMsg['p2Msg']		= "请再次输入登录密码。";
		aMsg['eMsg']		= "输入正确的电子邮箱，有助于您与朋友间建立畅通的联系。";
		aMsg['eMessage']	= "输入正确的电子邮箱，有助于您与朋友间建立畅通的联系。";
		aMsg['pMessage']	= "请输入您的会员密码。";
		aMsg['passwordsina']= "请输入您的会员密码。";
		aMsg['ssmsg']       = "请输入商务卡主卡密码。";
		aMsg['ddmsg']       = "请输入商务卡主卡有效期。";
		aMsg['ssmsg']       = "请输入商务卡主卡交易密码。";
		aMsg['bbmsg']       = "请输入商务卡主卡号码。";
		aMsg['ccmsg']       = "请输入证件号码。";
		aMsg['ppmsg']       = "请选择证件类型。";
		aMsg['pwdAMsg']     = "请输入找回密码问题答案。";
		aMsg['mmmsg']       = "请选择商务卡主卡有效期。";
		aMsg['yymsg']       = "请选择商务卡主卡有效期。";


	$(sid).className		= "tb1";
	$(sid).innerHTML		= aMsg[sid];
}

//========动态列表现是js Begin==============================================================================
function Pair(name, value)
{
	this.name = name;
	this.value = value == "-1" ? "" : value;
}

function loadList(action, myarray, id)
{
	var url = action;
	for (var i = 0; i < myarray.length; i++)
	{
		var item = myarray[i];
		url += i==0 ? "?" : "&";
		url += item.name + "=" + item.value;
	}
	showList(url, id);
}	

	function freshList(item, action, id)
{
	var url= action + "?" + item.name + "=" + item.value;
	showList(url,id);
}	

function showList(url, id)
{
	var xmlhttp;
	if (window.XMLHttpRequest) { // Mozilla, Safari,...
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp.overrideMimeType) {
            xmlhttp.overrideMimeType('text/xml');
            }
        } 	
    else if (window.ActiveXObject) { // IE
             try {
             xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
             } catch (e) {
             	try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                	} catch (e) {}
               }
           }
	if(xmlhttp)
	{
		xmlhttp.open("POST", url, false);
		xmlhttp.send();
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
			//	document.getElementById(id).innerHTML = xmlhttp.responseXML.getElementsByTagName("root")[0].firstChild.data;
			document.getElementById(id).innerHTML = xmlhttp.responseText;
			}else {
                     alert('There was a problem with the request.');
            }
		}
	}
}	
//========动态列表现是js End==============================================================================
//-->
var editselect=null;
var max_editselect=0;
function catch_keydown()
{  
   var sel = editselect;
   if(sel.selectedIndex < eval(max_editselect - 1)||max_editselect < 0||sel.options[sel.selectedIndex].value == "--选择输入--")
   {
     return;
   } 
  switch(event.keyCode)
	{
		case 13:
			//Enter;
			sel.options[sel.length] = new Option("","",false,true);
			event.returnValue = false;
			break;
		case 27:
			//Esc;
			alert("text:" + sel.options[sel.selectedIndex].text + ", value:" + sel.options[sel.selectedIndex].value + ";");
			event.returnValue = false;
			break;
		case 46:
			//Delete;
			if(confirm("删除当前选项!?"))
			{
				sel.options[sel.selectedIndex] = null;
				if(sel.length>0)
				{
					sel.options[0].selected = true;
				}
			}
			event.returnValue = false;
			break;
		case 8:
			//Back Space;
			var s = sel.options[sel.selectedIndex].text;
			sel.options[sel.selectedIndex].text = s.substr(0,s.length-1);
			sel.options[sel.selectedIndex].value = sel.options[sel.selectedIndex].text;
	        event.returnValue = false;
			break;
	}
	
}
function catch_press()
{
   var sel=editselect;
   if(sel.selectedIndex < eval(max_editselect - 1)||max_editselect < 0||sel.options[sel.selectedIndex].value == "--选择输入--"){
   	return;
   } 
    sel.options[sel.selectedIndex].text = sel.options[sel.selectedIndex].text + String.fromCharCode(event.keyCode);
    sel.options[sel.selectedIndex].value = sel.options[sel.selectedIndex].text;
	event.returnValue = false;
}
function catch_change(){
   var sel=editselect;
   if(sel.options[sel.selectedIndex].value == "--选择输入--"){
   for(var index = eval(sel.length - 1); index > sel.selectedIndex; index --){
   	if(sel.options[index].value == ""){
   	  sel.options[index] = null;
   	}
   }
	 sel.options[sel.length] = new Option("","",false,true);
   }
   event.returnValue = false;
}
function actsel(sel)
{
//sel.onkeydown=catch_keydown(sel);
//sel.onkeypress=catch_press(sel);
editselect = sel;
max_editselect = -1;
for(var index = 0;index < sel.length;index ++){
  if(sel.options[index].value == "--选择输入--")
  {
    max_editselect = index + 1;
  }
}
 if(max_editselect < 0)
	{
	  sel.options[sel.length] = new Option("--选择输入--","--选择输入--",false,false);
	  max_editselect = sel.length;
	}
sel.detachEvent("onchange",catch_change);
sel.detachEvent("onkeydown",catch_keydown);
sel.detachEvent("onkeypress",catch_press);
sel.attachEvent("onchange",catch_change);
sel.attachEvent("onkeydown",catch_keydown);
sel.attachEvent("onkeypress",catch_press);
return;
}
//-->
/*var popUpWin=0;	
function openhelp(var_trans,var_title){
　　//判断该窗口(popUpWin)是否已经存在，如果已经存在，则先关闭窗口，然后再打开新窗口 
　　if(popUpWin) 
　　{ 
　　if(!popUpWin.closed) popUpWin.close(); 
　　} 
　　//根据参数定位弹出窗口的展示位置 
　　popUpWin = window.open(var_trans,var_title,'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbar=no,resizable=no,copyhistory=yes,width=640,height=450, left=100,top=100,screenX=100,screenY=100');
}
function helpclose(){
　　if(popUpWin) 
　　{ 
　　if(!popUpWin.closed) popUpWin.close(); 
　　}
}*/
document.attachEvent("ondeactivate",helpclose);