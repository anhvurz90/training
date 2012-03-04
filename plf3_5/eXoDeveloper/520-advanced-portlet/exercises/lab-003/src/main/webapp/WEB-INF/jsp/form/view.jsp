<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<%
String countryName = renderRequest.getParameter("country");

%>


<blockquote>
<h2>Parameter Form</h2>
<portlet:actionURL var="action"/>
<form action="<%=action %>" method="post">
  <label for="rinput">Enter a country name: </label>
  <input type="text" name="country" id="rinput" value="<%=countryName%>" />&nbsp;
  <input type="submit" name="Send"/>  
</form>

</blockquote>

<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

