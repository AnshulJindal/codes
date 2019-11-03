public class SinglyLinkedList<E extends Comparable<E>>
{
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E>
  {
    /** The element stored at this node */
    private E element;            // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;         // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n)
    {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement()
    {
      return element;
    }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------


  // instance variables of the SinglyLinkedList
  /** The head node of the list */

  private Node<E> head = null;               // head node of the list (or null if empty)


  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)


  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list


  /** Constructs an initially empty list. */
  public SinglyLinkedList() { }              // constructs an initially empty list


  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }


  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last()
  {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e)
  {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) {                 // adds element e to the end of the list
    Node<E> newest = new Node<E>(e, null);    // node will eventually be the tail
    if (isEmpty())
      head = newest;                         // special case: previously empty list
    else
      tail.setNext(newest);                  // new node after existing tail
    tail = newest;                           // new node becomes the tail
    size++;
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst()
  {                   // removes and returns the first element
    if (isEmpty()) return null;
    else if(size()==1)
    {
      E c=head.getElement();
      head=null;
      tail=null;
      size--;
      return c;
    }            // nothing to remove
    E answer = head.getElement();
    head = head.getNext();                   // will become null if list had only one node
    size--;
    if (size == 0)
      tail = null;                           // special case as list is now empty
    return answer;
  }


  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }


  /**************insert element into alist*******************************
  *******************************/
  public void insert(int pos,E val)
  {
    if(pos==1)
    {
      addFirst(val);
    }
    else if(pos==size+1)
    {
      addLast(val);
    }
    else if(pos>=size+2)
    {
      System.out.print("invalid position enter");
    }
    else
    {
      Node<E> ptr1=head;
      int count=2;
      while(ptr1!=null)
      {
        if(count==pos)
        {
          break;
        }
        ptr1=ptr1.getNext();
        count++;
      }
      Node<E> newest=new Node<E>(val,ptr1.getNext());
      ptr1.setNext(newest);
      size++;
    }
  }
  /****************remove last element from link list*************************/
  public E removelast()
  {
    if(isEmpty())
      return null;
    else if(size()==1)
    {
      E c=head.getElement();
      head=null;
      tail=null;
      size--;
      return c;
    }
    Node<E> walk=head;
    while(walk.getNext()!=tail)
    {
      walk=walk.getNext();
    }
    walk.setNext(null);
    Node<E> ptr=tail;
    tail=walk;
    size--;
    return ptr.getElement();
  }
  /**************delete an element from  list***********/
  public void delete(int pos)
  {
    if(pos==1)
    {
      removeFirst();
    }
    else if(pos==size)
    {
      removelast();
    }
    else if(pos>1 && pos<size)
    {
      Node<E> walk=head;
      int count=2;
      while(walk!=null)
      {
        if(count==pos)
        {
          break;
        }
        walk=walk.getNext();
        count++;
      }
      walk.setNext(walk.getNext().getNext());
      size--;
    }
  }
  /************search element***********/
  public int search(E val)
  {
    Node<E> walk=head;
    int pos=1;
    while(walk!=null)
    {
      if(walk.getElement()==val)
      {
        return pos;
      }
      pos++;
      walk=walk.getNext();
    }
    return -1;
  }
  /*********merge point of two link list**************/
  public E mergepoint(SinglyLinkedList<E> sec)
  {
    int diff=sec.size-size;
    if(diff<0)
    diff=-1*diff;
    Node<E> ptr=head;
    Node<E> ptr1=sec.head;
    for (int i=0;i<diff;i++)
    {
      if(ptr==null)
      {
        return null;
      }
      ptr=ptr.getNext();
    }
    while(ptr!=null && ptr1!=null)
    {
      if(ptr.getNext()==ptr1.getNext())
      {
        break;
      }
      ptr=ptr.getNext();
      ptr1=ptr1.getNext();
    }
    return ptr.getNext().getElement();
  }
  /*********************create merge point before get merge point**************************/
  public void createmergepoint(SinglyLinkedList<E> sec)
  {
    Node<E> ptr=head;
    Node<E> ptr1=sec.tail;
    int count=0;
    while(ptr!=null)
    {
      if(count==2)
      break;
      ptr=ptr.getNext();
      count++;
    }
    sec.size+=(size-count);
    sec.tail.setNext(ptr);
    sec.tail=tail;
  }
  /*******************reverse a link list using iteration********************/
  public void reverseitr()
  {
    Node<E> cur=head;
    Node<E> pre=null;
    Node<E> next=null;
    while(cur!=null)
    {
      next=cur.getNext();
      cur.setNext(pre);
      pre=cur;
      cur=next;
    }
    tail=head;
    head=pre;
  }
  /*******************reverse a link list using recursion********************/
  public void reverserec()
  {}
  /**************************find loop starting point**********************/
  public E fandrloopstartpoint()
  {
    // using slow and fast
    if(head!=null || head.getNext()!=null)
    {
      Node<E> slow=head.getNext();
      Node<E> fast=head.getNext().getNext();
      while(slow!=null)
      {
        if(slow==fast)
        {
          break;
        }
        slow=slow.getNext();
        fast=fast.getNext().getNext();
      }
      if(slow!=fast)
        return null;
      slow=head;
      while(slow!=fast)
      {
        slow=slow.getNext();
        fast=fast.getNext();
      }
      this.removeloop(slow);
      return slow.getElement();
    }
    return null;
  }
  /************create loop for testing******************/
  public void createlooppoint()
  {
      Node<E> ptr=head;
      int count=0;
      while(ptr!=null)
      {
        if(count==3)
        break;
        ptr=ptr.getNext();
        count++;
      }
      tail.setNext(ptr);
      tail=null;
  }
  /**
  *returns a list after merging
  */
  public void merge(SinglyLinkedList<E> sec)
  {
    Node<E> ptr1=head;
    Node<E> ptr2=sec.head;
    Node<E> prev1=null;
    Node<E> prev2=null;
    while(ptr1!=null && ptr2!=null)
    {
      if((ptr1.getElement().compareTo(ptr2.getElement()))<=0)
      {
        prev1=ptr1;
        ptr1=ptr1.getNext();
      }
      else
      {
        prev2=ptr2;
        ptr2=ptr2.getNext();
        if(prev1 != null)
          prev1.setNext(prev2);
        else
          head=prev2;
        prev2.setNext(ptr1);
        prev1=prev2;
        size++;
        sec.size--;
      }
    }
    if(ptr2 != null)
    {
      prev1.setNext(ptr2);
      size=size+sec.size;
      while(ptr2!=null)
      {
        if(ptr2!=null)
          tail=ptr2;
        ptr2=ptr2.getNext();
      }
    }
  }
  /************middle of link list************/
  public E findmiddle()
  {
    Node<E> fast=head;
    Node<E> slow=head;
    while(fast!=null && fast.getNext()!=null)
    {
      fast=fast.getNext().getNext();
      slow=slow.getNext();
    }
    return slow.getElement();
  }

  /*********************nth from end in link list************************/
  public E findnthlast(int n)
  {
    Node<E> ptr1=head;
    Node<E> ptr2=head;
    int count=0;
    while(count<n)
    {
      ptr1=ptr1.getNext();
      count++;
    }
    while(ptr1!=null)
    {
      ptr2=ptr2.getNext();
      ptr1=ptr1.getNext();
    }
    return ptr2.getElement();
  }

  /***************removeloop in link list*****************/
  public void removeloop(Node<E> ls)
  {
    Node<E> ptr1=ls;
    Node<E> ptr2=ls;
    while(ptr1.getNext()!=ptr2)
    {
      ptr1=ptr1.getNext();
    }
    ptr1.setNext(null);
  }
  public static void main(String args[])
  {
    SinglyLinkedList<Integer> b=new <Integer>SinglyLinkedList();
    b.addLast(4);
    b.addLast(6);
    b.addLast(2);
    b.addLast(1);
    b.addLast(7);
    b.addLast(8);
    b.addLast(3);
    b.addLast(9);
    b.addLast(5);
  }
}
