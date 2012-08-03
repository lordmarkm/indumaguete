<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form page!</title>
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
			<form:label path="testObjects[0]">Weapon 1</form:label>
			<form:input path="testObjects[0].weapon" />
		</li>
		
		<li>
			<form:label path="testObjects[0]">Style 1</form:label>
			<form:input path="testObjects[0].style" />
		</li>
		
		<li>
			<form:label path="testObjects[2]">Weapon 2</form:label>
			<form:input path="testObjects[2].weapon" />
		</li>
		
		<li>
			<form:label path="testObjects[10]">Style 2</form:label>
			<form:input path="testObjects[10].style" />
		</li>
		
		<li>
			<input type="submit" />
		</li>
	</ol>
</form:form>

</body>
</html>