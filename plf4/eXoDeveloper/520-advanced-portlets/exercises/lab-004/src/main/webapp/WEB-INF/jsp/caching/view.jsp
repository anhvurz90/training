<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />

<blockquote>

<h3>Portlet Caching Demonstration</h3>

Time: <%= new java.util.Date()  %>

<p>
<portlet:actionURL var="action"/>
<u><a href="<%= action %>">Call Action</a></u>

</blockquote>

