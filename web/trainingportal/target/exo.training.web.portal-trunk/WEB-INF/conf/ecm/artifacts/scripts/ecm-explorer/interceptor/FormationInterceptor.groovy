import javax.jcr.Node;
import javax.jcr.Session;

import org.exoplatform.services.cms.scripts.CmsScript;
import org.exoplatform.services.jcr.RepositoryService;

public class FormationInterceptor implements CmsScript {
  
    private RepositoryService repositoryService_;
  
  public FormationInterceptor(RepositoryService repositoryService) {
    this.repositoryService_ = repositoryService;
  }
  
  public void execute(Object context) {
    // Exercice 1
    System.out.println("Hello template!");
  
    // exercice 3
    String path = (String) context;       
    println("received context " + path);
    Session session = null ;
    try {
	  String[] splittedPath = path.split("&workspaceName=");
      String[] splittedContent = splittedPath[1].split("&repository=");    
      
      session = repositoryService_.getRepository(splittedContent[1]).getSystemSession(splittedContent[0]);
	  Node match = (Node) session.getItem(splittedPath[0]);	    
	  System.out.println(match.getName());
	
	  // exercice 4
	  String recevant = match.getProperty("frm:equipeRecevant").getString();
	  String invitee = match.getProperty("frm:equipeInvitee").getString();
	  String title= recevant + " vs " + invitee;
	  match.setProperty("exo:title", title);
	  match.save();
		
	
	} catch(Exception e) {
	  e.printStackTrace() ;
	}	
	    
  }

  public void setParams(String[] params) {
    if (params != null) {
      println("params received!");
    }
  }

}