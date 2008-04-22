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
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS
 * @author eXoPlatform
 */
public class PreferencesDemo extends GenericPortlet {

  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {
    PortletPreferences preferences = renderRequest.getPreferences();

    int count = 0;
    java.util.Enumeration e = preferences.getNames();
    while(e.hasMoreElements()){
       e.nextElement();
       count++;
    }
    System.out.println(count);
  

    renderResponse.setContentType("text/html; charset=UTF-8");
    PortletContext context = getPortletContext();
    PortletRequestDispatcher rd = context.getRequestDispatcher("/jsp/prefdemo/PrefDemo.jsp");
    rd.include(renderRequest, renderResponse);
  }

  public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {

    renderResponse.setContentType("text/html; charset=UTF-8");
    PortletContext context = getPortletContext();
    PortletRequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/prefdemo/Preferences.jsp");
    dispatcher.include(renderRequest, renderResponse);
  }

  public void processAction(ActionRequest request, ActionResponse response)
      throws PortletException, IOException {
    if (PortletMode.EDIT.equals(request.getPortletMode())) {

      // retrieve parameters
      String background = request.getParameter("background");

      // demonstrates portlet preferences storage
      PortletPreferences pref = request.getPreferences();
      pref.setValue("background", background);
      pref.store();

      // back to view mode
      response.setPortletMode(PortletMode.VIEW);
    }
  }

}
