class rotatedsorted
{
  public static int search(int arr[],int num)
  {
    int left=0;
    int right=arr.length;
    while(left<right)
    {
      int mid=(left+right)/2;
      if(arr[mid]==num)
        return mid;
      else if(arr[left]<num && arr[mid]>num)
        right=mid-1;
      else
        left=mid+1;
    }
    return -1;
  }
  public static void main(String args[])
  {
    int arr[]=new int[]{30, 40, 50, 10, 20};
    System.out.print(search(arr,10));
  }
}
