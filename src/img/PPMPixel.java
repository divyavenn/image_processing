package img;

public class PPMPixel extends Pixel{

  public PPMPixel(int r, int g, int b){
    super(r,g,b);
  }

  @Override
  public String toString(){
    return r + "\n" + g + "\n" + b + "\n";
  }
}
