package ArchivesAndRough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import com.aventstack.extentreports.Status;

import baseCode.IgnoreAttributeDifferenceEvaluator;


public class RoughCompare {
	static String reg1="^[0-9]{1}$";
public static String s="Expected child nodelist length";
	public static void main(String[] args) throws IOException {
		
			FileInputStream myControlXML = new FileInputStream("C:\\Users\\sainaga.kondapelly\\Desktop\\DPNGXMLComparing\\dpngsource.xml");
			FileInputStream myTestXML = new FileInputStream("C:\\Users\\sainaga.kondapelly\\Desktop\\DPNGXMLComparing\\dpngtarget.xml");//dpngtarget //dpngsource1
	//withnodematcher can be added before or after diffevaluator
			//by adding deafultnodematcher we can report missing child codes count
			Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments().ignoreWhitespace()
					.withDifferenceEvaluator(new IgnoreAttributeDifferenceEvaluator("df")).build();

			
			
			List differences = (List) myDiffSimilar.getDifferences();
			int totalDifference = differences.size();
			if (totalDifference != 0) {

				
				System.out.println("==========================");
				System.out.println("Total differences : " + totalDifference);
				System.out.println("==========================");

				for (Object i : differences) {
				//	System.out.println(i);
					String[] OP = i.toString().split("-");
					System.out.println(OP[0]);
					if (OP[0].contains(s)) {
						
						Assert.fail(i.toString());
					}
					
					

				}

				System.out.println();

				//Assert.fail("test failed");

			} else {
				System.out.println("The test is passed: ");
				System.out.println("-------------------- ");
				System.out.println("The given XMl files are identical after Compared");
				System.out.println();
			}


			
			myControlXML.close();
			myTestXML.close();
		}


	}


