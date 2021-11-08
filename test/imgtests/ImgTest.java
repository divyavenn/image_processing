package imgtests;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


import img.ImageType;
import img.Img;
import img.Pixel;

/**
 * Tests the methods in the Image class.
 */
public abstract class ImgTest {
  Img littlePic;
  ImageType type;


  /**
   * Instantiates class variables based on type.
   */
  protected void instantiate() {
    littlePic = ImageType.makeImg(type, "small", 4, 2);
    littlePic.setPixel(0, 0, new Pixel(110, 115, 119));
    littlePic.setPixel(0, 1, new Pixel(120, 125, 129));
    littlePic.setPixel(1, 0, new Pixel(130, 135, 139));
    littlePic.setPixel(1, 1, new Pixel(140, 145, 149));

    littlePic.setPixel(2, 0, new Pixel(150, 155, 159));
    littlePic.setPixel(2, 1, new Pixel(160, 165, 169));
    littlePic.setPixel(3, 0, new Pixel(170, 175, 179));
    littlePic.setPixel(3, 1, new Pixel(180, 185, 189));
  }

  @Test
  public void testToString() {
    assertEquals(littlePic.toString(), "small");
  }

  @Test
  public void testSetPixel() {
    assertEquals(littlePic.getPixel(0, 0).getRed(), 110);
    assertEquals(littlePic.getPixel(0, 0).getGreen(), 115);
    assertEquals(littlePic.getPixel(0, 0).getBlue(), 119);
  }

  @Test
  public void testGetPixel() {
    Pixel gotPixel = littlePic.getPixel(0, 0);
    assertEquals(gotPixel.getRed(), 110);
    assertEquals(gotPixel.getGreen(), 115);
    assertEquals(gotPixel.getBlue(), 119);
  }

  @Test
  public void testGetHeight() {
    assertEquals(littlePic.getHeight(), 4);
  }

  @Test
  public void testGetWidth() {
    assertEquals(littlePic.getWidth(), 2);
  }

}