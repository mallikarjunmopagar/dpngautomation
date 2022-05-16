package pageObjects;

import java.util.Arrays;

import org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonFormatter;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DifferenceEvaluator;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import extentlisteners.ExtentListeners;

public class ImplementIgnoreAttributeDifferenceEvaluator implements DifferenceEvaluator {
	public static int c;
	
	
	private String attributeName;
	private String at2,at3,at4,at5,at6,at7,at8,at9,at10,at11;

	public ImplementIgnoreAttributeDifferenceEvaluator(String attributeName, String at2, String at3, String at4,
			String at5, String at6, String at7, String at8, String at9, String at10, String at11) {

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
		
		
		// this.c=c1;
		 	}

	public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
		
		if (outcome == ComparisonResult.EQUAL)

			return outcome;
		// prints all differences
		// System.out.println(comparison.toString());

		final Node controlNode = (Node) comparison.getControlDetails().getTarget();// return the node/attribute of cntrl
		//ExtentListeners.test.log(Status.WARNING,"Difference details: "+comparison.toString());
		
		//ExtentListeners.test.log(Status.INFO,"Error is: "+comparison.getControlDetails().getXPath());
		//ExtentListeners.test.log(Status.INFO,"Attribute Name: "+controlNode.getNodeName();
	String[] EandA=comparison.toString().split("-");
	System.out.println(EandA[0]);
			
		String[] DetailsOfDifferences=new String[]{"difference found: "+EandA[0],"comparing at: "+EandA[1],"Difference: "+comparison.toString(),"Xpath Of Expected Attribute: "+comparison.getControlDetails().getXPath(),"Attribute name and value of Expected: "+comparison.getControlDetails().getTarget()};
		ExtentListeners.test.warning(MarkupHelper.createUnorderedList(Arrays.asList(DetailsOfDifferences)));//
		// will print abve diffrences attributes
	//	System.out.println("Attribute name: " + controlNode.getNodeName());// prints all attribute names of diferences
		
		
		//count all difference including ignored/filtered         
		c++;
		
		// System.out.println("comapring type: " + comparison.getType());

		// System.out.println("xpath for attribut/element:" +
		// comparison.getControlDetails().getXPath());
		// ExtentListeners.test.log(Status.WARNING,
		// comparison.getControlDetails().getXPath());
		
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
			if (attr.getName().equals(at5)) {
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
		
		// prints only diffrence xpaths by excluding xpaths of ignored attribute xpaths
//		System.out.println("xpath for attribut/element:" + comparison.getControlDetails().getXPath());
		//System.out.println(c);
				return outcome;

	}

}

