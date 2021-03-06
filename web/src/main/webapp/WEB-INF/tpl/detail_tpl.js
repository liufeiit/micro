/**
 * detail template content
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
				"link" : "list.htm",
				"title" : "爆笑搞怪"
			}, {
				"link" : "list.htm",
				"title" : "哲理励志"
			}, {
				"link" : "list.htm",
				"title" : "健康生活"
			}, {
				"link" : "list.htm",
				"title" : "女性天地"
			}, {
				"link" : "list.htm",
				"title" : "健康生活"
			}, ],
			"theme" : "offcanvas1"
		},

		titlebar : {
			"content" : {
				"title" : "爆笑搞怪"
			},
			"theme" : "default"
		},

		paragraph_title : {
			"content" : {
				"content" : "<h2 class=\"am-slider-title\">秘这些高难度的动作1,000,000人中也许有一个人可以做到！</h2>"
			},
			"theme" : "default"
		},

		paragraph_content : {
			"options" : {
				"imgLightbox" : true,
				"tableScrollable" : true
			},
			"content" : {
				"content" : "<p>1.妹子很清秀，但看了图又觉得很可怕</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828434992_2.gif\"><p></p><p>2.传说的懒驴打滚，地躺刀绝技</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828440979_2.gif\"><p>3.用这个姿势过杆，看了感觉自己腰也快断了</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828454040_2.gif\"><p>4.这手...可以反着抓篮球了</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828466604_2.gif\"><p></p><p></p><p>5.这肚皮看起来好邪恶</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828473968_2.gif\" width=\"318\" height=\"156\"><p></p><p>6.会一字马很牛？人家两腿角度可以到270度</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828486988_2.gif\"><p>7.断了断了真的断了</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828496543_2.gif\"><p>8.妈妈再也不担心我被人家扭断脖子了</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828504240_2.gif\"><p>9.看晕了</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828512036_2.gif\" width=\"318\" height=\"205\"><p></p><p></p><p>10.这动作难度完全秒了什么《咒怨》、《驱魔人》...</p><p></p><img id=\"mimg\" onload=\"tts(this);\" src=\"http://img01.66666999.com/img/Content/141022/1828523377_2.gif\"><p></p><p></p><p></p>"
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
				"link" : "home.htm",
				"icon" : "home",
			}, {
				"title" : "商城",
				"link" : "home.htm",
				"icon" : "shopping-cart",
			}, {
				"title" : "一键分享",
				"link" : "home.htm",
				"icon" : "share-alt",
				"dataApi" : "data-am-navbar-share"
			}, {
				"title" : "我的收益",
				"link" : "profile.htm",
				"icon" : "user"
			}, {
				"title" : "盟友学堂",
				"link" : "study.htm",
				"icon" : "group",
				"dataApi" : ""
			} ]
		}
	}, html = template(data);

	$tpl.before(html);

	seajs.use([ 'menu', 'titlebar', 'paragraph', 'navbar' ], function(m, t, p,
			n) {
		var args = Array.prototype.slice.apply(arguments);
		$.each(args, function(i, m) {
			m.init && m.init();
		})
	});
});