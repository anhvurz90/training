<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />

<center><h1>Event Producer</h2></center>

<p>
  Portlets can send events to each other.
  These events can have arbitrary payload.
  <br/>
  Event is sent with : <code>PortletResponse.setEvent(name,value)</code><br/>
</p>
<p>
  Add the EventConsumer portlet on the same page and submit some event payload.<br/>
  <portlet:actionURL var="actionURL" />  
  <form action="<%=actionURL %>" method="post">
  <label for="rinput">Event payload: </label>
  <input type="text" name="evt" id="rinput" />&nbsp;<br/>
  <center><input type="submit" label="Save" /></center>  
  </form>
  
</p>  