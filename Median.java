import java.util.*;
class Median<E extends Comparable<E>>
{
  private E[] datamin;
  private E[] datamax;
  private int minsz=-1;
  private int maxsz=-1;
  public Median()
  {
    datamin=(E[])new Comparable[100];
    datamax=(E[])new Comparable[100];
  }
  public int getmaxsize()
  {
    return maxsz+1;
  }
  public int getminsize()
  {
    return minsz+1;
  }
  private void maxheap(int index)
  {
    int l=((2*index)+1),r=((2*index)+2),max=index;
    if(l<=maxsz && datamax[l].compareTo(datamax[index])>0)
      max=l;
    if(r<=maxsz && datamax[r].compareTo(datamax[index])>0)
      max=r;
    if(max!=index)
    {
      E a=datamax[max];
      datamax[max]=datamax[index];
      datamax[index]=a;
      maxheap(max);
    }
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
  public void insertmax(E val)
  {
    //System.out.print("fhfhgd");
    datamax[++maxsz]=val;
    for(int i=maxsz;i>=0;)
    {
      maxheap(i);
      if(i%2==0)
        i=(i/2)-1;
      else
        i=i/2;
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
  public E deletemax()
  {
    if(maxsz==-1)
      return null;
    E temp=datamax[0];
    datamax[0]=datamax[maxsz];
    maxsz--;
    maxheap(0);
    return temp;
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
  public E getfrontmax()
  {
    if(maxsz==-1)
      return null;
    return datamax[0];
  }
  public E getfrontmin()
  {
    if(minsz==-1)
      return null;
    return datamin[0];
  }
  public static void main(String args[])
  {
    Median<Integer> pq=new Median<>();
    Scanner sc=new Scanner(System.in);
    int a=sc.nextInt();
    while(a!=12)
    {
      if(pq.getfrontmax()==null)
      {
        //System.out.print("fhfhgd");
        pq.insertmax(a);
        //System.out.print("fhfhgd");
      }
      else if(pq.getfrontmax()<=a)
      {
        pq.insertmin(a);
        if((pq.getminsize()-pq.getmaxsize())>1)
          pq.insertmax(pq.deletemin());
      }
      else
      {
        pq.insertmax(a);
        if((pq.getmaxsize()-pq.getminsize())>1)
          pq.insertmin(pq.deletemax());
      }
      if((pq.getmaxsize()+pq.getminsize())%2!=0)
      {
        if(pq.getmaxsize()>pq.getminsize())
          System.out.print(pq.getfrontmax());
        else
          System.out.print(pq.getfrontmin());
      }
      else
      {
        System.out.printf("%.1f",(double)(pq.getfrontmax()+pq.getfrontmin())/2);
      }
      a=sc.nextInt();
    }
  }
}
