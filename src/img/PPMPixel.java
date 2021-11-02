package img;

public class PPMPixel extends Pixel{

  public PPMPixel(int r, int b, int g){
    super(r,b,g);
  }

  @Override
  public String toString(){
    return r + "\n" + b + "\n" + g + "\n";
  }
}
