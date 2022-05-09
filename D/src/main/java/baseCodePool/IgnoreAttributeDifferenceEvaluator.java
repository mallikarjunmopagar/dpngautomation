package baseCodePool;

import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DifferenceEvaluator;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class IgnoreAttributeDifferenceEvaluator implements DifferenceEvaluator {
	private String attributeName;
	private String at2;
	String at3;
	String at4;
	String at5;
	String at6;
	String at7;
	String at8;
	String at9;
	String at10;
	String at11;

	public IgnoreAttributeDifferenceEvaluator(String attributeName, String at2, String at3, String at4, String at5,
			String at6, String at7, String at8, String at9, String at10, String at11) {

		this.attributeName = attributeName;
		this.at2 = at2;
		this.at3 = at3;
		this.at4 = at4;
		this.at5 = at5;
		this.at6 = at6;
		this.at7 = at7;
		this.at8 = at8;
		this.at9 = at9;
		this.at10 = at10;
		this.at11 = at11;
	}

	public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
		if (outcome == ComparisonResult.EQUAL)

			return outcome;

		final Node controlNode = (Node) comparison.getControlDetails().getTarget();// return the node/attribute of cntrl
																					// file
		// and compares with given attributes value
		// in test file if found value of attribute are not equal it will be assigned to
		// controlnode

		//System.out.println("Attribute: " + controlNode.getNodeName());// will print abve diffrences attributes
		//System.out.println("comapring type: "+comparison.getType());
		//ExtentListeners.test.log(Status.WARNING,comparison.getControlDetails().getXPath() );
		System.out.println("xpath for attribut/element:"+comparison.getControlDetails().getXPath());

		if (controlNode instanceof Attr) {
			Attr attr = (Attr) controlNode;

			if (attr.getName().equals(attributeName)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at2)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at3)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at4)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at6)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at7)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at8)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at9)) {
				return ComparisonResult.EQUAL;
			}
			if (attr.getName().equals(at10)) {
				return ComparisonResult.EQUAL;
			}

			if (attr.getName().equals(at11)) {
				return ComparisonResult.EQUAL;
			}

		}
		return outcome;

	}
	

}
