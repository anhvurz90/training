<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page import = "org.exoplatform.training.portlet.eventing.AddressBookEntry" %> 
<portlet:defineObjects />

<blockquote>
<h2>Address Book</h2>


<%
  AddressBookEntry entry;
  if (renderRequest.getAttribute("event") != null) {
  	entry = (AddressBookEntry)renderRequest.getAttribute("event");
%>

Name : <%= entry.getFirstName() %> <%= entry.getLastName() %><br/>
Address :<%= entry.getAddress() %>

<%
 }
%>

</blockquote>