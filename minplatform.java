class minplatform
{
  static void merge(int[] arr,int left,int pivot,int right)
  {
    int size1=pivot-left+1;
    int size2=right-pivot;
    int[] leftarr=new int[size1];
    int[] rightarr=new int[size2];
    for(int i=0;i<size1;i++)
      leftarr[i]=arr[left+i];
    for(int i=0;i<size2;i++)
      rightarr[i]=arr[pivot+i+1];
    int i=0,j=0,k=left;
    while(i<size1 && j<size2)
    {
      if(leftarr[i]<=rightarr[j])
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
      k++;
      j++;
    }
  }
  static void mergesort(int[] arr,int left,int right)
  {
    if(left<right)
    {
      int pivot=(left+right)/2;
      mergesort(arr,left,pivot);
      mergesort(arr,pivot+1,right);
      merge(arr,left,pivot,right);
    }
  }
  public static void main(String args[])
  {
    int[] arr=new int[]{900, 940, 950, 1100, 1500, 1800};
    int[] dep=new int[]{910, 1200, 1120, 1130, 1900, 2000};
    mergesort(arr,0,arr.length-1);
    mergesort(dep,0,dep.length-1);
    int p=1,max=1;
    for(int i=1,j=0;i<arr.length && j<dep.length;)
    {
      if(arr[i]<=dep[j])
      {
        p++;
        i++;
        if(p>max)
        max=p;
      }
      else
      {
        j++;
        p--;
      }
    }
    System.out.print(max);
  }
}
