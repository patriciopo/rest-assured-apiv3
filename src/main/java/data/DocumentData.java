package data;

public class DocumentData extends ContentData {

  public DocumentData(String subject){
    this.subject = subject;
    this.type = "document";
    this.content = new ContentBodyData();
  }

}
