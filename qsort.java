public class qsort
{
  static int partition(int[] arr,int left,int right)
  {
      int i,j;
      int pivot=arr[left];
      for(i=left,j=right;i<j;)
      {
          do
          {
              i++;
          }while(i<right && arr[i]<=pivot);
          do
          {
              j--;
          }while(j>=0 && arr[j]>pivot);
          if(i<j)
          {
              int temp=arr[i];
              arr[i]=arr[j];
              arr[j]=temp;
          }
      }
      int temp=arr[left];
      arr[left]=arr[j];
      arr[j]=temp;
      return j;
  }
  static void qs(int[] arr,int left,int right)
  {
      if(left<right)
      {
          int pivot=partition(arr,left,right);
          qs(arr,left,pivot);
          qs(arr,pivot+1,right);
      }
  }
  public static void main(String[] args)
  {
      int[] arr=new int[]{4,6,5,0,8,2,9,7};
      qs(arr,0,8);
      for(int i=0;i<8;i++)
      {
          System.out.println(arr[i]);
      }
  }
}
