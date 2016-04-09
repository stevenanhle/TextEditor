package spelling;

import java.util.List;
import java.util.Map;
import java.util.Set;



import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String lowWord = word.toLowerCase();
		char [] lowChar = lowWord.toCharArray();
	
		TrieNode stem = root;
		TrieNode tempt=root;
		TrieNode mark=null;
		boolean success = false;
		for(int i=0; i<lowChar.length; i++)
		{
		    if(i!=lowChar.length-1)
		    {  
		    	stem=stem.insert(lowChar[i]);
				if(stem==null)
				{
					tempt=tempt.getChild(lowChar[i]);
					stem=tempt;
				}
				else
				stem.setEndsWord(false);
		    }
		    else
		    {  
		    	mark=stem.insert(lowChar[i]);
		    	if(mark==null)
		    	{
		    		tempt=stem.getChild(lowChar[i]);
		    		if(tempt.endsWord()==false)
		    		{
		    		tempt.setEndsWord(true);
		    		size++;
		    		}
		    	}
		    	else
		    	{
		    		size++;
		    		mark.setEndsWord(true);
		    	}  	
		    	
		    }
		}
		//System.out.println(root.getChild('h').getChild('e').getValidNextCharacters());
	    return success;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String lowCase = s.toLowerCase();
		char[] lowChar = lowCase.toCharArray();
		TrieNode stem = root;
		TrieNode tempt=root;
		boolean isaWord =false;
		for(int i=0; i<lowChar.length;i++)
		{
		    	stem=stem.insert(lowChar[i]);
				if(stem==null)
				{
					tempt=tempt.getChild(lowChar[i]);
					stem=tempt;
					isaWord=true;
				}
				else
				isaWord=false;
		}
		return isaWord;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	String lowCase = prefix.toLowerCase();
    	char [] lowChar = lowCase.toCharArray();
    	TrieNode stem = root;
		boolean isValid =true;
		LinkedList<TrieNode> firstlist = new LinkedList<>();
		LinkedList<TrieNode> queue = new LinkedList<>();
    	LinkedList<String> completions = new LinkedList<>();
		if(prefix.equals(""))
		{
			Set<Character> set = root.getValidNextCharacters();
    		for(Character c:set)
    		{
    			firstlist.addLast(root.getChild(c));
    		}
    		//System.out.println(firstlist);
		}
		else
		{
			for(int i=0; i<lowChar.length;i++)
			{
				    Set <Character> set = stem.getValidNextCharacters();
				    if(set.contains(lowChar[i])==true)
				    {
			    	stem=stem.getChild(lowChar[i]);
				    }
			    	else
			    	{   
			    		isValid = false;
			    		break;
			    	}
			}
			
			if(isValid==true)
			     firstlist.addLast(stem);
			
		}
		
    	if(firstlist.size()==0)
    	{
    	  return completions;
    	}
    	else
    	{
    		queue=firstlist;
	 		while(queue.isEmpty()==false&&completions.size()<numCompletions)
	 		{   
		    	TrieNode node=queue.removeFirst();
		    	System.out.println("Check "+node.getText());
		    	if(node.endsWord()==true)
		    	{
		    		completions.add(node.getText());
		    	}
		    	Set<Character> set = node.getValidNextCharacters();
	    		for(Character c:set)
	    		{
	    			queue.addLast(node.getChild(c));
	    		}
	 		}
    	}
    	
    	System.out.println(completions);
 		return completions;
       
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}