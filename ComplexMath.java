/**
* A set of basic static functions provided for operations on Complex objects
*
* @author     William Woodruff <woodrufwsoftware @ gmail.com>
* @version    0.9
* @since      2013-3-15
*/
public class ComplexMath
{
  
  private ComplexMath()
  {

  }

  /**
  * Finds the conjugate of the given Complex.
  *
  * <p>
  * The conjugate of any Complex represented as "a + bi" is given as "a - bi".
  *
  * @param c the Complex whose conjugate is to be found.
  * @return a new Complex whose value is the conjugate of c
  */
  public static Complex conjugate(Complex c)
  {
    return new Complex(c.getReal(), -c.getImag()); 
  }
  

  /**
  * Finds the reciprocal of the given Complex.
  *
  * <p>
  * The reciprocal of any Complex represented as "a + bi" is given as "1 / (a + bi)".
  *
  * @param c the Complex whose reciprocal is to be found.
  * @return a new Complex whose value is the reciprocal of c
  */
  public static Complex reciprocal(Complex c) 
  {
    double scale = (c.getReal() * c.getReal()) + (c.getImag() * c.getImag());

    double re = c.getReal() / scale;
    double im = c.getImag() / scale;

    return new Complex(re, im);
  }
  

  /**
  * Finds the sum of the given Complexes.
  *
  * <p>
  * The sum of any two Complexes "a + bi" and "x + yi" will always be equal to
  * "(a+x) + (b+y)i".
  *
  * @param c1 the first Complex
  * @param c2 the second Complex
  * @return a new Complex whose value is the sum of c1 and c2
  */
  public static Complex add(Complex c1, Complex c2)
  {
    double re = c1.getReal() + c2.getReal();
    double im = c1.getImag() + c2.getImag();
    return new Complex(re, im);
  }

  /**
  * Finds the sum of the given Complex and a non-complex number.
  *
  * <p>
  * The sum of any Complex ("a + bi") and any non-complex number ("x") will always
  * be equal to "(a+x) + bi"
  *
  * @param c the Complex
  * @param num the non-complex number
  * @return a new Complex whose value is the sum of c and num
  */
  public static Complex add(Complex c, double num)
  {
    return new Complex(c.getReal() + num, c.getImag());
  }
  
 /**
 * Finds the difference between two Complexes
 *
 * <p>
 * The difference between any two Complexes will be the differences between 
 * their real and imaginary parts.
 *
 * @param c1 the first Complex
 * @param c2 the second Complex
 * @return a new Complex whose value is the sum of c and num
 */
  public static Complex subtract(Complex c1, Complex c2)
  {
    double re = c1.getReal() - c2.getReal();
    double im = c1.getImag() - c2.getImag();
    return new Complex(re, im);
  }

  /**
  * Finds the difference between the given Complex and a non-complex number.
  *
  * <p>
  * The difference between any Complex and a non-complex number will always be
  * the Complex's real value minus the number, with the imaginary value unchanged.
  *
  * @param c the Complex
  * @param num the non-complex number
  * @return a new Complex whose value is the difference between c and num
  */
  public static Complex subtract(Complex c, double num)
  {
    return new Complex(c.getReal() - num, c.getImag());
  }
  
  /**
  * Finds the product of two Complexes.
  *
  * <p>
  * If a Complex is multiplied by it's conjugate Complex, the resulting value will
  * ALWAYS be real, with no imaginary part.
  * Otherwise, the resulting product will likely contain an imaginary part.
  *
  * @param c1 the first Complex
  * @param c2 the second Complex
  * @return a new Complex whose value is the product of c1 and c2
  */
  public static Complex multiply(Complex c1, Complex c2)
  {
    double re = c1.getReal() * c2.getReal() - c1.getImag() * c2.getImag();
    double im = c1.getReal() * c2.getImag() + c1.getImag() * c2.getReal();
    return new Complex(re, im);
  }

  /**
  * Finds the product of a Complex and a non-complex number.
  *
  * <p>
  * The product of a Complex and a non-complex number will always be the non-complex
  * number distributed across the Complex's real and imaginary parts.
  *
  * @param c the Complex
  * @param num the non-complex number
  * @return a new Complex whose value is the product of c and num
  */
  public static Complex multiply(Complex c, double num)
  {
    return new Complex(c.getReal() * num, c.getImag() * num);
  }
  
