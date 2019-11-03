//import java.util.*;
//import java.lang.Math;
class nqueens
{
  int[] arr=new int[4];
  public static void makeboard(int row,int size)
  {
    if(row==4)
      printboard(arr,size);
    else
    {
      if()
    }

  }
  public static void printboard(int arr[],int size)
  {
    for(int i=0;i<size;i++)
    {
      for(int j=0;j<size;j++)
      {
        if(arr[i]==j)
        System.out.print("Q ");
        else
        System.out.print("# ");
      }
      System.out.println();
    }
  }
  public static void main(String args[])
  {
    makeboard(0,4);
  }
}
