package org.exoplatform.training.portlet.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS
 * @author eXoPlatform
 */
public class HelloWorld extends GenericPortlet {

  /**
   * doView is called by portlet render() method when the portlet is in view mode
   */
  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
      throws PortletException, IOException {

    renderResponse.setContentType("text/html; charset=UTF-8");
    
    // Obtain the response PrintWriter and append output
    PrintWriter w = renderResponse.getWriter();
    w.println("Hello World !");
  }

}
