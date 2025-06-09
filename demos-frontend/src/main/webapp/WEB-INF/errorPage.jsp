<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="parts/header.jsp" %>

<h1 class="display-1 text-center"><%= request.getAttribute("status") %> <%= request.getAttribute("error") %></h1>
<h3><%= request.getAttribute("message") %></h3>
<p>
<%-- 	date: <fmt:formatDate type="both" dateStyle = "short" timeStyle="short" value="<%= request.getAttribute("timestamp") %>" /> --%>
	<br>
	url: <%= request.getAttribute("path") %>
</p>
<%@ include file="parts/footer.jsp" %>
