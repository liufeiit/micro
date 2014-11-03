$(document).ready(function(){
    if ($("#btnMenu")) {
        $("#btnMenu").click(function() {
            if ($("#menu").css("display")=="none") {
                $("#menu").show();
            } else {
                $("#menu").hide();
            }
        })
    }
});

function pageBack() {
	var a = window.location.href;
	if(/#top/.test(a)) {
        window.history.go(-2);
        window.location.load(a);
    } else {
        window.history.back();
        window.location.load(a);
    }
}

function addLocalStorage(d) {
	if (null != d) {
		var a = window.localStorage.getItem("viewItem");
		var c;
		if (a != null) {
			var e = new Array();
			e = a.split(",");
			for (var b = 0; b < e.length; b++) {
				if (d == e[b]) {
					c = true
				}
			}
			if (!c) {
				e.push(d)
			}
			if (e.length > 20) {
				e.shift();
				window.localStorage.setItem("viewItem", e)
			} else {
				window.localStorage.setItem("viewItem", e)
			}
		} else {
			window.localStorage.setItem("viewItem", d)
		}
	}
}
