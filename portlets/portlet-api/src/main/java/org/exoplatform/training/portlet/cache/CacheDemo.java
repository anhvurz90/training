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
package org.exoplatform.training.portlet.cache;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.CacheControl;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com 10
 * mars 08
 */
public class CacheDemo extends GenericPortlet {

  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {
    CacheControl cache = renderResponse.getCacheControl();
    String action = (String) renderRequest.getAttribute("action");
    

    int expiracy = 300;
    String scope = "private";
    
    if (validateCachedMarkup(renderRequest)) {
      cache.setExpirationTime(expiracy);
      cache.setUseCachedContent(true);
      System.out.println("etag valid, using cached content");
      return;
    }    
    
    String etag = generateEtag(renderRequest);
    cache.setETag(etag);    // same as renderResponse.setProperty(RenderResponse.ETAG, etag);

    // expire
    if ("expire".equals(action)) {
      try {
        expiracy = Integer.parseInt(((String) renderRequest.getAttribute("expiration")));
      } catch (Exception e) {

      }
      
      // change scope
    } else if ("scope".equals(action)) {
      scope = (String)renderRequest.getAttribute("scope");
      cache.setPublicScope("public".equals(scope));
    }
    
    
    cache.setExpirationTime(expiracy);
    renderRequest.setAttribute("expiracy", String.valueOf(cache.getExpirationTime()));
    renderRequest.setAttribute("scope", scope);
    
    System.out.println("called doView()");  
    renderResponse.setContentType("text/html; charset=UTF-8");
    PortletContext context = getPortletContext();
    PortletRequestDispatcher rd = context.getRequestDispatcher("/jsp/cache/CacheDemo.jsp");
    rd.include(renderRequest, renderResponse);
  }

  private String generateEtag(RenderRequest renderRequest) {
    // Fake logic : We use windows ID but you should add your own etag generation logic
    return renderRequest.getWindowID();
  }

  private boolean validateCachedMarkup(RenderRequest renderRequest) {
    boolean etagExists = (renderRequest.getETag() != null);
    // this is a fake validation logic to demonstrate valid/invalid
    boolean etagValid = "valid".equals((String)renderRequest.getAttribute("validity"));
    return etagExists && etagValid;
  }

  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
      throws PortletException, IOException {
    String action = actionRequest.getParameter("action");
    actionRequest.setAttribute("action", action);
    actionRequest.setAttribute("expiration", actionRequest.getParameter("expiration"));
    actionRequest.setAttribute("scope", actionRequest.getParameter("scope"));
    actionRequest.setAttribute("validity", actionRequest.getParameter("validity"));
  }
}
