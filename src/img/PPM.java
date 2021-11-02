package img;

public class PPM extends Img{

  public PPM(String name, int height, int width){
    super(name, height, width);
  }

  public PPM(){
    super("", 0, 0);
  }

  @Override
  public String fileRepresentation() {
    String contents = "P3\n";
    contents  = contents + "height" + "width\n";
    contents = contents + allPixelsToString();
    return contents;
  }
}

