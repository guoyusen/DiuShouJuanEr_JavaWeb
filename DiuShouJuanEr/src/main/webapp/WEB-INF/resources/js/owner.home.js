$(document).ready(function(e){
	HomeOperateUtil.ready();
	HomeChatOperateUtil.ready();
	HomeMainOperateUtil.ready();
	HomeUserOperateUtil.ready();
});

$(window).resize(function(e) {
	$("#userNavCont").mCustomScrollbar("update");
	$("#userNavChat").mCustomScrollbar("update");
	$("#senderBox").mCustomScrollbar("update");
	$("#chatRoom").mCustomScrollbar("update");
	$("#mainRecallList").mCustomScrollbar("update");
	$("#home_store").mCustomScrollbar("update");
	$("#userRecallList").mCustomScrollbar("update");
});

$(document).mousedown(function(e) {
	
	HomeChatOperateUtil.mouseDown(e);
	HomeOperateUtil.mouseDown(e);
	
});

var HomeDialogUtil = {
	options : {
		title : '',
		hasContent : false,
		callBack : function(){}
	},
	setOptions : function(newOptions) {
		for ( var pro in newOptions) {
			HomeDialogUtil.options[pro] = newOptions[pro];
		}
	},
	createDialog : function(options){
		HomeDialogUtil.setOptions(options);
		HomeDialogUtil.createDialogMask();
		$("body").append(HomeDialogUtil.getDialogHtml());
		
		$("#homeDialogContainer").removeClass("dialogHide");
		$("#homeDialogContainer").addClass("dialogShow");
	},
	createDialogMask : function(){
		$("body").append('<div id="homeDialogMask"></div>');
	},
	getDialogHtml : function(){
		var outPutHtml = '<div id="homeDialogContainer">';
		
		outPutHtml += '<div class="homeDialogTitle">';
		outPutHtml += HomeDialogUtil.options.title;
		outPutHtml += '</div>';
		
		outPutHtml += '<div class="homeDialogButton">';
		outPutHtml += '<span class="homeBtnCancle" onClick="HomeDialogUtil.btnCancle()">取消</span>';
		outPutHtml += '<span class="homeBtnConfirm" onClick="HomeDialogUtil.btnConfirm()">确认</span>';
		outPutHtml += '</div>';
		
		outPutHtml += '</div>';
		
		return outPutHtml;
	},
	btnCancle : function(){
		$("#homeDialogContainer").remove();
		$("#homeDialogMask").remove();
	},
	btnConfirm : function(){
		$("#homeDialogContainer").remove();
		$("#homeDialogMask").remove();
		HomeDialogUtil.options.callBack.call(this);
	}
}

