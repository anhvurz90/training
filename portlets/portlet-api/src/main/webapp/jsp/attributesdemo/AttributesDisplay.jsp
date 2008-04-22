<%@page import="javax.portlet.PortletSession" %>
<%@page import="org.exoplatform.training.portlet.basic.AttributesDemo" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>


<center>Attributes display</center>

<p>
Here you can view the session attributes set by AttributeDemo portlets
</p>

<portlet:defineObjects />
<%
String requestAttr = (String)renderRequest.getAttribute(AttributesDemo.REQUEST_ATTRIBUTE);
String sessionPortletAttr = (String)renderRequest.getPortletSession().getAttribute(AttributesDemo.SESSION_ATTRIBUTE);
String sessionApplicationAttr = (String)renderRequest.getPortletSession().getAttribute(AttributesDemo.APPLICATION_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);
%>


<p>
<ul>
  <li>
    Request Attribute: <b><%=requestAttr %></b>
    &nbsp; &lt;-- only survive during request lifetime
  </li>
  <li>
    Session scope : <b><%=sessionPortletAttr %></b>
    &nbsp;&lt;-- private to the portlet 
  </li>
  <li>
    Application scope : <b><%=sessionApplicationAttr %></b>
    &nbsp;&lt;-- shared among same application (war) 
  </li>
</ul>
</p>