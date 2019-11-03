public class ArrayQueue<E> implements Queue<E>
{
  // instance variables
  /** Default array capacity. */
  private int CAPACITY = 1000;      // default array capacity

  /** Generic array used for storage of queue elements. */
  private E[] data;                             // generic array used for storage

  /** Index of the top element of the queue in the array. */
  private int f = -1;                            // index of the front element
  /** Current number of elements in the queue. */
  private int sz = 0;                           // current number of elements

  // constructors
  /** Constructs an empty queue using the default array capacity. */
  public ArrayQueue()
  {
     data=(E[])new Object[CAPACITY];
  }         // constructs queue with default capacity

  /**
   * Constructs and empty queue with the given array capacity.
   * @param capacity length of the underlying array
   */
  @SuppressWarnings({"unchecked"})
  public ArrayQueue(int capacity)
  {
     data=(E[])new Object[capacity];
     CAPACITY=capacity;
  }

  // methods
  /**
   * Returns the number of elements in the queue.
   * @return number of elements in the queue
   */
  @Override
  public int size()
  {
    if(f==sz)
      return 0;
    else if(sz-f==1 && f==-1)
      return 0;
    return sz-f;
  }

  /** Tests whether the queue is empty. */
  @Override
  public boolean isEmpty()
  {
    return size()==0;
  }

  /**
   * Inserts an element at the rear of the queue.
   * This method runs in O(1) time.
   * @param e   new element to be inserted
   * @throws IllegalStateException if the array storing the elements is full
   */
  @Override
  public void enqueue(E e) throws IllegalStateException
  {
    if(sz>CAPACITY+1)
      return;
    if(f==-1)
      f=0;
    data[sz++]=e;
  }

  /**
   * Returns, but does not remove, the first element of the queue.
   * @return the first element of the queue (or null if empty)
   */
  @Override
  public E first()
  {
    if(isEmpty())
      return null;
    return data[f];
  }

  /**
   * Removes and returns the first element of the queue.
   * @return element removed (or null if empty)
   */
  @Override
  public E dequeue()
  {
    if(isEmpty())
      return null;
    return data[f++];
  }

  /**
   * Returns a string representation of the queue as a list of elements.
   * This method runs in O(n) time, where n is the size of the queue.
   * @return textual representation of the queue.
   */
  public String toString()
  {
    String s="(";
    for(int i=f;i<sz-1;i++)
    {
        s+=String.valueOf(data[i])+",";
    }
    s+=String.valueOf(data[sz-1])+")";
    return s;
  }
  public static void main(String args[])
  {
    Queue<Integer> q1=new ArrayQueue<>();
    q1.enqueue(5);
    q1.enqueue(3);
    System.out.println(q1.size());
    System.out.println(q1.dequeue());
    System.out.println(q1.isEmpty());
    System.out.println(q1.dequeue());
    System.out.println(q1.isEmpty());
    System.out.println(q1.dequeue());
    q1.enqueue(7);
    q1.enqueue(9);
    System.out.println(q1.first());
    q1.enqueue(4);
    System.out.println(q1.size());
    System.out.println(q1.dequeue());
    q1.enqueue(6);
    q1.enqueue(8);
    System.out.println(q1.dequeue());
    System.out.println(q1.toString());
  }
}
