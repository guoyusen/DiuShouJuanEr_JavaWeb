var HomeUserOperateUtil = {
	hasBottom : false,		
	ready : function(){
		HomeOperateUtil.initCustomScrollbar("#userRecallList",HomeUserOperateUtil.userRecallListScrolling);
	},
	userRecallListScrolling : function(){ 
		if(Math.abs(this.mcs.top) <= 60){
			$("#frameAddHead").css("background-color","#eee");
		}else if(Math.abs(this.mcs.top) > 60){
			$("#frameAddHead").css("background-color","#fdfdfd");
		}
		if(this.mcs.topPct >= 95 && !HomeUserRecallUtil.hasBottom){
			HomeUserOperateUtil.addFrameBottomHtml();
			HomeUserRecallUtil.nextPage();
		}
	},
	getFrameBottomHtml : function(){
		var outPutHtml = '<div id="frameUserBottom" class="displayInline">正在加载中...</div>';
	    return outPutHtml;
	},
	removeFrameBottomHtml : function(){
		$("#frameUserBottom").remove();
		HomeUserRecallUtil.hasBottom = false;
		HomeUserRecallUtil.updateRecallListScrollBar();
	},
	addFrameBottomHtml : function(){
		HomeUserRecallUtil.hasBottom = true;
		$("#userRecallList > .mCustomScrollBox > .mCSB_container").append(HomeUserOperateUtil.getFrameBottomHtml());
		HomeUserRecallUtil.updateRecallListScrollBar();
	},		
	homeUserAutographEditClick : function(){
		$(".frameUserHeadAutograph").empty(); 
		$(".frameUserHeadAutograph").append('<pre id="frameUserAutographEditer" contenteditable="true"></pre>'
				+ '<div id="frameUserAutographLeft">还可以输入<span>100</span>字</div>'
				+ '<span class="frameUserAutographSure" onClick="HomeUserOperateUtil.homeUserAutographEditPublish()">发布</span>'
				+ '<span class="frameUserAutographCancle" onClick="HomeUserOperateUtil.homeUserAutographEditReset()">取消</span>');
	},
	homeUserAutographEditReset : function(){
		$(".frameUserHeadAutograph").empty();
		$(".frameUserHeadAutograph").append('<span class="displayInline">签名：</span>'
				+ '<span id="frameUserAutograph" class="displayInline">'
			    + UserInfoUtil.getAutograph()
				+ '</span>'
				+ '<span class="frameUserAutographEdit" onClick="HomeUserOperateUtil.homeUserAutographEditClick()">修改</span>');
	},
	homeUserAutographEditPublish : function(){
		var autograph = HomeOperateUtil.HTMLEnCode($("#frameUserAutographEditer").html());
		AjaxUtil.request({
			method : "post",
			url : "1.0/users/modify/autograph",
			params : {autograph:autograph},
			type : 'json',
			callback : function(result){
				if (result && result.retCode == "success") {
					UserInfoUtil.setAutograph(result.data);
					$(".frameUserHeadAutograph").empty();
					$(".frameUserHeadAutograph").append('<span class="displayInline">签名：</span>'
							+ '<span id="frameUserAutograph" class="displayInline">'
						    + HomeOperateUtil.HTMLDecode(result.data)
							+ '</span>'
							+ '<span class="frameUserAutographEdit" onClick="HomeUserOperateUtil.homeUserAutographEditClick()">修改</span>');
				}else if (result.retCode == "fail" || result.retCode == "error") {
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
}

var HomeUserRecallUtil = {
		recallList : [],
		goodMap : {},
		currentPageIndex : 1,
		lastRecall : -1,
		hasBottom : false,
		deleteRecallNo : 0,
		deleteCommentNo : 0,
		deleteResponNo : 0,
		refresh : function(){
			HomeUserRecallUtil.currentPageIndex = 1;
			AjaxUtil.request({
				method : "get",
				url : "1.0/recalls",
				params : {type:2,pageIndex:HomeUserRecallUtil.currentPageIndex,pageSize:20,userNo:UserInfoUtil.getUserUserNo(),lastRecall:HomeUserRecallUtil.lastRecall},
				type : 'json',
				callback : HomeUserRecallUtil.refreshCallBack
			});
		},
		nextPage : function(){
			AjaxUtil.request({
				method : "get",
				url : "1.0/recalls",
				params : {type:2,pageIndex:HomeUserRecallUtil.currentPageIndex,pageSize:20,userNo:UserInfoUtil.getUserUserNo(),lastRecall:HomeUserRecallUtil.lastRecall},
				type : 'json',
				callback : HomeUserRecallUtil.refreshCallBack
			});
		},
		refreshCallBack : function(result){
			if (result.retCode == "success") {
				if(result.data && result.data.length > 0){
					HomeUserRecallUtil.lastRecall = result.data[0].recallNo;
					if(HomeUserRecallUtil.currentPageIndex == 1){
						HomeUserRecallUtil.recallList = [];
					}
					for(var i = 0; i < result.data.length; i++){
						HomeUserRecallUtil.recallList[HomeUserRecallUtil.recallList.length] = result.data[i];
					}
					HomeMainOperateUtil.removeFrameBottomHtml();
					if(HomeUserRecallUtil.currentPageIndex == 1){
						$("#userRecallList .mCSB_container .frameUserRecall").empty();
						HomeUserRecallUtil.showRecallListHtml(0);
					}else{
						HomeUserRecallUtil.showRecallListHtml(HomeUserRecallUtil.recallList.length - result.data.length);
					}
					/*分页索引加1*/
					HomeUserRecallUtil.currentPageIndex += 1;
				}else{
					$("#frameUserBottom").html("已加载全部数据");
				}
			}else if (result.retCode == "fail" || result.retCode == "error") {
				HomeOperateUtil.showNoticeTip(result.message);
			}
		},
		getGoodCountByRecallNo : function(recallNo){
			var length = 0;
			if(HomeUserRecallUtil.goodMap[recallNo]){
				for(var item in HomeUserRecallUtil.goodMap[recallNo]){
					length++;
				}
				return length;
			}
			return length;
		},
		isCurrentUserGooded : function(recallNo){
			if(HomeUserRecallUtil.goodMap[recallNo] && HomeUserRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()]){
				return true;
			}else{
				return false;
			}
		},
		removeCurrentUserGood : function(recallNo){
			$("#user_good" + recallNo + "_" + UserInfoUtil.getUserUserNo()).remove();
			if(HomeUserRecallUtil.goodMap[recallNo] && HomeUserRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()]){
				delete HomeUserRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()];
				$("#user_recall_" + recallNo + " .recallItemGoodBtn").removeClass("recallItemGoodBtnChose");
				$("#user_recall_" + recallNo + " .recallItemGoodBtn").addClass("recallItemGoodBtnUnChose");
				$("#user_recall_" + recallNo + " .recallItemGoodCount").html(HomeUserRecallUtil.getGoodCountByRecallNo(recallNo));
				
				AjaxUtil.request({
					method : "post",
					url : "1.0/goods/remove",
					params : {recallNo:recallNo},
					type : 'json',
					callback : function(){}
				});
			}
		},
		addCurrentUserGood : function(recallNo){
			if(HomeUserRecallUtil.goodMap[recallNo]){
				HomeUserRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()] = UserInfoUtil.getPicPath();
				$("#user_recall_" + recallNo + " .recallItemGoodList").append('<span id="user_good'
						+ recallNo + "_" + UserInfoUtil.getUserUserNo()
						+ '" class="recallItemGoodHead displayInline" style="background-image:url('
						+ UserInfoUtil.getPicPath()
						+ ')"></span>');
				$("#user_recall_" + recallNo + " .recallItemGoodBtn").addClass("recallItemGoodBtnChose");
				$("#user_recall_" + recallNo + " .recallItemGoodBtn").removeClass("recallItemGoodBtnUnChose");
				$("#user_recall_" + recallNo + " .recallItemGoodCount").html(HomeUserRecallUtil.getGoodCountByRecallNo(recallNo));
				
				AjaxUtil.request({
					method : "post",
					url : "1.0/goods/add",
					params : {recallNo:recallNo},
					type : 'json',
					callback : function(){}
				});
			}
		},
		doRecallItemGood : function(recallNo){
			if(HomeUserRecallUtil.isCurrentUserGooded(recallNo)){
				HomeUserRecallUtil.removeCurrentUserGood(recallNo);
			}else{
				HomeUserRecallUtil.addCurrentUserGood(recallNo);
			}
		},
		getRecallItemHeadHtml : function(index){
			var outPutHtml = '<div class="userRecallItemHead"></div>';
			return outPutHtml;
		},
		getRecallItemContentHtml : function(index){
			var outPutHtml = '<div class="recallItemContent displayInline">';
			outPutHtml += HomeOperateUtil.HTMLDecode(HomeUserRecallUtil.recallList[index].content);
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemGalleryItemHtml : function(index,j){
			var outPutHtml = '<div class="imageOutter displayInline"> <img src="';
			outPutHtml += HomeUserRecallUtil.recallList[index].pictureList[j].picPath;
			outPutHtml += '" style="';
			if(HomeUserRecallUtil.recallList[index].pictureList[j].height != 0){
				outPutHtml = outPutHtml + 'height:'+ HomeUserRecallUtil.recallList[index].pictureList[j].height + 'px;'
			}
			if(HomeUserRecallUtil.recallList[index].pictureList[j].width != 0){
				outPutHtml = outPutHtml + 'width:'+ HomeUserRecallUtil.recallList[index].pictureList[j].width + 'px;'
			}
			if(HomeUserRecallUtil.recallList[index].pictureList[j].offSetTop != 0){
				outPutHtml = outPutHtml + 'top:'+ HomeUserRecallUtil.recallList[index].pictureList[j].offSetTop + 'px;'
			}
			if(HomeUserRecallUtil.recallList[index].pictureList[j].offSetLeft != 0){
				outPutHtml = outPutHtml + 'left:'+ HomeUserRecallUtil.recallList[index].pictureList[j].offSetLeft + 'px;'
			}
			outPutHtml += '" class="recallItemImg"></div>';
			return outPutHtml;
		},
		getRecallItemGalleryHtml : function(index){
			var outPutHtml = '<div class="recallItemGallery displayInline">';
			for(var j = 0; j < HomeUserRecallUtil.recallList[index].pictureList.length; j++){
				outPutHtml += HomeUserRecallUtil.getRecallItemGalleryItemHtml(index,j);
			}
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemSubCommentHtml : function(index){
	        var outPutHtml = '<span id="userRecallItemComment_';
	        outPutHtml += HomeUserRecallUtil.getRecallItemRecallNoByIndex(index);
	        outPutHtml += '" class="recallItemSubComment" onClick="HomeUserRecallUtil.addHomeAddCommentHtml(';
	        outPutHtml += HomeUserRecallUtil.getRecallItemRecallNoByIndex(index);
	        outPutHtml += ',0,';
	        outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL;
	        outPutHtml += ')">评论';
			outPutHtml += (HomeUserRecallUtil.recallList[index].commentList.length <= 0?'':'('+ HomeUserRecallUtil.recallList[index].commentList.length +')');
			outPutHtml += '</span>';
			return outPutHtml;
		},
		getRecallItemSubDeleteHtml : function(index){
			var outPutHtml = '';
			outPutHtml += (HomeUserRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo()?('<span class="recallItemSubDelete" onClick="HomeUserRecallUtil.deleteRecallItemByRecallNo(' + HomeUserRecallUtil.getRecallItemRecallNoByIndex(index) + ')">删除</span>'):'')
			return outPutHtml;
		},
		getRecallItemSubTimeHtml : function(index){
			var outPutHtml = '<span class="recallItemSubTime">';
			outPutHtml += HomeUserRecallUtil.recallList[index].publishTime;
			outPutHtml += '</span>';
			return outPutHtml;
		},
		getRecallItemSubHtml : function(index){
			var outPutHtml = '<div class="recallItemSub displayInline">';
			outPutHtml += HomeUserRecallUtil.getRecallItemSubCommentHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemSubDeleteHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemSubTimeHtml(index);
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemGoodHtml : function(index){
			var isChosed = false;
			var tmpGoodListHtml = '<div class="recallItemGoodList displayInline">';
			HomeUserRecallUtil.goodMap[HomeUserRecallUtil.getRecallItemRecallNoByIndex(index)] = {};
			for(var j = 0; j < HomeUserRecallUtil.recallList[index].goodList.length; j++){
				if(!isChosed && HomeUserRecallUtil.recallList[index].goodList[j].userNo == UserInfoUtil.getUserUserNo()){
					isChosed = true;
				}
				tmpGoodListHtml += '<span id="user_good';
				tmpGoodListHtml += HomeUserRecallUtil.recallList[index].recallNo;
				tmpGoodListHtml += '_';
				tmpGoodListHtml += HomeUserRecallUtil.recallList[index].goodList[j].userNo;
				tmpGoodListHtml += '" class="recallItemGoodHead displayInline" style="background-image:url(';
				tmpGoodListHtml += HomeUserRecallUtil.recallList[index].goodList[j].userPicPath;
				tmpGoodListHtml += ')"></span>';
				
				HomeUserRecallUtil.goodMap[HomeUserRecallUtil.getRecallItemRecallNoByIndex(index)][HomeUserRecallUtil.recallList[index].goodList[j].userNo] = HomeUserRecallUtil.recallList[index].goodList[j].userPicPath; 
			}
			tmpGoodListHtml += '</div>';
			
			var outPutHtml = '<div class="recallItemGood displayInline"><div onClick="HomeUserRecallUtil.doRecallItemGood(';
			outPutHtml += HomeUserRecallUtil.getRecallItemRecallNoByIndex(index);
			outPutHtml += ')" class="recallItemGoodBtn displayInline';
			outPutHtml += (isChosed?' recallItemGoodBtnChose':' recallItemGoodBtnUnChose');
			outPutHtml += '"><span class="recallItemGoodCount displayInline">';
			outPutHtml += HomeUserRecallUtil.recallList[index].goodList.length;
			outPutHtml += '</span></div>';
			outPutHtml += tmpGoodListHtml;
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemCommentItemHeadHtml : function(index,j){
			var outPutHtml = '<span class="commentItemHead displayInline" style="background-image:url(';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].fromPicPath;
			outPutHtml += ')"></span>';
			return outPutHtml;
		},
		getRecallItemCommentItemContentHtml : function(index,j){
			var outPutHtml = '<div class="commentItemContent displayInline"><a>';
			outPutHtml += (ContactUtil.getContactDisplayName('fri_' + HomeUserRecallUtil.recallList[index].commentList[j].fromNo) == null?HomeUserRecallUtil.recallList[index].commentList[j].nickName:ContactUtil.getContactDisplayName('fri_' + HomeUserRecallUtil.recallList[index].commentList[j].fromNo));
			outPutHtml += '：</a>';
			outPutHtml += HomeOperateUtil.HTMLDecode(HomeUserRecallUtil.recallList[index].commentList[j].content);
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemCommentItemInfoHtml : function(index,j){
			var outPutHtml = '<div class="commentItemInfo displayInline">';
			outPutHtml += '<span class="commentItemTime displayInline">';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].addTime;
			outPutHtml += '</span>';
			outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeUserRecallUtil.addHomeAddCommentHtml(';
			outPutHtml += HomeUserRecallUtil.getCommentNoByIndex(index,j);
			outPutHtml += ',';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].fromNo;
			outPutHtml += ',';
			outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
			outPutHtml += ')"></span>';
			outPutHtml += ((HomeUserRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo() || HomeUserRecallUtil.recallList[index].commentList[j].fromNo == UserInfoUtil.getUserUserNo())?'<span class="commentItemDelete displayInline" onClick="HomeUserRecallUtil.deleteCommentByCommentNo('+HomeUserRecallUtil.getCommentNoByIndex(index, j)+','+HomeUserRecallUtil.getRecallItemRecallNoByIndex(index)+')"></span>':'');
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemCommentItemResponItemHeadHtml : function(index,j,k){
			var outPutHtml = '<span class="responItemHead displayInline" style="background-image:url(';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].responList[k].fromPicPath;
			outPutHtml += ')"></span>';
			return outPutHtml;
		},
		getRecallItemCommentItemResponItemContentHtml : function(index,j,k){
			var outPutHtml = '<div class="responItemContent displayInline"><a>';
			outPutHtml += (ContactUtil.getContactDisplayName("fri_" + HomeUserRecallUtil.recallList[index].commentList[j].responList[k].fromNo) == null?HomeUserRecallUtil.recallList[index].commentList[j].responList[k].nickNameFrom:ContactUtil.getContactDisplayName("fri_" + HomeUserRecallUtil.recallList[index].commentList[j].responList[k].fromNo));
			outPutHtml += '：</a>回复<a>';
			outPutHtml += (ContactUtil.getContactDisplayName("fri_" + HomeUserRecallUtil.recallList[index].commentList[j].responList[k].toNo) == null?HomeUserRecallUtil.recallList[index].commentList[j].responList[k].nickNameTo:ContactUtil.getContactDisplayName("fri_" + HomeUserRecallUtil.recallList[index].commentList[j].responList[k].toNo));
		    outPutHtml += '：</a>';
		    outPutHtml += HomeOperateUtil.HTMLDecode(HomeUserRecallUtil.recallList[index].commentList[j].responList[k].content);
		    outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemCommentItemResponItemInfoHtml : function(index,j,k){
			var outPutHtml = '<!--info--><div class="responItemInfo displayInline">';
			outPutHtml += '<span class="commentItemTime displayInline">';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].responList[k].addTime;
			outPutHtml += '</span>';
			outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeUserRecallUtil.addHomeAddCommentHtml(';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].responList[k].commentNo;
			outPutHtml += ',';
			outPutHtml += HomeUserRecallUtil.recallList[index].commentList[j].responList[k].fromNo;
			outPutHtml += ',';
			outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
			outPutHtml += ')"></span>';
			outPutHtml += ((HomeUserRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo() || HomeUserRecallUtil.recallList[index].commentList[j].responList[k].fromNo == UserInfoUtil.getUserUserNo())?'<span class="commentItemDelete displayInline" onClick="HomeUserRecallUtil.deleteResponByResponNo('+HomeUserRecallUtil.getResponNoByIndex(index,j,k)+')"></span>':'');
			outPutHtml += '</div><!--info-->';
			return outPutHtml;
		},
		getRecallItemCommentItemResponItemHtml : function(index,j,k){
			var outPutHtml = '<!--responItem--><div class="responItem displayInline" id="user_respon_';
			outPutHtml += HomeUserRecallUtil.getResponNoByIndex(index,j,k);
			outPutHtml += '">';
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemResponItemHeadHtml(index,j,k);
			outPutHtml += '<div class="responItemBody displayInline">';
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemResponItemContentHtml(index,j,k);
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemResponItemInfoHtml(index,j,k);
			outPutHtml += '</div></div><!--responItem-->';
			return outPutHtml;
		},
		getRecallItemCommentItemResponHtml : function(index,j){
			var outPutHtml = '<div class="commentItemRespon displayInline">';
			for(k = 0; k < HomeUserRecallUtil.recallList[index].commentList[j].responList.length; k++){
				outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemResponItemHtml(index,j,k);
			}
			outPutHtml += '</div>';
			return outPutHtml;
		},
		getRecallItemCommentItemHtml : function(index,j){
			var outPutHtml = '<!-- commentItem --><div class="commentItem displayInline" id="user_comment_';
			outPutHtml += HomeUserRecallUtil.getCommentNoByIndex(index, j);
			outPutHtml += '">';
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemHeadHtml(index,j);
			/*commentItemBody*/
			outPutHtml += '<div class="commentItemBody displayInline">';
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemContentHtml(index,j);
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemInfoHtml(index,j);
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemResponHtml(index,j);
			outPutHtml += '</div>';
			/*commentItemBody*/
			outPutHtml += '</div><!-- commentItem -->';
			return outPutHtml;
		},
		getRecallItemCommentHtml : function(index){
			var outPutHtml = '<!-- recallItemComment --><div class="recallItemComment displayInline">';
			for(var j = 0; j < HomeUserRecallUtil.recallList[index].commentList.length; j++){
				outPutHtml += HomeUserRecallUtil.getRecallItemCommentItemHtml(index,j);
			}
			outPutHtml += '</div><!-- recallItemComment -->';
			return outPutHtml;
		},
		getRecallItemBodyHtml : function(index){
			var outPutHtml = '<div class="userRecallItemBody displayInline"><div class="recallItemMain displayInline">';
			outPutHtml += HomeUserRecallUtil.getRecallItemContentHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemGalleryHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemSubHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemGoodHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemCommentHtml(index);
			outPutHtml += '</div></div>';
			return outPutHtml;
		},
		getRecallItemHtml : function(index){
			var outPutHtml = '<!-- userRecallItem --><div class="userRecallItem displayInline" id="user_recall_';
			outPutHtml += HomeUserRecallUtil.getRecallItemRecallNoByIndex(index);
			outPutHtml += '">';
			outPutHtml += HomeUserRecallUtil.getRecallItemHeadHtml(index);
			outPutHtml += HomeUserRecallUtil.getRecallItemBodyHtml(index);
			outPutHtml += '</div><!-- userRecallItem -->';
			return outPutHtml;
		},
		minusRecallItemCommentCount : function(recallNo){
			var commentCount = $("#userRecallItemComment_"+recallNo).text();
			if(commentCount.indexOf('(') < 0){
				$("#userRecallItemComment_"+recallNo).text("评论");
			}else{
				commentCount = commentCount.substring(commentCount.indexOf('(')+1,commentCount.indexOf(')'));
				commentCount -= 1;
				if(commentCount <= 0){
					$("#userRecallItemComment_"+recallNo).text("评论");
				}else{
					$("#userRecallItemComment_"+recallNo).text("评论(" + commentCount + ")");
				}
			}
		},
		addHomeAddCommentHtml : function(commentNo,toNo,type){
			HomeOperateUtil.clearHomeAddCommentHtml();
			if(type == HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL){
				$("#user_recall_" + commentNo + " .userRecallItemBody").append(HomeOperateUtil.getHomeAddCommentHtml(commentNo,toNo,type,HomeTypeUtil.HOME_ADDCOMMENT_FROM_USER));
			}else if(type == HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON){
				$("#user_comment_" + commentNo).parent().parent().parent().append(HomeOperateUtil.getHomeAddCommentHtml(commentNo,toNo,type,HomeTypeUtil.HOME_ADDCOMMENT_FROM_USER));
			}
			
			HomeOperateUtil.initCustomScrollbar("#homeAddCommentContent");
			HomeOperateUtil.initCustomEditor("#homeAddCommentContent","HomeOperateUtil.homeAddCommentContentKeyUp(event)","HomeOperateUtil.homeAddCommentContentKeyDown(event)");
			
			HomeUserRecallUtil.updateRecallListScrollBar();
		},
		addRecallItemCommentCount : function(recallNo){
			var commentCount = $("#userRecallItemComment_"+recallNo).text();
			if(commentCount.indexOf('(') < 0){
				$("#userRecallItemComment_"+recallNo).text("评论(1)");
			}else{
				commentCount = commentCount.substring(commentCount.indexOf('(')+1,commentCount.indexOf(')'));
				commentCount = parseInt(commentCount) + 1;
				$("#userRecallItemComment_"+recallNo).text("评论(" + commentCount + ")");
			}
		},
		deleteRecallItemByRecallNo : function(recallNo){
			HomeUserRecallUtil.deleteRecallNo = recallNo;
			AjaxUtil.request({
				method : "post",
				url : "1.0/recalls/delete",
				params : {recallno:recallNo},
				type : 'json',
				callback : function(result){
					if (result.retCode == "success") {
						HomeUserRecallUtil.updateRecallListScrollBar();
						$("#user_recall_"+HomeUserRecallUtil.deleteRecallNo).remove();
					}else if (result.retCode == "fail" || result.retCode == "error") {
						HomeOperateUtil.showNoticeTip(result.message);
					}
				}
			});
		},
		deleteCommentByCommentNo : function(commentNo,recallNo){
			HomeUserRecallUtil.deleteCommentNo = commentNo; 
			HomeUserRecallUtil.deleteRecallNo = recallNo;
			AjaxUtil.request({
				method : "post",
				url : "1.0/comments/delete",
				params : {commentno:commentNo},
				type : 'json',
				callback : function(result){
					if (result.retCode == "success") {
						HomeUserRecallUtil.minusRecallItemCommentCount(HomeUserRecallUtil.deleteRecallNo);
						$("#user_comment_"+HomeUserRecallUtil.deleteCommentNo).remove();
						HomeUserRecallUtil.updateRecallListScrollBar();
					}else if (result.retCode == "fail" || result.retCode == "error") {
						HomeOperateUtil.showNoticeTip(result.message);
					}
				}
			});
		},
		deleteResponByResponNo : function(responNo){
			HomeUserRecallUtil.deleteResponNo = responNo;
			AjaxUtil.request({
				method : "post",
				url : "1.0/respons/delete",
				params : {responno:responNo},
				type : 'json',
				callback : function(result){
					if (result.retCode == "success") {
						$("#user_respon_"+HomeUserRecallUtil.deleteResponNo).remove();
						HomeUserRecallUtil.updateRecallListScrollBar();
					}else if (result.retCode == "fail" || result.retCode == "error") {
						HomeOperateUtil.showNoticeTip(result.message);
					}
				}
			});
		},
		getResponNoByIndex : function(index,j,k){
			if(index >= HomeUserRecallUtil.recallList.length || j >= HomeUserRecallUtil.recallList[index].commentList.length || k >= HomeUserRecallUtil.recallList[index].commentList[j].responList.length){
				return -1;/*代表无效的responNo*/
			}else{
				return HomeUserRecallUtil.recallList[index].commentList[j].responList[k].responNo;
			}
		},
		getCommentNoByIndex : function(index,j){
			if(index >= HomeUserRecallUtil.recallList.length || j >= HomeUserRecallUtil.recallList[index].commentList.length){
				return -1;/*代表无效的commentNo*/
			}else{
				return HomeUserRecallUtil.recallList[index].commentList[j].commentNo;
			}
		},
		getRecallItemRecallNoByIndex : function(index){
			if(index >= HomeUserRecallUtil.recallList.length){
				/*代表无效的recallNo*/
				return -1;
			}else{
				return HomeUserRecallUtil.recallList[index].recallNo;
			}
		},
		updateRecallListScrollBar : function(){
			$("#userRecallList").mCustomScrollbar("update");
		},
		showRecallListHtml : function(i){
			for(;i < HomeUserRecallUtil.recallList.length; i++){
				$("#userRecallList .mCSB_container .frameUserRecall").append(HomeUserRecallUtil.getRecallItemHtml(i));
			}
			HomeUserRecallUtil.updateRecallListScrollBar();
		},
		publishRespon : function(commentNo, toNo){
			AjaxUtil.request({
				method : "post",
				url : "1.0/respons/respon",
				params : {commentno:commentNo,tono:toNo,content:HomeOperateUtil.HTMLEnCode($("#homeAddCommentContent .mCSB_container").html())},
				type : 'json',
				callback : function(result){
					if (result && result.retCode == "success" && result.data) {
						var respon = result.data;
						
						var outPutHtml = '<!-- responItem --><div class="responItem displayInline" id="user_respon_';
						outPutHtml += respon.responNo;
						outPutHtml += '">';
						/*head*/
						outPutHtml += '<span class="responItemHead displayInline" style="background-image:url(';
						outPutHtml += respon.fromPicPath;
						outPutHtml += ')"></span>';
						/*head*/
						outPutHtml += '<div class="responItemBody displayInline">';
						/*content*/
						outPutHtml += '<div class="responItemContent displayInline"><a>';
						outPutHtml += (ContactUtil.getContactDisplayName("fri_"+respon.fromNo) == null?respon.nickNameFrom:ContactUtil.getContactDisplayName("fri_" + respon.fromNo));
						outPutHtml += '：</a>回复<a>';
						outPutHtml += (ContactUtil.getContactDisplayName("fri_" + respon.toNo) == null?respon.nickNameTo:ContactUtil.getContactDisplayName("fri_" + respon.toNo));
					    outPutHtml += '：</a>';
					    outPutHtml += HomeOperateUtil.HTMLDecode(respon.content);
					    outPutHtml += '</div>';
						/*content*/
					    /*info*/
					    outPutHtml += '<div class="responItemInfo displayInline">';
						outPutHtml += '<span class="commentItemTime displayInline">';
						outPutHtml += respon.addTime;
						outPutHtml += '</span>';
						outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeUserRecallUtil.addHomeAddCommentHtml(';
						outPutHtml += respon.commentNo;
						outPutHtml += ',';
						outPutHtml += respon.fromNo;
						outPutHtml += ',';
						outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
						outPutHtml += ')"></span>';
						outPutHtml += (respon.fromNo == UserInfoUtil.getUserUserNo()?'<span class="commentItemDelete displayInline" onClick="HomeUserRecallUtil.deleteResponByResponNo('+respon.responNo+')"></span>':'');
						outPutHtml += '</div>';
						/*info*/
						outPutHtml += '</div>';
						outPutHtml += '</div><!-- responItem -->';
						$("#user_comment_" + respon.commentNo + " .commentItemRespon").append(outPutHtml);
						$("#homeAddComment").remove();
						HomeUserRecallUtil.updateRecallListScrollBar();
					}else{
					}
				}
			});
		},
		publishComment : function(recallNo){
			AjaxUtil.request({
				method : "post",
				url : "1.0/comments/comment",
				params : {recallno:recallNo,content:HomeOperateUtil.HTMLEnCode($("#homeAddCommentContent .mCSB_container").html())},
				type : 'json',
				callback : function(result){
					if (result && result.retCode == "success" && result.data) {
						var comment = result.data;
						var outPutHtml = '<!-- commentItem --><div class="commentItem displayInline" id="user_comment_';
						outPutHtml += comment.commentNo;
						outPutHtml += '">';
						outPutHtml += '<span class="commentItemHead displayInline" style="background-image:url(';
						outPutHtml += comment.fromPicPath;
						outPutHtml += ')"></span>';
						outPutHtml += '<div class="commentItemBody displayInline">';
						/*content*/
						outPutHtml += '<div class="commentItemContent displayInline"><a>';
						outPutHtml += (ContactUtil.getContactDisplayName('fri_' + comment.fromNo) == null?comment.nickName:ContactUtil.getContactDisplayName('fri_' + comment.fromNo));
						outPutHtml += '：</a>';
						outPutHtml += HomeOperateUtil.HTMLDecode(comment.content);
						outPutHtml += '</div>';
						/*content*/
						/*info*/
						outPutHtml += '<div class="commentItemInfo displayInline">';
						outPutHtml += '<span class="commentItemTime displayInline">';
						outPutHtml += comment.addTime;
						outPutHtml += '</span>';
						outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeUserRecallUtil.addHomeAddCommentHtml(';
						outPutHtml += comment.commentNo;
						outPutHtml += ',';
						outPutHtml += comment.fromNo;
						outPutHtml += ',';
						outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
						outPutHtml += ')"></span>';
						outPutHtml += (comment.fromNo == UserInfoUtil.getUserUserNo()?'<span class="commentItemDelete displayInline" onClick="HomeUserRecallUtil.deleteCommentByCommentNo('+comment.commentNo+','+comment.recallNo+')"></span>':'');
						outPutHtml += '</div>';
						/*info*/
						/*respon*/
						outPutHtml += '<div class="commentItemRespon displayInline"></div>';
						/*respon*/
						outPutHtml += '</div>';
						outPutHtml += '</div><!-- commentItem -->';
						
						$("#user_recall_" + comment.recallNo + " .recallItemComment").append(outPutHtml);
						HomeUserRecallUtil.addRecallItemCommentCount(comment.recallNo);
						$("#homeAddComment").remove();
						HomeUserRecallUtil.updateRecallListScrollBar();
					}else{
					}
				}
			});
		}
	}
