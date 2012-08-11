<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form page!</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	var $inptWeaponName = $('#inptWeaponName'),
		$inptWeaponStyle = $('#inptWeaponStyle'),
		$divHiddenWeapon = $('#hiddenWeapon');
	
	$('#btnNewWeapon').click(function(){
		var name = $inptWeaponName.val();
		var style = $inptWeaponStyle.val();
		
		console.debug('About to add: ' + name + ',' + style);
		
		addWeapon(name, style);
	});
	
	function addWeapon(name,style) {
		var $listWeapons = $('#listWeapons');
		var index = $divHiddenWeapon.find('.weapon').length;
		
		console.debug('Found ' + index + ' weapons.');
		
		//display
		var $liWeapon = $('<li>')
			.append('Name: ' + name + ', Style: ' + style)
			.appendTo($listWeapons);
		
		//add to hidden input
		$divHiddenWeapon.append('<input type="hidden" class="weapon" name="testObjects[' + index + '].weapon" value="' + name + '" />')
						.append('<input type="hidden" class="style" name="testObjects[' + index + '].style" value="' + style + '" />');
	}
});
</script>

</head>
<body>

<p>Here's the form!

<form:form method="POST" commandName="testForm" action="/spring-forms-test/form">
	<ol>
		<li>
			<form:label path="name">Name</form:label>
			<form:input path="name" />
		</li>
		<li>
			<form:label path="clinic">Clinic</form:label>
			<form:input path="clinic" />
		</li>
		<li>
			<ol id="listWeapons"></ol>
			<div id="hiddenWeapon" style="display:none"></div>
		</li>		
	</ol>
	
	<p><input type="submit" />
</form:form>

<ol>
	<li>
		<ol id="listWeapons">
			<li>
				<label for="inptWeaponName">Weapon name</label>
				<input type="text" id="inptWeaponName"></input>
			</li>
			<li>
				<label for="inptWeaponStyle">Style</label>
				<input type="text" id="inptWeaponStyle"></input>
			</li>
		</ol>
	</li>
</ol>
<button id="btnNewWeapon">Add Weapon</button>

</body>
</html>