/**
 * study template content
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
				"title" : "盟友学堂"
			},
			"theme" : "default"
		},

		accordion : {
			"options" : {
				"multiple" : true
			},
			"content" : [
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;微界是怎样让您赚钱的？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;您或许不知道：您的微信朋友圈、Q空间、博客、微博都是有价值的，都是可以换得收益的，只是无人帮您开发，所以您无法从中获得收益。</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;微界的出现，恰好解决了这个问题！微界的网页里植入了广告，通过展示网页可以获得广告收益。您只需要将我们的网文分享到您的朋友圈或微博里，就可以和我们分成广告收益了。</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;也就是说：我们想付费在您的朋友圈或微博里展示网文，让您的朋友圈或微博获到收益，您同意吗？</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;这一切只须两步：1、点上方“注册”成为盟友；2、点下方“精彩网文”，把获取的文章分享到朋友圈或者微博即可。</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;接下来点下方“盟友中心—我的业绩”随时关注自己的收入吧！动动手指就能做到，何乐而不为呢？</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;微界的收益有保障吗？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;微界所有广告均由百度公司提供，收入稳定，可靠性高，请各位盟友放心参与。</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;如何加入微界？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;两个方法：</p><p>&nbsp;&nbsp;&nbsp;&nbsp;1、点上方“注册”成为盟友，注册后别忘记收藏网址哦。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;2、关注微界的公众号(WLM66999)，关注后点“申请提现”，按提示完善资料。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;上面的任何一个方法都可以开启您的赚钱之旅。点下方的“精彩网文”获取到文章后分享到朋友圈或微博。然后……然后您就等着数钱吧！！！</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;5秒学会在微界赚钱！",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;一句话：点右上方的菜单按钮&nbsp;&nbsp;<i class=\"am-icon-navicon\"></i>，点击打开获得的文章，然后分享到朋友圈或微博就可以了。</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;微界是什么时间结算佣金？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;1、业绩以IP的形式计算，1IP相当于1个人查看了您分享的链接。</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;2、当月IP将于次月1日转换为现金，并在15-25日期间汇到您的支付宝账号，即时到账！</p><p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;3、更多问题请加QQ群咨询交流，Q群号：362123588</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;100个好友如何才能月入万？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;很多盟友说：我只有100个好友，反正赚不到多少钱，我就不参与了。 其实不是这样的！推广在于日积月累，在于您是否用心。即使只有100个好友，每天业绩100万IP也不是没有可能。所以，您要认真看一下下述的方法：</p><p>&nbsp;&nbsp;&nbsp;&nbsp;1、用心选择分享的内容。每个人都有不同的圈子，每个人的圈子都有不同的爱好内容，所以您一定要认真从微界网上查到适合您的圈子的内容分享。只有您分享的内容是您的朋友喜欢的，他们才有可能转发。转发？对，转是个特值得高兴的事，因为倍增的速度太快了，从100到1万，再到100万，也许就是几分钟的事，所以，您分享一个链接前，一定要想一下这个链接能不能让朋友们转发！</p><p>&nbsp;&nbsp;&nbsp;&nbsp;2、留心朋友圈里有价值的内容。您的朋友圈里一定有很多别人分享的精彩内容。您可以把这内容重新处理后分享即可。处理方法：在原文点“复制链接”，然后发送到微界微信里去，系统会给您回复一个链接，这个链接的内容和您发过去的一样，享这个链接您就可以获得业绩了。（详见盟友必读）</p><p>&nbsp;&nbsp;&nbsp;&nbsp;3、天分享适当的条数。分享的条数不要太多，也不可太少。一般不低于5条，不超过12条。按照这样的方法尝试一下吧，您将会发现：刚开始您每天的业绩只有您好友的三分之一，一周后业绩增长到和好友数目一样多，再过一周即可达到好友数量的两倍，再过一段时间，您会突然发现您的业绩已经成了好友数量的N倍。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;原因是什么呢？就是因为您坚持每天在做，总有一此链接被人一次又一次的转载，而不管转载多少次，我们的系统都会计入您自己的业绩。这是个几何倍增的效果，每天做到100万IP绝对不是梦，这需要您用心去做！</p>"
					},
					{
						"title" : "<i class=\"am-icon-question-circle\"></i>&nbsp;&nbsp;如何推荐朋友加入微界？",
						"content" : "<p><i class=\"am-icon-dot-circle-o\"></i>&nbsp;&nbsp;将下方网址发给你的朋友注册成为盟友即可。</p><p>&nbsp;&nbsp;http://joke.66666999.com/mob/usercenter.asp?tid=18591506955</p>"
					}, ],
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

	seajs.use([ 'header', 'menu', 'titlebar', 'accordion', 'navbar' ],
			function(h, m, t, a, n) {
				var args = Array.prototype.slice.apply(arguments);
				$.each(args, function(i, m) {
					m.init && m.init();
				})
			});

});