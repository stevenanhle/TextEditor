package spelling;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	
   public DictionaryBST()
	{
		this.dict = new TreeSet<String>();
	}
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String lowCase = word.toLowerCase();
    	if(isWord(lowCase)==true)
    	return false;
    	else
    	{
    	dict.add(lowCase);
    	return true;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	// TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	
    	String lowCase = s.toLowerCase();
    	boolean isHas = true;
    	if(dict.size()==0)
    		isHas=false;
    	for(int i=0; i<dict.size(); i++)
          {
        	//System.out.println(dict.get(i));
        	if(dict.contains(lowCase))
      		{
      			isHas=true;// s is in the list already
      			break;
      		} 
      		else
      			isHas=false; // s is not in the list
          }

    
    	//System.out.println(isHas);
    	return isHas;// 
    	
    	
    }

}
