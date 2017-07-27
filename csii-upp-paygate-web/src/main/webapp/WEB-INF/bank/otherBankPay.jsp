<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/load_style.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.4.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/slides.min.jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="js/prefixfree.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/modernizr.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.load_box {
	position: absolute;
	width: 300px;
	display: none;
	left: 50%;
	height: 200px;
	z-index: 1001;
	padding: 0px;
	text-align: center;
}

#load_bg {
	background-color: #ddd;
	position: absolute;
	z-index: 1000;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.5;
	filter: alpha(opacity = 50);
}
</style>
<script type="text/javascript">
	$(function() {
		showLoading();
		$("#otherBank").submit();

		function showLoading() {
			$("#load_bg").css({
				display : "block",
				height : $(document).height()
			});
			var $load_box = $('.load_box');
			$load_box.css({
				//设置弹出层距离左边的位置
				left : ($("body").width() - $load_box.width()) / 2 - 20 + "px",
				//设置弹出层距离上面的位置
				top : ($(window).height() - $load_box.height()) / 2
						+ $(window).scrollTop() + "px",
				display : "block"
			});
		}
	});
</script>
<title>统一支付</title>
</head>
<body>
	${ReturnForm}
	<div id="load_bg"></div>
	<div class="load_box">
		<div class='loader loader--glisteningWindow'></div>
		<!-- 		<div class='loader loader--audioWave'></div> -->
		<!-- 		<div class='loader loader--snake'></div> -->
		<!-- 		<div class='loader loader--spinningDisc'></div> -->
		<!-- 		<div class='loader loader--circularSquare'></div> -->
		<!-- 		<span style="font: 16px '微软雅黑';">处理中&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</span> -->
	</div>
</body>
</html>