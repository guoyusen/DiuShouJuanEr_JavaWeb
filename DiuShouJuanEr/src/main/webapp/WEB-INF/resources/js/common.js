/* ==================================加载loading==================================== */
(function($) {
	$.fn.shCircleLoader = function(first, second) {
		var defaultNamespace = "shcl", id = 1, sel = $(this);
		// Destroy the loader
		if (first === "destroy") {
			sel.find("." + defaultNamespace).detach();
			return;
			// Show progress status into the center
		} else if ((first === "progress") && (typeof second !== "undefined")) {
			sel.each(function() {
				var el = $(this), outer = el.find('.' + defaultNamespace);
				if (!outer.get(0))
					return;
				if (!el.find('span').get(0))
					outer.append("<span></span>");
				var span = outer.find('span').last();
				span.html(second)
						.css(
								{
									position : "absolute",
									display : "block",
									left : Math.round((outer.width() - span
											.width()) / 2)
											+ "px",
									top : Math.round((outer.height() - span
											.height()) / 2)
											+ "px"
								});
			});
			return;
		}
		// Default options
		var o = {
			namespace : defaultNamespace,
			radius : "auto", // "auto" - calculate from selector's width and
			// height
			dotsRadius : "auto",
			color : "auto", // "auto" - get from selector's color CSS property;
			// null - do not set
			dots : 12,
			duration : 1,
			clockwise : true,
			externalCss : false, // true - don't apply CSS from the script
			keyframes : '0%{{prefix}transform:scale(1)}80%{{prefix}transform:scale(.3)}100%{{prefix}transform:scale(1)}',
			uaPrefixes : [ 'o', 'ms', 'webkit', 'moz', '' ]
		};
		$.extend(o, first);
		// Usable options (for better YUI compression)
		var cl = o.color, ns = o.namespace, dots = o.dots, eCss = o.externalCss, ua = o.uaPrefixes,
		// Helper functions
		no_px = function(str) {
			return str.replace(/(.*)px$/i, "$1");
		}, parseCss = function(text) {
			var i, prefix, ret = "";
			for (i = 0; i < ua.length; i++) {
				prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
				ret += text.replace(/\{prefix\}/g, prefix);
			}
			return ret;
		}, prefixedCss = function(property, value) {
			var ret = {};
			if (!property.substr) {
				$.each(property, function(p, v) {
					$.extend(ret, prefixedCss(p, v));
				});
			} else {
				var i, prefix;
				for (i = 0; i < ua.length; i++) {
					prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
					ret[prefix + property] = value;
				}
			}
			return ret;
		};
		// Get unexisting ID
		while ($('#' + ns + id).get(0)) {
			id++;
		}
		// Create animation CSS
		if (!eCss) {
			var kf = o.keyframes.replace(/\s+$/, "").replace(/^\s+/, "");

			// Test if the first keyframe (0% or "from") has visibility
			// property. If not - add it.
			if (!/(\;|\{)\s*visibility\s*\:/gi.test(kf))
				kf = /^(0+\%|from)\s*\{/i.test(kf) ? kf.replace(
						/^((0+\%|from)\s*\{)(.*)$/i, "$1visibility:visible;$3")
						: (/\s+(0+\%|from)\s*\{/i.test(kf) ? kf.replace(
								/(\s+(0+\%|from)\s*\{)/i,
								"$1visibility:visible;")
								: ("0%{visibility:visible}" + kf));

			$($('head').get(0) ? 'head' : 'body').append(
					'<style id="'
							+ ns
							+ id
							+ '" type="text/css">'
							+ parseCss('@{prefix}keyframes ' + ns + id
									+ '_bounce{' + kf + '}') + '</style>');
		}
		// Create loader
		sel
				.each(function() {
					var r, dr, i, dot, rad, x, y, delay, offset, css, cssBase = {}, el = $(this), l = el
							.find('.' + defaultNamespace);
					// If loader exists, destroy it before creating new one
					if (l.get(0))
						l.shCircleLoader("destroy");
					el
							.html('<div class="'
									+ ns
									+ ((ns != defaultNamespace) ? (" " + defaultNamespace)
											: "") + '"></div>');
					if (eCss)
						el = el.find('div');
					x = el.innerWidth() - no_px(el.css('padding-left'))
							- no_px(el.css('padding-right'));
					y = el.innerHeight() - no_px(el.css('padding-top'))
							- no_px(el.css('padding-bottom'));
					r = (o.radius == "auto") ? ((x < y) ? (x / 2) : (y / 2))
							: o.radius;

					if (!eCss) {
						r--;
						if (o.dotsRadius == "auto") {
							dr = Math.abs(Math.sin(Math.PI / (1 * dots))) * r;
							dr = (dr * r) / (dr + r) - 1;
						} else
							dr = o.dotsRadius;

						el = el.find('div');

						i = Math.ceil(r * 2);
						css = {
							position : "relative",
							width : i + "px",
							height : i + "px"
						};

						if (i < x)
							css.marginLeft = Math.round((x - i) / 2);
						if (i < y)
							css.marginTop = Math.round((y - i) / 2);

						el.css(css);

						i = Math.ceil(dr * 2) + "px";
						cssBase = {
							position : "absolute",
							visibility : "hidden",
							width : i,
							height : i
						};

						if (cl !== null)
							cssBase.background = (cl == "auto") ? el
									.css('color') : cl;

						$.extend(cssBase, prefixedCss({
							'border-radius' : Math.ceil(dr) + "px",
							'animation-name' : ns + id + "_bounce",
							'animation-duration' : o.duration + "s",
							'animation-iteration-count' : "infinite",
							'animation-direction' : "normal"
						}));
					}

					for (i = 0; i < dots; i++) {
						el.append("<div></div>");
						if (eCss && (typeof dr === "undefined"))
							dr = (no_px(el.find('div').css('width')) / 2);
						dot = el.find('div').last();
						delay = (o.duration / dots) * i;
						rad = (2 * Math.PI * i) / dots;
						offset = r - dr;
						x = offset * Math.sin(rad);
						y = offset * Math.cos(rad);

						if (o.clockwise)
							y = -y;

						css = {
							left : Math.round(x + offset) + "px",
							top : Math.round(y + offset) + "px"
						};

						if (delay)
							$.extend(css, prefixedCss('animation-delay', delay
									+ 's'));

						$.extend(css, cssBase);
						dot.css(css);
					}
					;
				});
	}

})(jQuery);
/* ==============================封装Jquery.Cookie============================ */
var CookieUtil = {
		setCookie : function(cookieName, cookieValue, expiresNum){
			var options = {
					'path' : '/',
					/* 'domain': "diushoujuaner",*/
					'secure' : false,// 关闭https传输cookie
					'raw' : true,// 关闭cookie的自动编码功能
					/*'expires' : expiresNum || -1*/
			};

			$.cookie(cookieName, cookieValue, options);
		},
		getCookie : function(cookieName){
			return $.cookie(cookieName);
		},
		delCookie : function(cookieName){
			$.cookie(cookieName, '', {
				'path' : '/',
				'expires' : -1
			});
		}
	}
/* ==============================封装Ajax请求框架================================ */
var AjaxUtil = {
		ajaxPool:[],
		callbackPool:[],
		getInstance: function() {
	        for (var i = 0; i < this.ajaxPool.length; i++) {
	            if (this.ajaxPool[i].readyState == 0 || this.ajaxPool[i].readyState == 4) {
	            	this.callbackPool[i] = this.options.callback;
	                return this.ajaxPool[i];
	            }
	        }
	        // IE5中不支持push方法
	        this.ajaxPool[this.ajaxPool.length] = this.createRequest.call(this);
	        this.callbackPool[this.callbackPool.length] = this.options.callback;
	        return this.ajaxPool[this.ajaxPool.length - 1];
	    },
		// 基础选项
		options : {
			method : "get", // 默认提交的方法,get post
			url : "", // 请求的路径 required
			params : {}, // 请求的参数
			type : 'text', // 返回的内容的类型,text,xml,json
			callback : function() {
			}// 回调函数 required
		},
		// 创建XMLHttpRequest对象
		createRequest : function() {
			var xmlhttp;
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");// IE6以上版本
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");// IE6以下版本
				} catch (e) {
					try {
						xmlhttp = new XMLHttpRequest();
						if (xmlhttp.overrideMimeType) {
							xmlhttp.overrideMimeType("text/xml");
						}
					} catch (e) {
						alert("您的浏览器不支持Ajax");
					}
				}
			}
			return xmlhttp;
		},
		// 设置基础选项
		setOptions : function(newOptions) {
			for ( var pro in newOptions) {
				this.options[pro] = newOptions[pro];
			}
		},
		// 格式化请求参数
		formateParameters : function() {
			var paramsArray = [];
			var params = this.options.params;
			for ( var pro in params) {
				var paramValue = params[pro];
				/*
				 * if(this.options.method.toUpperCase() === "GET") { paramValue =
				 * encodeURIComponent(params[pro]); }
				 */
				paramsArray[paramsArray.length] = pro + "=" + paramValue;
			}
			return paramsArray.join("&");
		},
		// 状态改变的处理
		readystatechange : function(xmlhttp) {
			// 获取返回值
			var returnValue,callback;
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				switch (this.options.type) {
				case "xml":
					returnValue = xmlhttp.responseXML;
					break;
				case "json":
					var jsonText = xmlhttp.responseText;
					if (jsonText) {
						returnValue = eval("(" + jsonText + ")");
					}
					break;
				default:
					returnValue = xmlhttp.responseText;
					break;
				}
				for(var i = 0; i < this.ajaxPool.length; i++){
					if(this.ajaxPool[i] == xmlhttp){
						callback = this.callbackPool[i];
						break;
					}
				}
				if (returnValue && callback) {
					callback.call(this, returnValue);
				} else {
					callback.call(this);
				}
			}
		},
		// 发送Ajax请求
		request : function(options) {
			var ajaxObj = this;
			// 设置参数
			ajaxObj.setOptions.call(ajaxObj, options);
			// 创建XMLHttpRequest对象
			var xmlhttp = ajaxObj.getInstance.call(ajaxObj);
			// 设置回调函数
			xmlhttp.onreadystatechange = function() {
				ajaxObj.readystatechange.call(ajaxObj, xmlhttp);
			};
			// 格式化参数
			var formateParams = ajaxObj.formateParameters.call(ajaxObj);
			// 请求的方式
			var method = ajaxObj.options.method;
			var url = ajaxObj.options.url;
			if ("GET" === method.toUpperCase() && formateParams.length > 0) {
				url += "?" + formateParams;
			}
			// 建立连接
			xmlhttp.open(method, url, true);
			xmlhttp.setRequestHeader("Device-Type","Client/Browser");
			xmlhttp.setRequestHeader("AccessToken",CookieUtil.getCookie("AccessToken"));
			if ("GET" === method.toUpperCase()) {
				xmlhttp.send(null);
			} else if ("POST" === method.toUpperCase()) {
				// 如果是POST提交，设置请求头信息
				xmlhttp.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				xmlhttp.send(formateParams);
			}
		}
	}
var UploadUtil = {
		ajaxPool : [],
		callbackPool : [],
		getInstance: function() {
	        for (var i = 0; i < this.ajaxPool.length; i++) {
	            if (this.ajaxPool[i].readyState == 0 || this.ajaxPool[i].readyState == 4) {
	            	this.callbackPool[i] = this.options.callback;
	                return this.ajaxPool[i];
	            }
	        }
	        // IE5中不支持push方法
	        this.ajaxPool[this.ajaxPool.length] = this.createRequest.call(this);
	        this.callbackPool[this.callbackPool.length] = this.options.callback;
	        return this.ajaxPool[this.ajaxPool.length - 1];
	    },
	    acceptImage : 'image/gif,image/jpg,image/jpeg,image/png',
	    acceptFile : '',
		// 基础选项
		options : {
			url : "",
			callbefore : function(){
			},
			callback : function() {
			},
			acceptType : '',
			type : "json",
			params : {}
		},
		// 设置基础选项
		setOptions : function(newOptions) {
			for ( var pro in newOptions) {
				this.options[pro] = newOptions[pro];
			}
		},
		choseFile : function(options){
			
            UploadUtil.setOptions(options);
			
			var inputObj=document.createElement('input')
	         inputObj.setAttribute('id','_ef');
	         inputObj.setAttribute('type','file');
	         inputObj.setAttribute("style",'display:none');
			 inputObj.setAttribute('accept',this.options.acceptType);
			 inputObj.setAttribute("onChange",'UploadUtil.upload()');
	         document.body.appendChild(inputObj);
	         inputObj.click();
		},
		// 状态改变的处理
		readystatechange : function(xmlhttp) {
			// 获取返回值
			var returnValue,callback;
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				switch (this.options.type) {
				case "json":
					var jsonText = xmlhttp.responseText;
					if (jsonText) {
						returnValue = eval("(" + jsonText + ")");
					}
					break;
				default:
					returnValue = xmlhttp.responseText;
					break;
				}
				for(var i = 0; i < this.ajaxPool.length; i++){
					if(this.ajaxPool[i] == xmlhttp){
						callback = this.callbackPool[i];
						break;
					}
				}
				$("#_ef").remove();
				if (returnValue && callback) {
					callback.call(this, returnValue);
				} else {
					callback.call(this);
				}
			}
		},
		createRequest : function() {
			var xmlhttp;
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");// IE6以上版本
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");// IE6以下版本
				} catch (e) {
					try {
						xmlhttp = new XMLHttpRequest();
						if (xmlhttp.overrideMimeType) {
							xmlhttp.overrideMimeType("text/xml");
						}
					} catch (e) {
						alert("您的浏览器不支持Ajax");
					}
				}
			}
			return xmlhttp;
		},
		upload : function(){
			var formData = new FormData();
			formData.append('file', $('#_ef')[0].files[0]);
			for ( var pro in newOptions) {
				formData.append(pro, this.options.params[pro]);
			}
			UploadUtil.request(formData);
		},
		request : function(formData) {
			var ajaxObj = this;
			var xmlhttp = UploadUtil.getInstance();
			
			xmlhttp.onreadystatechange = function() {
				ajaxObj.readystatechange.call(ajaxObj, xmlhttp);
			};
			
			this.options.callbefore.call(this);
			
			xmlhttp.open("POST", this.options.url, true);
			xmlhttp.setRequestHeader("Device-Type","Client/Browser");
			xmlhttp.setRequestHeader("AccessToken",CookieUtil.getCookie("AccessToken"));
			xmlhttp.send(formData);
		}
}
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

