package imgtests;

import img.ImageType;

/**
 * Tests PPMPixel objects.
 */
public class PPMPixelTest extends PixelTest {
  /**
   * Creates a PPMPixelTestObject and instantiates three PPM pixels.
   */
  public PPMPixelTest() {
    type = ImageType.ppm;
    instantiatePixels();
  }
}
