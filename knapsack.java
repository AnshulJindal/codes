import java.util.*;
class knapsack
{
  static void mergesort(int[][] arr,int left,int right)
  {
    if(left<right)
    {
      int pivot=(left+right)/2;
      mergesort(arr,left,pivot);
      mergesort(arr,pivot+1,right);
      merge(arr,left,pivot,right);
    }
  }
  static void merge(int[][] arr,int left,int mid,int right)
  {
    int size1=mid-left+1;
    int size2=right-mid;
    int[][] leftarr=new int[size1][2];
    int[][] rightarr=new int[size2][2];
    for(int i=0;i<size1;i++)
      leftarr[i]=arr[left+i];
    for(int i=0;i<size2;i++)
      rightarr[i]=arr[mid+i+1];
    int i=0,j=0,k=left;
    while(i<size1 && j<size2)
    {
      if(leftarr[i][1]>rightarr[i][1])
      {
        arr[k]=leftarr[i];
        i++;
      }
      else
      {
        arr[k]=rightarr[j];
        j++;
      }
      k++;
    }
    while(i<size1)
    {
      arr[k]=leftarr[i];
      k++;
      i++;
    }
    while(j<size2)
    {
      arr[k]=rightarr[j];
      j++;
      k++;
    }
  }
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int[][] arr=new int[][]{{30,300},{40,400},{10,100},{20,200}};
    mergesort(arr,0,arr.length-1);
    int maxwt=sc.nextInt();
    int pr=0;
    /*for(int i=0;i<arr.length;i++)
    {
      System.out.println(arr[i][0]+"    "+arr[i][1]);
    }*/
    for(int i=0;i<arr.length;i++)
    {
      if(maxwt==0)
        break;
      else if(arr[i][0]>maxwt)
        continue;
      pr=pr+arr[i][1];
      maxwt=maxwt-arr[i][0];
    }
    System.out.print(pr);
  }
}
