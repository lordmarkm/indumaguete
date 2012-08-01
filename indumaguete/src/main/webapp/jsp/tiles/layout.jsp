<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#tilesbody {
	min-height: 1200px;
	width: 80%;
	margin-left: auto;
	margin-right: auto;
}

.default-header {
	background-color: blue;
}

.default-footer {
	background-color: red;
}
</style>
</head>
<body>
<div class="default-header">
<tiles:insertAttribute name="header" />
</div>

<div class="default-body">
<div id="tilesbody"><tiles:insertAttribute name="body" /></div>
</div>
<br/>

<div class="default-footer">
<div id="tilesfooter"><tiles:insertAttribute name="footer" /></div>
</div>

</body>
</html>