package img_tests;

import org.junit.Test;

import img.ImageType;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods in the Pixel class.
 */
public abstract class PixelTest {
  ImageType type;
  Pixel a;
  Pixel b;
  Pixel c;

  /**
   * Creates a PixelTestObject.
   */
  public PixelTest() {
  }

  /**
   * Instantiates pixels.
   */
  protected void instantiatePixels() {
    a = ImageType.makePixel(type, 10, 20, 30);
    b = ImageType.makePixel(type, 0, 0, 0);
    c = ImageType.makePixel(type, 255, 255, 255);
  }

  @Test
  public void testGetRed() {
    assertEquals(a.getRed(), 10);
    assertEquals(b.getRed(), 0);
    assertEquals(c.getRed(), 255);
  }

  @Test
  public void testGetBlue() {
    assertEquals(a.getBlue(), 30);
    assertEquals(b.getBlue(), 0);
    assertEquals(c.getBlue(), 255);
  }

  @Test
  public void testGetGreen() {
    assertEquals(a.getGreen(), 20);
    assertEquals(b.getGreen(), 0);
    assertEquals(c.getGreen(), 255);
  }

  @Test
  public void testGetValue() {
    assertEquals(a.getValue(), 30);
    assertEquals(b.getValue(), 0);
    assertEquals(c.getValue(), 255);
  }

  @Test
  public void testGetIntensity() {
    assertEquals(a.getValue(), 30);
    assertEquals(b.getValue(), 0);
    assertEquals(c.getValue(), 255);
  }

  @Test
  public void testgetLuma() {
    assertEquals(a.getLuma(), 19);
    assertEquals(b.getLuma(), 0);
    assertEquals(c.getLuma(), 255);
  }

}
