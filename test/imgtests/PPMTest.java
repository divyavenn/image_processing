package imgtests;

import img.ImageType;

/**
 * Tests specifically PPM objects.
 */
public class PPMTest extends ImgTest {
  public PPMTest() {
    type = ImageType.ppm;
    instantiate();
  }
}