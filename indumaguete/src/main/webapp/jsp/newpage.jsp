<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create or Edit a Page</title>

<link rel="stylesheet" href="createoredit.css" />
<link rel="stylesheet" href="indumaguete-theme/jquery.ui.all.css" />
<link rel="stylesheet" href="ui.selectmenu.css" />
<script type="text/javascript" src="jquery-1.7.min.js" ></script>
<script type="text/javascript" src="jquery-ui-1.8.14.custom.min.js" ></script>
<script type="text/javascript" src="ui.selectmenu.js" ></script>
<script type="text/javascript" src="jquery.validate.min.js" ></script>
<script type="text/javascript" src="indumaguete.js" ></script>

<link rel="stylesheet" type="text/css" href="jwysiwig/lib/blueprint/print.css" media="print" />
<!--[if lt IE 8]><link rel="stylesheet" href="jwysiwig/lib/blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<link rel="stylesheet" href="jwysiwig/jquery.wysiwyg.css" type="text/css"/>
<script type="text/javascript" src="jwysiwig/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="jwysiwig/controls/wysiwyg.image.js"></script>
<script type="text/javascript" src="jwysiwig/controls/wysiwyg.link.js"></script>
<script type="text/javascript" src="jwysiwig/controls/wysiwyg.table.js"></script>


<script>
	
var newpage = {
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
	
	ancestry_domains : [<c:forEach items="${ancestry}" var="ancestor" varStatus="status">'${ancestor}'${not status.last ? ', ' : ''}</c:forEach>]
}	

$(document).ready(function(){
	var $ancestry = $('#ancestry');	
	var homelink = '/' + newpage.item.domain;
	$ancestry.append($('<a>').attr('href', homelink).text(newpage.item.domain));	
	var numberOfAncestors = newpage.ancestry_domains.length;
	var thisLink;
	$.each(newpage.ancestry_domains, function(i, ancestor){
		var $ancestor_link = $('<a target="_blank">').text(ancestor);
	
		if(i == 0) {
			thisLink = '/' + newpage.item.domain + '/' + ancestor;
		} else {
			thisLink = thisLink + '/' + ancestor;
		}
		$ancestor_link.attr('href', thisLink);		
		$ancestry.append(' > ').append($ancestor_link);		
	});	
	

	var $title = $('#title');
	var $domain = $('#domain');	
	$title.change(function(){
		var val = $title.val();
		$domain.val(domainFromTitle(val));
	});
	function domainFromTitle(title) {
		return item.domain + '-' + title.replace(/^\s\s*/, '').replace(/\s\s*$/, '').replace(/[^A-Za-z0-9-\s]/g, "").replace(/\s+/g, '-').toLowerCase(); 
	}	
	$('#a_generate_domain').click(function(){$title.change()});

	
	$('#longdescription').wysiwyg({
		css: {
			fontFamily: 'segoe ui,Arial,sans-serif',
			fontSize: '1em'
		}
	});
	var textarea_wysiwyg = $("#longdescription-wysiwyg-iframe").contents().find('html');
	textarea_wysiwyg.keypress(function() {
		$button_save.removeAttr('disabled').attr('value', 'Save').removeClass('ui-state-hover ui-state-focus');
	});
	
	var $button_save = $('#button_save');
	$button_save.click(function() {
		$button_save.attr('value', 'Saved').attr('disabled', 'disabled');
	});
	var $summary = $dgte.constructSummary(newpage.item);
	$summary.addClass('ui-widget-content');	
	var $footer = $('<div class="footer">').appendTo($summary);
	var $button_edit_pInfo = $('<input type="button" id="button_edit_pInfo" value="Edit">').button().appendTo($footer);
	$button_edit_pInfo.click(function() {
		alert('go back to editing primary details!');
	});
	$('#summary').replaceWith($summary);
						
	$('input[type="button"]').button();
	$('.minibutton').button();
});
</script>

</head>
<body>
<div id="content" class="ui-widget">

	<h2 id="ancestry">You are creating a page for </h2>

	<form>
	
	<div class="ui-widget-content title">
		<p><span class="label">Title</span><input type="text" id="title" name="title" class="required"></input></input>
			<br/><span class="explanation"><a id="a_generate_domain" class="minibutton" href="javascript:;">Generate domain</a></span>
		<p><span class="label">Domain</span><input type="text" id="domain" name="domain" readOnly="true"></input></input></input>
	</div>
	
	<br/>
	
	<div id="container_longdescription" class="ui-widget-content">
		<textarea id="longdescription" rows="15" style="width: 90%"></textarea>
		<div class="footer">
				<input  type="button" id="button_save" value="Save" />
				<input  type="button" id="button_preview" value="Preview" />
				<input  type="button" id="button_done" value="Done!" />
		</div>
	</div>
	
	</form>
	<br/>

	<div id="summary"></div>
	<br/>	
</div>
</body>
</html>