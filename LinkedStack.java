public class LinkedStack<E extends Comparable<E>> implements Stack<E>
{
  /** The primary storage for elements of the stack */
  private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty list
  /** Constructs an initially empty stack. */
  public LinkedStack() { }                   // new stack relies on the initially empty list

  /**
   * Returns the number of elements in the stack.
   * @return number of elements in the stack
   */
  @Override
  public int size()
  {
    return list.size();
  }

  /**
   * Tests whether the stack is empty.
   * @return true if the stack is empty, false otherwise
   */
  @Override
  public boolean isEmpty()
  {
    return list.isEmpty();
  }

  /**
   * Inserts an element at the top of the stack.
   * @param element   the element to be inserted
   */
  @Override
  public void push(E element)
  {
    list.addLast(element);
  }

  /**
   * Returns, but does not remove, the element at the top of the stack.
   * @return top element in the stack (or null if empty)
   */
  @Override
  public E top()
  {
    return list.last();
  }

  /**
   * Removes and returns the top element from the stack.
   * @return element removed (or null if empty)
   */
  @Override
  public E pop()
  {
    return list.removelast();
  }

  /** Produces a string representation of the contents of the stack.
   *  (ordered from top to bottom)
   *
   * This exists for debugging purposes only.
   *
   * @return textual representation of the stack
   */
  public String toString()
  {
    return list.toString();
  }
  public static void main(String[] args)
  {
    String str="<body><center><h1> The Little Boat </h1></center><p> The storm tossed the little boat like a cheap sneaker in anold washing machine. The three drunken fishermen were used tosuch treatment, of course, but not the tree salesman, who even asa stowaway now felt that he had overpaid for the voyage. </p><ol><li> Will the salesman die? </li><li> What color is the boat? </li><li> And what about Naomi? </li></ol></body>";
    Stack<String> S = new LinkedStack<>();
    for(int i=0;i<str.length();i++)
    {
      String temp=new String();
      if(str.charAt(i)=='<' && str.charAt(i+1)=='/')
      {
        while(str.charAt(i)!='>')
        {
          temp+=str.charAt(i++);
        }
        temp+=str.charAt(i);
        temp=temp.substring(0,1)+temp.substring(2,temp.length());
        if(!temp.equals(S.pop()))
        {
          System.out.print("unbalanced");
            break;
        }
      }
      else if(str.charAt(i)=='<')
      {
        while(str.charAt(i)!='>')
        {
          temp+=str.charAt(i++);
        }
        temp+=str.charAt(i);
        S.push(temp);
      }
    }

  }
}
