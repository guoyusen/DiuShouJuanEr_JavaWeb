<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>丢手绢儿</title>
<link href="css/960.css" rel="stylesheet" type="text/css">
<link href="css/text.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="css/home.chat.css" rel="stylesheet" type="text/css">
<link href="css/home.main.css" rel="stylesheet" type="text/css">
<link href="css/home.user.css" rel="stylesheet" type="text/css">
<link href="css/customScrollbar.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="images/system/logo_theme.png">
</head>
<body>
	<audio id="chatAudio">
		<source src="audio/notify.wav" type="audio/wav">
		<source src="audio/notify.mp3" type="audio/mpeg">
	</audio>
	<div class="wrap">
		<div id="homeDialog"></div>
		<div id="frame_main" class="container_16">
			<div id="frameAddHead">
				<span id="frameAddBtn" class="frameAddBtnPost"
					onclick="HomeOperateUtil.homeAddPanelShow()"></span>
			</div>
			<div class="frameMainContent">
				<div id="mainRecallList" class="displayInline"></div>
			</div>
		</div>
		<div id="frame_chat" class="container_16 vanish">
			<div class="userArea">
				<div class="userHead grid_5">
					<div class="userImage">
						<img id="userHeadImg">
					</div>
					<div class="userInfo">
						<span id="nickName"></span>
						<div class="userMenu"
							onClick="HomeChatOperateUtil.homeChatShowMenu()">
							<ul id="dropMenu" class="vanish">
								<li><a id="iconChat">发起聊天</a></li>
								<li><a id="iconVoice">关闭声音</a></li>
								<li><a id="iconFeedback">意见反馈</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="userSearch grid_5">
					<input type="text" placeholder="搜索">
				</div>
				<div class="userTab">
					<div id="chatTab" title="聊天"
						onClick="HomeChatOperateUtil.homeChatChangeTab(0)"></div>
					<span class="tabBar"></span>
					<div id="contTab" title="通讯录"
						onClick="HomeChatOperateUtil.homeChatChangeTab(1)"></div>
				</div>
				<div id="userNavChat">
					<ul class="userNavList">
					</ul>
				</div>
				<div id="userNavCont" class="vanish">
					<ul class="userNavList">
					</ul>
				</div>
			</div>
			<div class="chatArea">
				<div class="chatHead grid_11">
					<div class="chatHeadInfo">
						<span id="chatObjectName"></span>
					</div>
				</div>
				<div id="chatRoom" class="grid_11 omega"></div>
				<div class="chatSender grid_11 omega">
					<div class="senderTool">
						<span id="btnFile"></span>
					</div>
					<div class="senderContent">
						<div id="senderBox"></div>
					</div>
					<div class="senderAction">
						<span class="sendNotice vanish">按下Ctrl+Enter换行</span>
						<div id="sendBut" onClick="HomeChatOperateUtil.sendMessage()">发送</div>
					</div>
				</div>
				<div id="contactsInfo" class="vanish">
					<div class="profile">
						<div class="avatar">
							<img id="contactInfoHead">
						</div>
						<div class="nickName_area">
							<div id="chatInfoNickname" class="chatInfoGenderMale"></div>
						</div>
						<p id="signature"></p>
						<div class="metaArea">
							<div class="metaRemark">
								<span id="metaRemarkTip">备注名：</span> <span id="metaRemarkCon">三木同学</span>
							</div>
						</div>
						<div class="actionArea">
							<div id="chatInfoSendBtn">发消息</div>
						</div>
					</div>
				</div>
				<div id="chattingInfo" class="vanish">
					<div class="chatNoInfoOuter">
						<img class="chatNoInfoImg" src="images/system/shin.png"> <span
							class="chatNoInfoTxt">未选择聊天</span>
					</div>
				</div>
			</div>
		</div>
		<div id="frame_user" class="container_16 vanish">
			<div class="frameUserContent">
				<div id="userRecallList" class="displayInline">
					<div class="frameUserHead displayInline">
						<div class="frameUserHeadImg displayInline">
							<div class="recallItemHeadOutter">
								<span id="frameUserImageHead" onClick="HomeUserOperateUtil.updateHeadPic()"></span>
							</div>
						</div>
						<div class="frameUserHeadInfo displayInline">
							<div class="frameUserHeadNickName displayInline">
								<span class="frameUserNickNameTip displayInline">昵称：</span> <span
									id="frameUserNickName" class="displayInline"></span>
							</div>
							<div class="frameUserHeadAutograph displayInline">
								<span class="displayInline">签名：</span> <span
									id="frameUserAutograph" class="displayInline"></span> <span
									class="frameUserAutographEdit"
									onClick="HomeUserOperateUtil.homeUserAutographEditClick()">修改</span>
							</div>
						</div>
					</div>
					<div class="frameUserRecall displayInline"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="home_menu">
		<div class="menuHead" onClick="HomeOperateUtil.menuSwitchByIndex(1)"></div>
		<div class="menuBody">
			<ul class="menuContent">
				<li id="menuChat" class="menuItem"
					onClick="HomeOperateUtil.menuSwitchByIndex(2)"><span
					class="newNoticeOuter"></span></li>
				<li id="menuUser" class="menuItem"
					onClick="HomeOperateUtil.menuSwitchByIndex(3)"><span
					class="newNoticeOuter"></span></li>
				<li id="menuExit" class="menuItem"
					onClick="HomeOperateUtil.homeExit()"></li>
			</ul>
		</div>
	</div>
	<div id="home_store"></div>
	<script src="js/jQuery.js" type="text/javascript"></script>
	<script src="js/customScrollbar.js" type="text/javascript"></script>
	<script src="js/cookie.js" type="text/javascript"></script>
	<script src="js/common.js" type="text/javascript"></script>
	<script src="js/easing.js" type="text/javascript"></script>
	<script src="js/timer.js" type="text/javascript"></script>
	<script src="js/owner.home.js" type="text/javascript"></script>
	<script src="js/owner.home.user.js" type="text/javascript"></script>
	<script src="js/owner.home.main.js" type="text/javascript"></script>
	<script src="js/owner.home.chat.js" type="text/javascript"></script>
</body>
</html>