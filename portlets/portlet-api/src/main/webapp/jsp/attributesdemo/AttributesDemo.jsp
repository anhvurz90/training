<%@page import="javax.portlet.PortletSession" %>
<%@page import="org.exoplatform.training.portlet.basic.AttributesDemo" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />

<h1><center>Portlet Attributes Demo</center></h1>
<%
String requestAttr = (String)renderRequest.getAttribute(AttributesDemo.REQUEST_ATTRIBUTE);
String sessionPortletAttr = (String)renderRequest.getPortletSession().getAttribute(AttributesDemo.SESSION_ATTRIBUTE);
String sessionApplicationAttr = (String)renderRequest.getPortletSession().getAttribute(AttributesDemo.APPLICATION_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);
%>

<portlet:actionURL var="action"/>
<form action="<%=action %>" method="post">
<p>
  <h2> Request Attributes</h2>
  Requests attributes only survive during one request lifetime.<br/>
  <code>PortletRequest.setAttribute(key,value)</code> :
  <input type="text" name="requestAttr" id="rinput" />&nbsp;<br/>
  <code>PortletRequest.getAttribute(key)</code>: <b><%=requestAttr %></b>
</p>

<p>
  <h2>Portlet scoped session attributes</h2>
  Portlets have sessions where you can also store longer living attributes.
  They will survive until the portlet session expires but they are private to a portlet instance.<br/>
  <code>PortletRequest.getPortletSession().setAttribute(key,value)</code> : 
  <input type="text" name="sessionPortletAttr" id="sinput" />&nbsp;<br/>
  <code>renderRequest.getPortletSession().getAttribute(key)</code> : <b><%=sessionPortletAttr %></b>
</p>

<p>
  <h2>Application scoped session attributes</h2>
  If you need to share some attribute between portlets of the same portlet application (war), you can use the application scoped session attributes.<br/>
  <code>PortletRequest.getPortletSession().setAttribute(key,value,PortletSession.APPLICATION_SCOPE)</code> : 
  <input type="text" name="sessionApplicationAttr" id="ainput" />&nbsp;<br/>
  <code>renderRequest.getAttribute(key,PortletSession.APPLICATION_SCOPE)</code> : <b><%=sessionApplicationAttr %></b>
</p>

<p>
  Now, add another instance of this portlet to the same page then send values to see attributes scopes and durations.<br/>
  <center><input type="submit" label="Save" /></center>
</p>
</form>

