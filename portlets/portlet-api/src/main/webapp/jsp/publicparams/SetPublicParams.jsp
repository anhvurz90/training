<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />

<center><h1>Set Public Params</h1></center>

<p>
Public renders parameters are a mean to coordinate portlets rendering.<br/> 
They are declared in portlet.xml and shared between portlets.
</p>
<p>
<portlet:actionURL var="action"/>
<form action="<%=action %>" method="post">
  <label for="rinput">Enter a country name: </label>
  <input type="text" name="country" id="rinput" />&nbsp;
  <input type="submit" name="Send"/>  
</form>
</p>
<p>
<portlet:renderURL var="spain"><
  <portlet:param name="country" value="Spain"/>
</portlet:renderURL>
<a href="<%=spain%>">Spain</a>&nbsp;
<portlet:renderURL var="france"><
  <portlet:param name="country" value="France"/>
</portlet:renderURL>
<a href="<%=france%>">France</a>&nbsp;
 
</p>

<%
String country = (String)renderRequest.getParameter("country");
if (country != null) {
%>
<p>
Now add UNDataCountry and WikipediaCountry to this page and see what they do with <strong><%=country %></strong>.
</p>
<%}%>