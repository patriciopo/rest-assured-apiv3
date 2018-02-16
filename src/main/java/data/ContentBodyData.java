package data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentBodyData {
  //required:
  public String text = "<body><p>Some interesting text</p></body>";
  public Boolean editable;

  //optional:
  public String type = "text/html";
  public String id;
}
