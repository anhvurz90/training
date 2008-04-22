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
package org.exoplatform.training.portlet.event;

import java.io.IOException;

import javax.portlet.EventPortlet;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com 7
 * mars 08
 */
public class EventConsumer extends GenericPortlet implements EventPortlet {

  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {
    renderResponse.setContentType("text/html; charset=UTF-8");
    PortletContext context = getPortletContext();
    PortletRequestDispatcher rd = context.getRequestDispatcher("/jsp/ipc/Consumer.jsp");
    rd.include(renderRequest, renderResponse);
  }

  /**@ProcessEvent(qname="{http://exoplatform.org/training}SampleEvent")
   * @ProcessEvent(name="SampleEvent")
   * */
  public void processEvent(EventRequest req, EventResponse resp) throws PortletException,
      IOException {
      SampleEvent event = (SampleEvent) req.getEvent().getValue();
      String value = event.getContent();
      req.setAttribute("evt", value);
  }

}
