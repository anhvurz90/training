<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>


<center><h1>Event Consumer</h1></center>

<p>
Portlets can register for events and receive events from other portlets.<br/>
The events are broadcasted by the portlet container to the <code>processEvent()</code> phase.<br/>
To process an event you will use : <code>EventRequest.getEvent()</code>
</p>

<p>
<portlet:defineObjects />
<%
String requestAttr = (String)renderRequest.getAttribute("evt");
if (requestAttr != null) {
%>
Event received with payload: <b><%=requestAttr%></b>
<%}%>
</p>