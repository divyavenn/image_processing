package imgtests;

import img.ImageType;

/**
 * Tests ImgTest specifically for JPEG type objects.
 */
public class PNGTest extends ImgTest {
  public PNGTest() {
    type = ImageType.png;
    instantiate();
  }
}

