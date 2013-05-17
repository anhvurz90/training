<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />


<blockquote>
 <h2>Portlet Session: Application Scope</h2>

 <%
  javax.portlet.PortletSession portleSession = renderRequest.getPortletSession();

  Object portletCurrentValue = portleSession.getAttribute( "counter",  javax.portlet.PortletSession.PORTLET_SCOPE );
  int portletScopeData =0;
  if (portletCurrentValue != null) {
	portletScopeData= ((Integer)portletCurrentValue).intValue();
  }

  int appScopeData = 0; 
  Object currentValue =  portleSession.getAttribute( "counter" ,  javax.portlet.PortletSession.APPLICATION_SCOPE) ;
   if( currentValue != null ){
       appScopeData = ((Integer)currentValue).intValue() ;
    }


 %>

Counter for Application Scope <%= appScopeData %> <br/>
Counter for Portlet Scope: <%= portletScopeData %>.

</blockquote>


<p>
Some Information about the portlet: 
<%= renderRequest.getPortletMode().toString()%> mode, 
<%= renderRequest.getWindowState().toString()%> window state.
</p>

<%

portletScopeData++;
appScopeData ++ ;
Integer appScopeNewValue = new Integer( appScopeData ) ;
Integer portletScopeNewValue = new Integer( portletScopeData ) ;


portleSession.setAttribute( "counter", appScopeNewValue,  javax.portlet.PortletSession.APPLICATION_SCOPE ) ;
portleSession.setAttribute( "counter", portletScopeNewValue,  javax.portlet.PortletSession.PORTLET_SCOPE ) ;

%>