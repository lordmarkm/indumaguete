<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error: ${message }</title>

<link rel="stylesheet" href="${main_css }" />

</head>
<body>
<div class="ui-widget">
<p>There was an error processing your request: <span class="error">${message }</span>
</div>
</body>
</html>