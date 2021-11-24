package model;

import java.io.IOException;
import java.util.ArrayList;

import img.FileType;
import img.Img;
import img.Pixel;

/**
 * Represents the backend operations processing an Image.
 */
public class ImgModelImplementation implements ImgModel {

  ArrayList<Img> images;


  /**
   * Constructs an ImageModelObject.
   */
  public ImgModelImplementation() {
    images = new ArrayList<>();
  }


  @Override
  public Img getImage(String imageName) {
    //the Img equals method is overridden to only compare name, so it does not matter what the
    // type is
    if (images.contains(new Img(imageName, 0, 0))) {
      return images.get(images.indexOf(new Img(imageName, 0, 0)));
    } else {
      throw new IllegalArgumentException("Image not in list");
    }
  }


  @Override
  public void load(String filePath, String destinationImageName)
          throws IllegalArgumentException {
    FileType type = FileType.getCorrectFileType(filePath);
    if (type != null) {
      Img destinationImage = type.makeImgFromFile(filePath, destinationImageName);
      images.add(destinationImage);
    }
  }

  @Override
  public void save(String filePath, String targetImageName) throws IOException {
    Img targetImage;
    if (targetImageName == null || filePath == null) {
      throw new IOException("");
    }
    try {
      targetImage = getImage(targetImageName);
    } catch (Exception e) {
      throw new IOException("");
    }
    targetImage.save(filePath);
  }


  @Override
  public void exportComponentByPixel(Command command, String imageName,
                                     String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    } catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    int value = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        switch (command) {
          case rc:
            value = targetImage.getPixel(i, j).getRed();
            break;
          case gc:
            value = targetImage.getPixel(i, j).getGreen();
            break;
          case bc:
            value = targetImage.getPixel(i, j).getBlue();
            break;
          case lc:
            value = targetImage.getPixel(i, j).getLuma();
            break;
          case vc:
            value = targetImage.getPixel(i, j).getValue();
            break;
          case ic:
            value = targetImage.getPixel(i, j).getIntensity();
            break;
          default:
            break;
        }
        destinationImage.setPixel(i, j, value, value, value);
      }
    }
    if (imageName.equals(destinationImageName)) {
      images.remove(targetImage);
    }
    images.add(destinationImage);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    } catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    Pixel targetPixel;
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        targetPixel = targetImage.getPixel(i, j);
        int r = targetPixel.getRed();
        int g = targetPixel.getGreen();
        int b = targetPixel.getBlue();
        destinationImage.setPixel(i, j, r + increment, g + increment, b + increment);
      }
    }
    if (imageName.equals(destinationImageName)) {
      images.remove(targetImage);
    }
    images.add(destinationImage);
  }

  @Override
  public void flip(Command command, String imageName,
                   String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    } catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    Pixel targetPixel;
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        targetPixel = targetImage.getPixel(i, j);
        int r = targetPixel.getRed();
        int g = targetPixel.getGreen();
        int b = targetPixel.getBlue();
        if (command.equals(Command.vflip)) {
          destinationImage.setPixel(height - i - 1, j, r, g, b);
        } else if (command.equals(Command.hflip)) {
          destinationImage.setPixel(i, width - j - 1, r, g, b);
        }
      }
    }
    if (imageName.equals(destinationImageName)) {
      images.remove(targetImage);
    }
    images.add(destinationImage);
  }

  /**
   * Copies all of image's attributes except name.
   *
   * @param fromImage    the image you wish to copy
   * @param newImageName name of copy
   * @return the copy of the image
   */
  protected Img copyImage(Img fromImage, String newImageName) {
    int height = fromImage.getHeight();
    int width = fromImage.getWidth();
    Img copy = new Img(newImageName, height, width);
    return copy;
  }

  @Override
  public void applyFilter(double[][] filter, String imageName, String destinationImageName) {
    if (filter.length != filter[0].length) {
      if (filter.length % 2 == 0) {
        throw new IllegalArgumentException("Filter must be square array of odd length");
      }
    }

    //filter being passed correctly
    int center = (int) Math.floor(filter.length / 2);
    Img img = getImage(imageName);
    Img destinationImage = copyImage(img, destinationImageName);
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel targetPixel;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double filteredRed = 0;
        double filteredGreen = 0;
        double filteredBlue = 0;
        for (int x = 0; x < filter.length; x++) {
          for (int y = 0; y < filter.length; y++) {
            int xDelta = center - x;
            int yDelta = center - y;
            targetPixel = img.getPixel(i - xDelta, j - yDelta);
            if (targetPixel != null) {
              //System.out.println(targetPixel.getRed()+ " * " + filter[x][y]);
              filteredRed = filteredRed + targetPixel.getRed() * filter[x][y];
              filteredBlue = filteredBlue + targetPixel.getBlue() * filter[x][y];
              filteredGreen = filteredGreen + targetPixel.getGreen() * filter[x][y];
            }
          }
        }
        //System.out.println("r: " +  filteredRed + ", g: " + filteredGreen + ", b: " +
        // filteredBlue);
        destinationImage.setPixel(i, j, (int) Math.round(filteredRed), (int) Math.round(filteredGreen),
                (int) Math.round(filteredBlue));
      }
    }
    if (imageName.equals(destinationImageName)) {
      images.remove(img);
    }
    images.add(destinationImage);
  }

  @Override
  public void applyColorTransformation(double[][] matrix, String imageName,
                                       String destinationImageName) {
    if (matrix.length != matrix[0].length) {
      if (matrix.length != 3) {
        throw new IllegalArgumentException("Filter must be square array of length 3");
      }
    }
    Img img = getImage(imageName);
    Img destinationImage = copyImage(img, destinationImageName);
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel targetPixel;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        targetPixel = img.getPixel(i, j);
        int red = targetPixel.getRed();
        int green = targetPixel.getGreen();
        int blue = targetPixel.getBlue();
        int newRed = (int) (red * matrix[0][0] + green * matrix[0][1] + blue * matrix[0][2]);
        int newGreen = (int) (red * matrix[1][0] + green * matrix[1][1] + blue * matrix[2][2]);
        int newBlue = (int) (red * matrix[2][0] + green * matrix[2][1] + blue * matrix[2][2]);
        destinationImage.setPixel(i, j, newRed, newGreen, newBlue);
      }
    }
    if (imageName.equals(destinationImageName)) {
      images.remove(img);
    }
    images.add(destinationImage);
  }


}
