<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />


<blockquote>
 <h2>Portlet Session: Portlet Scope</h2>

 <%
  javax.portlet.PortletSession portleSession = renderRequest.getPortletSession();

  Object appScopeData =  portleSession.getAttribute( "counter", javax.portlet.PortletSession.APPLICATION_SCOPE );


  int portletScopeData = 0; 
  Integer currentValue = (Integer) portleSession.getAttribute( "counter",  javax.portlet.PortletSession.PORTLET_SCOPE ) ;
   if( currentValue == null ){
       portletScopeData = 0 ;
    }
    else{
       portletScopeData = currentValue.intValue() ;
    }

 %>
Counter <u>Portlet Scope</u>: <%= portletScopeData %>
<br><br>
Counter <u>Application Scope</u>: <%= appScopeData %>
</blockquote>


<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

<%
portletScopeData ++ ;
Integer newValue = new Integer( portletScopeData ) ;
portleSession.setAttribute( "counter", newValue,  javax.portlet.PortletSession.PORTLET_SCOPE ) ;


%>