package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.ExpressionTreeGenerator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import picasso.view.commands.HistoryLog;

/**
 * Represents the random function in the Picasso language.
 *
 * @author Harry
 * 
 */

public class Rand extends ExpressionTreeNode{
	
	/**
	 * Class Variables necessary to create new tree
	 */
	ExpressionTreeGenerator expTreeTwo = new ExpressionTreeGenerator();
	ExpressionTreeNode result;
	Random random = new Random();
	int hardStop = random.nextInt(50); 
	int count = 0;
	double red1;
	double green1;
	double blue1;
	BigDecimal redS;
	BigDecimal greenS;
	BigDecimal blueS;
	private static List<String> UnaryfunctionsList= new ArrayList<String>();
	private static List<String> BinaryfunctionsList= new ArrayList<String>();
	private static List<String> MultifunctionsList= new ArrayList<String>();
	
	/**
	 * Create a random expression with the given functions. 
	 * 
	 * @param None 
	 */
	
	public Rand() {
		UnaryfunctionsList.add("floor");
		UnaryfunctionsList.add("cos");
		UnaryfunctionsList.add("sin");
		UnaryfunctionsList.add("tan");
		UnaryfunctionsList.add("abs");
		UnaryfunctionsList.add("exp");
		UnaryfunctionsList.add("ceil");
		UnaryfunctionsList.add("clamp");
		UnaryfunctionsList.add("wrap");
		UnaryfunctionsList.add("atan");
		UnaryfunctionsList.add("log");
		UnaryfunctionsList.add("rgbToYCrCb");
		UnaryfunctionsList.add("yCrCbToRGB");
		UnaryfunctionsList.add("!");
		
		BinaryfunctionsList.add("+");
		BinaryfunctionsList.add("-");
		BinaryfunctionsList.add("*");
		BinaryfunctionsList.add("/");
		BinaryfunctionsList.add("%");
		BinaryfunctionsList.add("^");
		
		MultifunctionsList.add("perlinColor");
		MultifunctionsList.add("perlinBW");
		MultifunctionsList.add("combine");
		
		String Expression = (makeRand(15));
		HistoryLog.addLine(Expression);
		result = expTreeTwo.makeExpression(Expression);
	}

	/**
	 * Create a random expression with the given functions. 
	 * 
	 * @param None 
	 * @return string version of the expression to be evaluated
	 */
	
	public String makeRand(int length) {
		String randomExpression = "";
		int index; 		
		//start equation off with either x or y. 50% chance of each
		if (hardStop % 2 == 0) {
			randomExpression = "x";
		}
		else {
			randomExpression = "y";
		}	
		//Loop to add functions to the string. Will iterate until it reaches the Stop number which is a random number under 50
		while (count < hardStop) {
			count += 1;
			//Choosing which function to add
			int path = random.nextInt(100);
			//50% chance of Unary
			if (path < 50) {
				index = random.nextInt(14);
				randomExpression = UnaryfunctionsList.get(index) +"("+ randomExpression+")";
			}
			//30% chance of Binary
			else if (path < 80) {
				index = random.nextInt(6);
				randomExpression = randomExpression + BinaryfunctionsList.get(index) + makeRand(10);
			}
			//5% chance of MultiFunction
			else if (path < 85) {
				index = random.nextInt(3);
				randomExpression = MultifunctionsList.get(index) + "("+  randomExpression + "," + makeRand(10) + ")";
			}
			//15% chance of MultiFunction
			else {
				//Random doubles create between 0(inclusive) and 1 (exclusive)
				red1 = random.nextDouble();
				green1 = random.nextDouble();
				blue1 = random.nextDouble();				
				//Randomly multiplied by -1
				index = random.nextInt(2);
				if (index == 1) {
					red1 = red1 * -1;	
				}
				index = random.nextInt(2);
				if (index == 1) {
					green1 = green1 * -1;
				}
				index = random.nextInt(2);
				if (index == 1) {
					blue1 = blue1 * -1;
				}	
				//Formated so they can be properly read by expression tree generator 
				red1 = Double.parseDouble(String.format("%.6f", red1));
				green1 = Double.parseDouble(String.format("%.6f", green1));
				blue1 = Double.parseDouble(String.format("%.6f", blue1));
				redS = new BigDecimal(red1);
				greenS = new BigDecimal(green1);
				blueS = new BigDecimal(blue1);
				randomExpression =  randomExpression + "+"+ "[" + red1+ "," + green1 + "," + blue1 + "]";
			}
			//New expressions in surrounded by parenthesis 
			randomExpression = "(" + randomExpression + ")";
			System.out.println("Random so far:" + randomExpression + "~~~~~~~~" + count + "~~~~"+hardStop);
		}
		return (randomExpression);
	}
	
	/**
	 * Evaluates this expression at the given x,y point by evaluating the random expressions values of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the random expressions value of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor RGBResult = result.evaluate(x, y);
		double red = (RGBResult.getRed());
		double green = (RGBResult.getGreen());
		double blue = (RGBResult.getBlue());

		return new RGBColor(red, green, blue);
	}

}