<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />


<blockquote>
 <h2>Portlet Session: Application Scope</h2>

 <%
  javax.portlet.PortletSession portleSession = renderRequest.getPortletSession();

  Object portletScopeData = portleSession.getAttribute( "counter",  javax.portlet.PortletSession.PORTLET_SCOPE );

  int appScopeData = 0; 
  Integer currentValue = (Integer) portleSession.getAttribute( "demoscope" ,  javax.portlet.PortletSession.APPLICATION_SCOPE) ;
   if( currentValue == null ){
       appScopeData = 0 ;
    }
    else{
       appScopeData = currentValue.intValue() ;
    }
    appScopeData ++ ;
    Integer newValue = new Integer( appScopeData ) ;
    portleSession.setAttribute( "demoscope", newValue,  javax.portlet.PortletSession.APPLICATION_SCOPE ) ;

 %>
Counter for Application Scope <%= appScopeData %> <br/>
Counter for Portlet Scope: <%= portletScopeData %>.

</blockquote>


<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

