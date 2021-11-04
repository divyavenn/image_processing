package img_tests;

import img.ImageType;

public class PPMPixelTest extends PixelTest {
  /**
   * Creates a PPMPixelTestObject and instantiates three PPM pixels.
   */
  public PPMPixelTest() {
    type = ImageType.ppm;
    instantiatePixels();
  }
}
