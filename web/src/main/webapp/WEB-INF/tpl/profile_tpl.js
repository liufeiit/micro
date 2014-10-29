/**
 * profile template content
 */
$(function() {
	var $tpl = $('#amz-tpl'), source = $tpl.text(), template = Handlebars
			.compile(source), data = {
		header : {
			"content" : {
				"left" : [ {
					"link" : "javascript:history.go(-1);",
					"icon" : "chevron-left"
				} ],
				"title" : "微界"
			},
			"theme" : "default"
		},

		menu : {
			"options" : {
				"cols" : "1"
			},
			"content" : [ {
				"link" : "./list.html",
				"title" : "爆笑搞怪"
			}, {
				"link" : "./list.html",
				"title" : "哲理励志"
			}, {
				"link" : "./list.html",
				"title" : "健康生活"
			}, {
				"link" : "./list.html",
				"title" : "女性天地"
			}, {
				"link" : "./list.html",
				"title" : "健康生活"
			}, ],
			"theme" : "offcanvas1"
		},

		titlebar : {
			"content" : {
				"title" : "我的收益"
			},
			"theme" : "default"
		},

		navbar : {
			"options" : {
				"cols" : "4",
				"iconpos" : "top"
			},
			"content" : [ {
				"title" : "首页",
				"link" : "./home.html",
				"icon" : "home",
			}, {
				"title" : "商城",
				"link" : "../index.html",
				"icon" : "shopping-cart",
			}, {
				"title" : "一键分享",
				"link" : "###",
				"icon" : "share-alt",
				"dataApi" : "data-am-navbar-share"
			}, {
				"title" : "我的收益",
				"link" : "./profile.html",
				"icon" : "user"
			}, {
				"title" : "盟友学堂",
				"link" : "./study.html",
				"icon" : "group",
				"dataApi" : ""
			} ]
		}
	}, html = template(data);

	$tpl.before(html);

	seajs.use([ 'menu', 'navbar' ], function(m, n) {
		var args = Array.prototype.slice.apply(arguments);
		$.each(args, function(i, m) {
			m.init && m.init();
		})
	});

});