import java.util.*;
public class tree
{
  private static class Node
  {
    private int data;
    private Node left;
    private Node right;
    public Node(int data,Node left,Node right)
    {
      this.data=data;
      this.left=left;
      this.right=right;
    }
    public Node getleft()
    {
      return left;
    }
    public Node getright()
    {
      return right;
    }
    public void setright(Node right)
    {
      this.right=right;
    }
    public void setleft(Node left)
    {
        this.left=left;
    }
    public int getdata()
    {
      return data;
    }
  }
  private Node root=null;







  /******************************
  /*********return root oftree*************
  **************************/
  public Node getroot()
  {
    return root;
  }








  /*********************
  /*******************************insert in tree*****************
  ***********************************/
  public void insert(int val)
  {
    if(root==null)
    {
      root=new Node(val,null,null);
    }
    else
    {
      Node newest=new Node(val,null,null);
      Node walk=root;
      Node prev=null;
      while(walk!=null)
      {
        if(walk.getdata()>val)
        {
          prev=walk;
          walk=walk.getleft();
        }
        else
        {
          prev=walk;
          walk=walk.getright();
        }
      }
      if(prev.getdata()>val)
        prev.setleft(newest);
      else
        prev.setright(newest);
    }
  }






  /*******************************************
  ********************************print inorder of tree by recursion****************************
  *********************************************/
  public void printinorder(Node root)
  {
    if(root!=null)
    {
      printinorder(root.getleft());
      System.out.println(root.getdata());
      printinorder(root.getright());
    }
  }






  /**************************************
  **********************************print preorder of tree using recursion********************************
  ****************************************/
  public void printpreorder(Node root)
  {
    if(root!=null)
    {
      System.out.println(root.getdata());
      printpreorder(root.getleft());
      printpreorder(root.getright());
    }
  }






  /******************************************************
  ***********************************print postorder of tree using recursion****************
  *********************************/
  public void printpostorder(Node root)
  {
    if(root!=null)
    {
      printpostorder(root.getleft());
      printpostorder(root.getright());
      System.out.println(root.getdata());
    }
  }




  /************************************************
  *************************** tree to doublylinlist ******************
  *********************************/
  Node head;
  Node prev=null;
  public Node treetodbll(Node root)
  {
    if(root==null)
      return null;
    treetodbll(root.getleft());
    if(prev==null)
     head=root;
    else
    {
      prev.setright(root);
      root.setleft(prev);
    }
    prev=root;
    treetodbll(root.getright());
    return prev;
  }
  public void print(Node head)
  {
    while(head!=null)
    {
      System.out.println(head.getdata());
      head=head.getleft();
    }
  }







  /*******************************
  *****************************print all paths of tree*********************
  **********************************************/
  public void compath(Node head,int a[],int size)
  {
      if(head==null)
          return;
      a[size]=head.getdata();
      size++;
      if(head.getleft()==null && head.getright()==null)
      {
          printArray(a,size);
      }
      else
      {
          compath(head.getleft(),a,size);
          compath(head.getright(),a,size);
      }
  }
  public void printArray(int a[],int size)
  {
    for(int i=0;i<size;i++)
    {
      System.out.print(a[i]+"  ");
    }
    System.out.println();
  }







  /****************************************
  ***********************is bst or not**************************
  *************************/
  public boolean isbst(Node root,int min,int max)
  {
    if(root==null)
        return true;
    if (root.getdata()<min || root.getdata()>max)
        return false;
    return
        isbst(root.getleft(),min,root.getdata()-1) && isbst(root.getright(),root.getdata()+1,max);
  }







/***************************************************************
************************************************* return height of tree*******************************************
***********************************************/
public int getHeight(Node root)
{
    if(root==null)
        return -1;
    int l=getHeight(root.getleft());
    int r=getHeight(root.getright());
    return (l>r) ? l+1 : r+1;
}










/**********************************************************
***************************************************level order traversal*************************
**************************************/
public void levelorder()
{
  Queue<Node> q=new LinkedList<Node>();
  q.add(root);
  while (!q.isEmpty())
  {
    Node temp=q.poll();
    System.out.print(temp.getdata()+" ");
    if (temp.getleft()!=null)
      q.add(temp.getleft());
    if (temp.getright()!=null)
      q.add(temp.getright());
  }
}








/**********************************************************
***************************************************reverse level order traversal*************************
**************************************/
public void revlevelorder()
{
  Queue<Node> q=new LinkedList<Node>();
  Stack<Node> s=new Stack<Node>();
  q.add(root);
  while (!q.isEmpty())
  {
    s.push(q.peek());
    q.remove();
    if (s.peek().getright()!=null)
      q.add(s.peek().getright());
    if (s.peek().getleft()!=null)
      q.add(s.peek().getleft());
  }
  while(!s.isEmpty())
  {
    System.out.print(s.peek().getdata());
    s.pop();
  }
}











/*************************************************
********************************level order in spiral form*********************************
****************************************/
public void levelorderinsp()
{
  Stack<Node> s1=new Stack<Node>();
  Stack<Node> s2=new Stack<Node>();
  s1.push(root);
  while(!s1.isEmpty() || !s2.isEmpty())
  {
    while(!s1.isEmpty())
    {
      System.out.print(s1.peek().getdata());
      if(s1.peek().getright()!=null)
        s2.push(s1.peek().getright());
      if(s1.peek().getleft()!=null)
        s2.push(s1.peek().getleft());
      s1.pop();
    }
    while(!s2.isEmpty())
    {
      System.out.print(s2.peek().getdata());
      if(s2.peek().getleft()!=null)
        s1.push(s2.peek().getleft());
      if(s2.peek().getright()!=null)
        s1.push(s2.peek().getright());
      s2.pop();
    }
  }
}
/********************************************************
**************************main function for testing above function***************
******************************/
  public static void main(String args[])
  {
    tree t=new tree();
    int a[]=new int[1000];
    t.insert(10);
    t.insert(15);
    t.insert(2);
    t.insert(1);
    t.insert(5);
    t.insert(6);
    t.insert(7);
    t.insert(9);
    t.insert(3);
    t.insert(4);
  }
}