/* 存储Home的所有Type */
var HomeTypeUtil = {
    HOME_ADDCOMMENT_MAINRECALL : 1,
    HOME_ADDCOMMENT_MAINRECALL_RESPON : 2,
    HOME_ADDCOMMENT_FROM_MAIN : 1,
    HOME_ADDCOMMENT_FROM_USER : 2,
    
    HOME_MENU_CHAT : 1,
    HOME_MENU_CONT : 2,
    HOME_MENU_ROOM : 3,
    HOME_MENU_MSG : 4,
    HOME_MENU_NOTICE : 5,
    
    HOME_STATUS_ERROR : 0,
}
/* Home的所有操作Util */
var HomeOperateUtil = {
	statusItemCount : 0,
	addPanelIsShow : false,
	frameHeadColor : null,
	deleteRecallPicId : -1,
	curentRecallPicCount : 0,
	currentMenuItem : "#frame_main",
	mouseDown : function(e){
		if ($("#homeAddComment").length > 0 && HomeOperateUtil.isMouseOutRange(e,"#homeAddComment")) {
			if($("#homeAddCommentContent .mCSB_container").text().length <= 0){
				$("#homeAddComment").remove();
				$("#userRecallList").mCustomScrollbar("update");
				$("#mainRecallList").mCustomScrollbar("update");
			}
		}
		if ($("#homeCustomMenu").length > 0 && HomeOperateUtil.isMouseOutRange(e,"#homeCustomMenu")) {
			$("#homeCustomMenu").remove();
		}
	},
	ready : function(){
		HomeOperateUtil.initCustomScrollbar("#home_store");
		UserInfoUtil.refresh();
	},
	isMouseOutRange : function(e,widgeId){
		if($(widgeId).offset().left > e.clientX
		 || $(widgeId).offset().left + $(widgeId).width() < e.clientX
		 || $(widgeId).offset().top > e.clientY 
		 || $(widgeId).offset().top + $(widgeId).height() < e.clientY){
			return true;
		}
		return false;
	},
	initCustomScrollbar : function(scrollBarId,whileScrollingCallback){
		$(scrollBarId).mCustomScrollbar(
				{
				setWidth : false,setHeight : 0,setTop : 0,setLeft : 0,
				axis : "y",scrollbarPosition : "inside",scrollEasing : "easeOutCirc",
				scrollInertia : 500,autoDraggerLength : true,autoHideScrollbar : false,
				autoExpandScrollbar : false,alwaysShowScrollbar : 0,snapAmount : null,
				snapOffset : 0,mouseWheel : {enable : true,scrollAmount : "auto",
				axis : "y",preventDefault : false,deltaFactor : "auto",normalizeDelta : false,
				invert : false,disableOver : [ "select", "option","keygen", "datalist",
				"textarea" ]},contentTouchScroll : 100,advanced : {autoExpandHorizontalScroll : false,
				autoScrollOnFocus : "input,textarea,select,button,datalist,keygen,a[tabindex],area,object,[contenteditable='true']",
				updateOnContentResize : false,updateOnImageLoad : true,updateOnSelectorChange : false,
				releaseDraggableSelectors : false},theme : "minimal-dark",callbacks : {
				whileScrolling : whileScrollingCallback}});
	},
	initCustomEditor : function(editorId, maxLength, tipId, enterCallback){
		HomeOperateUtil.initCustomScrollbar(editorId);
		$(editorId).css("cursor","text");	
		$(editorId).attr("onClick","HomeOperateUtil.initCustomOnClick('" + editorId + "')");	
		$(editorId + " .mCSB_container").attr("contenteditable","true");
		$(editorId + " .mCSB_container").css("height","auto");
		$(editorId + " .mCSB_container").css("border","none");
		$(editorId + " .mCSB_container").css("outline","none");
		$(editorId + " .mCSB_container").attr("onKeyDown","HomeOperateUtil.initCustomOnKeyDown(event,'"+ editorId + "'," + maxLength +")");
		$(editorId + " .mCSB_container").attr("onKeyUp","HomeOperateUtil.initCustomOnKeyUp(event,'"+ editorId + "'," + maxLength + "," + tipId + "," + enterCallback +")");
	},
	initCustomOnClick : function(editorId){
		$(editorId + " .mCSB_container").focus();
	},
	initCustomOnKeyDown : function(e, editorId, maxLength){
		if (e.which == 13 || ($(editorId + " .mCSB_container").html().length >= maxLength && e.which != 8)) {
			e.preventDefault();
		}
		if($(editorId + " .mCSB_container").html().length > maxLength){
			var tmpContent = $(editorId + " .mCSB_container").html();
			$(editorId + " .mCSB_container").html(tmpContent.substring(0,maxLength));
		}
	},
	initCustomOnKeyUp : function(e, editorId, maxLength, tipId, enterCallback){
		if(e.which == 13 && enterCallback != null){
			var callbacks = $.Callbacks();
			callbacks.add(enterCallback);
			callbacks.fire();
		}
		if($(editorId + " .mCSB_container").html().length > maxLength){
			var tmpContent = $(editorId + " .mCSB_container").html();
			$(editorId + " .mCSB_container").html(tmpContent.substring(0,maxLength));
		}
		if(tipId != null){
			if($(editorId + " .mCSB_container").html().length > 0){
				$(tipId).text(maxLength - $(editorId + " .mCSB_container").html().length);
			}else{
				$(tipId).text(maxLength);
			}
		}
		$(editorId).mCustomScrollbar("update");
	},
	menuShowHideAnimator : function(showMenuItem) {
		if(HomeOperateUtil.currentMenuItem == showMenuItem)
			return;
		$(HomeOperateUtil.currentMenuItem).addClass("vanish");
		HomeOperateUtil.currentMenuItem = showMenuItem;
		$(showMenuItem).removeClass("vanish");
		if(showMenuItem == "#frame_chat"){
			$("#userNavChat").mCustomScrollbar("update");
		}
	},
	menuSwitchIconByIndex : function(index){
		if(index == 1){
			$(".menuHead").css("background-image","url(images/system/logo_theme.png)")
		}else{
			$(".menuHead").css("background-image","url(images/system/logo_white.png)")
		}
		if(index == 2){
			$("#menuChat").css("background-image","url(images/system/chat_2.png)")
		}else{
			$("#menuChat").css("background-image","url(images/system/chat_1.png)")
		}
		if(index == 3){
			$("#menuUser").css("background-image","url(images/system/user_2.png)")
		}else{
			$("#menuUser").css("background-image","url(images/system/user_1.png)")
		}
		if(index == 4){
			$("#menuNotice").css("background-image","url(images/system/notice_2.png)")
		}else{
			$("#menuNotice").css("background-image","url(images/system/notice_1.png)")
		}
	},
	menuSwitchByIndex : function(index){
		HomeOperateUtil.menuSwitchIconByIndex(index);
		switch(index){
		case 1:
			HomeOperateUtil.menuShowHideAnimator("#frame_main");
			HomeMainRecallUtil.refresh();
			break;
		case 2:
			HomeOperateUtil.menuShowHideAnimator("#frame_chat");
			break;
		case 3:
			HomeOperateUtil.menuShowHideAnimator("#frame_user");
			HomeUserRecallUtil.refresh();
			break;
		case 4:
			HomeOperateUtil.menuShowHideAnimator("#frame_notice");
			break;
		}
	},
	homeExit : function(){		
		HomeDialogUtil.createDialog({
			title : '您确定要离开?',
			hasContent : false,
			callBack : function(){
				AjaxUtil.request({
					method : "get",
					url : "1.0/users/logout",
					params : {},
					type : 'json',
					callback : HomeOperateUtil.homeLogOutCallback
				});
			}
		});
	},
	homeLogOutCallback : function(result){
		if (result.retCode == "success") {
			CookieUtil.setCookie("AccessToken", "");
			window.location.href="login";
		} else if (result.retCode == "fail" || result.retCode == "error") {
			HomeOperateUtil.showNoticeTip(result.message);
		}
	},
	clickStatusItem : function(statusItemId, type){
		$("#statusItem_" + statusItemId).attr("onClick","");
		switch(type){
		case HomeChatTypeUtil.CHAT_GOOD:
			HomeOperateUtil.menuSwitchByIndex(3);
			break;
		}
	},
	showNoticeTip : function(message){
		HomeOperateUtil.showCustomNoticeTip({
			type : HomeTypeUtil.HOME_STATUS_ERROR,
			picPath : "images/system/system_tip.png",
			title : "系统错误通知",
			content : message
		});
	},
	showCustomNoticeTip : function(message){
		$("#home_store .mCSB_container").prepend(
				'<div id="statusItem_'
				+ HomeOperateUtil.statusItemCount
				+ '" class="homeStoreItem displayInline homeStoreItemTip" onClick="'
				+ 'HomeOperateUtil.clickStatusItem('
				+ HomeOperateUtil.statusItemCount
				+ ','
				+ message.type
				+ ')'
				+ '">'
				+ '<img src="'
				+ message.picPath
				+ '" class="displayInline">'
			    + '<div class="homeStoreItemInfo displayInline">'
			    + '<span class="homeStoreItemTitle displayInline">'
			    + message.title
				+ '</span>'
			    + '<span class="homeStoreItemContent displayInline">'
			    + message.content
			    + '</span></div></div>');
		HomeOperateUtil.statusItemCount += 1;
		$("#home_store").mCustomScrollbar("update");
	},
	clearHomeAddCommentHtml : function(){
		if($("#homeAddComment").length > 0 && $("#homeAddCommentContent").text().length > 0){
			$("#homeAddComment").remove();
		}
	},
	getHomeAddCommentHtml : function(commentNo,toNo,type,from){
		var outPutHtml = '<div id="homeAddComment" class="displayInline">';
		outPutHtml += '<div id="homeAddCommentContent" class="displayInline"></div>';
		outPutHtml += '<div class="homeAddCommentAction displayInline">';
		outPutHtml += '<div id="homeAddCommentLeftWord">还可以输入<span>500</span>字</div>';
		outPutHtml += '<div class="homeAddCommentSender" onClick="';
		
		switch(type){
		case HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL:
			if(from == HomeTypeUtil.HOME_ADDCOMMENT_FROM_MAIN){
				outPutHtml += 'HomeMainRecallUtil.publishComment(';
			}else if(from == HomeTypeUtil.HOME_ADDCOMMENT_FROM_USER){
				outPutHtml += 'HomeUserRecallUtil.publishComment(';
			}
			outPutHtml += commentNo;
			outPutHtml += ')';
			break;
		case HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON:
			if(from == HomeTypeUtil.HOME_ADDCOMMENT_FROM_MAIN){
				outPutHtml += 'HomeMainRecallUtil.publishRespon(';
			}else if(from == HomeTypeUtil.HOME_ADDCOMMENT_FROM_USER){
				outPutHtml += 'HomeUserRecallUtil.publishRespon(';
			}
			outPutHtml += commentNo;
			outPutHtml += ',';
			outPutHtml += toNo;
			outPutHtml += ')';
			break;
		}
		
		outPutHtml += '">发&nbsp;布</div>';
		outPutHtml += '</div>';
		outPutHtml += '</div>';
		return outPutHtml;
	},
	homeAddRecallPicture : function(){
		if(HomeOperateUtil.curentRecallPicCount >= 9){
			return;
		}
		UploadUtil.choseFile({
			url : '1.0/file/postpic',
			callbefore : HomeOperateUtil.homeAddRecallPictureBefore,
			callback : HomeOperateUtil.homeAddRecallPictureCallback,
			acceptType : UploadUtil.acceptImage
		});
	},
	homeAddRecallRemoveWait : function(){
		$("#postPicWait").remove();
	},
	homeAddRecallPictureBefore : function(){
		HomeOperateUtil.homeAddRecallRemoveWait();
		$("#postPanelSecondRow").removeClass("vanish");
		HomeOperateUtil.curentRecallPicCount ++;
		$("#postPicCount").html('图片(' + HomeOperateUtil.curentRecallPicCount + '/8)');
		$("#postPicBody").append('<div id="postPicWait" class="postPicItem displayInline"><div id="postPic_loading" style="padding-top:38px;"></div></div>');
    	$('#postPic_loading').shCircleLoader({
    		dots : 10,
    		dotsRadius : 3,
    		radius : 18,
    		keyframes:
           '0%   { background:#ccc}\
            40%  { background:transparent}\
            60%  { background:transparent}\
            100% { background:#ccc}'
        });
	},
	homeDeleteRecallPic : function(picId){
		HomeOperateUtil.deleteRecallPicId = picId;
		AjaxUtil.request({
			method : "post",
			url : "1.0/file/postpic/remove",
			params : {picId : picId},
			type : 'json',
			callback : function(result){
				if (result.retCode == "success") {
					$("#recallPic_" + HomeOperateUtil.deleteRecallPicId).remove();
					HomeOperateUtil.curentRecallPicCount --;
					if(HomeOperateUtil.curentRecallPicCount <= 0){
						$("#postPanelSecondRow").addClass("vanish");
					}
					$("#postPicCount").html('图片(' + HomeOperateUtil.curentRecallPicCount + '/8)');
				}else if (result.retCode == "fail" || result.retCode == "error") {
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
	homeAddRecallPictureCallback : function(result){
		HomeOperateUtil.homeAddRecallRemoveWait();
		if (result.retCode == "success") {
			var picture = result.data;
			
			var outPutHtml = '<div class="postPicItem displayInline" id="recallPic_';
			outPutHtml += picture.picId;
			outPutHtml += '"><span class="postPicItemDelete" onClick="HomeOperateUtil.homeDeleteRecallPic(';
			outPutHtml += picture.picId;
			outPutHtml += ')"></span>';
			outPutHtml += '<img class="postPicItemImg" src="';
			outPutHtml += picture.picPath;
			outPutHtml += '" style="';
			if(picture.height != 0){
				outPutHtml = outPutHtml + 'height:'+ picture.height + 'px;'
			}
			if(picture.width != 0){
				outPutHtml = outPutHtml + 'width:'+ picture.width + 'px;'
			}
			if(picture.offSetTop != 0){
				outPutHtml = outPutHtml + 'top:'+ picture.offSetTop + 'px;'
			}
			if(picture.offSetLeft != 0){
				outPutHtml = outPutHtml + 'left:'+ picture.offSetLeft + 'px;'
			}
			outPutHtml += '"></div>';
			
			$("#postPicBody").append(outPutHtml);
		} else if (result.retCode == "fail" || result.retCode == "error") {
			HomeOperateUtil.showNoticeTip(result.message);
		}
	},
	getRecallFirstRow : function(){
		var outPutHtml = '<div id="postPanelFirstRow" class="displayInline">';
		outPutHtml += '<div id="postPanelContent" class="displayInline"></div>';
		outPutHtml += '<div id="postPanelImage" class="displayInline" title="图片" onclick="HomeOperateUtil.homeAddRecallPicture()"></div>';
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallSecondRow : function(){
		var outPutHtml = '<div id="postPanelSecondRow" class="vanish">';
		outPutHtml += '<div class="displayInline postPicHead">';
		outPutHtml += '<div id="postPicTip" class="displayInline"><span id="postPicCount" class="displayInline">图片(0/9)</span></div>';
		outPutHtml += '<span id="postPicDelete" class="displayInline" onClick="HomeOperateUtil.homeDeletePanelPics();">删除图片</span>';
		outPutHtml += '</div>';
		outPutHtml += '<div id="postPicBody" class="displayInline"></div>';
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallThirdRow : function(){
		var outPutHtml = '<div id="postPanelThirdRow" class="displayInline">';
		outPutHtml += '<div id="postPanelSender" onclick="HomeMainOperateUtil.publishRecall()">发&nbsp;布</div>';
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getHomeAddRecallHtml : function(){
		var outPutHtml = '<div id="framePostPanel" class="displayInline">';
		outPutHtml += HomeOperateUtil.getRecallFirstRow();
		outPutHtml += HomeOperateUtil.getRecallSecondRow();
		outPutHtml += HomeOperateUtil.getRecallThirdRow();
		outPutHtml += '</div>';
		return outPutHtml;
	},
	homeAddPanelShow : function(){
		if(!HomeOperateUtil.addPanelIsShow){
			HomeOperateUtil.addPanelIsShow = true;
			$("#mainRecallList .mCSB_container").prepend(HomeOperateUtil.getHomeAddRecallHtml());
			HomeOperateUtil.initCustomEditor("#postPanelContent",5000, null, null);
			$("#frameAddBtn").removeClass("frameAddBtnPost");
			$("#frameAddBtn").addClass("frameAddBtnPostClose");
		}else{
			HomeOperateUtil.homeDeletePanelPics();
			$("#framePostPanel").remove();
			HomeOperateUtil.addPanelIsShow = false;
			$("#frameAddBtn").removeClass("frameAddBtnPostClose");
			$("#frameAddBtn").addClass("frameAddBtnPost");
		}
		$("#mainRecallList").mCustomScrollbar("update");
		$("#mainRecallList").mCustomScrollbar("scrollTo","first");
	},
	homeDeletePanelPics : function(){
		$("#postPanelSecondRow").addClass("vanish");
		$("#postPicBody").empty();
		HomeOperateUtil.curentRecallPicCount = 0;
		$("#postPicCount").html('图片(' + HomeOperateUtil.curentRecallPicCount + '/8)');
		AjaxUtil.request({
			method : "post",
			url : "1.0/file/postpic/removeall",
			params : {},
			type : 'json',
			callback : function(){}
		});
	},
	HTMLEnCode : function(str){
		var s = "";   
        if(str.length == 0)  
        	return "";   
        s = str.replace(/&amp;/g, "&");   
        s = s.replace(/&lt;/g, "<");   
        s = s.replace(/&gt;/g, ">");   
        s = s.replace(/&nbsp;/g, " ");   
        s = s.replace(/'/g, "\'");   
        s = s.replace(/&quot;/g, "\"");   
        s = s.replace(/<br>/g, "\n");   
        s = s.replace(/&#39;/g, "\'");  
        return    s;   
    },
    HTMLDecode : function(str){
    	var s = "";
        if (str.length == 0)
            return "";
        s = str.replace(/&/g, "&gt;");
        s = s.replace(/</g, "&lt;");
        s = s.replace(/>/g, "&gt;");
        s = s.replace(/ /g, "&nbsp;");
        s = s.replace(/\'/g, "'");
        s = s.replace(/\"/g, "&quot;");
        s = s.replace(/\n/g, "<br>");
        return s;
    },
    getMenuItemByTypeAndMenuId : function(type, menuId){
    	var outPutHtml = '';
    	switch(type){
    	case HomeTypeUtil.HOME_MENU_CHAT:
    		outPutHtml += '<li><a class="customDropMenuItem" onClick="ChatUtil.reLocateTop(&#39;';
    		outPutHtml += menuId
    		outPutHtml += '&#39;)">置顶</a></li>';
    		outPutHtml += '<li><a class="customDropMenuItem" onClick="ChatUtil.removeChatItem(&#39;';
    		outPutHtml += menuId;
    		outPutHtml += '&#39;)">关闭聊天</a></li>';
    		break;
    	}
    	return outPutHtml;
    },
    homeOnContextMenu : function(e, type, menuId){
    	if(e.button == 2){
    		$("#homeCustomMenu").remove();
    		
    		var outPutHtml = '<div id="homeCustomMenu" style="left:';
    		outPutHtml += e.clientX;
    		outPutHtml += 'px; top:';
    		outPutHtml += e.clientY;
    		outPutHtml += 'px;">';
    		outPutHtml += '<ul class="customDropMenu">';
    		outPutHtml += HomeOperateUtil.getMenuItemByTypeAndMenuId(type, menuId);
    		outPutHtml += '</ul>';
    		outPutHtml += '</div>';
    		
    		$(".wrap").append(outPutHtml);
    		
    		HomeOperateUtil.homeCloseContextMenu("#homeCustomMenu");
    	}
    },
    homeCloseContextMenu : function(contextId){
    	$(contextId)[0].oncontextmenu = function(){ return false; }
    }
}
/* 时间转换器Util */
var TimeFormatUtil = {
	getCurrentTime : function(){
		var current = new Date().Format("yyyy-MM-dd hh:mm:ss");
		return current;
	},
	getFormatTime : function(time){
		if(!time || time.length <= 0){
			return "";
		}
		var timeBetween = TimeFormatUtil.getTimeBetweenPreciseDay(time);
		switch(timeBetween){
		case 0:
			return TimeFormatUtil.getTime_HH_MM(time);
			break;
		case 1:
			return "昨天" + TimeFormatUtil.getTime_HH_MM(time);
			break;
		case 2:
			return "前天" + TimeFormatUtil.getTime_HH_MM(time);
			break;
		default:
			return TimeFormatUtil.getTimeYYMMDD(time);
		}
	},
	getTimeYYMMDD : function(time){
		var YY = time.substring(time.indexOf('-') - 2,time.indexOf('-'));
		var MM = time.substring(time.indexOf('-') + 1,time.indexOf('-') + 3);
		var DD = time.substring(time.lastIndexOf('-') + 1,time.lastIndexOf('-') + 3);
		
		return YY+"/"+MM+"/"+DD;
	},
	getTime_HH_MM : function(time){
		return time.substring(time.indexOf(':') - 2,time.lastIndexOf(':'));
	},
	getTimeBetweenPreciseDay : function(targetTime){
		var OneMonth = targetTime.substring(5,targetTime.lastIndexOf('-'));
		var OneDay = targetTime.substring(targetTime.lastIndexOf ('-')+1,targetTime.lastIndexOf ('-')+3);
		var OneYear = targetTime.substring(0,targetTime.indexOf ('-'));
			
		var currentTime = new Date().Format("yyyy-MM-dd");
			
		var TwoMonth = currentTime.substring(5,currentTime.lastIndexOf ('-'));
		var TwoDay = currentTime.substring(currentTime.lastIndexOf ('-')+1,currentTime.lastIndexOf ('-')+3);
		var TwoYear = currentTime.substring(0,currentTime.indexOf ('-'));

		var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);
		return Math.abs(cha);
	},
	getTimeBetweenPreciseMinute : function(startTime,endTime){
		var OneHour = startTime.substring(startTime.indexOf(':')-2,startTime.indexOf(':'));
		var OneMinute = startTime.substring(startTime.indexOf(':')+1,startTime.indexOf(':') + 3);
		var OneSecond = startTime.substring(startTime.lastIndexOf(':')+1,startTime.lastIndexOf(':') + 3);
		var OneMonth = startTime.substring(5,startTime.lastIndexOf('-'));
		var OneDay = startTime.substring(startTime.lastIndexOf ('-')+1,startTime.lastIndexOf ('-')+3);
		var OneYear = startTime.substring(0,startTime.indexOf ('-'));
		
		var TwoHour = endTime.substring(endTime.indexOf(':')-2,endTime.indexOf(':'));
		var TwoMinute = endTime.substring(endTime.indexOf(':')+1,endTime.indexOf(':') + 3);
		var TwoSecond = endTime.substring(endTime.lastIndexOf(':')+1,endTime.lastIndexOf(':') + 3);
		var TwoMonth = endTime.substring(5,endTime.lastIndexOf('-'));
		var TwoDay = endTime.substring(endTime.lastIndexOf ('-')+1,endTime.lastIndexOf ('-')+3);
		var TwoYear = endTime.substring(0,endTime.indexOf ('-'));
		
		var cha=((Date.parse(TwoYear,TwoMonth,TwoDay,TwoHour,TwoMinute,TwoSecond)- Date.parse(OneYear,OneMonth,OneDay,OneHour,OneMinute,OneSecond))/60000);
		return Math.abs(cha);
	}
}
/* 用户个人信息Util */
var UserInfoUtil = {
	userInfo : {},
	refresh : function() {
		AjaxUtil.request({
			method : "get",
			url : "1.0/users/info",
			params : {},
			type : 'json',
			callback : UserInfoUtil.refreshCallBack
		});
	},
	refreshCallBack : function(result) {
		if (result.retCode == "success") {
			UserInfoUtil.userInfo = result.data;
			$("#nickName").html(UserInfoUtil.getUserNickName());
			$("#userHeadImg").attr("src", UserInfoUtil.getPicPath());
			
			$("#frameUserNickName").text(UserInfoUtil.getUserNickName());
			$("#frameUserAutograph").text(UserInfoUtil.getAutograph());
			$("#frameUserImageHead").css("background-image","url(" + UserInfoUtil.getPicPath() + ")");
		    document.title = '丢手绢儿-' + UserInfoUtil.getUserNickName();
		    
		    ContactUtil.refresh();
		} else if (result.retCode == "fail" || result.retCode == "error") {
			HomeOperateUtil.showNoticeTip(result.message);
		}
	},
	getUserUserNo : function() {
		return UserInfoUtil.userInfo["userNo"];
	},
	getUserNickName : function() {
		return UserInfoUtil.userInfo["nickName"];
	},
	getPicPath : function() {
		return UserInfoUtil.userInfo["picPath"];
	},
	setAutograph : function(autograph){
		UserInfoUtil.userInfo["autograph"] = autograph;
	},
	getAutograph : function() {
		return UserInfoUtil.userInfo["autograph"];
	}
}
/* 离线信息Util */
var OffMsgUtil = {
		refresh : function() {
			AjaxUtil.request({
				method : "get",
				url : "1.0/offmsgs",
				params : {},
				type : 'json',
				callback : OffMsgUtil.refreshCallBack
			});
		},
		refreshCallBack : function(result) {
			if (result.retCode == "success") {
				var offMsgList = result.data;
				for (var i = 0; i < offMsgList.length; i++) {
					switch (offMsgList[i].msgType) {
					case HomeChatTypeUtil.CHAT_FRI:
						var messageItem = {
							chatId : "chat_fri_" + offMsgList[i].fromNo,
							fromNo : offMsgList[i].fromNo,
							time : offMsgList[i].time,
							content : offMsgList[i].content,
							conType : offMsgList[i].conType,
							owner : HomeChatTypeUtil.MESSAGE_YOU,
							partyNo : -1,
							msgType : HomeChatTypeUtil.CHAT_FRI,
							count : 1
						};
						MessageUtil.addMessageItem(messageItem);
						break;
					case HomeChatTypeUtil.CHAT_PAR:
						var messageItem = {
							chatId : "chat_par_" + offMsgList[i].toNo,
							fromNo : offMsgList[i].fromNo,
							time : offMsgList[i].time,
							content : offMsgList[i].content,
							conType : offMsgList[i].conType,
							owner : HomeChatTypeUtil.MESSAGE_YOU,
							msgType : HomeChatTypeUtil.CHAT_PAR,
							partyNo : offMsgList[i].toNo,
							count : 1
						};
						MessageUtil.addMessageItem(messageItem);
						break;
					}
				}

			} else if (result.retCode == "fail" || result.retCode == "error") {
				HomeOperateUtil.showNoticeTip(result.message);
			}
			WebSocketUtil.connect();
		}
}
