package img;

public class PPMPixel extends Pixel{

  public PPMPixel(int r, int g, int b){
    super(r,g,b);
  }

  public PPMPixel(){
    super(255,255,255);
  }

  @Override
  public String toString(){
    return r + " " + g + " " + b + "\n";
  }
}
