<%@ include file="/jsp/header.jsp"%>

<spring:url value="simplereg/save/" var="urlSubmit" />
<form:form method="post" commandName="item" action="${urlSubmit }">
<ul>
	<li>
		<form:label path="domain">Domain:</form:label>
		<form:input path="domain" />
	</li>
	
	<li>
		<form:label path="name">Business Name:</form:label>
		<form:input path="name" />
	</li>
	
	<li>
		<form:label path="shortDescription">Short Description:</form:label>
		<form:input path="shortDescription" />
	</li>
	
	<li>
		<form:label path="category">Category:</form:label>
		<form:select path="category" items="${categories }" />
	</li>
	
	<li>
		<form:label path="address">Address:</form:label>
		<form:input path="address" />
	</li>
	
	<li>
		<form:label path="email">Email:</form:label>
		<form:input path="email" />
	</li>
	
	<li>
		<form:label path="cellphone">Cellphone Number:</form:label>
		<form:input path="cellphone" />
	</li>
	
	<li>
		<form:label path="landline">Landline Number:</form:label>
		<form:input path="landline" />
	</li>
	
	<li>
		<input type="submit" />
	</li>
</ul>
</form:form>