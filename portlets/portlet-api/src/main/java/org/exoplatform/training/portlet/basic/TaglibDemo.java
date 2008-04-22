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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceServingPortlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Created by The eXo Platform SAS 
 * @author eXoPlatform
 */
public class TaglibDemo extends GenericPortlet implements ResourceServingPortlet {

  /*@RenderMode(name="")*/
  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {
    renderResponse.setContentType("text/html; charset=UTF-8");
    System.out.println("doView() was called on " + getClass().getName());
    
    PortletContext context = getPortletContext();
    PortletRequestDispatcher rd = context.getRequestDispatcher("/jsp/taglibdemo/TaglibDemo.jsp");
    rd.include(renderRequest, renderResponse);
  }

  
  @ProcessAction(name="bg")
  public void changeBackground(ActionRequest request, ActionResponse response)
      throws PortletException, IOException {
    if (PortletMode.VIEW.equals(request.getPortletMode())) {
      String param = request.getParameter("actionParam");
      String color = "red".equals(param) ? "green" : "red";
      response.setRenderParameter("color", color);
      
      // 
      
    }
  }

  public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException,
      IOException {
    String goal = request.getParameter("color");
    if (goal != null && goal.equals("red")) {
      response.setContentType("image/jpeg");
      Graphics2D graphics;
      java.io.OutputStream stream = response.getPortletOutputStream();
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
      BufferedImage bi = new BufferedImage(150, 20, BufferedImage.TYPE_BYTE_INDEXED);
      graphics = bi.createGraphics();
      graphics.setColor(Color.white);
      graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
      graphics.setColor(Color.red);
      char[] data = "eXo rules!".toCharArray();
      graphics.drawChars(data, 0, data.length, 0, 10);
      encoder.encode(bi);
    } else if (goal != null && goal.equals("green")) {
      response.setContentType("image/jpeg");
      Graphics2D graphics;
      java.io.OutputStream stream = response.getPortletOutputStream();
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
      BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_BYTE_INDEXED);
      graphics = bi.createGraphics();
      graphics.setColor(Color.white);
      graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
      graphics.setColor(Color.green);
      graphics.drawLine( 0, 24,  9, 24);
      graphics.drawLine( 9, 24, 19,  0);
      graphics.drawLine(19,  0, 29, 49);
      graphics.drawLine(29, 49, 39, 24);
      graphics.drawLine(39, 24, 49, 24);
      encoder.encode(bi);
    } else {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter w = response.getWriter();
      w.println("eXo rocks!");
    }
    
  }
 

}
