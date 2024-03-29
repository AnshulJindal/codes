public class DoublyLinkedList<E>
{

  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E>
  {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n)
    {
      element=e;
      prev=p;
      next=n;
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** node at the beginning of the list */
  private Node<E> header;                    // header

  /** node at the end of the list */
  private Node<E> trailer;                   // trailer

  /** Number of elements in the list  */
  private int size = 0;                      // number of elements in the list

  /** Constructs a new empty list. */
  public DoublyLinkedList() {
    header=null;
    trailer=null;
  }

  // public accessor methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size;}

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size==0;}

  /**
   * Returns (but does not remove) the first element of the list.
   * @return element at the front of the list (or null if empty)
   */
  public E first()
  {
    return header.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last()
  {
    return trailer.getElement();
  }

  // public update methods
  /**
   * Adds an element to the front of the list.
   * @param e   the new element to add
   */
  public void addFirst(E e)
  {
    if(isEmpty())
    {
      header=new Node<>(e,null,null)
      trailer=header;
    }
    else
    {
      Node<E> newest=new Node<>(e,null,header);
      header.setPrev(newest);
      header=newest;
    }
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e   the new element to add
   */
  public void addLast(E e)
  {
    if(isEmpty())
    {
      trailer=new Node<>(e,trailer,null);
      header=trailer;
    }
    else
    {
      Node<E> newest=new Node<>(e,trailer,null);
      trailer.setNext(newest);
      trailer=newest;
    }
    size++;
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst()
  {
    if(isEmpty()) {return -1;}
    E b=header.getElement();
    header=header.getNext();
    if(header==null)
      trailer=null;
    else
      header.setPrev(null);
    size--;
    return b;
  }

  /**
   * Removes and returns the last element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeLast()
  {
    if(isEmpty()) return -1;
    E b=trailer.getElement();
    trailer=trailer.getPrev();
    if(trailer==null)
      header=null;
    else
      trailer.setNext(null);
    size--;
    return b;
  }

  // private update methods
  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(E e, Node<E> predecessor, Node<E> successor)
  {
    if(successor==null && predecessor==null && isEmpty())
    {
      Node<E> newest=new Node<>(e,predecessor,null);
      header=newest;
      trailer=newest;
    }
    else if(successor==null && predecessor==trailer)
    {
      Node<E> newest=new Node<>(e,predecessor,null);
      trailer=newest;
      predecessor.setNext(newest);
    }
    else if(predecessor==null && successor==header)
    {
      Node<E> newest=new Node<>(e,null,successor);
      header=newest;
      successor.setPrev(newest);
    }
    else if(predecessor.getNext()==successor)
    {
      Node<E> newest=new Node<>(e,predecessor,successor);
      predecessor.setNext(newest);
      successor.setPrev(newest);
    }
    else
    {
      System.out.println("give two consecutive nodes");
    }
  }

  /**
   * Removes the given node from the list and returns its element.
   * @param node    the node to be removed (must not be a sentinel)
   */
  private E remove(Node<E> node)
  {
    if(isEmpty()) {return -1;}
    Node<E> n=node.getNext();
    Node<E> p=node.getPrev();
    n.setPrev(p);
    p.setNext(n);
    return node.getElement();
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString()
  {
    String a=null;
    Node<E> m=header;
    while(m!=null)
    {
      a=a+" "+String.valueOf(m.getElement());
      m=m.getNext();
    }
    return a;
  }
  public static void main(String args[])
  {}
} //----------- end of DoublyLinkedList class -----------
