<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />

<%
String countryName = renderRequest.getParameter("country");
%>

<blockquote>
 The country parameter value is: <b><%= countryName%></b>.
</blockquote>


<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

