<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />


<blockquote>
<h2>Parameter Form</h2>
<portlet:actionURL var="action"/>

<form action="<%=action %>" method="post">
  <table >
  <tr>
	<td>
  		<label for="firstName">First Name</label>
	</td>
	<td>
  		<input type="text" name="firstName" id="firstName" value="" />
	</td>
  </tr>
  <tr>
	<td>
  		<label for="lastName">Last Name</label>
	</td>
	<td>
  		<input type="text" name="lastName" id="lastName" value="" />
	</td>
  </tr>
  <tr>
	<td>
  		<label for="address">Address</label>
 	</td>
	<td>
 		<textarea name="address" id="address" rows=3 ></textarea>
    </td>
  </tr>
  <tr>
  	<td colspan="2">
  		<input type="submit" name="Send"/>  
    </td>
  </tr>
</table>
</form>

</blockquote>

<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>
