<%@ include file="/jsp/header.jsp"%>

<ul>
	<li>
		<label for="term">Search:</label>
		<input type="text" id="txtSearchTerm" name="term"/>
	</li>
	
	<li>
		<label for="selectCategory">Category:</label>
		<select id="selectCategory" name="category">
			<c:forEach var="category" items="${categories }">
			<option value="${category }" <c:if test="${fn:startsWith(category, 'All') || fn:startsWith(category, 'Any') == true}">class="bold"</c:if>>${category }</option>
			</c:forEach>
		</select>
	</li>
	
	<li>
		<button id="btnSearch">Search</button>
	</li>
</ul>

<div id="results"></div>

<div id="resultTemplate" style="display: none;">
	<ul>
		<li>
			<span class="name"></span>
		</li>
		<li>
			<span class="address"></span>
		</li>
	</ul>
</div>

<script type="text/javascript" src="${jquery_url }" ></script>
<script>
window.e = {
	urlSearch : '/indumaguete/simplesearch/',
	$btnSearch : $('#btnSearch'),
	$txtSearchTerm : $('#txtSearchTerm'),
	$selectCategory : $('#selectCategory'),
	$results : $('#results'),
	$resultTemplate : $('#resultTemplate')
};

window.search = {
	display : function(results) {
		for(var i in results) {
			var result = results[i];
			var $resultDiv = search.constructResultDiv(result);
		}
	},
	
	constructResultDiv : function(result) {
		var $divResult = e.$resultTemplate.clone()
							.css('display', '')
							.find('.name').text(result.name).end()
							.find('address').text(result.address).end();
		search.appendResultDiv(result);
	},
	
	appendResultDiv : function($divResult) {
		e.$results.append($divResult);
	}
};

$(function(){
	e.$btnSearch.click(function(){
		var params = {
				'term' : e.$txtSearchTerm.val(),
				'category' : e.$selectCategory.val()
		}
		$.post(e.urlSearch, params, function(results){
			search.display(results);
		});
	});
});
</script>