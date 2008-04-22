import java.util.ArrayList;
import java.util.List;

import org.exoplatform.services.cms.scripts.CmsScript;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UIFormSelectBox;

public class FillSports implements CmsScript {

  public FillSports() {
  }

  public void execute(Object context) {
    UIFormSelectBox selectBox = (UIFormSelectBox) context;
    List options = new ArrayList();
    options.add(new SelectItemOption("foot", "foot"));
    options.add(new SelectItemOption("baseball", "foot"));
    options.add(new SelectItemOption("rugby", "foot"));
    selectBox.setOptions(options);
  }

  public void setParams(String[] params) {
  }

}
