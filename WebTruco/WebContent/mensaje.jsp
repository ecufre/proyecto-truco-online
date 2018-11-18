<% if (request.getAttribute("ok") == null) { %>
<div class="alert alert-danger fade in alert-dismissible">
<% } else { %>
<div class="alert alert-success fade in alert-dismissible">
<% } %>
	<a href="#" class="close" data-dismiss="alert" aria-label="close"
		title="close">×</a> <strong>
		<%
			if (request.getAttribute("mensaje") != null)
				out.print(request.getAttribute("mensaje"));
			else
				out.print(request.getParameter("mensaje"));
		%>
	</strong>
</div>