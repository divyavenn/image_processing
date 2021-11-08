package modeltests;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import img.ImageType;
import img.Img;
import model.ImgModel;
import model.JPEGModel;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for JPEGModel
 */
public class JPEGModelTest {
  Img littlePic;
  JPEGModel model;
  ImageType type;

  @Before
  public void init() {

  }

  @Test
  public void testIsCorrectFileType() {
    model = new JPEGModel();
    System.out.println(ImageType.getCorrectFileType("C:\\Users\\Brandon\\Desktop\\OOD\\img_processing\\image_processing\\res\\HappyFace\\HappyFace.jpeg"));


  }
}
