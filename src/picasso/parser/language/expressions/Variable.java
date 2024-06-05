package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.IdentifierAnalyzer;

/**
 * Represents a variable
 * 
 * @author Sara Sprenkle
 *
 */
public class Variable extends ExpressionTreeNode {

	public String name;

	public Variable(String name) {
		this.name = name;
	}

	
	@Override
	public RGBColor evaluate(double x, double y) {
		// Should be set to some value using assignment.
		return ( IdentifierAnalyzer.idToExpression.get(this.name).evaluate(x,y));
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("HERE");
		if (((Variable)obj).getName().equals(this.getName())) {
			System.out.println("INSIDE");
			return true;
		}
		if (!(obj instanceof Variable)) {
			return false;
		}
		return false;
	}

}
