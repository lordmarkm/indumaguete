<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${css_dir}/pagebar.css" />
<link rel="stylesheet" href="${jquery_ui_css}" />
<script type="text/javascript" src="${jqyery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>

<script>
var pagebar = {
	item : {
			domain: '${item.domain}',
			imageUrl: '${item.imageUrl}',
			name: '${item.name}',
			shortDescription: '${item.shortDescription}',
			address: '${item.address}',
			subcategory: '${item.subcategory}',
			email: '<c:if test="${item.emailVisible}">${item.email}</c:if>',
			cellphone: '<c:if test="${item.cellphoneVisible}">${item.cellphone}</c:if>',
			landline: '<c:if test="${item.landlineVisible}">${item.landline}</c:if>'
	},
	
	getColor: function(level) {
		switch(level) {		
			case 0: return 'a0a0ff';
			case 1: return 'b0b0ff';
			case 2: return 'c0c0ff';
			case 3: return 'd0d0ff';
			case 4: return 'e0e0ff';
			case 5: return 'ffffff';
			case 6: return 'ffe0e0';
			case 7: return 'ffd0d0';
			case 8: return 'ffc0c0';
			case 9: return 'ffb0b0';
			case 10: return 'ffa0a0';
			default: return 'ff9090';
		}
	},

	makebar : function(page, $parent, level) {
		var $page = $('<div class="pagelink">').css('background-color', this.getColor(level));
		var $href = $('<a>').text(page.title).attr('href', page.ancestry + '/' + page.domain);
		
		$page.append($href);
		$parent.append($page);
		if(page.children) {
			for(var child in page.children) {
				this.makebar(page.children[child], $page, level + 1);
			};
		};
	}
};

$(document).ready(function(){
	$.get('/indumaguete/getArticles/' + pagebar.item.domain + '/', function(response) {
		pagebar.makebar(response, $('#pagebar_content'), 0);
	});
});
</script>

</head>
<body>
<div id="pagebar_content" class="ui-widget">

</div>
</body>
</html>