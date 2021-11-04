package img;

/**
 * Represents a single Pixel in an Image.
 */
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
  public Pixel(int r, int  g, int b){
    this.r = r;
    this.b = b;
    this.g = g;
  }

  /**
   * Get the red component of this Pixel.
   */
  public int getRed(){
    return this.r;
  }

  /**
   * Get the blue component of this Pixel.
   */
  public int getBlue(){
    return this.b;
  }

  /**
   * Get the green component of this Pixel.
   */
  public int getGreen(){
    return this.g;
  }

  /**
   * Get value of this Pixel.
   */
  public int getValue(){
    return Math.max(r,Math.max(g,b));
  }

  /**
   * Get intensity of this Pixel.
   */
  public int getIntensity(){
    double avg = (r + g + b)/3.0;
    return (int)(Math.ceil(avg));
  }

  /**
   * Get luma of this Pixel.
   */
  public int getLuma(){
    double w_sum = 0.2126*r + 0.7152*g + 0.0722*b;
    return (int)(Math.ceil(w_sum));
  }

}
