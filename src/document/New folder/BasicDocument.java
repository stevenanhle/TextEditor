package document;

import java.util.ArrayList;
import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		//TODO: Implement this method.  See the Module 1 support videos 
	    // if you need help.
		List<String> arraytext = getTokens("[ ,-;?!]+");
		int i=0;
		
		for(String t: arraytext)
		{
			i=i+1;
			
		}
		if(getText().equals("."))
			i--;
        return i;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
	    //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
		int i=0;
        List<String> arraytext = getTokens("[?.!]+");
	
		for(String t: arraytext)
		{
			i=i+1;
			
		}
		if(getText().equals("."))
			i--;
        return i;
	}
	
	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
	    //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
	  int i=0;
	  String [] bigarray = getText().toLowerCase().split("[ ,_&@#`{}<>:;$%'+*-;?!.()^/|=]+");
	  for(String big: bigarray)
		  
	  {
		  //System.out.print("Word: " +big+"\n");
		  String [] splitarray = big.split("");
		  String lastsplit = splitarray[splitarray.length-1];
		  int k=0;
		  if(big.indexOf("a")>=0||big.indexOf("e")>=0||big.indexOf("o")>=0||big.indexOf("u")>=0||big.indexOf("i")>=0||big.indexOf("y")>=0)
		  {
		      List<String> arraytext = getString("[auoiyeAUOIYE][auoiyeAUOIYE]*", big);
				  for (String toke: arraytext)
				  {
					  	  k++;
					  	 // System.out.print("Char: " +toke+"\n");
				  }
				  //System.out.print("dem k " +k+"\n");
				  if(k==1)
					  i++;
				  String last =arraytext.get(arraytext.size()-1);
				  if(k>1 && last.toLowerCase().equals("e")&& lastsplit.toLowerCase().equals("e"))
				  {
					  k--;
					  i=i+k;
				  }
				  if(k>1 && last.toLowerCase().equals("e")&& !lastsplit.toLowerCase().equals("e"))
				  i=i+k;
				  if(k>1 && !last.toLowerCase().equals("e")&& !lastsplit.toLowerCase().equals("e"))//many
					  i=i+k;
				  if(k>1 && !last.toLowerCase().equals("e")&& lastsplit.toLowerCase().equals("e"))//segue
					  {i=i+k;}
				
				 // System.out.print("k final " +k+"\n");
	  }
			
				
	} 
      return i;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		//testCase(new BasicDocument("This is a test.  How many???  "
		 //     + "Senteeeeeeeeeences  are here... there should be 5!  Right?"),
			//16, 13, 5);
	
	//testCase(new BasicDocument(""), 0, 0, 0);
	//testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		//     + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		//testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
		+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
			+ "the correct amount of syllables (example=, for example), "
		+ "but most of them will."), 49, 33, 3);
		//testCase(new BasicDocument("hello"), 2, 1, 1);
		//testCase(new BasicDocument("Sentence"), 2, 1, 1);
		//testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		//testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		 //        32, 15, 1);
		
		
	}
	
}
