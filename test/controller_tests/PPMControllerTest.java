package controller_tests;

import java.io.IOException;

import img.ImageType;

public class PPMControllerTest extends ImgControllerTest{
  public PPMControllerTest() throws IOException {
    type = ImageType.ppm;
    instantiate();
  }

}
