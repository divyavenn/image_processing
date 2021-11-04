package img_tests;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


import img.Img;
import img.PPM;
import img.PPMPixel;
import img.Pixel;

/**
 * Tests the methods in the Image class
 */
public class ImgTest {
  Img littlePic;

  /**
   * Constructs an imageTest object
   */
  public ImgTest() {
    littlePic = new PPM("small", 4, 2);
    littlePic.setPixel(0, 0, new PPMPixel(110, 115, 119));
    littlePic.setPixel(0, 1, new PPMPixel(120, 125, 129));
    littlePic.setPixel(1, 0, new PPMPixel(130, 135, 139));
    littlePic.setPixel(1, 1, new PPMPixel(140, 145, 149));

    littlePic.setPixel(2, 0, new PPMPixel(150, 155, 159));
    littlePic.setPixel(2, 1, new PPMPixel(160, 165, 169));
    littlePic.setPixel(3, 0, new PPMPixel(170, 175, 179));
    littlePic.setPixel(3, 1, new PPMPixel(180, 185, 189));
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
