// simple groovy script
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.PathParam

import org.exoplatform.forum.service.ForumService;
import org.exoplatform.container.ExoContainerContext;

@Path("/bike-service")
public class BikeService {
  
  ForumService forumService_ = null;
  public BikeService(ForumService forumService){forumService_ = forumService;}
  
  @GET
  @Path("price/{id}")
  public String getPrice(@PathParam("id") String id) {
    return "654,30 Euros";
  }
  @GET
  @Path("model/{id}")
  public String getModel(@PathParam("id") String id) {
    return "folding";
  }
  
  @GET
  @Path("forum")
  public String testForum() {
    ForumService forumServiceVar = (ForumService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(ForumService.class) ;
    return "Hello  " + forumServiceVar.toString() + " dependency injection service: " + forumService_.toString();
  }
}