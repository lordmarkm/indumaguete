<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Page</title>

<link rel="stylesheet" href="/indumaguete/resources/css/editpage.css" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<link rel="stylesheet" href="${main_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>
<script type="text/javascript" src="/indumaguete/resources/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script>
	
var editpage = {
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
			title: '${article.title}',
			ancestry: '${article.ancestry}'
	},
	
	dirty : false,
	
	domainFromTitle : function (title) {
		if(!title || $.trim(title).length == 0) return '';
		return editpage.item.domain + '-' + title.replace(/^\s\s*/, '').replace(/\s\s*$/, '').replace(/[^A-Za-z0-9-\s]/g, "").replace(/\s+/g, '-').toLowerCase(); 
	},
	
	save : function() {
		var domain = $('#domain').val(),
			title = $('#title').val()
			$button_save = $('#button_save');
		
		$.ajax({
			type: 'post',
			async: false,
			url: '/indumaguete/manage/' + editpage.item.domain +'/editpage/' + editpage.article.domain + '/save/',
			data: {
					newDomain : domain,
					newTitle : title,
					content : tinyMCE.get('longdescription').getContent()
				},
			success : function(response){
					if(response.status == 'success') {
						$button_save.attr('value', 'Saved').attr('disabled', 'disabled');
						if(editpage.article.domain !== domain) {
							editpage.article.domain = domain;
						}
						editpage.dirty = false;
					} else {
						$dgte.alert('Error saving page', response.status);
					}
				},
			error : function(jqXHR, textStatus, errorThrown) {
					$dgte.alert('Error saving page', textStatus + ': ' + jqXHR.status + ' - ' + errorThrown);
				}
		});
	},
	
	saveOk : function() {
		$('#button_save').removeAttr('disabled').attr('value', 'Save').removeClass('ui-state-hover ui-state-focus');
		editpage.dirty = true;
	},

	done : function() {
		window.location.href = '/indumaguete/' + editpage.item.domain + '/page/' + editpage.article.domain + '/';
	},
	
	alertUnsaved : function() {
		if(editpage.dirty) {
			if($dgte.confirm('Save unsaved data first?')) {
				editpage.save();
			};
		}
	},
	
	ancestry_domains : [<c:forEach items="${ancestryArray}" var="ancestor" varStatus="status">'${ancestor}'${not status.last ? ', ' : ''}</c:forEach>],
}	
window.onbeforeunload = window.editpage.alertUnsaved;

tinyMCE.init({
	// General options
	mode : "exact",
	elements: "longdescription",
	width: "980",
	theme : "advanced",
	skin : "o2k7",
	plugins : "autolink,lists,pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
	setup: function(ed) {
		ed.onKeyPress.add(editpage.saveOk);
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
	var $ancestry = $('#ancestry'),
		$button_save = $('#button_save'),
		$button_done = $('#button_done'),
		$title = $('#title'),
		$domain = $('#domain'),
		$a_generate_domain = $('#a_generate_domain'),
		$longdescription = $('#longdescription');	
	
	$title.change(function(){
		var val = $title.val();
		$domain.val(editpage.domainFromTitle(val));
	});
	$a_generate_domain.click(function(){$title.change();});
	
	$title.val(editpage.article.title).change();
	 
 	$longdescription.load('/indumaguete/resources/html/descriptions/' + editpage.article.domain + '.html');
	
	var homelink = '/' + editpage.item.domain;
	//$ancestry.append($('<a>').attr('href', homelink).text(editpage.item.domain));	   
	var numberOfAncestors = editpage.ancestry_domains.length;
	var thisLink;
	$.each(editpage.ancestry_domains, function(i, ancestor){
		var $ancestor_link = $('<a target="_blank">').text(ancestor);
	
		if(i == 0) {
			thisLink = '/indumaguete/' + ancestor;
		} else {
			thisLink = thisLink + '/' + ancestor;
		}
		thisLink += '/';
		$ancestor_link.attr('href', thisLink);		
		$ancestry.append(' > ').append($ancestor_link);		
	});	
	
	$button_save.click(function() {
		editpage.save();
	});
	$button_done.click(editpage.done);
	
	var $summary = $dgte.constructSummary(editpage.item);
	$summary.addClass('ui-widget-content');	
	$('#summary').replaceWith($summary);
						
	$('input[type="button"]').button();
	$('.minibutton').button();
	editpage.saveOk();
});
</script>

</head>
<body>
<div id="content" class="ui-widget">

	<h2 id="ancestry">Editing ${article.title }</h2>

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