<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Extra details!</title>

<link rel="stylesheet" href="${modify_css }" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<link rel="stylesheet" href="${selectmenu_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${selectmenu_js }" ></script>
<script type="text/javascript" src="${jquery_validation_url}" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>

<script type="text/javascript" src="/indumaguete/resources/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
window.modpage = window.modpage || {
	item : {
			domain: '${item.domain}',
			imageUrl: '${item.imageUrl}',
			name: '${item.name}',
			shortDescription: '${item.shortDescription}',
			address: '${item.address}',
			subcategory: '${item.subcategory}',
			email: '${item.email}',
			cellphone: '${item.cellphone}',
			landline: '${item.landline}'
	},
	
	save : function() {
		$.ajax({
			type: 'post',
			async: false,
			url: '/indumaguete/register/saveHomepage',
			data: {textboxContents: tinyMCE.get('longdescription').getContent()},
			success: function(response){
				if(response != 'success') {
					$dgte.alert('Server response', response);
					return false;
				} else {
					$('#button_save').attr('value', 'Saved').attr('disabled', 'disabled');
					modpage.dirty = false;
				}
			}
		});
		
		//$.post('/indumaguete/register/saveHomepage', {textboxContents: tinyMCE.get('longdescription').getContent()}, function(response){
		//	if(response != 'success') {
		//		$dgte.alert('Server response', response);
		//		return false;
		//	} else {
		//		$('#button_save').attr('value', 'Saved').attr('disabled', 'disabled');
		//		modpage.dirty = false;
		//	}
		//});		
	},
	
	saveOk : function() {
		$('#button_save').removeAttr('disabled').attr('value', 'Save').removeClass('ui-state-hover ui-state-focus');
		modpage.dirty = true;
	},
	
	alertUnsaved : function() {
		if(modpage.dirty) {
			if(confirm('Save unsaved data first?')) {
				modpage.save();
			};
		}
	}
};


	tinyMCE.init({
		// General options
		mode : "exact",
		elements: "longdescription",
		width: "980",
		theme : "advanced",
		skin : "o2k7",
		plugins : "autolink,lists,pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
		setup: function(ed) {
			ed.onKeyPress.add(modpage.saveOk)
		},

		// Theme options
		theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
		theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		// Example word content CSS (should be your site CSS) this one removes paragraph margins
		content_css : "css/word.css",

		// Drop lists for link/image/media/template dialogs
		template_external_list_url : "lists/template_list.js",
		external_link_list_url : "lists/link_list.js",
		external_image_list_url : "lists/image_list.js",
		media_external_list_url : "lists/media_list.js",

		// Replace values for the template plugin
		template_replace_values : {
			username : "Some User",
			staffid : "991234"
		}
	});

$(document).ready(function(){
	var $header = $('#header'),
		$longdescription = $('#longdescription'),
		$button_save = $('#button_save');
	
	switch('${source}') {
	case 'createHomepage':
		$header.html('Step 2: Create Your Homepage <span class="subtitle">(Almost there!)</span>');
		break;
	default:
		$header.html('Editing homepage of ' + modpage.item.name);
		break;
	}
	
	var $summary = $dgte.constructSummary(modpage.item);
	$summary.addClass('ui-widget-content');	
	
	var $footer = $('<div class="footer">').appendTo($summary);
	$('<a class="button" href="/indumaguete/manage/' + modpage.item.domain + '/edit/primary/">Edit</a>').button().appendTo($footer);
	
	$('#summary').replaceWith($summary);
	
	$button_save.click(function() {
		modpage.save();
	});
	
	$('#button_complete_registration').click(function(){
		//window.location.href = '/indumaguete/' + modpage.item.domain;
		window.location.href = '/indumaguete/register/regComplete/';
	});
	
	$('input[type="button"]').button();
});

window.onbeforeunload = function(){};
window.onbeforeunload = window.modpage.alertUnsaved;
window.onunload = function(){};
</script>

</head>
<body>
<div id="content" class="ui-widget">
	<h2 id="header"></h2>

	<div id="summary"></div>

	<br/>
	
	<div id="container_longdescription" class="ui-widget-content">
	<textarea id="longdescription" rows="15" style="width: 90%"></textarea>
	<div class="footer"><input type="button" id="button_save" value="Save" /></div>
	</div>
	
	<br/>
	<div id="container_complete_registration" class="ui-widget-content">
		<div class="footer"><input  type="button" id="button_complete_registration" value="Click here to complete Registration!" /></div>
	</div>
</div>
</body>
</html>