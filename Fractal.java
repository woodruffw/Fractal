/* Fractal.java    Author: William Woodruff
 * Generates a Mandelbrot-based fractal set recursively.
 * Each pixel of the set is colored based on whether or not it belongs to the set.
 * Coded 3/16/13
 */

import java.awt.image.*;
import java.awt.*;

public class Fractal
{ 
  //constant allowing for a maximum of 1024 iterations
  private static final int MAX_ITERATION = 1024;
  
  //instance variables
  private BufferedImage image;
  private int size, complexity;
  private double scale;
  
  //constructors - default omitted
  
  //takes a BufferedImage to be used 
  public Fractal(BufferedImage i)
  {
    image = i;
  }
  
  //takes the size of a BufferedImage to be created
  public Fractal(int sz)
  {
    scale = 10;
    size = sz;
    complexity = 3;
    image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
  }
  
  //takes the size, scale, and complexity of a BufferedImage to be created
  public Fractal(int sz, double sc, int comp)
  {
    size = sz;
    scale = sc;
    complexity = comp;
    image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
  }
  
  //accessors
  
  //getImage
  //@return the BufferedImage associated with this instance
  public BufferedImage getImage()
  {
    if (image != null)
      return image;
    throw new NullPointerException("No image found.");
  }
  
  //getSize
  //@return the size (of each side) of the BufferedImage
  public int getSize() {return size;}
  
  //getScale
  //@return the scale of the BufferedImage
  public double getScale() {return scale;}
  
  //getComplexity
  //@return the complexity of the fractal
  public int getComplexity() {return complexity;}
  
  //mutators
  
  //setImage
  //@param the BufferedImage to be used
  public void setImage(BufferedImage pic)
  {
    image = pic; 
  }
  
  //setSize
  //@param the new size of the BufferedImage
  public void setSize(int s)
  {
    size = s; 
  }
  
  //setScale
  //@param the new scale
  public void setScale(double sc)
  {
    scale = sc; 
  }
  
  //setComplexity
  //@param the new complexity of the fractal
  public void setComplexity(int comp)
  {
    complexity = comp; 
  }
  
  //tendsToInf
  //determines whether or not a given Complex belongs to the set
  //@param the Complex being tested
  //@return true if the complex tends, false otherwise (false belongs to set)
  private boolean tendsToInf(Complex z)
  {
    return (ComplexMath.mag(z) > 2.0);
  }
  
  //checkPoint
  //recursively determines whether or not a point belongs to the set
  //@param z the Complex being tested, count the number of iterations
  //@return an integer representing the number of iterations (max 1023)
  private int checkPoint(Complex z, int count)
  {
    if (!tendsToInf(z) && count < MAX_ITERATION) //if the complex's magnitude is less than 2 AND count < 1024
    {
      return checkPoint(ComplexMath.add(ComplexMath.pow(z, complexity), z), count + 1); //recurse, with the new complex having value z^2 + z 
    }
    return count; //return the number of iterations
  }
  
  
  //changePixel
  //changes the pixel at coordinates x, y to Color c
  //@param int x, int y, Color c
  private void changePixel(int x, int y, Color c)
  {
    //exception handling
    if (image == null || c == null)
    {
      throw new RuntimeException("No image or valid color.");
    }
    //pixel @ x, y changed to RGB values of c
    image.setRGB(x, y, c.getRGB());
  }
  
  //draw
  //creates the set, calling the recursive function checkPoint
  //for each pixel on the BufferedImage
  //@return a BufferedImage containing the set
  public BufferedImage draw()
  {
    
    //for each pixel on the BufferedImage
    for (int x = 0; x < size; x++)
    {
      for (int y = 0; y < size; y++)
      {
        //x and y are scaled to the a, bi plane
        double x0 = -scale/2 + scale*x/size;
        double y0 = -scale/2 + scale*y/size;
        //new Complex using x0 and y0 as real and imag
        Complex z0 = new Complex(x0, y0); 
        int gvalue = checkPoint(z0, 0) / 5; //call to recursive function
        Color c = new Color(gvalue, ((gvalue | 27) % 255), ((gvalue & 150) % 255));
        //pixel at x, y set to Color c (based on checkPoint)
        changePixel(x, y, c);
      }
    }
    //final image returned
    return image;
  }
  
  
  
}