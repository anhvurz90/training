/*
 * Copyright (C) 2003-2010 eXo Platform SAS.
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

* The DumpScript o

*/

public class DumpScript implements CmsScript {
  private RepositoryService repositoryService_;

  public DumpScript(RepositoryService repositoryService) {
    repositoryService_ = repositoryService;
  }

  public void execute(Object context) {    
	 System.out.println("DumpScript called.");  
     Map variables = (Map) context;                      
   	 dumpMap(variables);
	 dumpProperties(variables.get("actionNode"));
   
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
    System.out.println("node context variables");
    Iterator it = mp.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        System.out.println(pairs.getKey() + " = " + pairs.getValue());
    }
}
}