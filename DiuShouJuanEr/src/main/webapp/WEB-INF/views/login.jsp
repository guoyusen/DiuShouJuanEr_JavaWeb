<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>丢手绢儿</title>
<link href="css/960.css" rel="stylesheet" type="text/css">
<link href="css/text.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="images/system/logo_theme.png">
</head>
<body>
	<div class="wrap_center">
		<div class="content container_16">
			<div class="login_head grid_6 prefix_5 suffix_5">
				<div class="login_head_logo_outer">
					<span class="login_head_logo"></span>
				</div>
				<span class="login_head_slogon margin_top_10">留住童年，分享快乐</span>
			</div>
			<div class="login_split grid_16">
				<span class="login_split_left grid_6"></span>
				<div class="login_split_notice grid_4 alpha omega">
					<div id="login_split_login">
						<div id="change_login"
							class="login_split_btn grid_2 omega alpha login_split_btn_chose"
							onClick="LoginOperateUtil.showFrameLogin()">登录</div>
						<span class="login_split_btn_dot"></span>
						<div id="change_register"
							class="login_split_btn grid_2 alpha omega login_split_btn_unchose"
							onClick="LoginOperateUtil.showFrameRegister()">注册</div>
					</div>
					<div id="login_split_forget" class="vanish">
						<div
							class="login_split_btn grid_4 omega alpha login_split_btn_chose">
							忘记密码</div>
					</div>
				</div>
				<span class="login_split_right grid_6"></span>
			</div>
			<div class="login_body grid_6 prefix_5 suffix_5">
				<div id="form_login" class="login_form">
					<div class="login_account margin_top_20">
						<span class="add_on"><span class="add_on_account"></span></span> <input
							id="loginAccount" autocomplete="off" type="text" placeholder="手机号" value="18817800144">
					</div>
					<div class="login_password margin_top_10">
						<span class="add_on"><span class="add_on_password"></span></span>
						<input id="loginPsd" autocomplete="off" type="password" placeholder="密码" value="111111">
					</div>
					<div id="btn_login" class="login_submit btn_blue margin_top_10"
						onClick="LoginOperateUtil.executeLogin()">登 录</div>
					<div class="login_bottom margin_top_5">
						<a onClick="LoginOperateUtil.showFrameForget()">忘记密码</a>
					</div>
				</div>
				<div id="form_register" class="register_form vanish">
					<div class="login_account margin_top_20">
						<span class="add_on"><span class="add_on_account"></span></span> <input
							id="regiAccount" autocomplete="off" type="text" placeholder="手机号">
					</div>
					<div class="login_password margin_top_10">
						<span class="add_on"><span class="add_on_password"></span></span>
						<input id="regiPsd" autocomplete="off" type="password" placeholder="密码">
					</div>
					<div class="login_verify margin_top_10">
						<input id="regiMsg" type="text" placeholder="验证码">
						<div id="regiMsgBtn" class="login_verify_btn btn_blue"
							onClick="LoginOperateUtil.executeMsgReg()">获取短信验证码</div>
					</div>
					<div id="btn_register" class="login_submit btn_green margin_top_10"
						onClick="LoginOperateUtil.executeRegist()">注 册</div>
				</div>
				<div id="form_reset" class="forget_form vanish">
					<div class="login_account margin_top_20">
						<span class="add_on"><span class="add_on_account"></span></span> <input
							id="resetAccount" autocomplete="off" type="text" placeholder="手机号">
					</div>
					<div class="login_password margin_top_10">
						<span class="add_on"><span class="add_on_password"></span></span>
						<input id="resetPsd" autocomplete="off" type="password" placeholder="新密码">
					</div>
					<div class="login_verify margin_top_10">
						<input id="resetMsg" autocomplete="off" type="text" placeholder="验证码">
						<div id="resetMsgBtn" class="login_verify_btn btn_blue"
							onClick="LoginOperateUtil.executeMsgRet()">获取短信验证码</div>
					</div>
					<div id="btn_reset" class="login_submit btn_green margin_top_10"
						onClick="LoginOperateUtil.executeReset()">重 置</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<p>Copyright © 2015 DiuShouJuanEr. All Rights Reserved.</p>
	</div>
	<div id="change_forget" class="back_to_login vanish"
		onClick="LoginOperateUtil.showFrameLogin()">
		<span>我要登录</span>
	</div>
	<div class="content container_12">
		<div class="grid_4 prefix_4 suffix_4">
			<span id="notice_tip"></span>
		</div>
	</div>
	<script src="js/jQuery.js" type="text/javascript"></script>
	<script src="js/common.js" type="text/javascript"></script>
	<script src="js/easing.js" type="text/javascript"></script>
	<script src="js/timer.js" type="text/javascript"></script>
	<script src="js/cookie.js" type="text/javascript"></script>
	<script src="js/owner.login.js" type="text/javascript"></script>
</body>
</html>
