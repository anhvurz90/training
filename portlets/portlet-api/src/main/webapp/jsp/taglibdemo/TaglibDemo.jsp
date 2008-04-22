<%@page import="java.util.Date" %>  

<%-- declare portlet taglib--%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%-- Expose render phase portlet objects such as renderRequest --%>
<portlet:defineObjects />


<center><h1>Taglib Demo</h2></center>

<p>
Portlets have their own URLs that you can build using the portlet taglib. <br />
</p>

<h2>portlet:renderURL</h2>
<p>
  <portlet:renderURL var="renderURL"/>
  <code>&lt;portlet:renderURL&gt;</code> will generate an URL to call <code>render()</code> method.<br/>
  The following link was obtained with renderURL, click and watch the logs : <br/>
  <b><a href="<%=renderURL %>" style="color: blue;font-weight: bold;"><%=renderURL %></a></b>
</p>

<p><h2>portlet:param</h2>
  <portlet:renderURL var="renderParamURL">
    <portlet:param name="timestamp" value="<%=""+new Date().getTime() %>" />
  </portlet:renderURL> 
  <% String timestamp = renderRequest.getParameter("timestamp");%>  
  <code>&lt;portlet:param&gt;</code> may be used to append arbitrary parameters to an URL.<br/> 
  <a href="<%=renderParamURL %>" style="color: blue;font-weight: bold;">Click here</a> to append a timestamp to the render URL:
  <b><% if (timestamp!=null) {%><%=renderParamURL%><%} %></b>
</p>

<p><h2>portlet:actionURL</h2>
  <% String color = renderRequest.getParameter("color"); %> 
  <portlet:actionURL var="actionURL" name="bg">
    <portlet:param name="actionParam" value="<%=color %>" />
  </portlet:actionURL> 
  <code>&lt;portlet:actionURL&gt;</code> will generate an URL to call <code>processAction()</code> method.<br/>
  It can be used to perform some business logic and update some 
  <span style="font-weight: bold; color: <%=color %>"> render parameters</span>. 
  <a href="<%=actionURL %>" style="color: blue;font-weight: bold;">Test it</a>
</p>


<p>
<h2>portlet:resourceURL</h2>
  <code>&lt;portlet:resourceURL&gt;</code> will generate an URL to call <code>serveResource() method.</code><br/>
  It can be used to serve other resources such as graphics. <br/>
  It works very well with ajax requests but here we demonstrate it using an iframe. 
  <a href="<%=actionURL %>" style="color: blue;font-weight: bold;">Test it</a>

  <portlet:resourceURL var="resourceURL">
    <portlet:param name="color" value="<%=color %>" />
  </portlet:resourceURL>
  <br/>
  <iframe src="<%=resourceURL %>" frameborder="0" ></iframe>
</p>
