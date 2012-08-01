<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#main {
	width: 100%;
	margin-left: 10%;
}

#tilesbody {
	display: inline-block;
	width: 70%;
}

#pagebar {
	display: inline-block;
	width: 20%;
	height: 100%;
	vertical-align: top;    
}

.pagebar-header {
	background-color: blue;
}

.pagebar-footer {
	background-color:red;
}
</style>
</head>
<body>

<div class="pagebar-header">
<tiles:insertAttribute name="header" />
</div>

<div class="pagebar-main" id="main">
	<div id="tilesbody"><tiles:insertAttribute name="body" /></div>
	<div id="pagebar"><tiles:insertAttribute name="pagebar" /></div>
</div>

<br/>

<div class="pagebar-footer" id="tilesfooter"><tiles:insertAttribute name="footer" /></div>

</body>
</html>