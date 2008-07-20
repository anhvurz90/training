<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<% 
String message = renderRequest.getPreferences().getValue("message", ""); 

// Get the link to the HELP mode (note that could be done with Taglib)
javax.portlet.PortletURL helpURL = renderResponse.createRenderURL();
helpURL.setPortletMode(javax.portlet.PortletMode.HELP);
%>


<blockquote>
The message store in the portlet preference is : <b><%= message %></b>.
<p>
You can <a href="<%=helpURL%>"><u>edit</u></a> the portlet to change it. You can also use the <a href="<%=helpURL%>"><u>help</u></a>.
</p>
</blockquote>


<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

