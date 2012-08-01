<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title}</title>

<link rel="stylesheet" href="${view_css }" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>

<script>
window.viewarticle = window.viewarticle || {
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
	
	article : {
			domain: '${article.domain}',
			ancestry: '${article.ancestry}',
			title: '${article.title}'
	}
};

$(document).ready(function(){
	var $longdescription = $('#longdescription');
	
	var $summary = $dgte.constructSummary(viewarticle.item);
	$summary.addClass('ui-widget-content');	
	$('#summary').replaceWith($summary);
	
	$longdescription.load('/indumaguete/resources/html/descriptions/' + viewarticle.article.domain + '.html');
	
	$('input[type="button"]').button();
});
</script>

</head>
<body>
<div id="content" class="ui-widget">
	<h2><span class="highlight">${article.title }</span></h2>
	<br/>
	
	<div id="longdescContainer" class="ui-widget-content"> 
	<div id="longdescription"></div>
	</div>
	<br/>
	
	<div id="summary"></div>
	<br/>
</div>
</body>
</html>