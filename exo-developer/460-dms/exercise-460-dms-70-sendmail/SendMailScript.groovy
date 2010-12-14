/*
 * Copyright (C) 2003-2007 eXo Platform SAS.
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

import java.util.Map;
import javax.jcr.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.cms.scripts.CmsScript;
import org.exoplatform.services.jcr.RepositoryService;

/*

* Will need to get The MailService when it has been moved to exo-platform

*/

public class SendMailScript implements CmsScript {
  private RepositoryService repositoryService_;

  public SendMailScript(RepositoryService repositoryService) {
    repositoryService_ = repositoryService;
  }

  public void execute(Object context) {               
     Map variables = (Map) context;                
	 dumpMap(variables);
	 dumpProperties(variables.get("actionNode"));
	 
     String to = ""; //variables.get("exo:to");  
	 Node actionNode = variables.get("actionNode");
     Property toProp = actionNode.getProperty("exo:to");  
	 to = toProp.getString();
     //String nodePath = (String) variables.get("nodePath");
   
     try {
       //	 String nodeName = nodePath.split("/")[nodePath.split("/").size() -1]
       String subject = variables.get("actionName");            
       Property lifecylceProp = actionNode.getProperty("exo:lifecyclePhase"); 	   
       String message = "Node path : " + variables.get("nodePath") + "\n" + " initiator: " + variables.get("Initiator") + "\n" + " Lifecycle phase: " + toProp.getString() + "\n";
       MailService service = (MailService)PortalContainer.getComponent(MailService.class);
       Session mailSession = service.getMailSession();
       MimeMessage msg = new MimeMessage(mailSession);
       msg.setFrom(new InternetAddress("soren.schmidt@exoplatform.com"));
       msg.setRecipient(RecipientType.TO, new InternetAddress(to));
       msg.setSubject(subject);
       msg.setContent(message, "text/html ; charset=ISO-8859-1");
       service.sendMessage(msg);
       println("Send message in SendMailScript from " + msg.getFrom() + "to " + to);
     } catch (Exception e) {
	     System.err.println("Error in SendMailScript. exo:to=" + to );

         e.printStackTrace();
     }
  }

  public void setParams(String[] params) {}
  
  
  public static void dumpProperties(javax.jcr.Node node) {
    System.out.println("action node properties");
    PropertyIterator props = node.getProperties();
    while (props.hasNext()) {
        javax.jcr.Property p = props.nextProperty();
		System.out.print(p.getName() + "=");
		try {
                System.out.println(p.getString());
		} catch (javax.jcr.ValueFormatException vfe) {
		    // multivalue property ?			
			try {
				Value[] values = p.getValues();
				for(Value value: values) {
			        System.out.println(value.getString());
				}
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
    }
}

public static void dumpMap(Map mp) {
    Iterator it = mp.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        System.out.println(pairs.getKey() + " = " + pairs.getValue());
    }
}
}