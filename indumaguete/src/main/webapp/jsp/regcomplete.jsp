<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Complete!</title>

<link rel="stylesheet" href="/indumaguete/resources/css/regcomplete.css" />
<link rel="stylesheet" href="${jquery_ui_css }" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>
<script>
window.regc = window.regc || {
	item : {
			domain: '${item.domain}',
			imageUrl: '${item.imageUrl}',
			name: '${item.name}',
			shortDescription: '${item.shortDescription}',
			address: '${item.address}',
			subcategory: '${item.subcategory}',
			email: '${item.email}',
			cellphone: '${item.cellphone}',
			landline: '${item.landline}'
	}
}
	
$(document).ready(function(){
	$('span.dgte-prop').each(function(i, span) {
		var prop = $(span).attr('prop');
		var val = regc.item[prop];
		$(span).text(val);
	});
	
	$('input[type="button"]').button();
	$('a').button();
});
</script>

</head>
<body>
<div class="ui-widget">

<h3>Congratulations! You have successfully registered <span class="dgte-prop" prop="name"></span></h3>

<p>Whether you are an individual offering services or the representative of an organization or company, there are several steps you can take to promote yourself or your organization further:
	<ol style="list-style-type: lower-alpha;">
		<li>Create 'child' pages and go into detail about your operations.
		<li>Share your homepage on Facebook.
		<li>Introduce yourself or your organization in the Forums.
		<li>Get featured on the InDumaguete front page!
		<li>Contribute to the News Feed
	</ol>

<div class="ui-widget-content suggestion">	
<h3>1. Create child pages</h3>
	<p>On your <a href="javascript:;" target="_blank" class="view minibutton">View</a> screen, you will find a sidebar listing the its children. Simply click on "Add New" to create a new page.
	Or click <a href="javascript:;" target="_blank" class="newPage minibutton">here</a>.
	<p>Once you click on "Publish", the new page will be added to the sidebar.
	<p>Child pages may be accessed through that sidebar, but they may also be accessed directly by adding the page domain to your homepage URL (not the page title, because titles can contain
		non-URL friendly characters like '?' and '%'). So, for example, if you were to create a page entitled "Deliveries" you could access the page directly by entering www.indumaguete.com/<span class="dgte-prop" prop="domain"></span>/deliveries.
		One advantage of this method is giving you the ability to link directly to the deliveries page on your description or on a forum page discussing shops that deliver for free, for example.
</div>

<br/>
<div class="ui-widget-content suggestion">	
<h3>2. Share your homepage on <span class="highlight">Facebook</span></h3>
	<p>Near the bottom of your <a href="javascript:;" target="_blank" class="view minibutton">View</a> screen you will find the Facebook "Like" button. "Like" your homepage and this event will be
		published to your Facebook Feed. Encourage your friends and acquaintances to "like" your homepage. Links with enought likes will float to the top of Facebook's feed and increase
		your homepage's visibility.
	<p>In the future we will be featuring other social networks like <span class="highlight">Google+</span> and <span class="highlight">Reddit</span>!
</div>

<br/>
<div class="ui-widget-content suggestion">	
<h3>3. Introduce yourself or your organization in the forums</h3>
	<p>Customers are more likely to trust people that they are able to interact with. While your view screen provides a Facebook Comments plugin for interaction, questions are still best
		answered in the <a href="javascript:;" class="forums minibutton">forums</a>. There you can go in depth about your products and services. The forums also allow you to edit or delete comments should
		you wish to do so.
	<p>Another advantage of using the forums for interaction is the ability to link to individual threads for a topic that may be frequently discussed on your view page but is not major enough
		to warrant the creation of its own child page.
	<p>The forums are maintained by a 3rd party site and will require a separate registration process. You should link your forum account to your homepage. Every little bit of 
		advertising helps.
</div>

<br/>
<div class="ui-widget-content suggestion">	
<h3>4. Get featured on <span class="highlight">InDumaguete</span></h3>
	<p>Every so often the site staff will choose home pages that we think are particularly interesting and feature them on the main page. Make yours interesting to grab our attention!
</div>

<br/>
<div class="ui-widget-content suggestion">	
<h3>5. Contribute to the News Feed</h3>
	<p>Are you involved in an event that affects many people in Dumaguete? Write up an article and send it to indumaguete@gmail.com! People will thank you for helping keep everyone 
		up to date on happenings in our fair city. Of course, you'll want to promote yourself or your organization a couple of times in while doing so.
</div>

<br/>
<a href="/indumaguete/${item.domain }/">Take me to my Homepage</a>

</div>
</body>
</html>