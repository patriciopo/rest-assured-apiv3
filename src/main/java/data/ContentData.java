package data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentData {
  //readonly:
  public String id;
  public String author;
  public Boolean followed;
  public Long lastActivity; // Timestamp of the last time the content was viewed
  public String parent;
  public String published;
  public String updated;

  //required:
  @JsonProperty
  public ContentBodyData content;
  public String subject;
  public String type;
  public Integer typeCode;
  public String status;

}
