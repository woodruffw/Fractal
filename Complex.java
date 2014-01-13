/**
* The Complex class represents a complex number, in a + bi format.
* 
* @author     William Woodruff <woodrufwsoftware @ gmail.com>
* @version    0.9
* @since      2013-3-15
*/
public class Complex
{

  //instance variables
  private double real, imag;
  
  /**
  * Default constructor.
  */
  public Complex()
  {
    real = 0.0;
    imag = 0.0;
  }
  
  /**
  * Constructor taking inital real and imaginary values for the Complex instance
  * 
  * @param re the real part
  * @param im the imaginary part
  */
  public Complex(double re, double im)
  {
    real = re;
    imag = im;
  }
  
  /**
  * Returns the real component of the Complex object.
  *
  * @return the double value associated with the real component
  */
  public double getReal() {return real;}

  /**
  * Returns the imaginary component of the Complex object.
  *
  * @return the double value associated with the imaginary component
  */
  public double getImag() {return imag;}
  
  /**
  * Sets the real component of the Complex object.
  *
  * @param re the value to be used in the real component
  */
  public void setReal(double re) {real = re;}

  /**
  * Sets the imaginary component of the Complex object.
  *
  * @param im the value to be used in the imaginary component
  */
  public void setImag(double im) {imag = im;}

  //conversion functions
  
  /**
  * Converts the Complex into an integer, if possible.
  *
  * @throws ComplexConversionException if the Complex cannot be converted 
  * @return an int equal to the real component of the Complex
  */
  public int toInt()
  {
    if (this.getImag() != 0)
      throw new ComplexConversionException();
    else
      return (int) (this.getReal());
  }
  
  /**
  * Converts the Complex into an float, if possible.
  *
  * @throws ComplexConversionException if the Complex cannot be converted 
  * @return a float equal to the real component of the Complex
  */
  public float toFloat()
  {
    if (this.getImag() != 0)
      throw new ComplexConversionException();
    return (float) (this.getReal());
  }
  
  /**
  * Converts the Complex into an double, if possible.
  *
  * @throws ComplexConversionException if the Complex cannot be converted 
  * @return a double equal to the real component of the Complex
  */
  public double toDouble()
  {
    if (this.getImag() != 0)
      throw new ComplexConversionException();
    else
      return (this.getReal());
  }
  
  /**
  * Compares this Complex instance to another, checking for equality.
  * 
  * <p>
  * Two Complexes are considered equal if they meet two conditions:
  * Both must have equal real parts AND imaginary parts.
  *
  * @return true if the Complexes are equal, false otherwise
  */
  public boolean equals(Complex c)
  {
    return (c.getReal() == this.getReal() && c.getImag() == this.getImag());
  }
  
  /**
  * Creates a String that describes the Complex object in a + bi format.
  *
  * @return a String containing the real and imaginary parts in a + bi format
  */
  public String toString()
  {
    return real + " + " + imag + "i\n"; 
  }
  
}