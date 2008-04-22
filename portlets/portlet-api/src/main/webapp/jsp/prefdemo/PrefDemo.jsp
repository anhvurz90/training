<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<center>Preferences Demo</center>

<p>
Portlets can have preferences. They are typically edited in 'edit' mode.
You can access preferences through renderRequest.getPreferences()
</p>
<%
// this demonstrates preferences retrieving
String background = renderRequest.getPreferences().getValue("background", "white");
%>
<div
	style="margin: 10px;border: 1px solid black; width: 150px; height: 100px; background-color: <%= background %>">
Edit preferences to set the background of this box
</div>
