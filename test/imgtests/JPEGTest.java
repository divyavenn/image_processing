package imgtests;

import img.ImageType;

/**
 * Tests ImgTest specifically for JPEG type objects.
 */
public class JPEGTest extends ImgTest {
  public JPEGTest() {
    type = ImageType.jpeg;
    instantiate();
  }
}
