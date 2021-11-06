package img;

public class PNGPixel extends Pixel{
  /**
   * Constructs a Pixel object.
   *
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   */
  public PNGPixel(int r, int g, int b) {
    super(r, g, b);
  }

  @Override
  public String toString() {
    return r + " " + g + " " + b + "\n";
  }
}
