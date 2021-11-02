package model_tests;

import org.junit.Test;

import java.io.IOException;

import img.Img;
import img.PPM;
import img.PPMPixel;
import img.Pixel;

public class ImgModelTest {
  private String fullPath(String name){
    return "image_processing/images/" + name;
  }
  Img littlePic;

  public ImgModelTest() throws IOException {
    littlePic = new PPM("small", 4, 2);
    littlePic.setPixel(0, 0, new PPMPixel(110, 115, 119));
    littlePic.setPixel(0, 1, new PPMPixel(120, 125, 129));
    littlePic.setPixel(0, 2, new PPMPixel(130, 135, 139));
    littlePic.setPixel(0, 3, new PPMPixel(140, 145, 149));

    littlePic.setPixel(1, 0, new PPMPixel(150, 155, 159));
    littlePic.setPixel(1, 1, new PPMPixel(160, 165, 169));
    littlePic.setPixel(1, 2, new PPMPixel(170, 175, 179));
    littlePic.setPixel(1, 3, new PPMPixel(180, 185, 189));
    littlePic.save("image_processing/res/littlePic.ppm");
  }

  protected boolean contentsMatch(Pixel[][] a, Pixel[][]b){
    boolean same = true;
    if (a.length == b.length && a[0].length == b[0].length){
      for (int i = 0; i < a.length; i++){
        for (int j = 0; j < a[0].length; j++) {
          same = same && a[i][j].equals(b[i][j]);
        }
      }
      return same;
    }
    return false;
  }


  
  
}
