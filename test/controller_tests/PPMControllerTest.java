package controller_tests;

import java.io.IOException;

import img.ImageType;

/**
 * Tests specifically PPM Controller Methods
 */
public class PPMControllerTest extends ImgControllerTest {
  public PPMControllerTest() throws IOException {
    type = ImageType.ppm;
    instantiate();
  }

}
