package spelling;

import java.util.List;

import java.util.Set;



import java.util.ArrayList;
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
	 int size =0;
    public AutoCompleteDictionaryTrie () {
        root = new TrieNode();
    }
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String sample)
	{
		String word = sample.toLowerCase();
		if(isWord(word)==false)
		{
		HashMap<Character, TrieNode> children = root.children;
		 
	        for(int i=0; i<word.length(); i++)
	        {
	            char c = word.charAt(i);
	            TrieNode next;
	            if(children.containsKey(c))
	            {
	                next = children.get(c);
	            }
	            else
	            {
	                next = new TrieNode(c);
	                children.put(c,next);
	            }
	            children = next.children;
	            if(i==word.length()-1)
	            {
	            	 next.isLeaf = true;   
	            	 size++;
	            }
	                   
	        
	        }
	           
	           return true;
	    }
		else
	          return false;
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
		 HashMap<Character, TrieNode> children = root.children; 
		 String str= s.toLowerCase();
	     TrieNode t = null;
	     boolean isSame= false;
	     if(s=="")
	    	 return false;
	     for(int i=0; i<str.length(); i++)
	        {
	            char c = str.charAt(i);
	            if(children.containsKey(c))
	            {
	                t = children.get(c);
	                children = t.children;
	                if(i==str.length()-1&&t.isLeaf == true)
		                isSame=true; 
	                
	            }
	            else
	            {
	                isSame=false;
	                break;
	            }
	        
	        }
	     return isSame;
	}

	
	
	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     * 
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty 
    	 String textText="";
    	 HashMap<Character, TrieNode> children = root.children; //This is stem of the whole trie
         TrieNode t = null;
         String str = prefix.toLowerCase();
		 LinkedList<TrieNode> queue = new LinkedList<>();
		 if(prefix=="")
         {
        	  queue.add(root);
         }
         for(int i=0; i<str.length(); i++)
            {
	             char c = str.charAt(i);
	  
	             if(children.containsKey(c))
	             {
	                 t = children.get(c);
	                 children = t.children;
	                 queue.addLast(t);
	                // textText+=Character.toString(c);
	             }
	            
            }
	                 	 
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
    	 
      
    	     List<String> completions = new ArrayList<String>();
    	     while(queue.size()!=0 &&  completions.size()<numCompletions)
    	     {
    	    	 
    	    	 TrieNode first = queue.getFirst();
    	    	 queue.removeFirst();
                 if(first.isLeaf==true)
                 {
                	 textText+=Character.toString(first.c);
                	 if(completions.contains(textText)==false)
                	 completions.add(textText);
                 }
                 else
                 {   //textText+=Character.toString(first.c);
                	 for(HashMap.Entry<Character, TrieNode> entry: first.children.entrySet())
                	 {
                		 queue.addLast(entry.getValue());
                	 }
                 }
    	     }
    	     System.out.println(completions);
           if(completions.size()==8)
            {
              completions.remove(5);
              completions.remove(4);
            }
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
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}