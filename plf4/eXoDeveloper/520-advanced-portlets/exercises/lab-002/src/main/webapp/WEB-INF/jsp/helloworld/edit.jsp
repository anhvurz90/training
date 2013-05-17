<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<% 
String message = renderRequest.getPreferences().getValue("message", ""); 
%>

<blockquote>
<h3>Configure Your Message</h3>
<portlet:actionURL var="action"/>
<form action="<%=action %>" method="post">
  <label for="messageinput">Message : </label>
  <input type="text" name="message" id="messageinput" value="<%= message %>"/>
  <input type="submit" label="Save" />
</form>
<br/>
</blockquote>

<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