  /**
  * Finds the quotient of two Complexes.
  *
  * <p>
  * The quotient of two Complexes is always equal to the following:
  * The multiplication of the first Complex and the conjugate of the second Complex,
  * all divided by the the multiplication of the second Complex and it's conjugate.
  *
  * @param c1 the first Complex and the numerator
  * @param c2 the second Complex and the denominator
  * @return a new Complex whose value is the quotient of c1 over c2
  */
  public static Complex divide(Complex c1, Complex c2)
  {
    Complex conj = ComplexMath.conjugate(c2);

    Complex numerator = ComplexMath.multiply(c1, conj);
    double denominator = ComplexMath.multiply(c2, conj).toDouble();

    return ComplexMath.divide(numerator, denominator);
  }

 /**
 * Finds the quotient of a Complex and a non-complex number.
 *
 * <p>
 * The quotient of a Complex and a non-complex number will always be the non-complex
 * number distributed across the Complex's real and imaginary parts.
 *
 * @param c the Complex
 * @param num the non-complex number
 * @return a new Complex whose value is the quotient of c over num
 */
  public static Complex divide(Complex c, double num)
  {
    return new Complex(c.getReal() / num, c.getImag() / num);
  }
  
  /**
  * Finds the result of a Complex raised to a certain number
  *
  * <p>
  * @param c the Complex
  * @param raise the value that the Complex is raised to
  * @return a new Complex whose value is c to the power of raise
  */
  public static Complex pow(Complex c, int raise)
  {
    Complex ret = new Complex(c.getReal(), c.getImag());
    Complex orig = new Complex(c.getReal(), c.getImag());

    if (raise == 1) ret = new Complex(c.getReal(), c.getImag());
    
    else if (raise == 0) ret = new Complex(1, 0);
    
    else
    { 
      for (int i = 1; i < Math.abs(raise); i++)
        ret = ComplexMath.multiply(orig, ret);
    }

    if (raise < 0)
      ret = ComplexMath.divide(ComplexMath.reciprocal(ret), 1);

    return ret;
  }
  
  /**
  * Finds the magnitude of a Complex number.
  *
  * <p>
  * The magnitude (also known as the hypotenuse, rho, Z, etc) of any complex number 
  * is equal to the square root of the sum of the squares of the Complex's 
  * real and imaginary parts. 
  *
  * @param c the Complex
  * @return a double whose value is the magnitude (hypotenuse) of c
  */
  public static double mag(Complex c)
  {
    return Math.hypot(c.getReal(), c.getImag()); 
  }

  /**
  * Finds the sine of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the sine of c
  */
  public static Complex sin(Complex c)
  {
    double re = Math.sin(c.getReal()) * Math.cosh(c.getImag());
    double im = Math.cos(c.getReal()) * Math.sinh(c.getImag());

    return new Complex(re, im);
  }

  /**
  * Finds the hyperbolic sine of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the hyperbolic sine of c
  */
  public static Complex sinh(Complex c)
  {
    double re = Math.cos(c.getImag()) * Math.sinh(c.getReal());
    double im = Math.sin(c.getImag()) * Math.cosh(c.getReal());

    return new Complex(re, im);
  }

  /**
  * Finds the cosine of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the cosine of c
  */
  public static Complex cos(Complex c)
  {
    double re = Math.cos(c.getReal()) * Math.cosh(c.getImag());
    double im = Math.sin(c.getReal()) * Math.sinh(c.getImag());

    return new Complex(re, im);
  }

  /**
  * Finds the hyperbolic cosine of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the hyperbolic cosine of c
  */
  public static Complex cosh(Complex c)
  {
    double re = Math.cos(c.getImag()) * Math.cosh(c.getReal());
    double im = Math.sin(c.getImag()) * Math.sinh(c.getReal());

    return new Complex(re, im);
  }

  /**
  * Finds the tangent of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the tangent of c
  */
  public static Complex tan(Complex c)
  {
    return ComplexMath.divide(ComplexMath.sin(c), ComplexMath.cos(c));
  }

  /**
  * Finds the hyperbolic tangent of a Complex number.
  *
  * @param c the Complex
  * @return a Complex whose value is the hyperbolic tangent of c
  */
  public static Complex tanh(Complex c)
  {
    return ComplexMath.divide(ComplexMath.sinh(c), ComplexMath.cosh(c)); 
  }

}