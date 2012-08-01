<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	width: 80%;
	margin-left: auto;
	margin-right: auto;
}

.searchtemplate-banner {
	background-color: blue;
}

.searchtemplate-content {
	height: 1200px;
}

.searchtemplate-floatleft {
	float: left;
	width: 200px;
	background-color: green;
	height:100%;
}

.searchtemplate-floatright {
	float: right;
	width: 300px;
	background-color: green;
	height:100%; 
}

.searchtemplate-body {
	margin-left: 200px;
	margin-right: 300px;
}

.searchtemplate-footer {
	background-color:red;
}
</style>
</head>
<body>
<tiles:insertAttribute name="header" />

<div class="screentemplate-banner">
	<tiles:insertAttribute name="banner" />
</div>

<div class="searchtemplate-content">
	<div class="searchtemplate-floatleft">
		<tiles:insertAttribute name="floatleft" />
	</div>
	
	<div class="searchtemplate-floatright">
		<tiles:insertAttribute name="floatright" />
	</div>
	
	<div class="searchtemplate-body">
		<tiles:insertAttribute name="body" />
	</div>
</div>

<div class="searchtemplate-footer">
	<tiles:insertAttribute name="footer" />
</div>

</body>

</html>