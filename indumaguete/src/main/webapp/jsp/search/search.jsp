<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search!</title>

<link rel="stylesheet" href="${jquery_ui_css}" />
<link rel="stylesheet" href="${main_css}" />
<link rel="stylesheet" href="/indumaguete/resources/css/search.css" />
<script type="text/javascript" src="${jquery_url }" ></script>
<script type="text/javascript" src="${jquery_ui_url }" ></script>
<script type="text/javascript" src="${indumaguete_js_url }" ></script>
<script type="text/javascript" src="/indumaguete/resources/categoryselect.js" ></script>
<link rel="stylesheet" href="/indumaguete/resources/categoryselect.css" />
<script type="text/javascript" src="/indumaguete/resources/timeago/jquery.timeago.js"></script>
<script>

var search = {
	categories : {
		<c:forEach items="${categories}" var="category" varStatus="cstatus">
			'${category.key}' : [<c:forEach items="${category.value}" var="subcategory" varStatus="sstatus">'${subcategory}'<c:if test="${not sstatus.last}">,</c:if></c:forEach>]<c:if test="${not cstatus.last}">,</c:if>
		</c:forEach>
	},
	
	displayResults : function(results, $container) {
		$('.dgte-search-result').remove();
		$.each(results, function(i, result){
			search.createResultDiv(result).appendTo($container);
		});
	},
	
	createResultDiv : function(result) {
		var $R = $('<div class="dgte-search-result">');
		
		if(result.imageUrl) {
			var $imgcontainer = $('<div class="dgte-search-result-imgcontainer">').appendTo($R);
			$('<img class="dgte-search-result-img">').attr('src', result.imageUrl).appendTo($imgcontainer);
		}
		
		var $r = $('<div class="dgte-search-result-info">').appendTo($R);
		if(result.subcategory) {
			$('<div class="dgte-search-result-info">').text(result.title + ' (' + result.subcategory + ')').appendTo($r);
		} else {
			$('<div class="dgte-search-result-info">').text(result.title).appendTo($r);
		}
		
		$('<div class="dgte-search-result-info">').text(result.text).appendTo($r);
		
		var $footer = $('<div  class="footnote dgte-search-result-info">').text('by ' + result.sourceName).appendTo($r);
		if(result.pageDomain) {
			$footer.append(' in ' + result.pageDomain);
		}
		if(result.modified) {
			$('<div class="footnote dgte-search-result-info">').text('Updated ' + $.timeago(result.modified)).appendTo($r);
		}
		
		$R.click(function() {
			search.gotoResult(result);
		});
		
		return $R;
	},
	
	gotoResult : function(result) {
		window.location.href = '/indumaguete/' + result.domain + '/';
	}
};	
	
$(document).ready(function(){
	var $term = $('#term'),
		$subcategory = $('#subcategory'),
		$searchResults = $('#searchResults'),
		$button_search = $('#button_search');
	
	
	$button_search.button({ icons: { secondary: "ui-icon-search" } });
	$('a#subcategory').button({ icons: { secondary: "ui-icon-circle-triangle-s" } }).click(function(event){
		catsel.createSelect(search.categories, $(this)).show();
		event.stopPropagation();
	});
	
	var term, subcategory;
	$button_search.click(function(){
		term = $term.val();
		subcategory = $subcategory.text();
		if(term == search.lastTerm && subcategory == search.lastsubcategory) {
			return false;
		} 
		
		$.post('/indumaguete/search/', {term:term, subcategory:subcategory}, function(response) {
			search.displayResults(response, $searchResults);
		});
		
		search.lastTerm = term;
		search.lastsubcategory = subcategory;
		return false;
	});
	
	$button_search.click();
});
</script>

<style>
.dgte-search-result {
	font-size: 0.7em;
	background-color: lavender;
	margin: 5px;
	padding: 5px;
	width: 435px;
	vertical-align: middle;
	cursor: pointer;
}

.dgte-search-result-imgcontainer {
	height: 100%;
	width: 100px;  
	display: inline-block;
}
.dgte-search-result-img {
	height: 60px;
}
.dgte-search-result-info {
	display: inline-block;
	width: 330px;  
}
</style>

</head>
<body>
<div class="ui-widget">
<h2>Find a Something</h2>
<form id="form_basicinfo">		
		<fieldset>
		<p><span class="label">Search</span><input id="term" type="text"></input> <button id="button_search">Search</button>			
		<p><span class="label">Nature of Business</span><a id="subcategory" href="javascript:;">All</a></p>
		<span class="explanation">Right category not here? <a href='contact/' target='_blank'>Let us know and we'll add it</a>.</span>
		</fieldset>
		
		<div id="searchResults" class="dgte-search-results"></div>
</form>
</div>
</body>
</html>