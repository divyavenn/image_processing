package view;

import java.awt.*;

import javax.swing.*;

import img.Img;
import img.Pixel;

class HistogramPanel extends JPanel {
  private int[] redCoords;
  private int[] blueCoords;
  private int[] greenCoords;
  private int[] intensityCoords;

  int max;


  private final int graphHeight = 500;
  private final int graphWidth = 300;

  private double unitX = 1;
  private double unitY = 1;

  private int startX = 10;
  private int startY = 500;

  private int endX = startX + 300;
  private int endY = 0;

  private int prevX = startX;
  private int prevY = startY;


  boolean hasStarted;

  public HistogramPanel() {
     redCoords = new int[]{1,2,3,4};
     blueCoords = new int[]{2,5,6,8};
     greenCoords = new int[]{3,15,8,9};
     intensityCoords = new int[]{2,13,7,10};
    this.setBackground(Color.LIGHT_GRAY);
    hasStarted = false;
    this.setMinimumSize(new Dimension(endX, endY));
    max = endY;
  }


  public int getWidth(){
    return endX + 10;
  }
  public void setImage(Img img){
    Pixel pixel;
    int height = img.getHeight();
    int width = img.getWidth();
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
    System.out.println("MAX:" + max);
    hasStarted = true;
    repaint();
  }

  @Override
  public void paint(Graphics g) {
    setUnits();
    super.paint(g);
    System.out.println(redCoords.length);
    Graphics2D g2d = (Graphics2D) g;


    if (hasStarted) {
      g2d.setColor(Color.RED);
      g2d.fill(new Rectangle(100, 100));
    }

    //We draw in the following 2 loops the grid so it's visible what I explained before about each "unit"
    //We draw the axis here instead of before because otherwise they would become blue colored.
    g2d.setColor(Color.BLACK);
    g2d.drawLine(startX, startY, startX, endY);
    g2d.drawLine(startX, endY, endX, endY);

    System.out.println("RED LINE");
    //We draw each of our coords in red color
    g2d.setColor(Color.RED);
    for (int r : redCoords) {
      System.out.println(r);
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = startY-(int)(Math.round(r*unitY)));
      System.out.println(prevY);
    }

    resetBaseCoords();
    System.out.println("BLUE LINE");
    g2d.setColor(Color.BLUE);
    for (int b : blueCoords) {
      System.out.println(b);
      System.out.println(prevY);
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = startY-(int)(Math.round(b * unitY)));
    }

    resetBaseCoords();
    System.out.println("GREEN LINE");

    g2d.setColor(Color.GREEN);
    for (int gr : greenCoords) {
      System.out.println(gr);
      System.out.println(prevY);
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = startY-(int)(Math.round(gr * unitY)));
    }

    resetBaseCoords();

    System.out.println("INTENSITY LINE");
    g2d.setColor(Color.DARK_GRAY);
    for (int intensity : intensityCoords) {
      System.out.println(intensity);
      System.out.println(prevY);
      g2d.drawLine(prevX, prevY, prevX += unitX, prevY = startY-(int)(Math.round(intensity * unitY)));
    }
    resetBaseCoords();
  }


  private void resetBaseCoords(){
    prevX = startX;
    prevY = startY;
  }


  private void setUnits(){
    unitX = (double)graphWidth/(double)redCoords.length;
    System.out.println("Set unit X: " + unitX);
    unitY = (double)graphHeight/(double)max;
    System.out.println("Set unit y: " + unitY);
  }
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(getWidth(), endY + 100);
  }
}