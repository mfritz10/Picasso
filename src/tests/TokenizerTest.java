package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ParseException;
import picasso.parser.Tokenizer;
import picasso.parser.tokens.*;
import picasso.parser.tokens.chars.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

public class TokenizerTest {

	Tokenizer tokenizer;
	List<Token> tokens;

	@BeforeEach
	public void setUp() throws Exception {
		tokenizer = new Tokenizer();
	}
	
	/**
	 * Test that parsing an expression with a comment works
	 */
	@Test
	public void testTokenizeComment() {
		String expression = "x // this is a comment";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(1, tokens.size());
		
		expression = "// everything is a comment";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(0, tokens.size());
	}

	/**
	 * Test that parsing a constant works
	 */
	@Test
	public void testTokenizeConstant() {
		String expression = ".324";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(.324), tokens.get(0));

		expression = "-1";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1), tokens.get(0));

		// No problems here; problem will be in next step (Semantic Analysis)
		expression = "-1.2";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1.2), tokens.get(0));
	}

	@Test
	public void testTokenizeColor() {
		String expression = "[1, 1, 1]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(1, 1, 1), tokens.get(0));

		expression = "[-1, 0, .5]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(-1, 0, .5), tokens.get(0));
	}

	@Test
	public void testTokenizeInvalidColor() {
		String expression = "[1, 1.0001, 1]";

		assertThrows(ParseException.class, () -> {
			tokens = tokenizer.parseTokens(expression);
		});
	}

	@Test
	public void testTokenizeBasicFunctionExpression() {
		String expression = "floor(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new FloorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}

	@Test
	public void testTokenizeCombinedFunctionExpression() {
		String expression = "perlinColor(floor(x), y)";
		List<Token> tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...

		expression = "sin(perlinColor(x, y))";
		tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
	}
	//binary tests
	// TODO: Test arithmetic (rather than function-based) expressions ...
	@Test
	public void testTokenizeAddition() {
		String expression = "x + y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	
	@Test
	public void testTokenizeSubtraction() {
		String expression = "x - y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new MinusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	
	@Test
	public void testTokenizeMuiltiplication() {
		String expression = "x * y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new TimesToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	
	@Test
	public void testTokenizeDivision() {
		String expression = "x / y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new DivideToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	
	@Test
	public void testTokenizeExponentiate() {
		String expression = "x ^ y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ExponentiateToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	
	@Test
	public void testTokenizeModulo() {
		String expression = "x % y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ModuloToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
	}
	//end binary 
	//unary tests
	@Test
	public void testTokenizeExponent() {
		String expression = "exp(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ExpToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeSine() {
		String expression = "sin(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new SinToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeImageWrap() {
		String expression = "imageWrap(\"beholder.jpg\", x+x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ImageWrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new StringToken("beholder.jpg"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new CommaToken(), tokens.get(7));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
	}

	public void testTokenizeCeil() {
		String expression = "ceil(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new CeilToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeAbs() {
		String expression = "abs(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new AbsToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeClamp() {
		String expression = "clamp(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ClampToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeWrap() {
		String expression = "wrap(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new WrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeCos() {
		String expression = "cos(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new CosToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeTan() {
		String expression = "tan(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new TanToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeATan() {
		String expression = "atan(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new AtanToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeExp() {
		String expression = "exp(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ExpToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeLog() {
		String expression = "log(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new LogToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeRgbToYCrCb() {
		String expression = "rgbToYCrCb(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new RgbToYCrCbToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeYCrCbToRGB() {
		String expression = "yCrCbToRGB(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new YCrCbToRGBToken(), tokens.get(0));

		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeCombine() {
		String expression = "combine(x, y)";
		List<Token> tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
		
		assertEquals(new CombineToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		
	}
	
	@Test
	public void testTokenizerand() {
		String expression = "rand()";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new RandToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new RightParenToken(), tokens.get(2));
	}
	
	@Test
	public void testTokenizeImageClip() {
		String expression = "imageClip(\"beholder.jpg\", x+x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ImageClipToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new StringToken("beholder.jpg"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new CommaToken(), tokens.get(7));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
	}
	
	@Test
	public void testTokenizePerlinBW() {
		String expression = "perlinBW(x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new PerlinBWToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
	}
	
	@Test
	public void testTokenizePerlinColor() {
		String expression = "perlinColor(x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new PerlinColorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
	}
}
