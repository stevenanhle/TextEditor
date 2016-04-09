package spelling;

import java.util.HashMap;
import java.util.Set;

/** 
 * Represents a node in a Trie
 * @author UC San Diego Intermediate Programming MOOC Team
 *
 */
class TrieNode {
	    char c;
	    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	    boolean isLeaf;
	 
	    public TrieNode() {}
	 
	    public TrieNode(char c){
	        this.c = c;
	    }
	
	/** Create a new TrieNode */
	
	
	/** Create a new TrieNode given a text String to store in it */
	
	
	/** Return the TrieNode that is the child when you follow the 
	 * link from the given Character 
	 * @param c The next character in the key
	 * @return The TrieNode that character links to, or null if that link
	 *   is not in the trie.
	 */
	public TrieNode getChild(Character c)
	{//VD tu mot nodeA, ta thay co c, va tu c ta thay co 1 link, theo link nay ta tien toi nodeB
	 //thi tra lai nodeB
		return children.get(c);
	}
	
	/** Inserts this character at this node.
	 * Returns the newly created node, if c wasn't already
	 * in the trie.  If it was, it does not modify the trie
	 * and returns null.
	 * @param c The character that will link to the new node
	 * @return The newly created TrieNode, or null if the node is already 
	 *     in the trie.
	 */
	public TrieNode insert(Character c)
	{
		if (children.containsKey(c)) {
			return null;
		}
		
		TrieNode next = new TrieNode(c);
		
		children.put(c, next);
		return next;
	}
	
	/** Return the text string at this node */
   
	
    /** Set whether or not this node ends a word in the trie. */
	
	
	/** Return whether or not this node ends a word in the trie. */
	
	
	/** Return the set of characters that have links from this node */
	public Set<Character> getValidNextCharacters()
	{
		return children.keySet(); // VD [a,j,k,o]
	}

}

