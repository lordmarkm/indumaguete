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
var item = {
		domain: '${item.domain}',
		imageUrl: '${item.imageUrl}',
		name: '${item.name}',
		shortDescription: '${item.shortDescription}',
		address: '${item.address}',
		subcategory: '${item.subcategory}',
		email: '<c:if test="${item.emailVisible}">${item.email}</c:if>',
		cellphone: '<c:if test="${item.cellphoneVisible}">${item.cellphone}</c:if>',
		landline: '<c:if test="${item.landlineVisible}">${item.landline}</c:if>'
};

$(document).ready(function(){
	var $summary = $dgte.constructSummary(item);
	$summary.addClass('ui-widget-content');	
	
	$('#summary').replaceWith($summary);
	
	var $longdescription = $('#longdescription');
	$longdescription.load('/indumaguete/resources/html/descriptions/' + item.domain + '.html');
	
	$('input[type="button"]').button();
});
</script>

</head>
<body>
<div id="content" class="ui-widget">
	<h2>Viewing: <span class="highlight">${item.name }</span></h2>
	<div id="summary"></div>
	<br/>
	
	<div id="longdescContainer" class="ui-widget-content"> 
	<div id="longdescription"></div>
	</div>
	<br/>
</div>
</body>
</html>