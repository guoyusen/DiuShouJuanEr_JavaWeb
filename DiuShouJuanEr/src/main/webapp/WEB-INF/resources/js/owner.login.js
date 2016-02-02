var LoginOperateUtil = {
	isAnimating : false,
	isLogin : false,
	isRegister : false,
	isReset : false,
	isMsgReg : false,
	isMsgRet : false,
	showFrameLogin : function(){
		
		var form_login = $("#form_login");
		var form_register = $("#form_register");
		
		if(!LoginOperateUtil.isAnimating){
			LoginOperateUtil.isAnimating = true;
			form_register.animate({opacity: 0}, {
 				step: function(now, mx) {
					opacity = 1 - now;
					form_register.css({'opacity': opacity});
				},
				duration: 200, 
				complete: function(){
					form_register.addClass("vanish");
					form_register.css("opacity",1);
					form_login.removeClass("vanish"); 
					LoginOperateUtil.isAnimating = false;
				}, 
				//this comes from the custom easing plugin
				easing: 'easeInOutBack'
			});
		}else{
			return;
		}
		$("#form_reset").addClass("vanish");
		$("#change_forget").addClass("vanish");
		$("#change_login").removeClass("login_split_btn_unchose");
		$("#change_login").addClass("login_split_btn_chose");
		$("#change_register").addClass("login_split_btn_unchose");
		$("#change_register").removeClass("login_split_btn_chose");
		$("#login_split_login").removeClass("vanish");
		$("#login_split_forget").addClass("vanish");
	},
	showFrameRegister : function(){
		
		var form_login = $("#form_login");
		var form_register = $("#form_register");
		
		if(!LoginOperateUtil.isAnimating){
			LoginOperateUtil.isAnimating = true;
			form_login.animate({opacity: 0}, {
				step: function(now, mx) {
						opacity = 1 - now;
						form_login.css({'opacity': opacity});
				},
				duration: 200, 
				complete: function(){
					form_login.addClass("vanish");
					form_login.css("opacity",1);
					form_register.removeClass("vanish"); 
					LoginOperateUtil.isAnimating = false;
				}, 
				//this comes from the custom easing plugin
				easing: 'easeInOutBack'
			});
		}else{
			return;
		}
		$("#form_reset").addClass("vanish");
		$("#change_forget").addClass("vanish");
		$("#change_login").addClass("login_split_btn_unchose");
		$("#change_login").removeClass("login_split_btn_chose");
		$("#change_register").removeClass("login_split_btn_unchose");
		$("#change_register").addClass("login_split_btn_chose");
		$("#login_split_login").removeClass("vanish");
		$("#login_split_forget").addClass("vanish");
	},
	showFrameForget : function(){
		LoginOperateUtil.isAnimating = false;
		$("#form_login").addClass("vanish");
		$("#form_register").addClass("vanish");
		$("#form_reset").removeClass("vanish");
		$("#change_forget").removeClass("vanish");
		$("#login_split_login").addClass("vanish");
		$("#login_split_forget").removeClass("vanish");
	},
    executeLogin : function(){
    	if(!LoginOperateUtil.isLogin){
    		LoginOperateUtil.isLogin = true;
    		
    		var loginAccount = $("#loginAccount").val();
    		var loginPsd = $("#loginPsd").val();
    		
    		LoginOperateUtil.showCircleLoader("#btn_login","#3dbcf5");
    		
    		AjaxUtil.request({
    			method : "post",
    			url : "1.0/users/login",
    			params : {mobile:loginAccount,password:loginPsd},
    			type : "json",
    			callback : function(result){
    				LoginOperateUtil.isLogin = false;
    				if(result.retCode == "success"){
    					CookieUtil.setCookie("AccessToken", result.data.AccessToken);
    					window.location.href="home";
    				}else if(result.retCode == "fail" || result.retCode == "error"){
    					LoginOperateUtil.showNoticeTip(result.message);
    				}
    				$("#btn_login").hover(function(){
    					$("#btn_login").css("background-color","#3dbcf5");
    					},function(){
    					$("#btn_login").css("background-color","#049cdb");
    				});
    				$("#btn_login").html("");
    				$("#btn_login").text("登 录");
    			}
    		});
    	}
    },
    executeRegist : function(){
    	if(!LoginOperateUtil.isRegister){
    		LoginOperateUtil.isRegister = true;
    		
    		var regiAccount = $("#regiAccount").val();
    		var regiPsd = $("#regiPsd").val();
    		var regiMsg = $("#regiMsg").val();
    		
    		LoginOperateUtil.showCircleLoader("#btn_register","#6bd95b");
    		
    		AjaxUtil.request({
    			method : "post",
    			url : "1.0/users/regist",
    			params : {mobile:regiAccount,password:regiPsd,code:regiMsg},
    			type : "json",
    			callback : function(result){
    				LoginOperateUtil.isRegister = false;
    				if(result.retCode == "success"){
    					LoginOperateUtil.showNoticeTip(result.message);
    					showFrame("login");
    				}else if(result.retCode == "fail" || result.retCode == "error"){
    					LoginOperateUtil.showNoticeTip(result.message);
    				}
    				$("#btn_register").hover(function(){
    					$("#btn_register").css("background-color","#6bd95b");
    					},function(){
    					$("#btn_register").css("background-color","#49be38");
    				});
    				$("#btn_register").html("");
    				$("#btn_register").text("注 册");
    			}
    		});
    	}
    },
    executeReset : function(){
    	if(!LoginOperateUtil.isReset){
    		LoginOperateUtil.isReset = true;
    		
    		var resetAccount = $("#resetAccount").val();
    		var resetPsd = $("#resetPsd").val();
    		var resetMsg = $("#resetMsg").val();
    		
    		LoginOperateUtil.showCircleLoader("#btn_reset","#6bd95b");
    		
    		AjaxUtil.request({
    			method : "post",
    			url : "1.0/users/reset",
    			params :{mobile:resetAccount,password:resetPsd,code:resetMsg},
    			type : "json",
    			callback : function(result){
    				LoginOperateUtil.isReset = false;
    				if(result.retCode == "success"){
    					LoginOperateUtil.showNoticeTip(result.message);
    					LoginOperateUtil.showFrameLogin();
    				}else if(result.retCode == "fail" || result.retCode == "error"){
    					LoginOperateUtil.showNoticeTip(result.message);
    				}
    				$("#btn_reset").hover(function(){
    					$("#btn_reset").css("background-color","#6bd95b");
    					},function(){
    					$("#btn_reset").css("background-color","#49be38");
    				});
    				$("#btn_reset").html("");
    				$("#btn_reset").text("重 置");
    			}
    		});
    	}
    },
    executeMsgReg : function(){
    	if(!LoginOperateUtil.isMsgReg){
    		LoginOperateUtil.isMsgReg = true;
    		
    		var regiAccount = $("#regiAccount").val();
    		
    		LoginOperateUtil.showCircleLoader("#regiMsgBtn","#3dbcf5");
    		
    		AjaxUtil.request({
    			method : "post",
    			url : "1.0/verify/code",
    			params :{mobile:regiAccount,type:1},
    			type : "json",
    			callback : function(result){
    				if(result.retCode == "success"){
    					var timeCount = 60;
    					$(this).everyTime('1s','B',function(){ 
    						if(timeCount == 60){
    							$("#regiMsgBtn").html(timeCount+"秒后点击重试");
    						}
    						$("#regiMsgBtn").text(timeCount+"秒后点击重试");
    						if(timeCount == 0){
    							$("#regiMsgBtn").text("重新发送");
    							LoginOperateUtil.isMsgReg = false;
    							$("#regiMsgBtn").hover(function(){
    								$("#regiMsgBtn").css("background-color","#3dbcf5");
    								},function(){
    								$("#regiMsgBtn").css("background-color","#049cdb");
    							});
    						}
    						timeCount--;
    					},61); 
    				}else if(result.retCode == "fail" || result.retCode == "error"){
    					LoginOperateUtil.showNoticeTip(result.message);
    				}
    			}
    		});
    	}
    },
    executeMsgRet : function(){
    	if(!LoginOperateUtil.isMsgRet){
    		LoginOperateUtil.isMsgRet = true;
    		var retAccount = $("#resetAccount").val();
    		
    		LoginOperateUtil.showCircleLoader("#resetMsgBtn","#3dbcf5");
    		
    		AjaxUtil.request({
    			method : "post",
    			url : "1.0/verify/code",
    			params : {mobile:retAccount,type:2},
    			type : "json",
    			callback : function(result){
    				if(result.retCode == "success"){
    					var timeCount = 60;
    					$(this).everyTime('1s','B',function(){ 
    						if(timeCount == 60){
    							$("#resetMsgBtn").html(timeCount+"秒后点击重试");
    						}
    						$("#resetMsgBtn").text(timeCount+"秒后点击重试");
    						if(timeCount == 0){
    							$("#resetMsgBtn").text("重新发送");
    							LoginOperateUtil.isMsgRet = false;
    							$("#resetMsgBtn").hover(function(){
    								$("#resetMsgBtn").css("background-color","#3dbcf5");
    								},function(){
    								$("#resetMsgBtn").css("background-color","#049cdb");
    							});
    						}
    						timeCount--;
    					},61); 
    				}else if(result.retCode == "fail" || result.retCode == "error"){
    					LoginOperateUtil.showNoticeTip(result.message);
    				}
    			}
    		});
    	}
    },
    showNoticeTip : function(message){
    	$("#notice_tip").animate({height:'42px'},"500","easeInOutBack",function(){$("#notice_tip").text(message)});
    	$(this).oneTime('2s','A',function(){ 
    		$("#notice_tip").text("");
    		$("#notice_tip").animate({height:'0px'},"500");
    	}); 
    },
    showCircleLoader : function(showId, color){
    	$(showId).text("");
    	$(showId).hover(function(){
    		$(showId).css("background-color",color);
    	},function(){
    		$(showId).css("background-color",color);
    	});
    	$(showId).append("<div id='login_loading' style='padding-top:11px;'></div>");
    	$('#login_loading').shCircleLoader({
    		dots : 8,
    		dotsRadius : 2,
    		radius : 14,
    		keyframes:
           '0%   { background:#fff}\
            40%  { background:transparent}\
            60%  { background:transparent}\
            100% { background:#fff}'
        });
    }
}

