package view;

import java.awt.*;

import javax.swing.*;

import img.Img;
import img.Pixel;

class Histogram extends JPanel {
  private int[] redCoords;
  private int[] blueCoords;
  private int[] greenCoords;
  private int[] intensityCoords;
  private int startX = 0;
  private int startY = 0;
  private int unitX = 4;
  private int endX = 255*unitX;
  private int endY = 255;
  private int unitY = 1;
  private int prevX = startX;
  private int prevY = endY;

  public Histogram(Img img) {
    Pixel pixel;
    int height = img.getHeight();
    int width = img.getWidth();
    /**
    redCoords = new int[]{1,2,3,4};
    blueCoords = new int[]{2,5,6,8};
    greenCoords = new int[]{3,15,8,9};
    intensityCoords = new int[]{2,13,7,10};
     **/
    int max = 0;
    redCoords = new int[256];
    blueCoords = new int[256];
    greenCoords = new int[256];
    intensityCoords = new int[256];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        pixel = img.getPixel(y, x);
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int intensity = pixel.getIntensity();
        redCoords[r] = redCoords[r] + 1;
        blueCoords[b] = blueCoords[b] + 1;
        greenCoords[g] = greenCoords[g] + 1;
        intensityCoords[intensity] = intensityCoords[intensity] + 1;
        max = Math.max(intensityCoords[intensity],
                Math.max(greenCoords[g],
                Math.max(blueCoords[b],
                Math.max(max,redCoords[r]))));
      }
    }
    unitY = (int)(255/max);
    this.setBounds(0,0,500,250);
    repaint();
  }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //We draw in the following 2 loops the grid so it's visible what I explained before about each "unit"
    g2d.setColor(Color.lightGray);

    for (int i = startY; i <= endY; i += unitY) {
      g2d.drawLine(startX, i, endX, i);
    }

    //We draw the axis here instead of before because otherwise they would become blue colored.
    g2d.setColor(Color.BLACK);
    g2d.drawLine(startX, startY, startX, endY);
    g2d.drawLine(startX, endY, endX, endY);

    //We draw each of our coords in red color
    g2d.setColor(Color.RED);
    for (int r : redCoords) {
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (r * unitY));
    }

    resetBaseCoords();

    g2d.setColor(Color.BLUE);
    for (int b : blueCoords) {
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (b * unitY));
    }

    resetBaseCoords();

    g2d.setColor(Color.GREEN);
    for (int gr : greenCoords) {
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (gr * unitY));
    }

    resetBaseCoords();

    g2d.setColor(Color.DARK_GRAY);
    for (int intensity : intensityCoords) {
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (intensity * unitY));
    }
  }


  private void resetBaseCoords(){
    prevX = startX;
    prevY = endY;
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(endX + 100, endY + 100);
  }
}