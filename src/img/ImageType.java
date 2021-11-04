package img;

import controller.ImgController;
import controller.PPMController;
import model.ImgModel;
import model.PPMModel;
import view.ImgView;

/**
 * Represents the different image types this program can handle. Has methods
 * to return appopriate objects based on ImageType.
 */
public enum ImageType {
  ppm;

  public static ImgModel makeModel(ImageType type) {
    switch (type) {
      case ppm:
        return new PPMModel();
    }
    throw new IllegalArgumentException("Not a valid image type");
  }


  /**
   * Returns a controller specific to the ImageType.
   *
   * @param type the type
   * @param view the view for the controller
   * @param in   the Readable for the controller
   * @return an ImgController
   */
  public static ImgController makeController(ImageType type, ImgModel model, ImgView view,
                                             Readable in) {
    switch (type) {
      case ppm:
        return new PPMController(model, view, in);
    }
    throw new IllegalArgumentException("Not a valid image type");
  }

  /**
   * Returns a controller specific to the ImageType, automatically making an appopriate model.
   *
   * @param type the type
   * @param view the view for the controller
   * @param in   the Readable for the controller
   * @return an ImgController
   */
  public static ImgController makeController(ImageType type, ImgView view, Readable in) {
    ImgModel model = makeModel(type);
    switch (type) {
      case ppm:
        return new PPMController(model, view, in);
    }
    throw new IllegalArgumentException("Not a valid image type");
  }

  /**
   * Returns an Img Object corresponding to the implementing class.
   *
   * @param type   the type of Image
   * @param name   the name of the image
   * @param height the height of the image
   * @param width  the width of the image
   * @returns an Img Object
   */
  public static Img makeImg(ImageType type, String name, int height, int width) {
    switch (type) {
      case ppm:
        return new PPM(name, height, width);
    }
    throw new IllegalArgumentException("Not a valid image type");
  }

  /**
   * Returns a Pixel Object corresponding to the implementing class.
   *
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   * @returns a Pixel Object
   */
  public static Pixel makePixel(ImageType type, int r, int g, int b) {
    switch (type) {
      case ppm:
        return new PPMPixel(r, g, b);
    }
    throw new IllegalArgumentException("Not a valid image type");
  }


}
