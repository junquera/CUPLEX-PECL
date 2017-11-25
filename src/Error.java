public class Error {

  private String text;
  private int column;
  private int line;

  public Error(String text, int column, int line){
    this.text = text;
    this.column = column;
    this.line = line;
  }

  public String getText(){
    return this.text;
  }

  public int getColumn(){
    return this.column;
  }

  public int getLine(){
    return this.line;
  }

  public void setText(String text){
    this.text = text;
  }

  public void setColumn(int column){
    this.column = column;
  }

  public void setLine(int line){
    this.line = line;
  }
}
