var HomeMainRecallUtil = {
	recallList : [],
	goodMap : {},
	currentPageIndex : 1,
	lastRecall : -1,
	hasBottom : false,
	deleteRecallNo : 0,
	deleteCommentNo : 0,
	deleteResponNo : 0,
	refresh : function(){
		HomeMainRecallUtil.currentPageIndex = 1;
		AjaxUtil.request({
			method : "get",
			url : "1.0/recalls",
			params : {type:1,pageIndex:HomeMainRecallUtil.currentPageIndex,pageSize:20,userNo:UserInfoUtil.getUserUserNo(),lastRecall:HomeMainRecallUtil.lastRecall},
			type : 'json',
			callback : HomeMainRecallUtil.refreshCallBack
		});
	},
	nextPage : function(){
		AjaxUtil.request({
			method : "get",
			url : "1.0/recalls",
			params : {type:1,pageIndex:HomeMainRecallUtil.currentPageIndex,pageSize:20,userNo:UserInfoUtil.getUserUserNo(),lastRecall:HomeMainRecallUtil.lastRecall},
			type : 'json',
			callback : HomeMainRecallUtil.refreshCallBack
		});
	},
	refreshCallBack : function(result){
		if (result.retCode == "success") {
			if(result.data && result.data.length > 0){
				HomeMainRecallUtil.lastRecall = result.data[0].recallNo;
				if(HomeMainRecallUtil.currentPageIndex == 1){
					HomeMainRecallUtil.recallList = [];
				}
				for(var i = 0; i < result.data.length; i++){
					HomeMainRecallUtil.recallList[HomeMainRecallUtil.recallList.length] = result.data[i];
				}
				HomeMainOperateUtil.removeFrameBottomHtml();
				if(HomeMainRecallUtil.currentPageIndex == 1){
					$("#mainRecallList .mCSB_container").empty();
					HomeMainRecallUtil.showRecallListHtml(0);
				}else{
					HomeMainRecallUtil.showRecallListHtml(HomeMainRecallUtil.recallList.length - result.data.length);
				}
				/*分页索引加1*/
				HomeMainRecallUtil.currentPageIndex += 1;
			}else{
				$("#frameMainBottom").html("已加载全部数据");
			}
		}else if (result.retCode == "fail" || result.retCode == "error") {
			HomeOperateUtil.showNoticeTip(result.message);
		}
	},
	getGoodCountByRecallNo : function(recallNo){
		var length = 0;
		if(HomeMainRecallUtil.goodMap[recallNo]){
			for(var item in HomeMainRecallUtil.goodMap[recallNo]){
				length++;
			}
			return length;
		}
		return length;
	},
	isCurrentUserGooded : function(recallNo){
		if(HomeMainRecallUtil.goodMap[recallNo] && HomeMainRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()]){
			return true;
		}else{
			return false;
		}
	},
	removeCurrentUserGood : function(recallNo){
		$("#main_good" + recallNo + "_" + UserInfoUtil.getUserUserNo()).remove();
		if(HomeMainRecallUtil.goodMap[recallNo] && HomeMainRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()]){
			delete HomeMainRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()];
			$("#main_recall_" + recallNo + " .recallItemGoodBtn").removeClass("recallItemGoodBtnChose");
			$("#main_recall_" + recallNo + " .recallItemGoodBtn").addClass("recallItemGoodBtnUnChose");
			$("#main_recall_" + recallNo + " .recallItemGoodCount").html(HomeMainRecallUtil.getGoodCountByRecallNo(recallNo));
			
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
		if(HomeMainRecallUtil.goodMap[recallNo]){
			HomeMainRecallUtil.goodMap[recallNo][UserInfoUtil.getUserUserNo()] = UserInfoUtil.getPicPath();
			$("#main_recall_" + recallNo + " .recallItemGoodList").append('<span id="main_good'
					+ recallNo + "_" + UserInfoUtil.getUserUserNo()
					+ '" class="recallItemGoodHead displayInline" style="background-image:url('
					+ UserInfoUtil.getPicPath()
					+ ')"></span>');
			$("#main_recall_" + recallNo + " .recallItemGoodBtn").addClass("recallItemGoodBtnChose");
			$("#main_recall_" + recallNo + " .recallItemGoodBtn").removeClass("recallItemGoodBtnUnChose");
			$("#main_recall_" + recallNo + " .recallItemGoodCount").html(HomeMainRecallUtil.getGoodCountByRecallNo(recallNo));
			
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
		if(HomeMainRecallUtil.isCurrentUserGooded(recallNo)){
			HomeMainRecallUtil.removeCurrentUserGood(recallNo);
		}else{
			HomeMainRecallUtil.addCurrentUserGood(recallNo);
		}
	},
	getRecallItemHeadHtml : function(index){
		var outPutHtml = '<div class="recallItemHead displayInline"><div class="recallItemHeadOutter"><span class="recallItemHeadInner" style="background-image:url(';
		outPutHtml += HomeMainRecallUtil.recallList[index].userPicPath;
		outPutHtml += ')"></span></div>';
		outPutHtml += '<span class="recallItemUserName displayInline">';
		outPutHtml += (ContactUtil.getContactDisplayName("fri_"+HomeMainRecallUtil.recallList[index].userNo)!=null?ContactUtil.getContactDisplayName("fri_"+HomeMainRecallUtil.recallList[index].userNo):HomeMainRecallUtil.recallList[index].userName);
		outPutHtml += '</span>';
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemContentHtml : function(index){
		var outPutHtml = '<div disabled="true" class="recallItemContent displayInline">';
		outPutHtml += HomeOperateUtil.HTMLDecode(HomeMainRecallUtil.recallList[index].content);
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemGalleryItemHtml : function(index,j){
		var outPutHtml = '<div class="imageOutter displayInline"> <img src="';
		outPutHtml += HomeMainRecallUtil.recallList[index].pictureList[j].picPath;
		outPutHtml += '" style="';
		if(HomeMainRecallUtil.recallList[index].pictureList[j].height != 0){
			outPutHtml = outPutHtml + 'height:'+ HomeMainRecallUtil.recallList[index].pictureList[j].height + 'px;'
		}
		if(HomeMainRecallUtil.recallList[index].pictureList[j].width != 0){
			outPutHtml = outPutHtml + 'width:'+ HomeMainRecallUtil.recallList[index].pictureList[j].width + 'px;'
		}
		if(HomeMainRecallUtil.recallList[index].pictureList[j].offSetTop != 0){
			outPutHtml = outPutHtml + 'top:'+ HomeMainRecallUtil.recallList[index].pictureList[j].offSetTop + 'px;'
		}
		if(HomeMainRecallUtil.recallList[index].pictureList[j].offSetLeft != 0){
			outPutHtml = outPutHtml + 'left:'+ HomeMainRecallUtil.recallList[index].pictureList[j].offSetLeft + 'px;'
		}
		outPutHtml += '" class="recallItemImg"></div>';
		return outPutHtml;
	},
	getRecallItemGalleryHtml : function(index){
		var outPutHtml = '<div class="recallItemGallery displayInline">';
		for(var j = 0; j < HomeMainRecallUtil.recallList[index].pictureList.length; j++){
			outPutHtml += HomeMainRecallUtil.getRecallItemGalleryItemHtml(index,j);
		}
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemSubCommentHtml : function(index){
        var outPutHtml = '<span id="mainRecallItemComment_';
        outPutHtml += HomeMainRecallUtil.getRecallItemRecallNoByIndex(index);
        outPutHtml += '" class="recallItemSubComment" onClick="HomeMainRecallUtil.addHomeAddCommentHtml(';
        outPutHtml += HomeMainRecallUtil.getRecallItemRecallNoByIndex(index);
        outPutHtml += ',0,';
        outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL;
        outPutHtml += ')">评论';
		outPutHtml += (HomeMainRecallUtil.recallList[index].commentList.length <= 0?'':'('+ HomeMainRecallUtil.recallList[index].commentList.length +')');
		outPutHtml += '</span>';
		return outPutHtml;
	},
	getRecallItemSubDeleteHtml : function(index){
		var outPutHtml = '';
		outPutHtml += (HomeMainRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo()?('<span class="recallItemSubDelete" onClick="HomeMainRecallUtil.deleteRecallItemByRecallNo(' + HomeMainRecallUtil.getRecallItemRecallNoByIndex(index) + ')">删除</span>'):'')
		return outPutHtml;
	},
	getRecallItemSubTimeHtml : function(index){
		var outPutHtml = '<span class="recallItemSubTime">';
		outPutHtml += HomeMainRecallUtil.recallList[index].publishTime;
		outPutHtml += '</span>';
		return outPutHtml;
	},
	getRecallItemSubHtml : function(index){
		var outPutHtml = '<div class="recallItemSub displayInline">';
		outPutHtml += HomeMainRecallUtil.getRecallItemSubCommentHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemSubDeleteHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemSubTimeHtml(index);
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemGoodHtml : function(index){
		var isChosed = false;
		var tmpGoodListHtml = '<div class="recallItemGoodList displayInline">';
		HomeMainRecallUtil.goodMap[HomeMainRecallUtil.getRecallItemRecallNoByIndex(index)] = {};
		for(var j = 0; j < HomeMainRecallUtil.recallList[index].goodList.length; j++){
			if(!isChosed && HomeMainRecallUtil.recallList[index].goodList[j].userNo == UserInfoUtil.getUserUserNo()){
				isChosed = true;
			}
			tmpGoodListHtml += '<span id="main_good';
			tmpGoodListHtml += HomeMainRecallUtil.recallList[index].recallNo;
			tmpGoodListHtml += '_';
			tmpGoodListHtml += HomeMainRecallUtil.recallList[index].goodList[j].userNo;
			tmpGoodListHtml += '" class="recallItemGoodHead displayInline" style="background-image:url(';
			tmpGoodListHtml += HomeMainRecallUtil.recallList[index].goodList[j].userPicPath;
			tmpGoodListHtml += ')"></span>';
			
			HomeMainRecallUtil.goodMap[HomeMainRecallUtil.getRecallItemRecallNoByIndex(index)][HomeMainRecallUtil.recallList[index].goodList[j].userNo] = HomeMainRecallUtil.recallList[index].goodList[j].userPicPath; 
		}
		tmpGoodListHtml += '</div>';
		
		var outPutHtml = '<div class="recallItemGood displayInline"><div onClick="HomeMainRecallUtil.doRecallItemGood(';
		outPutHtml += HomeMainRecallUtil.getRecallItemRecallNoByIndex(index);
		outPutHtml += ')" class="recallItemGoodBtn displayInline';
		outPutHtml += (isChosed?' recallItemGoodBtnChose':' recallItemGoodBtnUnChose');
		outPutHtml += '"><span class="recallItemGoodCount displayInline">';
		outPutHtml += HomeMainRecallUtil.recallList[index].goodList.length;
		outPutHtml += '</span></div>';
		outPutHtml += tmpGoodListHtml;
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemCommentItemHeadHtml : function(index,j){
		var outPutHtml = '<span class="commentItemHead displayInline" style="background-image:url(';
		outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].fromPicPath;
		outPutHtml += ')"></span>';
		return outPutHtml;
	},
	getRecallItemCommentItemContentHtml : function(index,j){
		var outPutHtml = '<div class="commentItemContent displayInline"><a>';
		outPutHtml += (ContactUtil.getContactDisplayName('fri_' + HomeMainRecallUtil.recallList[index].commentList[j].fromNo) == null?HomeMainRecallUtil.recallList[index].commentList[j].nickName:ContactUtil.getContactDisplayName('fri_' + HomeMainRecallUtil.recallList[index].commentList[j].fromNo));
		outPutHtml += '：</a>';
		outPutHtml += HomeOperateUtil.HTMLDecode(HomeMainRecallUtil.recallList[index].commentList[j].content);
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemCommentItemInfoHtml : function(index,j){
		var outPutHtml = '<div class="commentItemInfo displayInline">';
		outPutHtml += '<span class="commentItemTime displayInline">';
		outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].addTime;
		outPutHtml += '</span>';
		if(HomeMainRecallUtil.recallList[index].commentList[j].fromNo != UserInfoUtil.getUserUserNo()){
			outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeMainRecallUtil.addHomeAddCommentHtml(';
			outPutHtml += HomeMainRecallUtil.getCommentNoByIndex(index,j);
			outPutHtml += ',';
			outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].fromNo;
			outPutHtml += ',';
			outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
			outPutHtml += ')"></span>';
		}
		outPutHtml += ((HomeMainRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo() || HomeMainRecallUtil.recallList[index].commentList[j].fromNo == UserInfoUtil.getUserUserNo())?'<span class="commentItemDelete displayInline" onClick="HomeMainRecallUtil.deleteCommentByCommentNo('+HomeMainRecallUtil.getCommentNoByIndex(index, j)+','+HomeMainRecallUtil.getRecallItemRecallNoByIndex(index)+')"></span>':'');
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemCommentItemResponItemHeadHtml : function(index,j,k){
		var outPutHtml = '<span class="responItemHead displayInline" style="background-image:url(';
		outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromPicPath;
		outPutHtml += ')"></span>';
		return outPutHtml;
	},
	getRecallItemCommentItemResponItemContentHtml : function(index,j,k){
		var outPutHtml = '<div class="responItemContent displayInline"><a>';
		outPutHtml += (ContactUtil.getContactDisplayName("fri_" + HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromNo) == null?HomeMainRecallUtil.recallList[index].commentList[j].responList[k].nickNameFrom:ContactUtil.getContactDisplayName("fri_" + HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromNo));
		outPutHtml += '：</a>回复<a>';
		outPutHtml += (ContactUtil.getContactDisplayName("fri_" + HomeMainRecallUtil.recallList[index].commentList[j].responList[k].toNo) == null?HomeMainRecallUtil.recallList[index].commentList[j].responList[k].nickNameTo:ContactUtil.getContactDisplayName("fri_" + HomeMainRecallUtil.recallList[index].commentList[j].responList[k].toNo));
	    outPutHtml += '：</a>';
	    outPutHtml += HomeOperateUtil.HTMLDecode(HomeMainRecallUtil.recallList[index].commentList[j].responList[k].content);
	    outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemCommentItemResponItemInfoHtml : function(index,j,k){
		var outPutHtml = '<!--info--><div class="responItemInfo displayInline">';
		outPutHtml += '<span class="commentItemTime displayInline">';
		outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].responList[k].addTime;
		outPutHtml += '</span>';
		if(HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromNo != UserInfoUtil.getUserUserNo()){
			outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeMainRecallUtil.addHomeAddCommentHtml(';
			outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].responList[k].commentNo;
			outPutHtml += ',';
			outPutHtml += HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromNo;
			outPutHtml += ',';
			outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
			outPutHtml += ')"></span>';
		}
		outPutHtml += ((HomeMainRecallUtil.recallList[index].userNo == UserInfoUtil.getUserUserNo() || HomeMainRecallUtil.recallList[index].commentList[j].responList[k].fromNo == UserInfoUtil.getUserUserNo())?'<span class="commentItemDelete displayInline" onClick="HomeMainRecallUtil.deleteResponByResponNo('+HomeMainRecallUtil.getResponNoByIndex(index,j,k)+')"></span>':'');
		outPutHtml += '</div><!--info-->';
		return outPutHtml;
	},
	getRecallItemCommentItemResponItemHtml : function(index,j,k){
		var outPutHtml = '<!--responItem--><div class="responItem displayInline" id="main_respon_';
		outPutHtml += HomeMainRecallUtil.getResponNoByIndex(index,j,k);
		outPutHtml += '">';
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemResponItemHeadHtml(index,j,k);
		outPutHtml += '<div class="responItemBody displayInline">';
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemResponItemContentHtml(index,j,k);
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemResponItemInfoHtml(index,j,k);
		outPutHtml += '</div></div><!--responItem-->';
		return outPutHtml;
	},
	getRecallItemCommentItemResponHtml : function(index,j){
		var outPutHtml = '<div class="commentItemRespon displayInline">';
		for(k = 0; k < HomeMainRecallUtil.recallList[index].commentList[j].responList.length; k++){
			outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemResponItemHtml(index,j,k);
		}
		outPutHtml += '</div>';
		return outPutHtml;
	},
	getRecallItemCommentItemHtml : function(index,j){
		var outPutHtml = '<!-- commentItem --><div class="commentItem displayInline" id="main_comment_';
		outPutHtml += HomeMainRecallUtil.getCommentNoByIndex(index, j);
		outPutHtml += '">';
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemHeadHtml(index,j);
		/*commentItemBody*/
		outPutHtml += '<div class="commentItemBody displayInline">';
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemContentHtml(index,j);
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemInfoHtml(index,j);
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemResponHtml(index,j);
		outPutHtml += '</div>';
		/*commentItemBody*/
		outPutHtml += '</div><!-- commentItem -->';
		return outPutHtml;
	},
	getRecallItemCommentHtml : function(index){
		var outPutHtml = '<!-- recallItemComment --><div class="recallItemComment displayInline">';
		for(var j = 0; j < HomeMainRecallUtil.recallList[index].commentList.length; j++){
			outPutHtml += HomeMainRecallUtil.getRecallItemCommentItemHtml(index,j);
		}
		outPutHtml += '</div><!-- recallItemComment -->';
		return outPutHtml;
	},
	getRecallItemBodyHtml : function(index){
		var outPutHtml = '<div class="recallItemBody displayInline"><div class="recallItemMain displayInline">';
		outPutHtml += HomeMainRecallUtil.getRecallItemContentHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemGalleryHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemSubHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemGoodHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemCommentHtml(index);
		outPutHtml += '</div></div>';
		return outPutHtml;
	},
	getRecallItemHtml : function(index){
		var outPutHtml = '<!-- recallItem --><div class="recallItem displayInline" id="main_recall_';
		outPutHtml += HomeMainRecallUtil.getRecallItemRecallNoByIndex(index);
		outPutHtml += '">';
		outPutHtml += HomeMainRecallUtil.getRecallItemHeadHtml(index);
		outPutHtml += HomeMainRecallUtil.getRecallItemBodyHtml(index);
		outPutHtml += '</div><!-- recallItem -->';
		return outPutHtml;
	},
	minusRecallItemCommentCount : function(recallNo){
		var commentCount = $("#mainRecallItemComment_"+recallNo).text();
		if(commentCount.indexOf('(') < 0){
			$("#mainRecallItemComment_"+recallNo).text("评论");
		}else{
			commentCount = commentCount.substring(commentCount.indexOf('(')+1,commentCount.indexOf(')'));
			commentCount -= 1;
			if(commentCount <= 0){
				$("#mainRecallItemComment_"+recallNo).text("评论");
			}else{
				$("#mainRecallItemComment_"+recallNo).text("评论(" + commentCount + ")");
			}
		}
	},
	addHomeAddCommentHtml : function(commentNo,toNo,type){
		HomeOperateUtil.clearHomeAddCommentHtml();
		if(type == HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL){
			$("#main_recall_" + commentNo + " .recallItemBody").append(HomeOperateUtil.getHomeAddCommentHtml(commentNo,toNo,type,HomeTypeUtil.HOME_ADDCOMMENT_FROM_MAIN));
		}else if(type == HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON){
			$("#main_comment_" + commentNo).parent().parent().parent().append(HomeOperateUtil.getHomeAddCommentHtml(commentNo,toNo,type,HomeTypeUtil.HOME_ADDCOMMENT_FROM_MAIN));
		}
		HomeOperateUtil.initCustomEditor("#homeAddCommentContent", 500, "'#homeAddCommentLeftWord span'", null);
		HomeMainRecallUtil.updateRecallListScrollBar();
	},
	addRecallItemCommentCount : function(recallNo){
		var commentCount = $("#mainRecallItemComment_"+recallNo).text();
		if(commentCount.indexOf('(') < 0){
			$("#mainRecallItemComment_"+recallNo).text("评论(1)");
		}else{
			commentCount = commentCount.substring(commentCount.indexOf('(')+1,commentCount.indexOf(')'));
			commentCount = parseInt(commentCount) + 1;
			$("#mainRecallItemComment_"+recallNo).text("评论(" + commentCount + ")");
		}
	},
	deleteRecallItemByRecallNo : function(recallNo){
		HomeMainRecallUtil.deleteRecallNo = recallNo;
		AjaxUtil.request({
			method : "post",
			url : "1.0/recalls/remove",
			params : {recallNo:recallNo},
			type : 'json',
			callback : function(result){
				if (result.retCode == "success") {
					$("#main_recall_"+HomeMainRecallUtil.deleteRecallNo).remove();
					HomeMainRecallUtil.updateRecallListScrollBar();
				}else if (result.retCode == "fail" || result.retCode == "error") {
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
	deleteCommentByCommentNo : function(commentNo,recallNo){
		HomeMainRecallUtil.deleteCommentNo = commentNo; 
		HomeMainRecallUtil.deleteRecallNo = recallNo;
		AjaxUtil.request({
			method : "post",
			url : "1.0/comments/remove",
			params : {commentNo:commentNo},
			type : 'json',
			callback : function(result){
				if (result.retCode == "success") {
					HomeMainRecallUtil.minusRecallItemCommentCount(HomeMainRecallUtil.deleteRecallNo);
					$("#main_comment_"+HomeMainRecallUtil.deleteCommentNo).remove();
					HomeMainRecallUtil.updateRecallListScrollBar();
				}else if (result.retCode == "fail" || result.retCode == "error") {
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
	deleteResponByResponNo : function(responNo){
		HomeMainRecallUtil.deleteResponNo = responNo;
		AjaxUtil.request({
			method : "post",
			url : "1.0/respons/remove",
			params : {responNo:responNo},
			type : 'json',
			callback : function(result){
				if (result.retCode == "success") {
					HomeMainRecallUtil.updateRecallListScrollBar();
					$("#main_respon_"+HomeMainRecallUtil.deleteResponNo).remove();
				}else if (result.retCode == "fail" || result.retCode == "error") {
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
	getResponNoByIndex : function(index,j,k){
		if(index >= HomeMainRecallUtil.recallList.length || j >= HomeMainRecallUtil.recallList[index].commentList.length || k >= HomeMainRecallUtil.recallList[index].commentList[j].responList.length){
			return -1;/*代表无效的responNo*/
		}else{
			return HomeMainRecallUtil.recallList[index].commentList[j].responList[k].responNo;
		}
	},
	getCommentNoByIndex : function(index,j){
		if(index >= HomeMainRecallUtil.recallList.length || j >= HomeMainRecallUtil.recallList[index].commentList.length){
			return -1;/*代表无效的commentNo*/
		}else{
			return HomeMainRecallUtil.recallList[index].commentList[j].commentNo;
		}
	},
	getRecallItemRecallNoByIndex : function(index){
		if(index >= HomeMainRecallUtil.recallList.length){
			/*代表无效的recallNo*/
			return -1;
		}else{
			return HomeMainRecallUtil.recallList[index].recallNo;
		}
	},
	updateRecallListScrollBar : function(){
		$("#mainRecallList").mCustomScrollbar("update");
	},
	showRecallListHtml : function(i){
		for(;i < HomeMainRecallUtil.recallList.length; i++){
			$("#mainRecallList .mCSB_container").append(HomeMainRecallUtil.getRecallItemHtml(i));
		}
		HomeMainRecallUtil.updateRecallListScrollBar();
	},
	publishRespon : function(commentNo, toNo){
		AjaxUtil.request({
			method : "post",
			url : "1.0/respons/add",
			params : {commentNo:commentNo,toNo:toNo,content:HomeOperateUtil.HTMLEnCode($("#homeAddCommentContent .mCSB_container").html())},
			type : 'json',
			callback : function(result){
				if (result && result.retCode == "success" && result.data) {
					var respon = result.data;
					
					var outPutHtml = '<!-- responItem --><div class="responItem displayInline" id="main_respon_';
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
					if(respon.fromNo != UserInfoUtil.getUserUserNo()){
						outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeMainRecallUtil.addHomeAddCommentHtml(';
						outPutHtml += respon.commentNo;
						outPutHtml += ',';
						outPutHtml += respon.fromNo;
						outPutHtml += ',';
						outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
						outPutHtml += ')"></span>';
					}
					outPutHtml += (respon.fromNo == UserInfoUtil.getUserUserNo()?'<span class="commentItemDelete displayInline" onClick="HomeMainRecallUtil.deleteResponByResponNo('+respon.responNo+')"></span>':'');
					outPutHtml += '</div>';
					/*info*/
					outPutHtml += '</div>';
					outPutHtml += '</div><!-- responItem -->';
					$("#main_comment_" + respon.commentNo + " .commentItemRespon").append(outPutHtml);
					$("#homeAddComment").remove();
					HomeMainRecallUtil.updateRecallListScrollBar();
				}else{
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	},
	publishComment : function(recallNo){
		AjaxUtil.request({
			method : "post",
			url : "1.0/comments/add",
			params : {recallNo:recallNo,content:HomeOperateUtil.HTMLEnCode($("#homeAddCommentContent .mCSB_container").html())},
			type : 'json',
			callback : function(result){
				if (result && result.retCode == "success" && result.data) {
					var comment = result.data;
					var outPutHtml = '<!-- commentItem --><div class="commentItem displayInline" id="main_comment_';
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
					if(comment.fromNo != UserInfoUtil.getUserUserNo()){
						outPutHtml += '<span class="commentItemAdd displayInline" onClick="HomeMainRecallUtil.addHomeAddCommentHtml(';
						outPutHtml += comment.commentNo;
						outPutHtml += ',';
						outPutHtml += comment.fromNo;
						outPutHtml += ',';
						outPutHtml += HomeTypeUtil.HOME_ADDCOMMENT_MAINRECALL_RESPON;
						outPutHtml += ')"></span>';
					}
					outPutHtml += (comment.fromNo == UserInfoUtil.getUserUserNo()?'<span class="commentItemDelete displayInline" onClick="HomeMainRecallUtil.deleteCommentByCommentNo('+comment.commentNo+','+comment.recallNo+')"></span>':'');
					outPutHtml += '</div>';
					/*info*/
					/*respon*/
					outPutHtml += '<div class="commentItemRespon displayInline"></div>';
					/*respon*/
					outPutHtml += '</div>';
					outPutHtml += '</div><!-- commentItem -->';
					
					$("#main_recall_" + comment.recallNo + " .recallItemComment").append(outPutHtml);
					HomeMainRecallUtil.addRecallItemCommentCount(comment.recallNo);
					$("#homeAddComment").remove();
					HomeMainRecallUtil.updateRecallListScrollBar();
				}else{
					HomeOperateUtil.showNoticeTip(result.message);
				}
			}
		});
	}
}
var HomeMainOperateUtil = {
		ready : function(){
			HomeOperateUtil.initCustomScrollbar("#mainRecallList",HomeMainOperateUtil.userRecallListScrolling);
		},
		userRecallListScrolling : function(){
			if(Math.abs(this.mcs.top) <= 60){
				$("#frameAddHead").css("background-color","#eee");
			}else if(Math.abs(this.mcs.top) > 60){
				$("#frameAddHead").css("background-color","#fdfdfd");
			}
			if(this.mcs.topPct >= 95 && !HomeMainRecallUtil.hasBottom){
				HomeMainOperateUtil.addFrameBottomHtml();
				HomeMainRecallUtil.nextPage();
			}
		},
		getFrameBottomHtml : function(){
			var outPutHtml = '<div id="frameMainBottom" class="displayInline">正在加载中...</div>';
			return outPutHtml;
		},
		removeFrameBottomHtml : function(){
			$("#frameMainBottom").remove();
			HomeMainRecallUtil.hasBottom = false;
			HomeMainRecallUtil.updateRecallListScrollBar();
		},
		addFrameBottomHtml : function(){
			HomeMainRecallUtil.hasBottom = true;
			$("#mainRecallList .mCSB_container").append(HomeMainOperateUtil.getFrameBottomHtml());
			HomeMainRecallUtil.updateRecallListScrollBar();
		},
		publishRecall : function(){
			AjaxUtil.request({
				method : "post",
				url : "1.0/recalls/add",
				params : {content:HomeOperateUtil.HTMLEnCode($("#postPanelContent .mCSB_container").html()),picount : HomeOperateUtil.curentRecallPicCount},
				type : 'json',
				callback : HomeMainOperateUtil.publishRecallCallBack
			});
		},
		publishRecallCallBack: function(result){
			if (result.retCode == "success") {
				HomeOperateUtil.addPanelIsShow = false;
				$("#frameAddBtn").removeClass("frameAddBtnPostClose");
				$("#frameAddBtn").addClass("frameAddBtnPost");
				HomeMainRecallUtil.refresh();
			}else if (result.retCode == "fail" || result.retCode == "error") {
				showNoticeTip(result.message);
			}
		}
}