package img;

public abstract class Pixel {

  int r;
  int g;
  int b;


  /**
   * Constructs a Pixel object.
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   */
  public Pixel(int r, int g, int b){
    this.r = r;
    this.b = b;
    this.g = g;
  }

}
