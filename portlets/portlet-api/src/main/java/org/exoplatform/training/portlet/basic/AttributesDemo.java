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
package org.exoplatform.training.portlet.basic;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS 
 */
public class AttributesDemo extends GenericPortlet {

  public static String REQUEST_ATTRIBUTE = "org.exoplatform.training.portlet.basic.AttributesDemo.requestAttr";
  public static String SESSION_ATTRIBUTE = "org.exoplatform.training.portlet.basic.AttributesDemo.sessionAttr";
  public static String APPLICATION_ATTRIBUTE = "org.exoplatform.training.portlet.basic.AttributesDemo.applicationAttr";
  
  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {
    renderResponse.setContentType("text/html; charset=UTF-8");
    PortletContext context = getPortletContext();
    PortletRequestDispatcher rd = context.getRequestDispatcher("/jsp/attributesdemo/AttributesDemo.jsp");
    rd.include(renderRequest, renderResponse);

  }

  public void processAction(ActionRequest request, ActionResponse response)
      throws PortletException, IOException {

    String requestAttr = request.getParameter("requestAttr");
    if (requestAttr != null && !"".equals(requestAttr)) {
      request.setAttribute(REQUEST_ATTRIBUTE, requestAttr);
    }

    String sessionPortletAttr = request.getParameter("sessionPortletAttr");
    if (sessionPortletAttr != null && !"".equals(sessionPortletAttr)) {
      request.getPortletSession().setAttribute(SESSION_ATTRIBUTE, sessionPortletAttr);
    }

    String sessionApplicationAttr = request.getParameter("sessionApplicationAttr");
    if (sessionApplicationAttr != null && !"".equals(sessionApplicationAttr)) {
      request.getPortletSession().setAttribute(APPLICATION_ATTRIBUTE, sessionApplicationAttr,
          PortletSession.APPLICATION_SCOPE);
    }

  }

}
