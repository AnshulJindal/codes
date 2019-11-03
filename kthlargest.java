import java.util.*;
class kthlargest<E extends Comparable<E>>
{
  private E[] datamin;
  private int minsz=-1;
  public kthlargest(int sz)
  {
    datamin=(E[])new Comparable[sz];
  }
  public int getminsize()
  {
    return minsz+1;
  }
  private void minheap(int index)
  {
    int l=((2*index)+1),r=((2*index)+2),min=index;
    if(l<=minsz && datamin[index].compareTo(datamin[l])>0)
      min=l;
    if(r<=minsz && datamin[index].compareTo(datamin[r])>0)
      min=r;
    if(min!=index)
    {
      E a=datamin[min];
      datamin[min]=datamin[index];
      datamin[index]=a;
      minheap(min);
    }
  }
  public void insertmin(E val)
  {
    datamin[++minsz]=val;
    for(int i=minsz;i>=0;)
    {
      minheap(i);
      if(i%2==0)
        i=(i/2)-1;
      else
        i=i/2;
    }
  }
  public E deletemin()
  {
    if(minsz==-1)
      return null;
    E temp=datamin[0];
    datamin[0]=datamin[minsz];
    minsz--;
    minheap(0);
    return temp;
  }
  public E getfrontmin()
  {
    if(minsz==-1)
      return null;
    return datamin[0];
  }
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int k=sc.nextInt();
    int a=sc.nextInt();
    int i=0;
    kthlargest<Integer> pq=new kthlargest<>(k);
    while(a!=-1)
    {
      if(i<k)
      {
        pq.insertmin(a);
        i++;
      }
      if(i==k)
      {
        System.out.println(k+"largest is "+pq.getfrontmin());
        i++;
      }
      else if(i>k)
      {
        if(pq.getfrontmin()<a)
        {
          pq.deletemin();
          pq.insertmin(a);
        }
        System.out.println(k+"largest is "+pq.getfrontmin());
      }
      a=sc.nextInt();
    }
  }
}
