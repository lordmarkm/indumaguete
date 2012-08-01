<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advertise!</title>

<link rel="stylesheet" href="${advertise_css }" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<link rel="stylesheet" href="${selectmenu_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${selectmenu_js }" ></script>
<script type="text/javascript" src="${jquery_validation_url}" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>
<script type="text/javascript" src="${json_url }"></script>
<script type="text/javascript" src="/indumaguete/resources/categoryselect.js" ></script>
<link rel="stylesheet" href="/indumaguete/resources/categoryselect.css" />

<script>
var register = {
		categories : {
			<c:forEach items="${categories}" var="category" varStatus="cstatus">
				'${category.key}' : [<c:forEach items="${category.value}" var="subcategory" varStatus="sstatus">'${subcategory}'<c:if test="${not sstatus.last}">,</c:if></c:forEach>]<c:if test="${not cstatus.last}">,</c:if>
			</c:forEach>
		}
};

$(document).ready(function(){
	$('#subcategory').selectmenu();
	$('input[type="button"]').button();
	$('input[type="submit"]').button();
	
	var $form_basicinfo = $('#form_basicinfo');
	$form_basicinfo.validate({
		rules: {
			password: {
				required: true,
				minlength: 5
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			cellphone: {
				number: true
			},
			landline: {
				number: true
			}
		},
		
		messages: {
			confirm_password: {
				equalTo: "Passwords do not match."
			}
		}
	});
	$form_basicinfo.submit(function(){
		var subcategory = $('#select_subcategory').text();
		alert('setting ' + subcategory);
		$('#subcategory').val(subcategory);
		
		var item = $(this).serializeObject();
		$.postJSON("/indumaguete/register/submitPrimary", item, function(response) {
			if(response.message == 'success') {
				window.location.href = '/indumaguete/register/createHomepage/' + response.domain + '/';
			} else {
				$dgte.alert('Server Response', response.message);
			}
		});
		return false;
	});
	
	$('p:odd', $form_basicinfo).css('background-color', 'whitesmoke');
	
	var $imageUrl = $('#imageUrl');
	var $div_imagePreview = $('#imagePreview');
	$imageUrl.change(function() {
		$div_imagePreview.html('');
		if($imageUrl.val().length == 0) {
			return;
		} else {
			console.debug('Creating ' + $imageUrl.val());
			$('<img class="profile">').attr('src', $imageUrl.val()).appendTo($div_imagePreview);			
		}
	});
	$('#a_imagePreview').click($imageUrl.change());
	
	var $button_preview = $('#button_preview');
	$button_preview.click(function(){
		$form_basicinfo.validate();
		if(!$form_basicinfo.valid()) {
			return false;
		}
		
		var item = {
			domain: $('#domain').val(),
			imageUrl: $('#imageUrl').val(),
			name: $('#name').val(),
			shortDescription: $('#shortDescription').val(),
			subcategory: $('#subcategory').val(),
			address: $('#address').val(),
			email: $('#email').val(),
			cellphone: $('#cellphone').val(),
			landline: $('#landline').val()
		};
		
		var $preview = $dgte.constructSummary(item);
		$preview.dialog({
			modal: true,
			minWidth: 800
		});
	});
	
	$('a#select_subcategory').button({ icons: { secondary: "ui-icon-circle-triangle-s"}}).click(function(event){
		catsel.createSelect(register.categories, $(this), 'registration').show();
		event.stopPropagation();
	});
	
	function fill(){
		$('#domain').val('Baldwinternet');
		$('#password').val('12345a');
		$('#confirm_password').val('12345a');
		$('#imageUrl').val('https://encrypted-tbn2.google.com/images?q=tbn:ANd9GcSBP8OaV1HzSj1ByJTRts_I-vtD2yOL9Z9p-Oglhlssh0c3C2o8aw');
		$('#name').val('Baldwinternet And Gaming');
		$('#shortDescription').val('Yo mama');
		$('#address').val('Up yours, \nCunt');
		$('#email').val('lordmarkm@gmail.com');
		$('#cellphone').val('123123123');
		$('#landline').val('1123123');
	}
	fill();
});

</script>

</head>
<body>
<div class="ui-widget">
<h2>Step 1: Basic Information</h2>
<form id="form_basicinfo" method="post" action="/indumaguete/register/submitPrimary">		
		<fieldset>
		
		<p><span class="label">Desired domain</span><input id="domain" class="required" type="text" name="domain"></input>			
		<br/><span class="explanation">This will determine your domain on InDumaguete. For example, a domain of 'JamesHandicrafts' will be accessible by the URL <a target='_blank' href='http://www.indumaguete.com/jameshandicrafts'>http://www.indumaguete.com/jameshandicrafts</a></span>
		
		<p><span class="label">Password</span><input id="password" type="password" name="password"></input>
		<p><span class="label">Confirm password</span><input id="confirm_password" type="password" name="confirm_password"></input>
		<br/><span class="explanation">This password will be required if you wish to edit this entry in the future.</span>
					
 	    <p><span class="label">Business name</span><input id="name" class="required" type="text" name="name"></input>
	    <div id="imagePreview"></div>
	    
	    <p><span class="label">Image URL</span><input type="text" id="imageUrl" name="imageUrl" /><a href="javascript:;" id="a_imagePreview"><span class="subtitle">Preview</span></a>
	    <span class="explanation">Please enter an image URL or upload your profile image on a 3rd party site such as <a target="_blank" href="http://www.tinypic.com">tinypic.com</a> or
	  	<a href="http://www.imageshack.com">imageshack.com</a>and have it resized to a maximum of 320x320 pixels.</span>
	    
	    <p><span class="label">Short Description</span><input id="shortDescription" type="text" name="shortDescription"></input>
 		
 		<p><span class="label">Address</span><textarea id="address" rows=3 name="address"></textarea>
		
		<p><span class="label">Nature of Business</span><a id="select_subcategory" href="javascript:;">All</a></p>
		<input id="subcategory" type="hidden" name="subcategory" />
		
		<span class="explanation">Can't find your business? <a href='contact/' target='_blank'>Let us know and we'll add it</a>.</span>
			
		<p><span class="label">Email</span><input id="email" class="{required:true, email:true, messages:{required:'Please enter your email address', email:'Please enter a valid email address'}}" type="email" name="email">
		<br/><span class="container_checkbox"><input type="checkbox" name="emailVisible" id="display_email" value="true" checked="checked"/><label class="subtitle" for="display_email">Visible to users</label></span></p>					
		
		<p><span class="label">Cellphone number</span><input id="cellphone" type="number" name="cellphone">
		<br/><span class="container_checkbox"><input type="checkbox" id="display_cellphone" name="cellphoneVisible" value="true" checked="checked"/><label class="subtitle" for="display_cellphone">Visible to users</label></span></p>
		
		<p><span class="label">Landline number</span><input id="landline" type="number" name="landline">
		<br/><span class="container_checkbox"><input type="checkbox" id="display_landline" name="landlineVisible" value="true" checked="checked"/><label class="subtitle" for="display_landline">Visible to users</label></span></p>
		
		<div class="footer">
			<p><span class="label"><input id="button_preview" type="button" value="Preview" /><input type="submit" value="Next!"/></span>
		</div>
		</fieldset>
</form>
</div>
</body>
</html>