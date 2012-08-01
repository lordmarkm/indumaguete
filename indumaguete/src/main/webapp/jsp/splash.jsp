<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>In Dumaguete</title>

<link rel="stylesheet" href="/indumaguete/resources/css/splash.css" />
<link rel="stylesheet" href="/indumaguete/resources/indumaguete-theme/jquery.ui.all.css" />
<script type="text/javascript" src="/indumaguete/resources/jquery/jquery-1.7.min.js" ></script>
<script type="text/javascript" src="/indumaguete/resources/jquery/jquery-ui-1.8.14.custom.min.js" ></script>
<script type="text/javascript" src="/indumaguete/resources/jquery/jquery.corner.js" ></script>

<script>
$(document).ready(function(){

});
</script>

<style>
.dgte-splash-head {
	background-color: #3B5998;
	height: 82px;
	width: 100%l
}

.dgte-splash-body {
	height: 536.567px;
	min-width: 980px;
	background: white; /* for non-css3 browsers */
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='white', endColorstr='#D3D8E8'); /* for IE */
	background: -webkit-gradient(linear, left top, left bottom, from(white), to(#D3D8E8)); /* for webkit browsers */
	background: -moz-linear-gradient(center top, white, #D3D8E8) repeat scroll 0 0 transparent; /* for firefox 3.6+ */ 
}

.dgte-splash-content {
	width: 980px;
	margin: auto;
	background: url('/indumaguete/resources/images/splash.png');
	background-size: 100%;  
}

.dgte-splash-content-child {
	width: 50%;
}

</style>

</head>
<body>
<div class="dgte-splash-head"></div>
<div class="dgte-splash-body">
	<div class="dgte-splash-content">
	<div class="dgte-splash-content-child float-left">
		Indumaguete.com <br>
		The first place you go when you're in Dumaguete.
		<img src="/indumaguete/resources/images/splash.png" />  
	</div>
	<div class="dgte-splash-content-child float-right">
		<h1>What do you want to do?</h1>
		<a href="/indumaguete/register/advertise/">Advertise<span class="subtitle">(It's free)</span></a>
		<a href="/indumaguete/search/">Search</a>
	</div>
	</div>
</div>
</body>
</html>