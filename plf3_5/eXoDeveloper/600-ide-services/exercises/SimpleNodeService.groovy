// simple groovy script
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.PathParam

import javax.jcr.*
import javax.jcr.query.*
import org.exoplatform.services.jcr.RepositoryService
import org.exoplatform.container.ExoContainerContext;

@Path("/nodeService")
public class SimpleNodeService {
  @GET
  @Path("jcr/{code}")
  public String hello(@PathParam("code") String code) {
    
    Credentials credentials = new SimpleCredentials("root", "gtn".toCharArray());
    
    RepositoryService repositoryService = (RepositoryService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(RepositoryService.class);
    Repository repository = repositoryService.getDefaultRepository();
    
    // get a session on workspace ”collaboration”
    Session jcrSession = repository.login(credentials, "collaboration");
    
    // get the root node of the workspace
    Node root = jcrSession.getRootNode();
    
    String rootNames = "";
    NodeIterator iterator = root.getNodes();
    while (iterator.hasNext()) {
      Node iterNode= iterator.nextNode();
       rootNames = rootNames + iterNode.getName() + " <br> ";
      }
    
    
    QueryManager qm = jcrSession.getWorkspace().getQueryManager();
    Query q = qm.createQuery("select * from exo:article", Query.SQL)
    NodeIterator ni = q.execute().getNodes();
    String names = "";
    while (ni.hasNext()) { 
      Node node = (Node) ni.next();
      System.out.println(node.getName());
      names = names + "  > " + node.getName() + " : " + node.getProperty("exo:title").getValue().getString() + "<br>";
      }   
      return "<html><body>" + names + rootNames + "<html><body>";
    }
  }

