<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />


<center><h1>Wikipedia Country</h1></center>

<%
String country = (String)renderRequest.getParameter("country");
if (country != null) {
%>
<p>
Let's see what Wikipedia says about <strong><%=country%></strong>. 
</p>

<iframe src="http://en.wikipedia.org/wiki/<%=country %>" frameborder="0" width="100%" height="100%"></iframe>
<% } else { %>
<p>Choose a country in SetPublicParams portlet.</p>
<% }%>