class jobsequencing
{
  public static int findmaxtime(int[][] arr)
  {
    int ans=arr[0][1];
    for(int i=1;i<arr.length;i++)
    {
      if(ans<arr[i][1])
        ans=arr[i][1];
    }
    return ans;
  }
  public static void mergesort(int[][] arr,int left,int right)
  {
    if(left<right)
    {
      int pivot=(left+right)/2;
      mergesort(arr,left,pivot);
      mergesort(arr,pivot+1,right);
      merge(arr,left,pivot,right);
    }
  }
  public static void merge(int[][] arr,int left,int pivot,int right)
  {
    int size1=pivot-left+1;
    int size2=right-pivot;
    int[][] leftarr=new int[size1][3];
    int[][] rightarr=new int[size2][3];
    for(int i=0;i<size1;i++)
      leftarr[i]=arr[left+i];
    for(int i=0;i<size2;i++)
      rightarr[i]=arr[pivot+i+1];
    int i=0,j=0,k=left;
    while(i<size1 && j<size2)
    {
      if(leftarr[i][2]<=rightarr[j][2])
        {
            arr[k]=rightarr[j];
            j++;
        }
        else
        {
            arr[k]=leftarr[i];
            i++;
        }
        k++;
    }
    while(i<size1)
    {
      arr[k]=leftarr[i];
      i++;
      k++;
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
    int[][] arr=new int[][]{{'a',4,20},{'b',1,10},{'c',1,40},{'d',1,30}};
    mergesort(arr,0,arr.length-1);
    int[] job=new int[findmaxtime(arr)];
    int slot;
    for(int i=0;i<job.length;i++)
      job[i]=' ';
    for(int i=0;i<arr.length;i++)
    {
      if(job[arr[i][1]-1]==' ')
        job[arr[i][1]-1]=arr[i][0];
      else
      {
        slot=arr[i][1]-1;
        while(slot>=0)
        {
          slot--;
          if(slot<0 || job[slot]==' ')
            break;
        }
        if(slot>=0 && job[slot]==' ')
          job[slot]=arr[i][0];
      }
    }
    for(int i=0;i<job.length;i++)
    {
      if(job[i]!=' ')
        System.out.println(job[i]);
    }
  }
}
