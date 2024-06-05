package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.language.expressions.Variable;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ParseExpressionTreeTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}
	//binary tests
	@Test
	public void additionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y");
		assertEquals(new Addition(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x+y");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Addition(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Addition(new Addition(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void subtractionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x - y");
		assertEquals(new Subtraction(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x-y");
		assertEquals(new Subtraction(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Subtraction(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Subtraction(new Subtraction(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void multiplicationExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x * y");
		assertEquals(new Multiplication(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x*y");
		assertEquals(new Multiplication(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] * y");
		assertEquals(new Multiplication(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x * y * [ -.51, 0, 1]");
		assertEquals(new Multiplication(new Multiplication(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void divisionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x / y");
		assertEquals(new Division(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x/y");
		assertEquals(new Division(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] / y");
		assertEquals(new Division(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x / y / [ -.51, 0, 1]");
		assertEquals(new Division(new Division(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void exponentiateExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x ^ y");
		assertEquals(new Exponentiate(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x^y");
		assertEquals(new Exponentiate(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] ^ y");
		assertEquals(new Exponentiate(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x ^ y ^ [ -.51, 0, 1]");
		assertEquals(new Exponentiate(new Exponentiate(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void moduloExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x % y");
		assertEquals(new Modulo(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x%y");
		assertEquals(new Modulo(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] % y");
		assertEquals(new Modulo(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x % y % [ -.51, 0, 1]");
		assertEquals(new Modulo(new Modulo(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	//paren test	
	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Addition(new X(), new Addition(new Y(), new RGBColor(1, 1, 1))), e);
	}
	//unary tests
	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("floor(x) + y");
		assertEquals(new Addition(new Floor(new X()), new Y()), e);
	}
		
	@Test
	public void absFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos(x)");
		assertEquals(new Cosine(new X()), e);
		
		e = parser.makeExpression("cos(x+y)");
		assertEquals(new Cosine(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("cos(x) + y");
		assertEquals(new Addition(new Cosine(new X()), new Y()), e);
		
	}
	
	@Test
	public void tangentFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("tan( x )");
		assertEquals(new Tangent(new X()), e);
		
		e = parser.makeExpression("tan( x + y )");
		assertEquals(new Tangent(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("tan(x) + y");
		assertEquals(new Addition(new Tangent(new X()), new Y()), e);
		
	}


	@Test
	public void exponentFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("exp( x )");
		assertEquals(new Exponent(new X()), e);

		e = parser.makeExpression("exp( x + y )");
		assertEquals(new Exponent(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("exp(x) + y");
		assertEquals(new Addition(new Exponent(new X()), new Y()), e);
	}
	
	@Test
	public void imageWrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"beholder.jpg\", x + x, y)");
		assertEquals(new ImageWrap("beholder.jpg", new Addition(new X(), new X()), new Y()), e);

		e = parser.makeExpression("imageWrap(\"vortex.jpg\", x-x, y+y)");
		assertEquals(new ImageWrap("vortex.jpg",new Subtraction(new X(), new X()), new Addition(new Y(), new Y())), e);
		
		e = parser.makeExpression("imageWrap(\"vortex.jpg\", x*x, y/y)");
		assertEquals(new ImageWrap("vortex.jpg",new Multiplication(new X(), new X()), new Division(new Y(), new Y())), e);
	
	}

	@Test
	public void ceilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);

		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("ceil(x) + y");
		assertEquals(new Addition(new Ceil(new X()), new Y()), e);
	}
	
	@Test
	public void clampFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("clamp( x )");
		assertEquals(new Clamp(new X()), e);

		e = parser.makeExpression("clamp( x + y )");
		assertEquals(new Clamp(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("clamp(x) + y");
		assertEquals(new Addition(new Clamp(new X()), new Y()), e);
	}
	
	@Test
	public void wrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("wrap( x )");
		assertEquals(new Wrap(new X()), e);

		e = parser.makeExpression("wrap( x + y )");
		assertEquals(new Wrap(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("wrap(x) + y");
		assertEquals(new Addition(new Wrap(new X()), new Y()), e);
	}
	
	@Test
	public void sineFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("sin( x )");
		assertEquals(new Sine(new X()), e);

		e = parser.makeExpression("sin( x + y )");
		assertEquals(new Sine(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("sin(x) + y");
		assertEquals(new Addition(new Sine(new X()), new Y()), e);
	}
	
	@Test
	public void cosFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos( x )");
		assertEquals(new Cosine(new X()), e);

		e = parser.makeExpression("cos( x + y )");
		assertEquals(new Cosine(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("cos(x) + y");
		assertEquals(new Addition(new Cosine(new X()), new Y()), e);
	}
	
	@Test
	public void aTanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("atan( x )");
		assertEquals(new Atan(new X()), e);

		e = parser.makeExpression("atan( x + y )");
		assertEquals(new Atan(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("atan(x) + y");
		assertEquals(new Addition(new Atan(new X()), new Y()), e);
	}
	
	@Test
	public void LogFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("log( x )");
		assertEquals(new Log(new X()), e);

		e = parser.makeExpression("log( x + y )");
		assertEquals(new Log(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("log(x) + y");
		assertEquals(new Addition(new Log(new X()), new Y()), e);
	}
	
	@Test
	public void rgbToYCrCbFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("rgbToYCrCb( x )");
		assertEquals(new RgbToYCrCb(new X()), e);

		e = parser.makeExpression("rgbToYCrCb( x + y )");
		assertEquals(new RgbToYCrCb(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("rgbToYCrCb(x) + y");
		assertEquals(new Addition(new RgbToYCrCb(new X()), new Y()), e);
	}
	
	
	@Test
	public void yCrCbToRGBFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("yCrCbToRGB( x )");
		assertEquals(new YCrCbToRGB(new X()), e);

		e = parser.makeExpression("yCrCbToRGB( x + y )");
		assertEquals(new YCrCbToRGB(new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("yCrCbToRGB(x) + y");
		assertEquals(new Addition(new YCrCbToRGB(new X()), new Y()), e);
	}

	//Order of operations tests
	@Test
	public void orderOfOperationsTest() {
		ExpressionTreeNode e = parser.makeExpression("(x + y * x)");
		assertEquals(new Addition(new X(), new Multiplication (new Y(), new X())), e);
		
		e = parser.makeExpression("(y * x + y)");
		assertEquals(new Addition(new Multiplication (new Y(), new X()), new Y()), e);
		
		e = parser.makeExpression("(x + y / x)");
		assertEquals(new Addition(new X(), new Division (new Y(), new X())), e);
		
		e = parser.makeExpression("(y / x + y)");
		assertEquals(new Addition(new Division (new Y(), new X()), new Y()), e);
		
		e = parser.makeExpression("(y * x - y)");
		assertEquals(new Subtraction(new Multiplication (new Y(), new X()), new Y()), e);
		
		e = parser.makeExpression("(x - y / x)");
		assertEquals(new Subtraction(new X(), new Division (new Y(), new X())), e);
				
	}
	
	//Assignment tests
	@Test
	public void AssignmentTest() {
		ExpressionTreeNode e = parser.makeExpression("a = (y * x)");
		assertEquals(new Assignment(new Variable("a"), new Multiplication (new Y(), new X())), e);
		
		e = parser.makeExpression("a = (x + y)");
		assertEquals(new Assignment(new Variable("a"), new Addition(new X(), new Y())), e);
		
		e = parser.makeExpression("a = x");
		assertEquals(new Assignment(new Variable("a"), new X()), e);
		
		e = parser.makeExpression("a = (y / x)");
		assertEquals(new Assignment(new Variable("a"), new Division(new Y(), new X())), e);
				
	}
	
	@Test

	public void combineTests() {
		ExpressionTreeNode e = parser.makeExpression("combine(x, y)");
		assertEquals(new Combine(new X(), new Y()),e);
	}
	
	@Test
	public void imageClipFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"beholder.jpg\", x + x, y)");

		assertEquals(new ImageClip("beholder.jpg", new Addition(new X(), new X()), new Y()), e);
		
		e = parser.makeExpression("imageClip(\"vortex.jpg\", x-x, y+y)");
		assertEquals(new ImageClip("vortex.jpg",new Subtraction(new X(), new X()), new Addition(new Y(), new Y())), e);
		
		e = parser.makeExpression("imageClip(\"vortex.jpg\", x*x, y/y)");
		assertEquals(new ImageClip("vortex.jpg",new Multiplication(new X(), new X()), new Division(new Y(), new Y())), e);
	
	}
	
	@Test
	public void perlinBWFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("perlinBW(x + x, y)");

		assertEquals(new PerlinBW(new Addition(new X(), new X()), new Y()), e);
		
		e = parser.makeExpression("perlinBW(x-x, y+y)");
		assertEquals(new PerlinBW(new Subtraction(new X(), new X()), new Addition(new Y(), new Y())), e);
		
		e = parser.makeExpression("perlinBW(x*x, y/y)");
		assertEquals(new PerlinBW(new Multiplication(new X(), new X()), new Division(new Y(), new Y())), e);
	
	}
	
	@Test
	public void perlinColorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("perlinColor(x + x, y)");

		assertEquals(new PerlinColor(new Addition(new X(), new X()), new Y()), e);
		
		e = parser.makeExpression("perlinColor(x-x, y+y)");
		assertEquals(new PerlinColor(new Subtraction(new X(), new X()), new Addition(new Y(), new Y())), e);
		
		e = parser.makeExpression("perlinColor(x*x, y/y)");
		assertEquals(new PerlinColor(new Multiplication(new X(), new X()), new Division(new Y(), new Y())), e);
	
	}
	
}
