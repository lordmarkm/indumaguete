<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${item.name}</title>

<link rel="stylesheet" href="${view_css }" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>

<script>
window.viewall = window.viewall || {
	firstResult : 0,
	maxResult: 10,
	createDiv: function(item) {
		var $div = $('<div class="dgte-viewall-summary">');
		$('<div class="dgte-viewall-summary-name">').appendTo($div).text(item.name);
		$('<div class="dgte-viewall-summary-shortdescription">').appendTo($div).text(item.shortDescription);
		$('<div class="dgte-viewall-summary-subcategory">').appendTo($div).text(item.subcategory);
		$div.click(function(){
			window.location.href = '/indumaguete/' + item.domain + '/';
		});
		return $div;
	},
	items: [
	        <c:forEach items="${items}" var="item">
			{
				domain: '${item.domain}',
				name: '${item.name}',
				address: '${item.address}',
				subcategory: '${item.subcategory}',
				shortDescription: '${item.shortDescription}', 
			},
			</c:forEach>
			]
};

$(document).ready(function(){
	var $div_items = $('#div_items');
	for(var i = 0; i < viewall.items.length; i++) {
		viewall.createDiv(viewall.items[i]).appendTo($div_items);
	}
});
</script>

<style>
.dgte-viewall-summary {
	cursor: pointer;
	background-color: red;
}

.dgte-viewall-summary-name {
	font-weight: bold;
}

.dgte-viewall-summary-shortdescription {
	font-style: italic;
	font-size: 0.8em;
}

.dgte-viewall-summary-subcategory {
	font-size: 0.6em;
}
</style>

</head>
<body>
<div id="content" class="ui-widget">
	<div id="div_items">
	
	<c:forEach items="items" var="item">
	</c:forEach>
		
	</div>
</div>
</body>
</html>