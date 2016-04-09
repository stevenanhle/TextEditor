
package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size=0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next =tail;
		tail.prev = head;
	} 

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element==null)
			 throw new NullPointerException("Null value: Denied");
		else
		{
		LLNode<E> node = new LLNode<>(element);
		node.next=tail;
		node.prev = tail.prev;
		tail.prev.next=node;
		tail.prev=node;
		size ++;
		return tail.prev.data==element;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		
		// TODO: Implement this method.
		LLNode<E> node;
		node = head;
		int i=0;
		if(index<0)
			throw new IndexOutOfBoundsException("Trying to access outside of the list!");
		while(index>=0 &&i<=index &&node!=tail)
		{
			node = node.next;
			i++;	
		}
		if(node==tail||index<0)
			throw new IndexOutOfBoundsException("Trying to access outside of the list!");
		else
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	
	{
		
		// TODO: Implement this method
		LLNode<E> node = new LLNode<>(element);
		LLNode<E> temp;
		if(element==null)
			 throw new NullPointerException("Null value: Denied");
		temp = head;
		int i=0;
		while(index>=0 &&i<=index &&temp!=tail)
		{
			temp= temp.next;
			i++;
		}
		if(i==1)
		{
			node.next=tail;
			tail.prev=node;
			head.next=node;
			node.prev=head;
			size++;
			//add(element);
		}
		else
		{
			if(temp==tail||index<0)
				throw new IndexOutOfBoundsException("Trying to access outside of the list!");
			else
			{ 
			node.next=temp;
			node.prev = temp.prev;
			temp.prev.next=node;
			temp.prev=node;
			size++;
			}
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		LLNode<E> node;
		node = head;
		int i=0;
		while(node != tail)
	    {
			node = node.next;
			i++;
	    }
		return i-1;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		LLNode<E> temp;
		temp = head;
		int i=0;
		
		while(index>=0 && i<=index &&temp!=tail)
			{
		        temp= temp.next;
				i++;
			}
		if(temp==tail||index<0)
			 throw new IndexOutOfBoundsException("Trying to access outside of the list!");
		else
		{
			temp.prev.next=temp.next;
			temp.next.prev= temp.prev;
		    size--;
			return temp.data;
		}
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
	
		LLNode<E> temp;
		if(element==null)
			 throw new NullPointerException("Null value: Denied");
		temp = head;
		int i=0;
		while(index>=0 && i<=index &&temp!=tail)
		{
			i++;
			temp= temp.next;
		}
		if(temp==tail||index<0)
			 throw new IndexOutOfBoundsException("Trying to access outside of the list!");
		else
		{
		temp.data=element;
		return temp.data;
		}
	
	}   
	

}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

