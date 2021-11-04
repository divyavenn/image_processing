package img;

/**
 * Represents a Pixel in a PPM Image.
 */
public class PPMPixel extends Pixel{

  /**
   * Constructs a PPMPixel Object.
   * @param r the red value
   * @param g the green vaue
   * @param b the blue value.
   */
  public PPMPixel(int r, int g, int b){
    super(r,g,b);
  }

  /**
   * Default constructor sets Pixel to white.
   */
  public PPMPixel(){
    super(255,255,255);
  }

  @Override
  public String toString(){
    return r + " " + g + " " + b + "\n";
  }
}
