<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Results page!</title>

<style>
.list-main {
	list-style-type: katakana;
}
.list-child {
	list-style-type: katakana-iroha;
}
</style>

</head>
<body>

<p>Here's the results!

<ol class="list-main">
	<li>Name: ${testform.name }</li>
	<li>Clinic: ${testform.clinic }</li>
	<c:forEach var="testobject" items="${testform.testObjects }" varStatus="status">
		<ol class="list-child">
			<li>Index: ${status.index }</li>
			<li>Weapon: ${testobject.weapon }</li>
			<li>Style: ${testobject.style }</li>
		</ol>
	</c:forEach>
</ol>

</body>
</html>