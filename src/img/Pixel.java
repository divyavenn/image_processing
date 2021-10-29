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

  /**
   * Brightens or darkens pixel by corresponding increment
   * @param increment
   */
  public void brighten(int increment){
    r = Math.min(Math.max(r + increment,0), 255);
    g = Math.min(Math.max(g + increment,0), 255);
    b = Math.min(Math.max(b + increment,0), 255);
  }
}
