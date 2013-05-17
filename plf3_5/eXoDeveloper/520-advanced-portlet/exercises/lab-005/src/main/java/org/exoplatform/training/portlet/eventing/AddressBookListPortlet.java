/*
 * Copyright (C) 2003-2008 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.training.portlet.eventing;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.PortletPreferences;
import javax.xml.namespace.QName;


/**
 * Created by The eXo Platform SAS 
 * @author eXoPlatform
 */
public class AddressBookListPortlet extends GenericPortlet {

	
	private static final String viewPage = "/WEB-INF/jsp/addressbooklist/view.jsp";

	private static AddressBookEntry event=null;

	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
				
				System.out.println("doView request string : " + request.toString() );
				System.out.println("request hash  : " + request.hashCode() );
				
				
				AddressBookEntry event2 = (AddressBookEntry) request.getAttribute("event");
				
				
				AddressBookEntry eventRes = (AddressBookEntry) request.getAttribute("eventRes");
				if (event2 != null) System.out.println("Event has arrived in doView: " + event2.toString() );
				if (eventRes != null) System.out.println("EventRes has arrived in doView: " + eventRes.toString() );
				
				if (this.event!=null) {					
					request.setAttribute("event", this.event);
				}
		processRequest( request,  response); 
	}


	
	public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
		// get the event from the request
				System.out.println("processEvent request string : " + request.toString() );
				System.out.println("request hash  : " + request.hashCode() );

		AddressBookEntry event = (AddressBookEntry) request.getEvent().getValue();

		System.out.println("Event has arrived in processEvent: " + event.toString());
		
		this.event = event;
		/*
		AddressBookEntry entry = new AddressBookEntry();
			entry.setFirstName( "firstName" );
			entry.setLastName( "lastName" );
			entry.setAddress( "address" );
			request.setAttribute("event", entry );
			*/
		
		// Put the event in some parameter
        request.setAttribute("event", event);
       

	}
	
	/**
	 * Method use to manage the various mode
	 * @param request
	 * @param response
	 * @throws PortletException
	 * @throws IOException
	 */
	 private void processRequest(RenderRequest request, RenderResponse response) 
		throws PortletException, IOException {
		
		PortletMode mode = request.getPortletMode();
		String pageToShow = viewPage;
      // set response content-type to value in request
      response.setContentType(request.getResponseContentType());
      if (getPortletContext().getResourceAsStream(pageToShow) != null) {
          try {
              // dispatch edit request to edit.jsp
              PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher(pageToShow);
              dispatcher.include(request, response);
          } catch (IOException e) {
              throw new PortletException("Exception in Portlet", e);
          }
      } else {
          throw new PortletException("Exception JSP is missing.");
      }
		
	}


}
