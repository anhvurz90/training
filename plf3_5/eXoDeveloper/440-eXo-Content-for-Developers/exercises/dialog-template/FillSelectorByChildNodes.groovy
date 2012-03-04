

/*********************************************************************************
Dialog Template usage hint:

String[] fieldParameters = ["jcrPath=/node/exo:language", "script=ecm-explorer/widget/FillSelectBoxWithNodeChildren.groovy","scriptParams=collaboration,/Documents", lang] ;

*********************************************************************************/

import java.util.List ;
import java.util.ArrayList ;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;

import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.core.model.SelectItemOption;

import org.exoplatform.services.cms.scripts.CmsScript;

public class FillSelectorByChildNodes  implements CmsScript {
  
  private RepositoryService repositoryService_;
  private String folder_;
  private String workspace_;
  
  public FillSelectorByChildNodes (RepositoryService repositoryService) {
    repositoryService_ = repositoryService;
  }
  
  public void execute(Object context) {
    UIFormSelectBox selectBox = (UIFormSelectBox) context;

    ManageableRepository jcrRepository = repositoryService_.getCurrentRepository();
    List options = new ArrayList();
    Session session = null;
    try{
      session = jcrRepository.login(workspace_);
      QueryManager queryManager = session.getWorkspace().getQueryManager();
      
      String xpath = "/jcr:root" + folder_ + "/*";
      println("  xpath : " + xpath);
      Query q = queryManager.createQuery(xpath, Query.XPATH);
      QueryResult result = q.execute();
      NodeIterator nodeIterator = result.getNodes();
      
      while (nodeIterator.hasNext()) {
        Node n = nodeIterator.nextNode();
        String title = n.getName();
        if (n.hasProperty("exo:title")) {title = n.getProperty("exo:title").getValue().getString()}
        options.add(new SelectItemOption(title, n.getPath()));
      }
      session.logout();
    } catch (Exception e) {
      if(session !=null) {
        session.logout();
      }
        e.printStackTrace();
    } 
    
    selectBox.setOptions(options);
  }

  public void setParams(String[] params) {
    workspace_ = params[0];
    folder_ = params[1];
    println("  workspace_ : " + workspace_);
    println("  folder_ : " + folder_);
  }

}
