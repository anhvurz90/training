<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />


<%
// retrieve portlet preferences with renderRequest.getPreferences()
String background = renderRequest.getPreferences().getValue("background", ""); 
%>

<%--
// use portlet:actionURL tag to generate the portlet action URL where form data is posted --
--%>
<portlet:actionURL var="action"/>
<form action="<%=action %>" method="post">
  <label for="bginput">Background Color</label>
  <input type="text" name="background" id="bginput" value="<%=background %>"/>
  <input type="submit" label="Save" />
</form>

